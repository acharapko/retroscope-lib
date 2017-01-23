package retroscope.nodeensemble;

import io.netty.channel.ChannelHandlerContext;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import retroscope.Retroscope;
import retroscope.hlc.Timestamp;
import retroscope.log.Log;
import retroscope.log.RetroMap;
import retroscope.net.protocol.Protocol;
import retroscope.net.protocol.ProtocolHelpers;
import retroscope.net.server.CallbackWrapper;
import retroscope.net.server.Callbacks;

/**
 * Created by Aleksey on 11/2/2016.
 * handles ensemble of remote nodes
 */
public class Ensemble<K extends Serializable, V extends Serializable> {

    public static int maxKnownId = 0;
    public static long maxKnownRID;

    //private
    private HashMap<Integer, RemoteNode<K, V>> remoteNodes;
    private HashMap<Long, CallbackWrapper<K, V>> callbacks;
    private HashMap<Long, CallbackAggregator> callbacksAggregators;

    private ReentrantLock lock;

    public Ensemble() {
        remoteNodes = new HashMap<Integer, RemoteNode<K, V>>();
        callbacks = new HashMap<Long, CallbackWrapper<K, V>>();
        callbacksAggregators = new HashMap<Long, CallbackAggregator>();
        lock = new ReentrantLock();
    }

    public static int incrementId() {
        return ++maxKnownId;
    }

    public static long getNextRID() {
        maxKnownRID++;
        if (maxKnownRID == Long.MAX_VALUE) {
            maxKnownRID = 0;
        }
        return maxKnownRID;
    }

    public int getEnsembleSize() {
        return remoteNodes.size();
    }


    /**
     * This method processes new connection to the ensemble. typically we want to do it on masterEnsemble
     * containing all nodes. When we need a subset of all nodes, we can create a smaller ensemble.
     * @param ctx ChannelHandlerContext channel context
     * @param connectMsg ConnectMsg connect message from the remote node
     */
    public void processConnect(ChannelHandlerContext ctx, Protocol.ConnectMsg connectMsg) {
        if (connectMsg.getRetroscopeVersion() == Retroscope.VERSION) {
            lock.lock();
            RemoteNode<K, V> remoteNode = new RemoteNode<K, V>(incrementId(), ctx);
            remoteNodes.put(remoteNode.getId(), remoteNode);
            lock.unlock();
            Protocol.ConnectMsgResponse connectMsgResponse = Protocol.ConnectMsgResponse.newBuilder()
                    .setRetroscopeVersion(Retroscope.VERSION)
                    .setNodeID(remoteNode.getId())
                    .build();
            Protocol.RetroServerMsg msg = Protocol.RetroServerMsg.newBuilder()
                    .setConnectResponse(connectMsgResponse)
                    .build();
            ctx.writeAndFlush(msg);
        } else {
            System.err.println("Version mismatch");
        }
    }


    /**
     * Retrieves a subset of this Ensemble containing all nodes with provided ids
     * subset ensemble has no callback or aggregators registered, even if there were some
     * in the parent Ensemble
     * @param ids int[] list of node ids to be in subset ensemble
     * @return Ensemble<K, V> subset ensemble
     */
    public Ensemble<K, V> getSubset(int[] ids) {
        Ensemble<K, V> en = new Ensemble<K, V>();
        for (int i : ids) {
            if (remoteNodes.containsKey(i)) {
                en.remoteNodes.put(i, remoteNodes.get(i));
            }
        }
        return en;
    }

    private void writeAndFlush(
            Protocol.RetroServerMsg msg,
            long rid,
            Callbacks.GenericCallback callback
    ) {

        lock.lock();
        for(Map.Entry<Integer, RemoteNode<K,V>> entry : remoteNodes.entrySet()) {
            entry.getValue().getCtx().writeAndFlush(msg);
        }
        callbacks.put(rid, new CallbackWrapper<K, V>(remoteNodes.size(), callback));
        // the if statement is redundant at this time, as currently all callbacks need CallbackAggregators
        if (callback instanceof Callbacks.PullDataCallback) {
            callbacksAggregators.put(rid, new CallbackDataAggregator(remoteNodes.size()));
        }

        if (callback instanceof Callbacks.PullLogSliceCallback) {
            callbacksAggregators.put(rid, new CallbackLogAggregator(remoteNodes.size()));
        }

        lock.unlock();
    }

    /*------------------------------------------------------
     *
     * Methods to request data from nodes at given time
     *
     --------------------------------------------------------*/

    public long pullData(String logName, Callbacks.PullDataCallback<K, V> callback) {
        long rid = getNextRID();
        Protocol.RetroServerMsg msg = Protocol.RetroServerMsg.newBuilder()
                .setRID(rid)
                .setDataRequest(Protocol.GetData.newBuilder().setLogName(logName))
                .build();

        writeAndFlush(msg, rid, callback);
        return rid;
    }

    public long pullData(String logName, long time, Callbacks.PullDataCallback<K, V> callback) {
        long rid = getNextRID();
        Protocol.RetroServerMsg msg = Protocol.RetroServerMsg.newBuilder()
                .setRID(rid)
                .setDataRequest(Protocol.GetData.newBuilder().setLogName(logName).setHlcTime(time))
                .build();

        writeAndFlush(msg, rid, callback);
        return rid;
    }

    public long pullData(String logName, String key, Callbacks.PullDataCallback<K, V> callback) {
        long rid = getNextRID();
        Protocol.RetroServerMsg msg = Protocol.RetroServerMsg.newBuilder()
                .setRID(rid)
                .setDataRequest(Protocol.GetData.newBuilder().setLogName(logName)
                        .addKeys(ProtocolHelpers.serializableToByteString(key)))
                .build();

        writeAndFlush(msg, rid, callback);
        return rid;
    }

    public long pullData(String logName, String key, long time, Callbacks.PullDataCallback<K, V> callback) {
        long rid = getNextRID();
        Protocol.RetroServerMsg msg = Protocol.RetroServerMsg.newBuilder()
                .setRID(rid)
                .setDataRequest(Protocol.GetData.newBuilder().setLogName(logName).setHlcTime(time)
                        .addKeys(ProtocolHelpers.serializableToByteString(key)))
                .build();

        writeAndFlush(msg, rid, callback);
        return rid;
    }

    /*------------------------------------------------------
     *
     * Methods to request Log Slice form the nodes
     *
     ----------------------------------------------------- */
    public long pullLogSlice(
            String logName,
            Timestamp startTime,
            Timestamp endTime,
            Callbacks.PullLogSliceCallback<K, V> callback
    ) {
        return pullLogSlice(logName, startTime.toLong(), endTime.toLong(), callback);
    }

    public long pullLogSlice(
            String logName,
            long startTime,
            long endTime,
            Callbacks.PullLogSliceCallback<K, V> callback
    ) {
        long rid = getNextRID();
        Protocol.RetroServerMsg msg = Protocol.RetroServerMsg.newBuilder()
                .setRID(rid)
                .setLogSliceRequest(Protocol.GetLog.newBuilder()
                        .setLogName(logName)
                        .setHLCstartTime(startTime)
                        .setHLCendTime(endTime)
                ).build();
        writeAndFlush(msg, rid, callback);
        return rid;
    }

    public long pullLog(String logName, Callbacks.PullLogSliceCallback<K, V> callback) {
        long rid = getNextRID();
        Protocol.RetroServerMsg msg = Protocol.RetroServerMsg.newBuilder()
                .setRID(rid)
                .setLogSliceRequest(Protocol.GetLog.newBuilder()
                        .setLogName(logName)
                ).build();
        writeAndFlush(msg, rid, callback);
        return rid;
    }

    public long pullLog(String logName, List<String> keys, Callbacks.PullLogSliceCallback<K, V> callback) {
        long rid = getNextRID();
        Protocol.RetroServerMsg msg = Protocol.RetroServerMsg.newBuilder()
                .setRID(rid)
                .setLogSliceRequest(Protocol.GetLog.newBuilder()
                        .setLogName(logName)
                        .addAllParameterNames(keys)
                ).build();
        writeAndFlush(msg, rid, callback);
        return rid;
    }





    /*---------------------------------------------------------
     *
     *
     * Data Receive Handlers
     *
     *
     ----------------------------------------------------------*/


    public void handleDataReceive(
            int nodeId,
            long rid,
            long HLCtime,
            String logName,
            RetroMap<K, V> data,
            int errorcode
    ) throws RetroscopeServerEnsembleException {
        RemoteNode<K, V> workingNode = remoteNodes.get(nodeId);
        if (workingNode == null) {
            throw new RetroscopeServerEnsembleException("Node " + nodeId + " is not in the ensemble");
        }
        CallbackWrapper<K, V> cb = callbacks.get(rid);
        //System.out.println("have callback: " + (cb != null));
        if (cb != null) {
            cb.decrementLeftToReceive();
            if (cb.getCallback() instanceof Callbacks.PullDataCallback) {
                ((Callbacks.PullDataCallback<K, V>) cb.getCallback()).pullDataComplete(rid, nodeId, logName, data, errorcode);
                CallbackDataAggregator ca = (CallbackDataAggregator) callbacksAggregators.get(rid);
                if (ca != null) {
                    ca.addData(nodeId, errorcode, data);
                }
                if (cb.getLeftToReceive() == 0) {
                    //aggregate callback final call
                    ((Callbacks.PullDataCallback<K, V>) cb.getCallback()).pullAllDataComplete(
                            rid,
                            ca.getNodeIds(),
                            logName,
                            ca.getDataMaps(),
                            ca.getErrors()
                    );
                }
                if (cb.getLeftToReceive() == 0) {
                    callbacks.remove(rid); // clean, we do not need the callback anymore
                }
            }
        }
        if (HLCtime > 0) {
            workingNode.setDataAtTime(new Timestamp(HLCtime), logName, data);
        }
    }

    public void handleLogReceive(
            int nodeId,
            long rid,
            Log<K, V> log,
            int errorcode
    ) throws RetroscopeServerEnsembleException {
        RemoteNode<K, V> workingNode = remoteNodes.get(nodeId);
        if (workingNode == null) {
            throw new RetroscopeServerEnsembleException("Node " + nodeId + " is not in the ensemble");
        }
        CallbackWrapper<K, V> cb = callbacks.get(rid);
        //System.out.println("have callback: " + (cb != null));
        if (cb != null) {
            cb.decrementLeftToReceive();
            if (cb.getCallback() instanceof Callbacks.PullLogSliceCallback) {
                ((Callbacks.PullLogSliceCallback<K, V>) cb.getCallback())
                        .pullDataComplete(rid, nodeId, log, errorcode);
                CallbackLogAggregator ca = (CallbackLogAggregator) callbacksAggregators.get(rid);
                if (ca != null) {
                    ca.addLog(nodeId, errorcode, log);
                }
                if (cb.getLeftToReceive() == 0) {
                    //aggregate callback final call
                    ((Callbacks.PullLogSliceCallback<K, V>) cb.getCallback()).pullAllDataComplete(
                            rid,
                            ca.getNodeIds(),
                            ca.getLogs(),
                            ca.getErrors()
                    );
                }

                if (cb.getLeftToReceive() == 0) {
                    callbacks.remove(rid); // clean, we do not need the callback anymore
                }
            }
        }
    }

    class CallbackAggregator {
        protected int nodeIds[];
        protected int errors[];
        protected int lastPos = 0;

        public CallbackAggregator(int numNodes) {
            nodeIds = new int[numNodes];
            errors = new int[numNodes];
        }

        public int[] getNodeIds() {
            return nodeIds;
        }

        public int[] getErrors() {
            return errors;
        }
    }

    class CallbackDataAggregator extends CallbackAggregator {
        private RetroMap<K, V> dataMaps[];

        public CallbackDataAggregator(int numNodes){
            super(numNodes);
            dataMaps = new RetroMap[numNodes];
        }

        public void addData(int NodeId, int errorCode, RetroMap<K, V> datamap) {
            nodeIds[lastPos] = NodeId;
            dataMaps[lastPos] = datamap;
            errors[lastPos] = errorCode;
            lastPos++;
        }

        public RetroMap<K, V>[] getDataMaps() {
            return dataMaps;
        }

    }

    class CallbackLogAggregator extends CallbackAggregator {
        private Log<K, V> logs[];

        public CallbackLogAggregator(int numNodes){
            super(numNodes);
            logs = new Log[numNodes];
        }

        public void addLog(int NodeId, int errorCode, Log<K, V> log) {
            nodeIds[lastPos] = NodeId;
            logs[lastPos] = log;
            errors[lastPos] = errorCode;
            lastPos++;
        }
        public Log<K, V>[] getLogs() {
            return logs;
        }
    }

}
