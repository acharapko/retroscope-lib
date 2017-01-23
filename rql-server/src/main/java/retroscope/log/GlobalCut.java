package retroscope.log;

import retroscope.hlc.Timestamp;
import retroscope.rql.RQLItem;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Aleksey on 12/24/2016.
 * this class represents a consistent global cut from multiple logs
 */
public class GlobalCut implements Cloneable {

    private ArrayList<RetroMap<String, RQLItem>> localSnapshots;
    private ArrayList<String> localSnapshotNames;
    private ArrayList<Integer> nodeIds;
    private Timestamp cutTime;
    private int count = 0;

    public GlobalCut(Timestamp cutTime) {
        localSnapshots = new ArrayList<RetroMap<String, RQLItem>>();
        localSnapshotNames = new ArrayList<String>();
        nodeIds = new ArrayList<Integer>();
        this.cutTime = cutTime;
    }

    /*public void addLocalSnapshot(String logName, RetroMap<String, RQLItem> localSnapshot) {
        addLocalSnapshot(logName, -1, localSnapshot);
    }*/

    public void addLocalSnapshot(String logName, int nodeId, RetroMap<String, RQLItem> localSnapshot) {
        localSnapshots.add(count, localSnapshot);
        localSnapshotNames.add(count, logName);
        nodeIds.add(count, nodeId);
        count++;
    }

    public Timestamp getCutTime() {
        return cutTime;
    }

    public RetroMap<String, RQLItem> getLocalSnapshot(String logName) {
        //find it first
        for (int i = 0; i < count; i++) {
            if (localSnapshotNames.get(i).equals(logName)) {
                return localSnapshots.get(i);
            }
        }
        return null;
    }

    public ArrayList<RetroMap<String, RQLItem>> getLocalSnapshots() {
        return localSnapshots;
    }

    public ArrayList<String> getLocalSnapshotNames() {
        return localSnapshotNames;
    }

    public ArrayList<Integer> getNodeIds() {
        return nodeIds;
    }

    public GlobalCut clone() {
        GlobalCut gc = new GlobalCut(cutTime);
        gc.count = count;
        gc.localSnapshotNames = new ArrayList<String>(localSnapshotNames.size());
        gc.localSnapshotNames.addAll(localSnapshotNames);
        gc.localSnapshots = new ArrayList<RetroMap<String, RQLItem>>(localSnapshots.size());
        for(RetroMap<String, RQLItem> ls : localSnapshots) {
            RetroMap<String, RQLItem> clone = new RetroMap<String, RQLItem>();
            clone.putAll(ls);
            gc.localSnapshots.add(clone);
        }

        return gc;
    }
}
