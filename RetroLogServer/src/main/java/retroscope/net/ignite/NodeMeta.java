package retroscope.net.ignite;

import retroscope.hlc.HLC;
import retroscope.hlc.Timestamp;

import java.util.List;

/**
 * Created by Aleksey on 7/23/2017.
 * this class represents teh node meta data to be stored in KV meta store, such as ignite
 */
public class NodeMeta {

    private int nodeId;
    private String appName;
    private long timestamp;
    private long seqSize;
    private long hotTime;


    public NodeMeta(String appName) {
        this.appName = appName;
    }

    public String getAppName() {
        return appName;
    }

    public NodeMeta setAppName(String appName) {
        this.appName = appName;
        return this;
    }

    public NodeMeta setNodeId(int nodeId) {
        this.nodeId = nodeId;
        return this;
    }

    public NodeMeta setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public int getNodeId() {
        return nodeId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public long getSeqSize() {
        return seqSize;
    }

    public NodeMeta setSeqSize(long seqSize) {
        this.seqSize = seqSize;
        return this;
    }

    public long getHotTime() {
        return hotTime;
    }

    public NodeMeta setHotTime(long hotTime) {
        this.hotTime = hotTime;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Node Mata: app = ");
        sb.append(this.appName);
        sb.append("; id = ");
        sb.append(this.nodeId);
        sb.append("; hlc = ");
        sb.append(this.timestamp);
        return sb.toString();
    }
}
