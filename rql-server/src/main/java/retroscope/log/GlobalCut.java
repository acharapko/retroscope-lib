package retroscope.log;

import retroscope.hlc.Timestamp;
import retroscope.rql.RQLItem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Aleksey on 12/24/2016.
 * this class represents a consistent global cut from multiple logs
 */
public class GlobalCut implements Cloneable {

    private ArrayList<RQLSetMap> localSnapshots;
    private ArrayList<String> localSnapshotNames; //log name
    private ArrayList<Integer> nodeIds; 

    private Set<Integer> knownNodes;

    private Timestamp cutTime;
    private int count = 0;

    public GlobalCut(Timestamp cutTime) {
        localSnapshots = new ArrayList<RQLSetMap>();
        localSnapshotNames = new ArrayList<String>();
        nodeIds = new ArrayList<Integer>();
        this.cutTime = cutTime;
        knownNodes = new HashSet<Integer>();
    }
    
    public void addLocalSnapshot(String logName, int nodeId, RQLSetMap localSnapshot) {
        localSnapshots.add(count, localSnapshot);
        localSnapshotNames.add(count, logName);
        nodeIds.add(count, nodeId);
        knownNodes.add(nodeId);
        count++;
    }

    public void addLocalSnapshot(String logName, int nodeId, RetroMap<String, RQLItem> localSnapshot) {
        localSnapshots.add(count, new RQLSetMap(localSnapshot));
        localSnapshotNames.add(count, logName);
        nodeIds.add(count, nodeId);
        knownNodes.add(nodeId);
        count++;
    }

    public Timestamp getCutTime() {
        return cutTime;
    }

    public RQLSetMap getLocalSnapshot(String logName) {
        //find it first
        for (int i = 0; i < count; i++) {
            if (localSnapshotNames.get(i).equals(logName)) {
                return localSnapshots.get(i);
            }
        }
        return null;
    }

    public RQLSetMap getLocalSnapshot(String logName, int nodeId) {
        //find it first
        for (int i = 0; i < count; i++) {
            if (localSnapshotNames.get(i).equals(logName) && nodeIds.get(i) == nodeId) {
                return localSnapshots.get(i);
            }
        }
        return null;
    }

    public ArrayList<RQLSetMap> getLocalSnapshots() {
        return localSnapshots;
    }

    public ArrayList<String> getLocalSnapshotNames() {
        return localSnapshotNames;
    }

    public ArrayList<Integer> getNodeIds() {
        return nodeIds;
    }

    public Set<Integer> getKnownNodes() {
        return knownNodes;
    }

    public GlobalCut clone() {
        GlobalCut gc = new GlobalCut(cutTime);
        gc.count = count;
        gc.localSnapshotNames = new ArrayList<String>(localSnapshotNames.size());
        gc.localSnapshotNames.addAll(localSnapshotNames);
        gc.nodeIds = new ArrayList<Integer>(nodeIds.size());
        gc.knownNodes = new HashSet<Integer>();
        gc.knownNodes.addAll(knownNodes);
        gc.nodeIds.addAll(nodeIds);
        gc.localSnapshots = new ArrayList<RQLSetMap>(localSnapshots.size());
        for(RQLSetMap ls : localSnapshots) {
            RQLSetMap clone = new RQLSetMap();
            clone.putAll(ls);
            gc.localSnapshots.add(clone);
        }

        return gc;
    }
}
