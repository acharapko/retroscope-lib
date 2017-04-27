package retroscope.nodeensemble;

import io.netty.channel.ChannelHandlerContext;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

import retroscope.Retroscope;
import retroscope.hlc.Timestamp;
import retroscope.log.Log;
import retroscope.log.RetroMap;
import retroscope.net.protocol.Protocol;
import retroscope.net.protocol.ProtocolHelpers;
import retroscope.net.server.CallbackWrapper;
import retroscope.net.server.Callbacks;

import javax.security.auth.callback.Callback;

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
        remoteNodes = new HashMap<>();
        callbacks = new HashMap<>();
        callbacksAggregators = new HashMap<Long, CallbackAggregator>();
        lock = new ReentrantLock();
    }

    public synchronized static int incrementId() {
        return ++maxKnownId;
    }

    public synchronized static long getNextRID() {
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
    public int processConnect(ChannelHandlerContext ctx, Protocol.ConnectMsg connectMsg) {
        int nodeId = -1;
        if (connectMsg.getRetroscopeVersion() == Retroscope.VERSION) {
            if (connectMsg.hasNodeId()) {
                nodeId = connectMsg.getNodeId();
                if (nodeId > maxKnownId) {
                    maxKnownId = nodeId;
                }
            } else {
                nodeId = incrementId();
            }
            lock.lock();
            RemoteNode<K, V> remoteNode = new RemoteNode<K, V>(nodeId, ctx);
            System.out.println("Connecting client id = " + remoteNode.getId());
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
        return nodeId;
    }

    /**
     * This method processes new connection to the ensemble. typically we want to do it on masterEnsemble
     * containing all nodes. When we need a subset of all nodes, we can create a smaller ensemble.
     * @param ctx ChannelHandlerContext channel context
     * @param disconnectMsg disconnect message from the remote node
     */
    public void processDisconnect(ChannelHandlerContext ctx, Protocol.DisconnectMsg disconnectMsg) {
        int nodeId = disconnectMsg.getNodeId();
        removeNode(nodeId);
        Protocol.DisconnectMsgResponse disconnectMsgResponse = Protocol.DisconnectMsgResponse.newBuilder()
                .setNodeID(nodeId)
                .build();
        Protocol.RetroServerMsg msg = Protocol.RetroServerMsg.newBuilder()
                .setDisconnectResponse(disconnectMsgResponse)
                .build();
        ctx.writeAndFlush(msg);
    }

    public void removeNode(int nodeId) {
        remoteNodes.remove(nodeId);

        Set<Long> rids = callbacks.keySet();
        for (long rid : rids) {
            CallbackWrapper cb = callbacks.get(rid);

            if (cb.receivedAll(remoteNodes.keySet())) {
                CallbackAggregator ca = callbacksAggregators.get(rid);

                if (ca instanceof Ensemble.CallbackLogAggregator && cb instanceof Callbacks.PullLogSliceCallback) {
                    Ensemble.CallbackLogAggregator cla = ( Ensemble.CallbackLogAggregator) ca;
                    ((Callbacks.PullLogSliceCallback<K, V>) cb.getCallback()).pullAllDataComplete(
                            rid,
                            cla.getNodeIds(),
                            cla.getLogs(),
                            cla.getErrors()
                    );
                }
                if (ca instanceof Ensemble.CallbackDataAggregator && cb instanceof Callbacks.PullDataCallback) {
                    Ensemble.CallbackDataAggregator cda = (Ensemble.CallbackDataAggregator) ca;
                    ((Callbacks.PullDataCallback<K, V>) cb.getCallback()).pullAllDataComplete(
                            rid,
                            cda.getNodeIds(),
                            "",
                            cda.getDataMaps(),
                            cda.getErrors()
                    );
                }

                callbacks.remove(rid); // clean, we do not need the callback anymore
                callbacksAggregators.remove(rid);
            }
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

    /**
     * Retrieves a subset of this Ensemble containing all nodes with provided ids
     * subset ensemble has no callback or aggregators registered, even if there were some
     * in the parent Ensemble
     * @param ids Integer list of node ids to be in subset ensemble
     * @return Ensemble<K, V> subset ensemble
     */
    public Ensemble<K, V> getSubset(List<Integer> ids) {
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

    public long pullData(String logName, Timestamp time, Callbacks.PullDataCallback<K, V> callback) {
        return pullData(logName, time.toLong(), callback);
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

    public long pullData(String logName, List<K> keys, Timestamp time, Callbacks.PullDataCallback<K, V> callback) {
        return pullData(logName, keys, time.toLong(), callback);
    }

    public long pullData(String logName, List<K> keys, long time, Callbacks.PullDataCallback<K, V> callback) {
        long rid = getNextRID();
        Protocol.GetData.Builder gdb = Protocol.GetData.newBuilder();
        gdb.setLogName(logName).setHlcTime(time);
        for (K key : keys) {
            gdb.addKeys(ProtocolHelpers.serializableToByteString(key));
        }
        Protocol.RetroServerMsg msg = Protocol.RetroServerMsg.newBuilder()
                .setRID(rid)
                .setDataRequest(gdb)
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

    public long pullLog(String logName, List<K> keys, Callbacks.PullLogSliceCallback<K, V> callback) {
        long rid = getNextRID();
        Protocol.GetLog.Builder glb = Protocol.GetLog.newBuilder();
        glb.setLogName(logName);
        for (K key : keys) {
            glb.addParameterNames(ProtocolHelpers.serializableToByteString(key));
        }
        Protocol.RetroServerMsg msg = Protocol.RetroServerMsg.newBuilder()
                .setRID(rid)
                .setLogSliceRequest(glb)
                .build();
        writeAndFlush(msg, rid, callback);
        return rid;
    }

    public long pullLog(
            String logName,
            List<K> keys,
            Timestamp start,
            Timestamp end,
            Callbacks.PullLogSliceCallback<K, V> callback
    ) {
        long rid = getNextRID();
        Protocol.GetLog.Builder glb = Protocol.GetLog.newBuilder();
        glb
                .setLogName(logName)
                .setHLCstartTime(start.toLong())
                .setHLCendTime(end.toLong());

        for (K key : keys) {
            glb.addParameterNames(ProtocolHelpers.serializableToByteString(key));
        }
        Protocol.RetroServerMsg msg = Protocol.RetroServerMsg.newBuilder()
                .setRID(rid)
                .setLogSliceRequest(glb).build();
        writeAndFlush(msg, rid, callback);
        return rid;
    }

    public long pullLog(
            String logName,
            Timestamp start,
            Timestamp end,
            Callbacks.PullLogSliceCallback<K, V> callback
    ) {
        long rid = getNextRID();
        Protocol.GetLog.Builder glb = Protocol.GetLog.newBuilder();
        glb
                .setLogName(logName)
                .setHLCstartTime(start.toLong())
                .setHLCendTime(end.toLong());
        Protocol.RetroServerMsg msg = Protocol.RetroServerMsg.newBuilder()
                .setRID(rid)
                .setLogSliceRequest(glb).build();
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
            cb.decrementLeftToReceive(nodeId);
            if (cb.getCallback() instanceof Callbacks.PullDataCallback) {
                ((Callbacks.PullDataCallback<K, V>) cb.getCallback()).pullDataComplete(rid, nodeId, logName, data, errorcode);
                CallbackDataAggregator ca = (CallbackDataAggregator) callbacksAggregators.get(rid);
                if (ca != null) {
                    ca.addData(nodeId, errorcode, data);

                    if (cb.receivedAll(remoteNodes.keySet())) {
                        //aggregate callback final call
                        ((Callbacks.PullDataCallback<K, V>) cb.getCallback()).pullAllDataComplete(
                                rid,
                                ca.getNodeIds(),
                                logName,
                                ca.getDataMaps(),
                                ca.getErrors()
                        );
                    }
                }
                if (cb.receivedAll(remoteNodes.keySet())) {
                    callbacks.remove(rid); // clean, we do not need the callback anymore
                    callbacksAggregators.remove(rid);
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
        System.out.println("receiving log from " + nodeId);
        if (cb != null) {
            cb.decrementLeftToReceive(nodeId);
            if (cb.getCallback() instanceof Callbacks.PullLogSliceCallback) {
                ((Callbacks.PullLogSliceCallback<K, V>) cb.getCallback())
                        .pullDataComplete(rid, nodeId, log, errorcode);
                CallbackLogAggregator ca = (CallbackLogAggregator) callbacksAggregators.get(rid);
                if (ca != null) {
                    ca.addLog(nodeId, errorcode, log);

                    if (cb.receivedAll(remoteNodes.keySet())) {
                        //aggregate callback final call
                        ((Callbacks.PullLogSliceCallback<K, V>) cb.getCallback()).pullAllDataComplete(
                                rid,
                                ca.getNodeIds(),
                                ca.getLogs(),
                                ca.getErrors()
                        );
                    }
                }

                if (cb.receivedAll(remoteNodes.keySet())) {
                    callbacks.remove(rid); // clean, we do not need the callback anymore
                    callbacksAggregators.remove(rid);
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

    private class CallbackDataAggregator extends CallbackAggregator {
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

    private class CallbackLogAggregator extends CallbackAggregator {
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
