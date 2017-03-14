package retroscope.nodeensemble;

import io.netty.channel.ChannelHandlerContext;
import retroscope.hlc.Timestamp;
import retroscope.log.RetroMap;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by Aleksey on 11/2/2016.
 */
public class RemoteNode<K extends Serializable, V extends Serializable> {

    private HashMap<Long, HashMap<String, RetroMap<K, V>>> remoteData;
    private int id;
    private ChannelHandlerContext ctx;

    public RemoteNode(int id, ChannelHandlerContext ctx) {
        this.id = id;
        remoteData = new HashMap<Long, HashMap<String, RetroMap<K, V>>>();
        this.ctx = ctx;
    }

    public RetroMap<K, V> getDataByTimeAndName(Timestamp timestamp, String name) {
        HashMap<String, RetroMap<K, V>> logs = remoteData.get(timestamp.toLong());
        if (logs != null) {
            return logs.get(name);
        }
        return null;
    }

    public void purgeDataAtTime(Timestamp timestamp) {
        HashMap<String, RetroMap<K, V>> logs = remoteData.get(timestamp.toLong());
    }

    public void setDataAtTime(Timestamp time, String logName, RetroMap<K, V> data) {
        HashMap<String, RetroMap<K, V>> logs = remoteData.get(time.toLong());
        if (logs == null) {
            logs = new HashMap<String, RetroMap<K, V>>();
            remoteData.put(time.toLong(), logs);
        }
        logs.put(logName, data);
    }

    public int getId() {
        return id;
    }

    public ChannelHandlerContext getCtx() {
        return ctx;
    }




}
