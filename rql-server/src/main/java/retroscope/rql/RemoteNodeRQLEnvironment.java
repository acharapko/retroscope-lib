package retroscope.rql;

import retroscope.hlc.Timestamp;
import retroscope.log.*;
import retroscope.net.server.Callbacks;
import retroscope.net.server.Server;
import retroscope.nodeensemble.Ensemble;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by ALEKS on 1/15/2017.
 * this class is for RQL environment backed by the Retroscope server.
 * It gets the logs from remote servers.
 */
public class RemoteNodeRQLEnvironment extends RQLEnvironment {

    Server<String, RQLItem> retroServer;

    public RemoteNodeRQLEnvironment(Server<String, RQLItem> server) {
        this.retroServer = server;
    }

    public Server<String, RQLItem> getRetroServer() {
        return retroServer;
    }

    private void genericRetrieveLogs(
            Ensemble<String, RQLItem> ensemble,
            List<String> remoteLogs,
            List<String> keys,
            Timestamp startTime,
            Timestamp endTime
    ) {
        logs = new ArrayList<RQLLog>(remoteLogs.size() * ensemble.getEnsembleSize()); //get new logs
        final CountDownLatch latch = new CountDownLatch(remoteLogs.size());
        for (String log : remoteLogs) {
            Callbacks.PullLogSliceCallback<String, RQLItem> logCallback
                    = new Callbacks.PullLogSliceCallback<String, RQLItem>() {

                public void pullDataComplete(long rid, int nodeId, Log<String, RQLItem> remoteLog, int errorCode) {
                    //left blank, we do nothing when partial data comes in
                }
                public void pullAllDataComplete(long rid, int[] nodeIds, Log<String, RQLItem>[] remoteLogs, int[] errorCode) {
                    for (int i = 0; i < nodeIds.length; i++) {
                        //here, for each node we add log to the list of logs
                        /*System.out.println("All Logs:");
                        System.out.println(rid + " @ " + nodeIds[i] + " log: " + remoteLogs[i].getName());
                        System.out.println(remoteLogs[i]);*/
                        RQLLog dmLog = null;
                        if (remoteLogs[i] instanceof DataMapLog) {
                            dmLog = new RQLDataMapLog(nodeIds[i], (DataMapLog<String, RQLItem>)remoteLogs[i]);
                        } else {
                            dmLog = new RQLAppendLog(nodeIds[i], remoteLogs[i]);
                        }
                        logs.add(dmLog);
                    }
                    latch.countDown();
                }
            };
            if (keys == null) {
                if (startTime == null || endTime == null) {
                    ensemble.pullLog(log, logCallback);
                } else {
                    ensemble.pullLog(log, startTime, endTime, logCallback);
                }
            } else {
                if (startTime == null || endTime == null) {
                    ensemble.pullLog(log, keys, logCallback);
                } else {
                    ensemble.pullLog(log, keys, startTime, endTime, logCallback);
                }
            }
        }
        try {
            latch.await();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    @Override
    public void retrieveSingleCut(RQLRetrieveParam rqlRetrieveParam) {
        tempGlobalCuts = new ArrayList<GlobalCut>();
        Ensemble<String, RQLItem> ensemble;
        if (rqlRetrieveParam.getNodeIds() == null || rqlRetrieveParam.getNodeIds().size() == 0) {
            ensemble = retroServer.getMasterEnsemble();
        } else {
            ensemble = retroServer.getMasterEnsemble().getSubset(rqlRetrieveParam.getNodeIds());
        }

        final CountDownLatch latch = new CountDownLatch(rqlRetrieveParam.getLogs().size());
        final GlobalCut emitCut = new GlobalCut(rqlRetrieveParam.getStartTime());
        for (String log : rqlRetrieveParam.getLogs()) {
            Callbacks.PullDataCallback<String, RQLItem> callback
                    = new Callbacks.PullDataCallback<String, RQLItem>() {

                public void pullDataComplete(long rid, int nodeId, String logName, RetroMap<String, RQLItem> data, int errorCode) {
                    //we do nothing on single node compelte
                }

                public void pullAllDataComplete(long rid, int[] nodeIds, String logName, RetroMap<String, RQLItem>[] data, int[] errorCode) {
                    for (int i = 0; i < data.length; i++) {
                        emitCut.addLocalSnapshot(logName, nodeIds[i], data[i]);
                    }
                    tempGlobalCuts.add(emitCut);
                    latch.countDown();
                }
            };
            if (rqlRetrieveParam.getKeys() == null) {
                ensemble.pullData(log, rqlRetrieveParam.getStartTime(), callback);
            } else {
                ensemble.pullData(log, rqlRetrieveParam.getKeys(), rqlRetrieveParam.getStartTime(), callback);
            }
        }
        try {
            latch.await();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    @Override
    public void retrieveRemoteLogs(RQLRetrieveParam rqlRetrieveParam) {
        Ensemble<String, RQLItem> ensemble;
        if (rqlRetrieveParam.getNodeIds() == null || rqlRetrieveParam.getNodeIds().size() == 0) {
             ensemble = retroServer.getMasterEnsemble();
        } else {
            ensemble = retroServer.getMasterEnsemble().getSubset(rqlRetrieveParam.getNodeIds());
        }
        genericRetrieveLogs(
                ensemble,
                rqlRetrieveParam.getLogs(),
                rqlRetrieveParam.getKeys(),
                rqlRetrieveParam.getStartTime(),
                rqlRetrieveParam.getEndTime()
        );
    }

}
