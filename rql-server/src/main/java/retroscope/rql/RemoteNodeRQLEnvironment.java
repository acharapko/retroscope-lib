package retroscope.rql;

import retroscope.hlc.Timestamp;
import retroscope.log.DataMapLog;
import retroscope.log.Log;
import retroscope.log.RQLDataMapLog;
import retroscope.net.server.Callbacks;
import retroscope.net.server.Server;
import retroscope.nodeensemble.Ensemble;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

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

    @Override
    public void retrieveRemoteLogs(String[] remoteLogs) {
        logs = new ArrayList<RQLDataMapLog>(remoteLogs.length * retroServer.getEnsembleSize()); //get new logs
        final CountDownLatch latch = new CountDownLatch(remoteLogs.length);
        Ensemble<String, RQLItem> ensemble = retroServer.getMasterEnsemble();
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
                        RQLDataMapLog dmLog = null;
                        if (remoteLogs[i] instanceof DataMapLog) {
                            dmLog = new RQLDataMapLog(nodeIds[i], (DataMapLog<String, RQLItem>)remoteLogs[i]);
                        } else {
                            dmLog = new RQLDataMapLog(nodeIds[i], remoteLogs[i]);
                        }
                        logs.add(dmLog);
                    }
                    latch.countDown();
                }
            };
            ensemble.pullLog(log, logCallback);
        }
        try {
            latch.await();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    @Override
    public void retrieveRemoteLogs(String[] logs, Timestamp startTime, Timestamp endTime) {

    }

    @Override
    public void retrieveSingleCut(String[] logs, Timestamp cutTime) {

    }

    @Override
    public void retrieveRemoteLogs(String[] logs, Timestamp startTime, Timestamp endTime, int[] nodeList) {

    }
}
