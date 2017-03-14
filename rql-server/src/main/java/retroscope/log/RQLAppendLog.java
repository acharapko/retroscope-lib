package retroscope.log;

import retroscope.RetroscopeException;
import retroscope.hlc.Timestamp;
import retroscope.rql.RQLItem;

import java.util.*;

/**
 * Created by Aleksey on 12/28/2016.
 * this class represents the log abstraction as seen by the RQL parser
 * This class adds nodeID to the log, so that we can distinguish logs with the
 * same name retrieved from multiple remote machines.
 * <p>
 * This log class is not used on the nodes. It uses data of regualr log to compile the lists of RQLItems for
 * a given Key, as accumulated by a given time.
 */
public class RQLAppendLog extends Log<String, RQLItem> implements RQLLog {

    private static int snapCount = 0;
    private int nodeId = 0;
    protected RQLSetMap dataList;
    protected HashMap<Integer, RQLSetMap> snapshots;

    public RQLAppendLog(int nodeId, Log<String, RQLItem> log) {
        super(log.maxLengthMillis, log.name, log.logCheckpointIntervalMs);
        this.nodeId = nodeId;
        this.head = log.head;
        this.tail = log.tail;
        this.length = log.length;

        compileDataList();
        snapshots = new HashMap<Integer, RQLSetMap>();
    }

    public int getNodeId() {
        return nodeId;
    }

    private void compileDataList() {
        dataList = new RQLSetMap();
        LogEntry<String, RQLItem> le = this.head;
        while (le != null) {
            putToListMap(dataList, le.getKey(), le.getToV());
            le = le.getNext();
        }
    }


    private void putToListMap(HashMap<String, Set<DataEntry<RQLItem>>> diffListMap, String key, DataEntry<RQLItem> put) {
        Set<DataEntry<RQLItem>> list = diffListMap.get(key);
        if (list == null) {
            list = new HashSet<DataEntry<RQLItem>>();
            diffListMap.put(key, list);
        }
        list.add(put);
    }

    protected RQLSetMap computeListDiffBackwards(
            LogEntry<String, RQLItem> startingPoint,
            LogEntry<String, RQLItem> endingPoint
    ) throws LogOutTimeBoundsException {
        if (startingPoint.getTime().compareTo(endingPoint.getTime()) < 0) {
            throw new LogOutTimeBoundsException("Starting logentry cannot be before or at ending log entry");
        }
        LogEntry<String, RQLItem> currentLogItem = startingPoint;
        boolean notEnd = true;
        RQLSetMap diffListMap = new RQLSetMap(getLength());

        while (notEnd && currentLogItem != null) {
            if (endingPoint != currentLogItem) {
                putToListMap(diffListMap, currentLogItem.getKey(), currentLogItem.getToV());
                if (!currentLogItem.isHead()) {
                    currentLogItem = currentLogItem.getPrev();
                } else {
                    throw new LogOutTimeBoundsException("Could not reach the ending log entry");
                }
            } else {
                notEnd = false;
            }
        }
        diffListMap.setAssociatedLogEntry(endingPoint.getPrev());
        return diffListMap;
    }

    protected RQLSetMap computeListDiffBackwards(Timestamp timeInThePast, LogEntry<String, RQLItem> startingPoint) throws LogOutTimeBoundsException {
        if (startingPoint == null) {
            throw new LogOutTimeBoundsException("Starting Point does not exist");
        }
        if (timeInThePast.compareTo(startingPoint.getTime()) > 0) {
            throw new LogOutTimeBoundsException("Cannot compute backwards diff, because target time was ahead of the starting point ("
                    + timeInThePast + " > " + startingPoint.getTime());
        }

        LogEntry<String, RQLItem> currentLogItem = startingPoint;
        boolean notEnd = true;
        RQLSetMap diffListMap = new RQLSetMap(getLength());
        diffListMap.setAssociatedLogEntry(currentLogItem);

        while (notEnd && currentLogItem != null) {
            if (timeInThePast.compareTo(currentLogItem.getTime()) < 0) {
                putToListMap(diffListMap, currentLogItem.getKey(), currentLogItem.getToV());
                diffListMap.setAssociatedLogEntry(currentLogItem.getPrev());

                //rollBackCounter++;
                if (!currentLogItem.isHead()) {
                    currentLogItem = currentLogItem.getPrev();
                } else {
                    notEnd = false;
                }
            } else {
                notEnd = false;
            }
        }

        return diffListMap;
    }

    protected RQLSetMap computeListDiffForwards(
            LogEntry<String, RQLItem> startingPoint,
            LogEntry<String, RQLItem> endingPoint
    ) throws LogOutTimeBoundsException {
        if (startingPoint.getTime().compareTo(endingPoint.getTime()) > 0) {
            throw new LogOutTimeBoundsException("Starting log entry cannot be after or at ending log entry");
        }
        LogEntry<String, RQLItem> currentLogItem = startingPoint.getNext();
        boolean notEnd = true;
        RQLSetMap diffListMap = new RQLSetMap(getLength());

        while (notEnd && currentLogItem != null) {
            putToListMap(diffListMap, currentLogItem.getKey(), currentLogItem.getFromV());
            if (endingPoint != currentLogItem) {
                //rollBackCounter++;
                if (!currentLogItem.isTail()) {
                    currentLogItem = currentLogItem.getNext();
                } else {
                    throw new LogOutTimeBoundsException("Could not reach the ending log entry");
                }
            } else {
                notEnd = false;
            }
        }
        diffListMap.setAssociatedLogEntry(endingPoint);
        return diffListMap;
    }

    protected RQLSetMap computeListDiffForwards(
            Timestamp timeInTheFuture,
            LogEntry<String, RQLItem> startingPoint
    ) throws LogOutTimeBoundsException {
        if (timeInTheFuture.compareTo(startingPoint.getTime()) < 0) {
            throw new LogOutTimeBoundsException("Cannot compute forward diff, because target time was before of the starting point");
        }
        LogEntry<String, RQLItem> currentLogItem = startingPoint.getNext();
        boolean notEnd = true;
        RQLSetMap diffListMap = new RQLSetMap(getLength());
        diffListMap.setAssociatedLogEntry(startingPoint);
        while (notEnd && currentLogItem != null) {
            if (timeInTheFuture.compareTo(currentLogItem.getTime()) >= 0) {
                putToListMap(diffListMap, currentLogItem.getKey(), currentLogItem.getFromV());
                diffListMap.setAssociatedLogEntry(currentLogItem);
                //rollBackCounter++;
                if (!currentLogItem.isTail()) {
                    currentLogItem = currentLogItem.getNext();
                } else {
                    notEnd = false;
                }
            } else {
                notEnd = false;
            }
        }
        return diffListMap;
    }

    public RQLSetMap computeListDiff(Timestamp timeInThePast) throws LogOutTimeBoundsException {
        return computeListDiffBackwards(timeInThePast, tail);
    }

    public RQLSetMap computeListDiff(Timestamp startTime, Timestamp endTime) throws LogOutTimeBoundsException {
        LogEntry<String, RQLItem> startingPoint = findEntry(startTime);
        return computeListDiff(endTime, startingPoint);
    }

    public RQLSetMap computeListDiff(Timestamp time, LogEntry<String, RQLItem> startingPoint) throws LogOutTimeBoundsException {
        if (startingPoint == null) {
            throw new LogOutTimeBoundsException("Starting Point does not exist");
        }
        int comparison = startingPoint.getTime().compareTo(time);
        if (comparison < 0) {
            return computeListDiffForwards(time, startingPoint);
        } else if (comparison > 0) {
            return computeListDiffBackwards(time, startingPoint);
        } else {
            RQLSetMap empty = new RQLSetMap();
            return empty;
        }
    }

    public RQLSetMap getAllLists(Timestamp timestamp) throws LogOutTimeBoundsException {
        HashMap<String, Set<DataEntry<RQLItem>>> diffListMap;
        try {
            lock();
            diffListMap = this.computeListDiff(timestamp);
            unlock();
        } catch (LogOutTimeBoundsException lotbe) {
            unlock();
            throw lotbe;
        }
        RQLSetMap clone = new RQLSetMap(dataList);
        subtractLists(clone, diffListMap);

        return clone;
    }

    public int makeSnapshot(Timestamp snapshotTime) throws LogOutTimeBoundsException {
        RQLSetMap snap = getAllLists(snapshotTime); // locking is here
        int entryId = snapCount++;
        this.snapshots.put(entryId, snap);
        return entryId;
    }

    public void rollSnapshot(int snapshotID, Timestamp newTime) throws RetroscopeException, LogOutTimeBoundsException {
        RQLSetMap snap = getSnapshot(snapshotID);
        if (snap == null) {
            throw new RetroscopeException("snapshot does not exist");
        }
        RQLSetMap diff = computeListDiff(newTime, this.getKnownEntry(snapshotID));
        //need to update the known logEntry for this snapshot
        snap.setAssociatedLogEntry(diff.getAssociatedLogEntry());

    }

    public RQLSetMap getSnapshot(int snapshotID) {
        return snapshots.get(snapshotID);
    }

    public RQLSetMap getSnapshotSetMap(int snapshotID) {
        return snapshots.get(snapshotID);
    }

    private void addLists(
            HashMap<String, Set<DataEntry<RQLItem>>> lists1,
            HashMap<String, Set<DataEntry<RQLItem>>> lists2
    ) {
        HashSet<String> keys = new HashSet<String>();
        Iterator<Map.Entry<String, Set<DataEntry<RQLItem>>>> it = lists1.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Set<DataEntry<RQLItem>>> pair = it.next();
            keys.add(pair.getKey());            
        }
        it = lists2.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Set<DataEntry<RQLItem>>> pair = it.next();
            keys.add(pair.getKey());
        }
        //now we have all keys from both lists
        for (String k : keys) {
            Set<DataEntry<RQLItem>> s1 = new HashSet<DataEntry<RQLItem>>(lists1.get(k));
            Set<DataEntry<RQLItem>> s2 = lists2.get(k);
            if (s2 != null) {
                s1.addAll(s2);
            }
            lists1.put(k, s1);
        }
    }


    private void subtractLists(
            HashMap<String, Set<DataEntry<RQLItem>>> lists1,
            HashMap<String, Set<DataEntry<RQLItem>>> lists2
    ) {

        HashSet<String> keys = new HashSet<String>();
        Iterator<Map.Entry<String, Set<DataEntry<RQLItem>>>> it = lists1.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Set<DataEntry<RQLItem>>> pair = it.next();
            keys.add(pair.getKey());
        }
        it = lists2.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Set<DataEntry<RQLItem>>> pair = it.next();
            keys.add(pair.getKey());
        }
        //now we have all keys from both lists
        for (String k : keys) {
            Set<DataEntry<RQLItem>> s1 = new HashSet<DataEntry<RQLItem>>(lists1.get(k));
            Set<DataEntry<RQLItem>> s2 = lists2.get(k);
            if (s2 != null) {
                s1.removeAll(s2);
            }
            lists1.put(k, s1);
        }
    }

}
