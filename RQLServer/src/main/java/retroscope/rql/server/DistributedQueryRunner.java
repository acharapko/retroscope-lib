package retroscope.rql.server;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteTransactions;
import org.apache.ignite.Ignition;
import org.apache.ignite.lang.IgniteFuture;
import org.apache.ignite.transactions.Transaction;
import org.apache.log4j.Logger;
import retroscope.datamodel.datastruct.variables.LongRQLVariable;
import retroscope.hlc.Timestamp;
import retroscope.net.ignite.StateSequence;
import retroscope.rql.environment.Environment;
import retroscope.rql.environment.GlobalCut;
import retroscope.rql.errors.RQLRunTimeException;
import retroscope.rql.errors.RQLRunTimeWarning;
import retroscope.rql.parser.RQLSearchParam;
import retroscope.rql.syntaxtree.Param;
import retroscope.rql.syntaxtree.Query;
import retroscope.rql.syntaxtree.QueryEnd;
import retroscope.rql.syntaxtree.expression.IllegalExpressionException;

import java.util.*;

import static org.apache.ignite.transactions.TransactionConcurrency.PESSIMISTIC;
import static org.apache.ignite.transactions.TransactionIsolation.REPEATABLE_READ;

public class DistributedQueryRunner implements Runner {

    private ArrayList<GlobalCut> emittedGlobalCuts;

    //error stuff
    private ArrayList<RQLRunTimeException> exceptions;
    private ArrayList<RQLRunTimeWarning> warnings;

    private Query q;

    //private QueryEnvironment queryEnv; //used for testing

    private String appName;
    private Ignite ignite;
    private IgniteCache<String, String> appMeta;
    private int seqSize;
    private int hotTime;

    private boolean affinityCompute = true;

    private Logger logger = Logger.getLogger(DistributedQueryRunner.class);


    public DistributedQueryRunner(Query q, String appName, String igniteConfigPath) {
        this.q = q;
        this.appName = appName;
        logger.info("Starting Ignite Client with igniteConfigPath = " + igniteConfigPath);
        Ignition.setClientMode(true);
        if (!igniteConfigPath.isEmpty()) {
            this.ignite = Ignition.start(igniteConfigPath);
        } else {
            this.ignite = Ignition.start();
        }
        appMeta = ignite.getOrCreateCache(appName);
    }

    public DistributedQueryRunner(Query q, String appName, Ignite ignite) {
        this.q = q;
        this.appName = appName;
        this.ignite = ignite;
        appMeta = ignite.getOrCreateCache(appName);
    }

    public DistributedQueryRunner(Query q, String appName) {
        this.q = q;
        this.appName = appName;
        appMeta = ignite.getOrCreateCache(appName);
    }

    public ArrayList<RQLRunTimeException> getExceptions() {
        return exceptions;
    }

    public boolean hasRunTimeErrors() {
        return exceptions.size() >= 1;
    }

    public boolean hasRunTimeWarnings() {
        return warnings.size() >= 1;
    }

    public ArrayList<RQLRunTimeWarning> getWarnings() {
        return warnings;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public void setAffinityCompute(boolean affinityCompute) {
        this.affinityCompute = affinityCompute;
    }

    @Override
    public boolean isExplain() {
        return q.isExplain();
    }

    private RQLSearchParam prepareParams() {
        //set fetchedLogs we retrieve
        RQLSearchParam rqlSearchParam = new RQLSearchParam().setLogs(Arrays.asList(q.getLogs().getIdentifiers()));
        //set log name for all params missing it
        /*List<Param> defaultLogsParams = q.getParams().getParams().get("");
        q.getParams().getParams().put(q.getLogs().getIdentifiers()[0], defaultLogsParams);

        rqlSearchParam.setAllKeys(q.getParams().getParams());*/

        QueryEnd qe = q.getQe();
        //set nodes we use
        if (qe.getNodeIds() != null && !qe.getNodeIds().isAllNodes()) {
            String nodeIds[] = qe.getNodeIds().getIdentifierList().getIdentifiers();
            ArrayList<Integer> nodes = new ArrayList<>();
            for (String node : nodeIds) {
                nodes.add(Integer.parseInt(node));
            }
            rqlSearchParam.setNodeIds(nodes);
        }

        //set times we retrieve
        setTimeConstraints(rqlSearchParam);

        //set condition

        if (qe.getConditions() != null) {
            rqlSearchParam.setCondition(qe.getConditions());
        }
        if (qe.getComputeStatements() != null) {
            rqlSearchParam.setComputeExpression(qe.getComputeStatements());
        }
        return rqlSearchParam;
    }

    public String explain() {
        StringBuilder sb = new StringBuilder();
        return sb.toString();
    }

    public void execute() {
        long start = System.currentTimeMillis();
        emittedGlobalCuts = new ArrayList<>();
        exceptions = new ArrayList<>();
        warnings = new ArrayList<>();
        QueryEnd qe = q.getQe();
        //set fetchedLogs we retrieve
        RQLSearchParam rqlSearchParam = prepareParams();

        ArrayList<String> paramList = new ArrayList<>();

        for (Param p : q.getParams().getParams()) {
            paramList.add(p.toString());
        }
        rqlSearchParam.setParams(paramList);

        try {
            learnMeta();
        } catch (DistributedJobException e) {
            exceptions.add(e);
        }

        //now if we decided whether we retrieve a log slice or just a single cut
        //single cut is retrieved only when an exact time is given
        if (rqlSearchParam.getStartTime() != null && rqlSearchParam.getEndTime() == null) {
           //single cut query
        } else {

            /* naive algo:
             * check to if all StateSeqs are finalized
             * if not finalized then
             *    finalize
             * end if
             * run check remotely
             * collect emitted cuts
             */

            long getrcStart = 0;
            if (logger.isDebugEnabled()) {
                getrcStart = System.currentTimeMillis();
            }


            ArrayList<IgniteCache<Long, StateSequence>> caches = new ArrayList<>();
            long[] times = getCaches(caches);
            if (caches.size() == 0) {
                exceptions.add(new DistributedJobException("No caches found"));
            }

            long minFirstTime = times[0];
            long maxEndTime = times[1];
            if (logger.isDebugEnabled()) {
                logger.debug("getCaches completed in " + (System.currentTimeMillis() - getrcStart) + " ms");
            }

            //now find query execution interval
            long endTime = getBlockTime(Math.min(new Timestamp().getWallTime() - hotTime, new Timestamp(maxEndTime).getWallTime()));
            long startTime = getBlockTime(new Timestamp(minFirstTime).getWallTime());

            if (qe.getTimeEx1() != null && qe.getTimeEx1().getValue() instanceof LongRQLVariable) {
                if (((LongRQLVariable) qe.getTimeEx1().getValue()).getValue() > 0) {
                    startTime = getBlockTime(new Timestamp(((LongRQLVariable) qe.getTimeEx1().getValue()).getValue(), (short) 0).getWallTime());
                }
            }
            if (qe.getTimeEx2() != null && qe.getTimeEx2().getValue() instanceof LongRQLVariable) {
                if (((LongRQLVariable) qe.getTimeEx2().getValue()).getValue() > 0) {
                    endTime = getBlockTime(new Timestamp(((LongRQLVariable) qe.getTimeEx2().getValue()).getValue(), (short) 0).getWallTime());
                }
            }

            //and schedule search workers
            if (!hasRunTimeErrors()) {
                if (affinityCompute) {
                    executeCollocated(startTime, endTime, rqlSearchParam, caches);
                } else {
                    executeNotCollocated(startTime, endTime, rqlSearchParam, caches);
                }
            } else {
                for (RQLRunTimeException e: getExceptions()) {
                    System.out.println(e.getMessage());
                }
            }
        }
        System.out.println("Runner executed in: " + (System.currentTimeMillis() - start) + " ms");
    }

    private void executeNotCollocated(
            long startTime,
            long endTime,
            RQLSearchParam rqlSearchParam,
            ArrayList<IgniteCache<Long, StateSequence>> caches) {
        //and schedule search workers

        System.out.println("Start time = " + startTime);
        System.out.println("End time = " + endTime);

        Collection<SearchJobCallable> calls = new ArrayList<>();
        for (long time = startTime; time <= endTime; time += seqSize) {
            calls.add(new SearchJobCallable(time, q.getLogs().getIdentifiers(), rqlSearchParam, caches));
        }

        System.out.println("Starting " + calls.size() + " workers");

        Collection<ArrayList<GlobalCut>> res = ignite.compute().call(calls);

        for (ArrayList<GlobalCut> nodeCuts: res) {
            emittedGlobalCuts.addAll(nodeCuts);
        }

        Collections.sort(emittedGlobalCuts);
        //fillEmittedBlanks(rqlSearchParam.countKeys());
    }

    private void executeCollocated(
            long startTime,
            long endTime,
            RQLSearchParam rqlSearchParam,
            ArrayList<IgniteCache<Long, StateSequence>> caches) {

        IgniteCache<Long, StateSequence> localCache = caches.get(0);
        caches.remove(0);

        System.out.println("Start time = " + startTime);
        System.out.println("End time = " + endTime);

        Collection<SearchJobCallable> calls = new ArrayList<>();
        for (long time = startTime; time <= endTime; time += seqSize) {
            calls.add(new SearchJobCallable(time, q.getLogs().getIdentifiers(), rqlSearchParam.clone(), caches, localCache));
        }

        System.out.println("Starting " + calls.size() + " workers");


        Collection<IgniteFuture<ArrayList<GlobalCut>>> computeFutures = new ArrayList<>();
        for (SearchJobCallable callable : calls) {
            IgniteFuture<ArrayList<GlobalCut>> future =
                    ignite.compute().affinityCallAsync(callable.getLocalCache().getName(), callable.getSeqTime(), callable);
            computeFutures.add(future);
        }

        for (IgniteFuture<ArrayList<GlobalCut>> future : computeFutures) {
            ArrayList<GlobalCut> futureCuts = future.get();
            if (futureCuts != null) {
                emittedGlobalCuts.addAll(futureCuts);
            }
        }

        Collections.sort(emittedGlobalCuts);
        //fillEmittedBlanks(rqlSearchParam.countKeys());
    }

    private void fillEmittedBlanks(int expectedVars) {
        for(int i = 1; i < emittedGlobalCuts.size(); i++) {
            GlobalCut cut = emittedGlobalCuts.get(i);
            GlobalCut priorCut = emittedGlobalCuts.get(i - 1);
            //if (cut.getCut().equals(priorCut.getCut())) {

            //}
        }
    }


    private long[] getCaches(ArrayList<IgniteCache<Long, StateSequence>> rc) {
        long minFirstTime = Long.MAX_VALUE;
        long maxEndTime = Long.MIN_VALUE;
        for (String logName : q.getLogs().getIdentifiers()) {

            String frstBlckStr = appMeta.get(logName + "_firstBlockTimestamp");
            if (frstBlckStr != null) {
                long temp = Long.parseLong(frstBlckStr);
                if (temp < minFirstTime) {
                    minFirstTime = temp;
                }
            } else {
                exceptions.add(new DistributedJobException("Log is corrupt at storage: no _firstBlockTimestamp"));
            }

            String lastBlckStr = appMeta.get(logName + "_lastBlockTimestamp");
            long lastBlockTime = 0;
            if (lastBlckStr != null) {
                lastBlockTime = Long.parseLong(lastBlckStr);
                if (maxEndTime < lastBlockTime) {
                    maxEndTime = lastBlockTime;
                }
            } else {
                exceptions.add(new DistributedJobException("Log is corrupt at storage: no _lastBlockTimestamp"));
            }

            //String temp = appMeta.get(logName + "_staseq_lastfinalized");
            IgniteCache<Long, StateSequence> cache = ignite.cache(appName+"_"+logName+"_stateseq");

            rc.add(cache);
        }

        long times[] = new long[2];

        times[0] = minFirstTime;
        times[1] = maxEndTime;

        return times;
    }

    private long getBlockTime(long time) {
        return time / seqSize * seqSize;
    }

    private void learnMeta() throws DistributedJobException{
        IgniteTransactions transactions = ignite.transactions();
        IgniteCache<String, String> appMeta = ignite.getOrCreateCache(appName);
        try (Transaction tx = transactions.txStart(PESSIMISTIC, REPEATABLE_READ)) {
            String tmp = appMeta.get("blockSize");
            if (tmp != null) {
                seqSize = Integer.parseInt(tmp);
            } else {
                logger.error("Cannot process query: seqSize is not defined");
                throw new DistributedJobException("Cannot process query: seqSize is not defined");
            }

            tmp = appMeta.get("hotTime");
            if (tmp != null) {
                hotTime = Integer.parseInt(tmp);
            } else {
                logger.error("Cannot process query: hotTime is not defined");
                throw new DistributedJobException("Cannot process query: hotTime is not defined");
            }
            tx.commit();
        }
    }

    private void setTimeConstraints(RQLSearchParam rqlSearchParam) {
        Environment env = new Environment();
        QueryEnd qe = q.getQe();
        if (qe.getTimeEx1() != null) {
            try {
                qe.getTimeEx1().evaluate();
            } catch (IllegalExpressionException iee) {
                env.addRunTimeException(iee);}
            if (qe.getTimeEx1().getValue() instanceof LongRQLVariable) {
                rqlSearchParam.setStartTime(new Timestamp(((LongRQLVariable) qe.getTimeEx1().getValue()).getValue(), (short)0));
            }
        }

        if (qe.getTimeEx2() != null) {
            try {
                qe.getTimeEx2().evaluate();
            } catch (IllegalExpressionException iee) {
                env.addRunTimeException(iee);}
            if (qe.getTimeEx2().getValue() instanceof LongRQLVariable) {
                rqlSearchParam.setEndTime(new Timestamp(((LongRQLVariable) qe.getTimeEx1().getValue()).getValue(), (short)0));
            }
        }
    }


    /*---------------------------
    /* Output stuff
    /*---------------------------*/

    public ArrayList<GlobalCut> getEmittedCuts() {
        return emittedGlobalCuts;
    }
}