package retroscope.log;

import retroscope.rql.RQLItem;

/**
 * Created by Aleksey on 12/28/2016.
 * this class represents the log abstraction as seen by the RQL parser
 * This class adds nodeID to the log, so that we can distinguish logs with the
 * same name retrieved from multiple remote machines.
 */
public class RQLDataMapLog extends DataMapLog<String, RQLItem> {

    private int nodeId = 0;

    public RQLDataMapLog(int nodeId, DataMapLog<String, RQLItem> log) {
        super(log.maxLengthMillis, log.name, log.logCheckpointIntervalMs);
        this.nodeId = nodeId;
        this.snapshots = log.snapshots;
        this.dataMap = log.dataMap;
        this.head = log.head;
        this.tail = log.tail;
        this.length = log.length;
    }

    public RQLDataMapLog(int nodeId, Log<String, RQLItem> log, RetroMap<String, RQLItem> data) {
        super(log.maxLengthMillis, log.name, log.logCheckpointIntervalMs);
        this.nodeId = nodeId;
        this.dataMap = data;
        this.head = log.head;
        this.tail = log.tail;
        this.length = log.length;
    }

    public RQLDataMapLog(int nodeId, Log<String, RQLItem> log) {
        super(log.maxLengthMillis, log.name, log.logCheckpointIntervalMs);
        this.nodeId = nodeId;
        this.head = log.head;
        this.tail = log.tail;
        this.length = log.length;

        //now try to reconstruct the datamap with info we have. all data older than the log will be missing

    }


    public RQLDataMapLog(long maxLengthMillis, String name) {
        super(maxLengthMillis, name);
    }

    public RQLDataMapLog(long maxLengthMillis, String name, long logCheckpointIntervalMs) {
        super(maxLengthMillis, name, logCheckpointIntervalMs);
    }

    /*public RetroMap<String, RQLItem> getSnapshotRQLItems(int snapshotID) {
        RetroMap<String, ByteArray> snap = getSnapshot(snapshotID);
        RetroMap<String, RQLItem> rqlSnap = new RetroMap<String, RQLItem>(snap.size());
        Iterator<Map.Entry<String, DataEntry<ByteArray>>> it = snap.entrySet().iterator();
        while (it.hasNext()) { //iterating through the symbols in the symbol table
            Map.Entry<String, DataEntry<ByteArray>> pair = it.next();
            RQLItem rqlItem = new RQLItem(pair.getValue().getValue());
            rqlSnap.put(pair.getKey(), new DataEntry<RQLItem>())
        }

    }*/

    public int getNodeId() {
        return nodeId;
    }
}
