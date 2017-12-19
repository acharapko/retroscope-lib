package retroscope.rql.server;

import org.apache.ignite.IgniteCache;
import org.apache.ignite.lang.IgniteCallable;
import retroscope.hlc.Timestamp;
import retroscope.net.ignite.StateSequence;
import retroscope.rql.environment.GlobalCut;
import retroscope.rql.parser.RQLSearchParam;
import retroscope.rql.worker.QueryWorker;

import java.util.ArrayList;
import java.util.Collection;


public class SearchJobCallable implements IgniteCallable<ArrayList<GlobalCut>> {

    private long seqTime;
    private String[] logIds;
    private RQLSearchParam rqlSearchParam;
    private Collection<IgniteCache<Long, StateSequence>> remoteCaches;
    private IgniteCache<Long, StateSequence> localCache;

    public SearchJobCallable(
            long seqTime,
            String[] logIds,
            RQLSearchParam rqlSearchParam,
            Collection<IgniteCache<Long, StateSequence>> remoteCaches
    ) {
        this.seqTime = seqTime;
        this.logIds = logIds;
        this.rqlSearchParam = rqlSearchParam;
        this.remoteCaches = remoteCaches;
    }

    public SearchJobCallable(
            long seqTime,
            String[] logIds,
            RQLSearchParam rqlSearchParam,
            Collection<IgniteCache<Long, StateSequence>> remoteCaches,
            IgniteCache<Long, StateSequence> localCache
    ) {
        this.seqTime = seqTime;
        this.logIds = logIds;
        this.rqlSearchParam = rqlSearchParam;
        this.remoteCaches = remoteCaches;
        this.localCache = localCache;
    }

    @Override
    public ArrayList<GlobalCut> call() throws Exception {
        QueryWorker qw = new QueryWorker(rqlSearchParam, new Timestamp(getSeqTime(), (short)0));
        qw.setRemoteCaches(remoteCaches);
        qw.setLocalCache(localCache);
        return qw.runWorker();
    }

    public long getSeqTime() {
        return seqTime;
    }

    public IgniteCache<Long, StateSequence> getLocalCache() {
        return localCache;
    }
}
