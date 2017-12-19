package retroscope.net.ignite;

import org.apache.ignite.*;
import org.apache.ignite.stream.StreamVisitor;
import org.apache.ignite.transactions.Transaction;
import org.apache.log4j.Logger;
import retroscope.hlc.HLC;
import retroscope.hlc.Timestamp;
import retroscope.log.RetroscopeLog;
import retroscope.net.Streamer;
import retroscope.util.Utils;
import javax.cache.expiry.Duration;
import javax.cache.expiry.ModifiedExpiryPolicy;
import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import static org.apache.ignite.transactions.TransactionConcurrency.PESSIMISTIC;
import static org.apache.ignite.transactions.TransactionIsolation.REPEATABLE_READ;

/**
 * Created by Aleksey on 7/23/2017.
 * This class handles all communication with Apache Ignite
 */
public class IgniteHandler implements Streamer {

    public static final int DEFAULT_STATE_SEQ_LENGTH = 100;
    public static final int DEFAULT_HEARTBEAT_INTERVAL = 1000;
    public static final int DEFAULT_HEARTBEAT_MISSES = 3;
    public static final int DEFAULT_BUFFER_SIZE = 60;
    public static final int DEFAULT_FLUSH_RATE = 200;

    public int bufSize = DEFAULT_BUFFER_SIZE;
    public int bufFlushRate = DEFAULT_FLUSH_RATE;

    private Ignite ignite;
    private String igniteConfigPath;
    private Map<String, IgniteDataStreamer<Long, StreamEntry>> streamers;

    private HLC hlc;
    private NodeMeta nodeMeta;
    //private HashMap<String, Log> namedLogs;
    private long currentBlockTime;
    private long lastBlockTime;

    private boolean doHeartbeats = true;
    private boolean startInClientMode = true;

    //config data
    private int heartbeatInterval = DEFAULT_HEARTBEAT_INTERVAL;
    private int maxMissedHeartbeats = DEFAULT_HEARTBEAT_MISSES;
    private int seqSize = DEFAULT_STATE_SEQ_LENGTH;
    private int hotTime = 10 * DEFAULT_STATE_SEQ_LENGTH;

    private static ReentrantLock lock  = new ReentrantLock();

    //logger

    Logger logger = Logger.getLogger(IgniteHandler.class);

    public IgniteHandler(NodeMeta nodeMeta, HLC hlc) {
        this(nodeMeta, hlc, "");
    }

    public IgniteHandler(
            NodeMeta nodeMeta,
            HLC hlc,
            //HashMap<String, Log> namedLogs,
            String igniteConfigPath
    ) {
        this.nodeMeta = nodeMeta;
        this.hlc = hlc;
        //this.namedLogs = namedLogs;
        this.igniteConfigPath = igniteConfigPath;
    }


    @Override
    public boolean connect() {

        ignite();
        learnMeta();
        return true;
    }

    @Override
    public boolean connect(int nodeID) {

        ignite();
        this.nodeMeta.setNodeId(nodeID);
        logger.info("Using provided nodeID = " + nodeID);
        IgniteCache<String, String> appMeta = Utils.getAppMetaCache(ignite, nodeMeta.getAppName());
        learnHeartbeatInterval(appMeta);
        streamers = new HashMap<>();
        runHeartbeat();
        return true;
    }

    public void addStreamer(String logName) {
        String cacheName = nodeMeta.getAppName() + "_" + logName + "_stream";
        logger.info("creating streamer " + cacheName);

        ignite.getOrCreateCache(cacheName);

        Utils.getStateSeqCache(ignite, nodeMeta.getAppName(), logName);

        IgniteDataStreamer<Long, StreamEntry> streamer
                = ignite.dataStreamer(cacheName);

        String stateSeqCacheName = nodeMeta.getAppName() + "_" + logName + "_stateseq";


        streamer.allowOverwrite(true);
        streamer.perNodeBufferSize(bufSize);
        streamer.autoFlushFrequency(bufFlushRate);
        streamer.receiver(StreamVisitor.from((cache, e) -> {
            //this is a streamer
            //IgniteTransactions transactions = ignite.transactions();
            long seqStart = e.getKey();
            IgniteCache<Long, StateSequence> stateSeq = ignite.getOrCreateCache(stateSeqCacheName);

            lock.lock();
            ConcurrentMap<Long, StateSequence> localSeqCache = ignite.cluster().nodeLocalMap();
            StateSequence seq = localSeqCache.get(seqStart);
            if (seq == null) {
                seq = stateSeq.localPeek(seqStart);
            }
            if (seq == null) {
                seq = createStateSeq(seqStart, logName);
                localSeqCache.put(seqStart, seq);
            }
            if (e.getValue() instanceof CommitStreamEntry) {
                seq.addInitState(e.getValue().getEntry(), ((CommitStreamEntry) e.getValue()).timestamp);
                //seq.commit(stateSeq, nodeMeta.getSeqSize() * 3);
                stateSeq.put(seqStart, seq);
                localSeqCache.put(seqStart, seq);
                lock.unlock();
                //logger.debug(seqStart + " Streamer received commit item: " + e.getValue().getEntry());
                //logger.debug(seqStart + " seq: " + seq);
            } else  if (e.getValue() instanceof EventStreamEntry) {
                seq.appendItem((EventStreamEntry) e.getValue());
                localSeqCache.put(seqStart, seq);
                lock.unlock();
                //stateSeq.put(seqStart, seq);
                //logger.debug(seqStart + " Streamer processed item: " + e.getValue().getEntry());
            }
            //logger.debug(seqStart + " Streamer processed item: " + e.getValue().getEntry());
        }));
        streamers.put(logName, streamer);
    }

    private StateSequence createStateSeq(long seqStart, String logName) {
        IgniteCache<String, String> appMeta = ignite.getOrCreateCache(nodeMeta.getAppName());
        StateSequence seq = new StateSequence(new Timestamp(seqStart, (short) 0));
        //logger.info("Creating new StateSequence @: " + seq.getStartingTimestamp().toString());

        //update last block info
        String lastBlckStr = appMeta.get(logName + "_lastBlockTimestamp");
        long lastBlockTime = 0;
        if (lastBlckStr != null) {
            lastBlockTime = Long.parseLong(lastBlckStr);
        }
        if (lastBlockTime < seq.getStartingTimestamp().toLong()) {
            appMeta.put(logName + "_lastBlockTimestamp", seq.getStartingTimestamp().toLong() + "");
        }
        if (appMeta.get(logName + "_firstBlockTimestamp") == null) {
            appMeta.put(logName + "_firstBlockTimestamp", seq.getStartingTimestamp().toLong() + "");
        }

        //update block count
        String blocksStr = appMeta.get("blockCount");
        int blocks = 0;
        if (blocksStr != null) {
            blocks = Integer.parseInt(blocksStr);
        }
        appMeta.put("blockCount", blocks + "");
        return seq;
    }


    public long getCurrentBlockTime() {
        return currentBlockTime;
    }

    @Override
    public synchronized void stream(StreamEntry streamEntry, RetroscopeLog log, long blockTime) {
        try {
            IgniteDataStreamer<Long, StreamEntry> stmr = streamers.get(log.getName());
            if (currentBlockTime == 0) {
                lastBlockTime = blockTime - seqSize;
                currentBlockTime = blockTime;
            }
            if (currentBlockTime < blockTime) {
                streamCommit(log, streamEntry.getHLCTime());

                currentBlockTime = blockTime;
            }
            stmr.addData(blockTime, streamEntry);

            //System.out.println("Streaming data");
        } catch (Exception e) {
            logger.error("Streamer Error: " + e.getMessage());
            //e.printStackTrace();
        }
    }

    @Override
    public synchronized void streamCommit(RetroscopeLog log, long lastEntryHLC) {
        try {

            String snap = log.getSnap(currentBlockTime);

            for (long time = lastBlockTime + seqSize; time <= currentBlockTime; time += seqSize) {
                //typically this loop only runs one time. However, if the node had no events the entire duration of the
                //time-shard (seqSize), then we may iterate more.
                //we still need to streamCommit, but since data has not been changed, we streamCommit the
                //same snapshot snap @ currentBlockTime

                //logger.debug("Streaming Commit Message for block " + time);
                IgniteDataStreamer<Long, StreamEntry> stmr = streamers.get(log.getName());
                Timestamp ts = new Timestamp(lastEntryHLC);
                CommitStreamEntry commitEntry = new CommitStreamEntry(ts, snap, nodeMeta.getNodeId());

                stmr.addData(time, commitEntry);
            }

            //System.out.println("Streaming data (" + streamEntry.getTime().toLong()+ "): " + streamEntry);
        } catch (Exception e) {
            logger.error("Streamer Error: " + e.getMessage());
        } finally {
            lastBlockTime = currentBlockTime;
        }
    }

    @Override
    public void close() {
        doHeartbeats = false;
        Iterator<Map.Entry<String, IgniteDataStreamer<Long, StreamEntry>>> iterator
                = streamers.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, IgniteDataStreamer<Long, StreamEntry>> strm = iterator.next();
            strm.getValue().close();
        }
        ignite.close();

    }

    @Override
    public void flushStreams() {
        Iterator<Map.Entry<String, IgniteDataStreamer<Long, StreamEntry>>> iterator
                = streamers.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, IgniteDataStreamer<Long, StreamEntry>> strm = iterator.next();
            System.out.println("Flushing");
            strm.getValue().flush();
        }
    }

    private void ignite() {
        logger.info("Starting Ignite Client with igniteConfigPath = " + igniteConfigPath);
        Ignition.setClientMode(startInClientMode);
        if (!igniteConfigPath.isEmpty()) {
            this.ignite = Ignition.start(igniteConfigPath);
        } else {
            this.ignite = Ignition.start();
        }
        this.ignite.configuration().setDataStreamerThreadPoolSize(1);
    }

    public void setIgnite(Ignite ignite) {
        this.ignite = ignite;
        this.ignite.configuration().setDataStreamerThreadPoolSize(1);
        learnMeta();
    }

    private void learnMeta() {
        IgniteTransactions transactions = ignite.transactions();

        IgniteCache<String, String> appMeta = Utils.getAppMetaCache(ignite, nodeMeta.getAppName());

        int nodeID = 0;
        try (Transaction tx = transactions.txStart(PESSIMISTIC, REPEATABLE_READ)) {
            String maxId = appMeta.get("maxid");
            if (maxId != null) {
                nodeID = Integer.parseInt(maxId) + 1;
            } else {
                nodeID = 1;
            }
            nodeMeta.setNodeId(nodeID);
            appMeta.put("maxid", nodeID + "");
            tx.commit();
            logger.info("Assigned nodeID = " + nodeID);
            String tmp = appMeta.get("blockSize");
            if (tmp != null) {
                seqSize = Integer.parseInt(tmp);
            } else {
                seqSize = DEFAULT_STATE_SEQ_LENGTH;
                appMeta.put("blockSize", seqSize + "");
            }
            nodeMeta.setSeqSize(seqSize);

            tmp = appMeta.get("hotTime");
            if (tmp != null) {
                hotTime = Integer.parseInt(tmp);
            } else {
                hotTime = 10 * DEFAULT_STATE_SEQ_LENGTH;
                appMeta.put("hotTime", hotTime + "");
            }
            nodeMeta.setHotTime(hotTime);

            tmp = appMeta.get("bufSize");
            if (tmp != null) {
                bufSize = Integer.parseInt(tmp);
            } else {
                bufSize = DEFAULT_BUFFER_SIZE;
                appMeta.put("bufSize", bufSize + "");
            }

            tmp = appMeta.get("bufFlushRate");
            if (tmp != null) {
                bufFlushRate = Integer.parseInt(tmp);
            } else {
                bufFlushRate = DEFAULT_FLUSH_RATE;
                appMeta.put("bufFlushRate", bufFlushRate + "");
            }
        }
        learnHeartbeatInterval(appMeta);
        streamers = new HashMap<>();
        runHeartbeat();
    }

    public long getBlockTime(long time) {
        return time / seqSize * seqSize;
    }



    private void learnHeartbeatInterval(IgniteCache<String, String> appMeta) {
        String tmp = appMeta.get("heartbeatInterval");
        if (tmp != null) {
            heartbeatInterval = Integer.parseInt(tmp);
        }
        tmp = appMeta.get("maxMissedHeartbeats");
        if (tmp != null) {
            maxMissedHeartbeats = Integer.parseInt(tmp);
        }
        logger.info("Learned app parameters");
    }

    private void heatbeat() {
        nodeMeta.setTimestamp(hlc.now().toLong());
        IgniteCache<Integer, NodeMeta> ndMeta = ignite.getOrCreateCache(nodeMeta.getAppName()+"_nodes");
        ndMeta = ndMeta.withExpiryPolicy(
                new ModifiedExpiryPolicy(new Duration(TimeUnit.MILLISECONDS, heartbeatInterval * maxMissedHeartbeats)));
        ndMeta.put(nodeMeta.getNodeId(), nodeMeta);
        logger.info("Heartbeat at HLC time " + nodeMeta.getTimestamp());
    }

    private void runHeartbeat() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                while (doHeartbeats) {
                    heatbeat();
                    try {
                        Thread.sleep(heartbeatInterval);
                    } catch (InterruptedException ie) {
                        System.err.println(ie.getMessage());
                    }
                }
            }
        };
        Thread t = new Thread(r);
        t.start();
    }

    public boolean isStartInClientMode() {
        return startInClientMode;
    }

    public void setStartInClientMode(boolean startInClientMode) {
        this.startInClientMode = startInClientMode;
    }

    public NodeMeta getNodeMeta() {
        return nodeMeta;
    }
}
