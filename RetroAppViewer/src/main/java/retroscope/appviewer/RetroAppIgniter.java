package retroscope.appviewer;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteTransactions;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.query.ScanQuery;
import org.apache.ignite.transactions.Transaction;
import org.apache.log4j.Logger;
import retroscope.net.ignite.IgniteHandler;
import retroscope.net.ignite.NodeMeta;
import retroscope.util.Utils;

import javax.cache.Cache;
import java.util.Iterator;

import static org.apache.ignite.transactions.TransactionConcurrency.PESSIMISTIC;
import static org.apache.ignite.transactions.TransactionIsolation.REPEATABLE_READ;

public class RetroAppIgniter {

    private Ignite ignite;
    private String igniteConfigPath;

    Logger logger = Logger.getLogger(RetroAppIgniter.class);

    public RetroAppIgniter() {
        this("");
    }

    public RetroAppIgniter(String igniteConfigPath) {
        this.igniteConfigPath = igniteConfigPath;
        ignite();
    }

    private void ignite() {
        logger.info("Starting Ignite Client for RetroAppViewer with igniteConfigPath = " + igniteConfigPath);
        Ignition.setClientMode(true);
        if (!igniteConfigPath.isEmpty()) {
            this.ignite = Ignition.start(igniteConfigPath);
        } else {
            this.ignite = Ignition.start();
        }
    }


    public AppData getAppData(String appName) {
        AppData data = new AppData(appName);

        IgniteTransactions transactions = ignite.transactions();
        IgniteCache<String, String> appMeta = Utils.getAppMetaCache(ignite, data.getAppName());

        try (Transaction tx = transactions.txStart(PESSIMISTIC, REPEATABLE_READ)) {
            String maxIdS = appMeta.get("maxid");
            int maxId;
            if (maxIdS != null) {
                maxId = Integer.parseInt(maxIdS);
            } else {
                maxId = 0;
                createApp(appMeta);
            }
            data.setMaxId(maxId);
            String tmp = appMeta.get("heartbeatInterval");
            if (tmp != null) {
                data.setHeartbeatInterval(Integer.parseInt(tmp));
            }
            tmp = appMeta.get("maxMissedHeartbeats");
            if (tmp != null) {
                data.setMaxMissedHeartbeats(Integer.parseInt(tmp));
            }
            tmp = appMeta.get("blockSize");
            if (tmp != null) {
                data.setBlockSize(Integer.parseInt(tmp));
            }
            tmp = appMeta.get("blockCount");
            if (tmp != null) {
                data.setNumBlocks(Integer.parseInt(tmp));
            }
            tmp = appMeta.get("bufSize");
            if (tmp != null) {
                data.setBufSize(Integer.parseInt(tmp));
            }
            tmp = appMeta.get("bufFlushRate");
            if (tmp != null) {
                data.setBufFlushRate(Integer.parseInt(tmp));
            }
            tx.commit();
        }

        data.setNumNodes(getNumberOfNodes(appName));

        return data;
    }

    private int getNumberOfNodes (String appName) {
        ScanQuery scanQuery = new ScanQuery();
        Iterator<Cache.Entry<Integer, NodeMeta>> iterator
                = ignite.getOrCreateCache(appName + "_nodes").query(scanQuery).iterator();
        int count = 0;
        while (iterator.hasNext()) {
            count++;
            iterator.next();
        }
        return count;
    }


    private void createApp(IgniteCache<String, String> appMeta) {
        try {
            appMeta.put("heartbeatInterval", IgniteHandler.DEFAULT_HEARTBEAT_INTERVAL + "");
            appMeta.put("maxMissedHeartbeats", IgniteHandler.DEFAULT_HEARTBEAT_MISSES + "");
            appMeta.put("blockSize",  IgniteHandler.DEFAULT_STATE_SEQ_LENGTH + "");
            appMeta.put("bufSize", IgniteHandler.DEFAULT_BUFFER_SIZE + "");
            appMeta.put("bufFlushRate", IgniteHandler.DEFAULT_FLUSH_RATE + "");
            appMeta.put("maxid", "0");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void updateApp(AppData appData) {
        IgniteCache<String, String> appMeta = ignite.getOrCreateCache(appData.getAppName());
        IgniteTransactions transactions = ignite.transactions();
        try (Transaction tx = transactions.txStart()) {
            appMeta.put("heartbeatInterval", appData.getHeartbeatInterval() + "");
            appMeta.put("bufSize", appData.getBufSize() + "");
            appMeta.put("bufFlushRate", appData.getBufFlushRate() + "");
            appMeta.put("blockSize", appData.getBlockSize() + "");
            appMeta.put("maxMissedHeartbeats", appData.getMaxMissedHeartbeats() + "");
            tx.commit();
        }
    }

}
