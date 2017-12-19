package retroscope.rql.worker;

import org.apache.ignite.IgniteCache;
import org.apache.log4j.Logger;
import retroscope.hlc.Timestamp;
import retroscope.net.ignite.StateSequence;
import retroscope.rql.environment.GlobalCut;
import retroscope.rql.environment.ScanningEnvironment;
import retroscope.rql.log.RQLStateSequence;
import retroscope.rql.parser.RQLSearchParam;

import java.util.ArrayList;
import java.util.Collection;

public class QueryWorker {

    private RQLSearchParam searchParam;
    private Timestamp sequenceTime;

    private IgniteCache<Long, StateSequence> localCache;
    private Collection<IgniteCache<Long, StateSequence>> remoteCaches;
    private Logger logger = Logger.getLogger(QueryWorker.class);

    private ArrayList<RQLStateSequence> seqs;

    public QueryWorker(RQLSearchParam searchParam, Timestamp sequenceTime) {
        this.searchParam = searchParam;
        this.sequenceTime = sequenceTime;
        seqs = new ArrayList<>();
    }

    public void setLocalCache(IgniteCache<Long, StateSequence> cache) {
        localCache = cache;
    }

    public void setRemoteCaches(Collection<IgniteCache<Long, StateSequence>> remoteCaches) {
        this.remoteCaches = remoteCaches;
    }

    public void retrieveStateSeq() {
        long start = 0;
        if (logger.isDebugEnabled()) {
            start = System.currentTimeMillis();
        }
        if (localCache != null) {
            //logger.debug("Peeking at seq at time " + sequenceTime.getWallTime());
            StateSequence seq = localCache.localPeek(sequenceTime.getWallTime());
            if (seq != null) {
                RQLStateSequence rqlseq = new RQLStateSequence(seq);
                this.seqs.add(rqlseq);
            }
        }
        if (remoteCaches != null) {
            for (IgniteCache<Long, StateSequence> seqCache: remoteCaches) {
                long t2 = System.currentTimeMillis();
                StateSequence seq = seqCache.get(sequenceTime.getWallTime());
                System.out.println("StateSeq fetching complete in " + (System.currentTimeMillis() - t2) + " ms");
                if (seq != null) {
                    RQLStateSequence rqlseq = new RQLStateSequence(seq);
                    this.seqs.add(rqlseq);
                }
            }
        }

        /*if(logger.isDebugEnabled()) {
            //logger.debug("StateSeq retrieval complete in " + (System.currentTimeMillis() - start) + " ms");
            //System.out.println("StateSeq retrieval complete in " + (System.currentTimeMillis() - start) + " ms");
        }*/
    }

    public ArrayList<GlobalCut> runWorker() {
        this.retrieveStateSeq();
        //set condition for search
        if (this.seqs.size() > 0) {
            ScanningEnvironment env = new ScanningEnvironment(this.seqs.get(0), searchParam.getParams());
            if (searchParam.getCondition() != null) {
                env.addExpression(searchParam.getCondition());
            }
            if (searchParam.getComputeExpression() != null) {
                env.setComputeExpression(searchParam.getComputeExpression());
            }
            env.scan();
            return env.getEmittedCuts();
        }
        return new ArrayList<>();
    }
}
