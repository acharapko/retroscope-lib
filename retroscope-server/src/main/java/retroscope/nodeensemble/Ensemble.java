package retroscope.nodeensemble;

import io.netty.channel.ChannelHandlerContext;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import retroscope.Retroscope;
import retroscope.hlc.Timestamp;
import retroscope.log.LogEntry;
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

    //private
    private long maxKnownRID;
    private int maxKnownId = 0;
    private HashMap<Integer, RemoteNode<K, V>> remoteNodes;

    private HashMap<Long, CallbackWrapper<K, V>> pullCallbacks;
    private HashMap<Long, CallbackAggregator> pullCallbacksAggregators;

    private ReentrantLock lock;

    public Ensemble() {
        remoteNodes = new HashMap<Integer, RemoteNode<K, V>>();
        pullCallbacks = new HashMap<Long, CallbackWrapper<K, V>>();
        pullCallbacksAggregators = new HashMap<Long, CallbackAggregator>();
        lock = new ReentrantLock();
    }

    private int getNextId() {
        return ++maxKnownId;
    }

    public long getNextRID() {
        maxKnownRID++;
        if (maxKnownRID == Long.MAX_VALUE) {
            maxKnownRID = 0;
        }
        return maxKnownRID;
    }

    public void processConnect(ChannelHandlerContext ctx, Protocol.ConnectMsg connectMsg) {
        if (connectMsg.getRetroscopeVersion() == Retroscope.VERSION) {
            lock.lock();
            RemoteNode<K, V> remoteNode = new RemoteNode<K, V>(getNextId(), ctx);
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

    private void writeAndFlush(
            Protocol.RetroServerMsg msg,
            long rid,
            Callbacks.GenericPullCallback callback
    ) {

        lock.lock();
        for(Map.Entry<Integer, RemoteNode<K,V>> entry : remoteNodes.entrySet()) {
            entry.getValue().getCtx().writeAndFlush(msg);
        }
        pullCallbacks.put(rid, new CallbackWrapper<K, V>(remoteNodes.size(), callback));
        if (callback instanceof Callbacks.AggregatePullCallback) {
            pullCallbacksAggregators.put(rid, new CallbackAggregator(remoteNodes.size()));
        }
        lock.unlock();
    }

    /*------------------------------------------------------
     *
     * Methods to request data from nodes at given time
     *
     --------------------------------------------------------*/

    public long pullData(String logName, Callbacks.GenericPullCallback<K, V> callback) {
        long rid = getNextRID();
        Protocol.RetroServerMsg msg = Protocol.RetroServerMsg.newBuilder()
                .setRID(rid)
                .setGetData(Protocol.GetData.newBuilder().setLogName(logName))
                .build();

        writeAndFlush(msg, rid, callback);
        return rid;
    }

    public long pullData(String logName, long time, Callbacks.GenericPullCallback<K, V> callback) {
        long rid = getNextRID();
        Protocol.RetroServerMsg msg = Protocol.RetroServerMsg.newBuilder()
                .setRID(rid)
                .setGetData(Protocol.GetData.newBuilder().setLogName(logName).setHlcTime(time))
                .build();

        writeAndFlush(msg, rid, callback);
        return rid;
    }

    public long pullData(String logName, String key, Callbacks.GenericPullCallback<K, V> callback) {
        long rid = getNextRID();
        Protocol.RetroServerMsg msg = Protocol.RetroServerMsg.newBuilder()
                .setRID(rid)
                .setGetData(Protocol.GetData.newBuilder().setLogName(logName)
                        .addKeys(ProtocolHelpers.serializableToByteString(key)))
                .build();

        writeAndFlush(msg, rid, callback);
        return rid;
    }

    public long pullData(String logName, String key, long time, Callbacks.GenericPullCallback<K, V> callback) {
        long rid = getNextRID();
        Protocol.RetroServerMsg msg = Protocol.RetroServerMsg.newBuilder()
                .setRID(rid)
                .setGetData(Protocol.GetData.newBuilder().setLogName(logName).setHlcTime(time)
                        .addKeys(ProtocolHelpers.serializableToByteString(key)))
                .build();

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
        CallbackWrapper<K, V> cb = pullCallbacks.get(rid);
        //System.out.println("have callback: " + (cb != null));
        if (cb != null) {
            cb.decrementLeftToReceive();
            cb.getCallback().pullDataComplete(rid, nodeId, logName, data, errorcode);
            if (cb.getCallback() instanceof Callbacks.AggregatePullCallback) {
                CallbackAggregator ca = pullCallbacksAggregators.get(rid);
                if (ca != null) {
                    ca.addData(nodeId, errorcode, data);
                }
                if (cb.getLeftToReceive() == 0) {
                    //aggregate callback final call
                    ((Callbacks.AggregatePullCallback) cb.getCallback()).pullAllDataComplete(
                            rid,
                            ca.getNodeIds(),
                            logName,
                            ca.getDataMaps(),
                            ca.getErrors()
                    );
                }
            }
            if (cb.getLeftToReceive() == 0) {
                pullCallbacks.remove(rid); // clean, we do not need the callback anymore
            }
        }
        if (HLCtime > 0) {
            workingNode.setDataAtTime(new Timestamp(HLCtime), logName, data);
        }
    }

    class CallbackAggregator {
        private int nodeIds[];
        private RetroMap<K, V> dataMaps[];
        private int errors[];
        int lastPos = 0;
        public CallbackAggregator(int numNodes){
            nodeIds = new int[numNodes];
            dataMaps = new RetroMap[numNodes];
            errors = new int[numNodes];
        }

        public void addData(int NodeId, int errorCode, RetroMap<K, V> datamap) {
            nodeIds[lastPos] = NodeId;
            dataMaps[lastPos] = datamap;
            errors[lastPos] = errorCode;
            lastPos++;
        }

        public int[] getNodeIds() {
            return nodeIds;
        }

        public RetroMap<K, V>[] getDataMaps() {
            return dataMaps;
        }

        public int[] getErrors() {
            return errors;
        }
    }

}
