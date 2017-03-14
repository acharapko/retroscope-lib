package retroscope.rql;

import retroscope.hlc.Timestamp;

import java.util.List;

/**
 * Created by Aleksey on 1/26/2017.
 *
 */
public class RQLRetrieveParam {
    private Timestamp startTime, endTime;
    private List<String> logs, keys;
    private List<Integer> nodeIds;

    public RQLRetrieveParam() {
    }

    public RQLRetrieveParam setStartTime(Timestamp startTime) {
        this.startTime = startTime;
        return this;
    }

    public RQLRetrieveParam setEndTime(Timestamp endTime) {
        this.endTime = endTime;
        return this;
    }

    public RQLRetrieveParam setLogs(List<String> logs) {
        this.logs = logs;
        return this;
    }

    public RQLRetrieveParam setKeys(List<String> keys) {
        this.keys = keys;
        return this;
    }

    public RQLRetrieveParam setNodeIds(List<Integer> nodeIds) {
        this.nodeIds = nodeIds;
        return this;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public List<String> getLogs() {
        return logs;
    }

    public List<String> getKeys() {
        return keys;
    }

    public List<Integer> getNodeIds() {
        return nodeIds;
    }
}