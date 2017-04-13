package retroscope.log;

import retroscope.RetroscopeException;
import retroscope.hlc.Timestamp;
import retroscope.net.protocol.Protocol;
import retroscope.net.protocol.ProtocolHelpers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by aleksey on 7/17/16.
 * this class is basic bidirectional window-retroscope.log implementation
 * The retroscope.log if formed as the double-linked list of LogEntry objects
 * thus, moving along the retroscope.log requires O(n) operations, where n is
 * number of LogEntry objects.
 *
 * maxLengthMillis determines how far back the retroscope.log keeps the information
 */

public class Log<K extends Serializable, V extends Serializable> implements BasicLog<K, V> {

    public int known_snapshot_log_id = 0;

    protected ReentrantLock lock;

    protected LogEntry<K, V> head;
    protected LogEntry<K, V> tail;

    protected long maxLengthMillis = 0;
    protected long originalMaxLengthSeconds;

    protected int length = 0;

    protected HashMap<Long, Integer> timeToCheckpointId;
    protected HashMap<Integer, LogEntry<K, V>> knownCheckpointLogEntries;

    protected String name;
    protected long logCheckpointIntervalMs;


    public Log(long maxLengthMillis, String name) {
        this(maxLengthMillis, name, 0);
    }

    public Log(long maxLengthMillis, String name, long logCheckpointIntervalMs) {
        this.maxLengthMillis = maxLengthMillis;
        //this.id = id_counter++;
        this.name = name;
        this.logCheckpointIntervalMs = logCheckpointIntervalMs;
        lock = new ReentrantLock();

        //used for internal indexing for cases when we want to go back to a known point in the past
        knownCheckpointLogEntries = new HashMap<Integer, LogEntry<K, V>>(10);
        timeToCheckpointId = new HashMap<Long, Integer>(10);
    }

    public Log(Protocol.Log protocolLog) {
        this.name = protocolLog.getName();
        this.maxLengthMillis = protocolLog.getMaxLengthMillis();
        this.logCheckpointIntervalMs = protocolLog.getLogCheckpointIntervalMillis();
        LogEntry<K, V> logEntry = null;
        LogEntry<K, V> prevLogEntry = null;
        //ArrayList<LogEntry<K, V>> logSlice = new ArrayList<LogEntry<K, V>>(protocolLog.getItemsCount());
        for (int i = 0; i < protocolLog.getItemsCount(); i++) {
            Protocol.LogItem item = protocolLog.getItems(i);
            //System.out.println(item);
            V valTo = null;
            V valFrom = null;
            if (item.hasValueFrom()) {
                valFrom = ProtocolHelpers.byteStringToSerializable(item.getValueFrom());
            }
            if (item.hasValueTo()) {
                valTo = ProtocolHelpers.byteStringToSerializable(item.getValueTo());
            } else {
                System.out.println("poop");
            }
            K key = ProtocolHelpers.byteStringToSerializable(item.getKey());
            logEntry = new LogEntry<K, V>(
                    key,
                    new DataEntry<V>(valFrom),
                    new DataEntry<V>(valTo, new Timestamp(item.getHlcTime()))
            );

            if (head != null) {
                logEntry.setPrev(prevLogEntry);
                prevLogEntry.setNext(logEntry);
            } else {
                head = logEntry;
            }
            prevLogEntry = logEntry;
            length++;
        }
        tail = logEntry;
    }

    private Log<K, V> setHeadAndTail(LogEntry<K, V> head, LogEntry<K, V> tail) {
        this.head = head;
        this.tail = tail;
        return this;
    }


    public int addKnownEntries(LogEntry<K, V> entry) {
        knownCheckpointLogEntries.put(++known_snapshot_log_id, entry);
        entry.setSnapshotEntryId(known_snapshot_log_id);
        return known_snapshot_log_id;
    }

    public LogEntry<K, V> getKnownEntry(int id) {
        return knownCheckpointLogEntries.get(id);
    }


    public int append(LogEntry<K, V> entry) throws LogOutTimeBoundsException {
        lock();
        if (tail != null && entry.getTime().compareTo(tail.getTime()) <= 0) {
            throw new LogOutTimeBoundsException("Cannot append entry with timestamp same as or older then the timestamp of a tail");
        }
        int lengthOriginal = length;
        length++;
        if (head == null) {
            head = tail = entry;
        } else {
            tail.setNext(entry);
            entry.setPrev(tail);
            tail = entry;

            //control max length of the chain
            long tailWallTime = tail.getTime().getWallTime();
            LogEntry<K, V> tempHead = head;

            while (length > 0 && tempHead != null && tempHead.getTime().getWallTime() + maxLengthMillis < tailWallTime) {
                if (tempHead.getSnapshotEntryId() > -1) {
                    knownCheckpointLogEntries.remove(tempHead.getSnapshotEntryId()); //clean up known snapshots to allow GC
                    if (logCheckpointIntervalMs > 0) {
                        long headTmod = tempHead.getTime().getWallTime() / logCheckpointIntervalMs;
                        timeToCheckpointId.remove(headTmod);
                    }
                }
                tempHead = tempHead.getNext();
                length--;
            }

            if (tempHead != head) {
                //tempHead and head reference different objects
                tempHead.setPrev(null);
                head = tempHead;
            }
        }

        //checkpointing for faster retroscope log element location
        checkpoint(entry);
        unlock();
        return length - lengthOriginal;
    }

    protected void checkpoint(LogEntry<K, V> entry) {
        if (logCheckpointIntervalMs > 0) {
            long t = entry.getTime().getWallTime();
            long tmod = t / logCheckpointIntervalMs;
            if (timeToCheckpointId.get(tmod) == null) {
                int knownId = addKnownEntries(entry);
                timeToCheckpointId.put(tmod, knownId);
            }
        }
    }

    public LogEntry<K, V> findEntry(long time) throws LogOutTimeBoundsException {
        //user gave us PT only, try our best
        return findEntry(new Timestamp(time, (short)0));
    }

    public LogEntry<K, V> findEntry(Timestamp time) throws LogOutTimeBoundsException{
        if (time.compareTo(head.getTime()) < 0) {
            throw new LogOutTimeBoundsException("Requested search time is before the oldest retroscope.log element available: "+
                                                time + " < " + head.getTime());
        }
        if (time.compareTo(tail.getTime()) > 0) {
            throw new LogOutTimeBoundsException("Requested search time is ahead of the newest retroscope.log element available: "+
                                                time + " > " + tail.getTime());
        }

        LogEntry<K, V> startLE = null;
        if (logCheckpointIntervalMs > 0) {
            long tmod = time.getWallTime() / logCheckpointIntervalMs;
            if (timeToCheckpointId.get(tmod) != null) {
                int knownId = timeToCheckpointId.get(tmod);
                if (knownCheckpointLogEntries.get(knownId) != null) {
                    startLE = knownCheckpointLogEntries.get(knownId);
                }
            }
        }

        //we traverse from the tail backwards or from the known checkpoint forward
        if (startLE == null) {
            return rollBackwards(time, tail);
        } else {
            return rollForwards(time, startLE);
        }

    }


    /**
     * This is a linear search for the log. It scans the entire log for matches to a specified Key
     * Indexed log may come in the future.
     * @param key K key of the item to search for
     * @param val V condition Val
     * @param comparator Comparator used to compare values.
     * @param acceptedVal int comparator value used for accepting LogEntry
     * @return
     */
    public ArrayList<LogEntry<K, V>> findEntriesByKey(K key, V val, Comparator<V> comparator, int acceptedVal) {
        ArrayList<LogEntry<K, V>> entries = new ArrayList<LogEntry<K, V>>();

        LogEntry<K, V> logEntry = head;
        while (logEntry != tail.getNext()) {
            if (key.equals(logEntry.getKey()) && comparator.compare(logEntry.getToV().getValue(), val) == acceptedVal) {
                entries.add(logEntry);
            }
            logEntry = logEntry.getNext();
        }
        return entries;
    }

    private LogEntry<K, V> rollForwards(Timestamp find, LogEntry<K, V> startingLE)
    {
        LogEntry<K, V> currentLogItem = startingLE;
        boolean notEnd = true;
        while (notEnd && currentLogItem != null) {
            if (find.compareTo(currentLogItem.getTime()) >= 0) {
                if (!currentLogItem.isTail()) {
                    currentLogItem = currentLogItem.getNext();
                } else {
                    //this is the end... we either have results ion the tail
                    //or found nothing
                    if (find.compareTo(currentLogItem.getTime()) == 0) {
                        return currentLogItem;
                    } else {
                        return null; //found nothing
                    }
                }
            } else {
                notEnd = false;
            }
        }
        // if the item we are looking for is the tail,
        // we need to return it instead of a previous item

        return currentLogItem.getPrev();
    }

    private LogEntry<K, V> rollBackwards(Timestamp find, LogEntry<K, V> startingLE)
    {
        LogEntry<K, V> currentLogItem = startingLE;
        boolean notEnd = true;
        while (notEnd && currentLogItem != null) {
            if (find.compareTo(currentLogItem.getTime()) < 0) {
                if (!currentLogItem.isHead()) {
                    currentLogItem = currentLogItem.getPrev();
                } else {
                    notEnd = false;
                }
            } else {
                notEnd = false;
            }
        }

        return currentLogItem;
    }

    public RetroMap<K, V> computeDiff(Timestamp timeInThePast) throws LogOutTimeBoundsException
    {
        return computeDiffBackwards(timeInThePast, tail);
    }

    public RetroMap<K, V> computeDiff(Timestamp startTime, Timestamp endTime) throws LogOutTimeBoundsException {
        LogEntry<K, V> startingPoint = findEntry(startTime);
        return computeDiff(endTime, startingPoint);
    }

    public RetroMap<K, V> computeDiff(Timestamp time, LogEntry<K, V> startingPoint) throws LogOutTimeBoundsException {
        if (startingPoint == null) {
            throw new LogOutTimeBoundsException("Starting Point does not exist");
        }
        int comparison = startingPoint.getTime().compareTo(time);
        if (comparison < 0) {
            return computeDiffForwards(time, startingPoint);
        } else if (comparison > 0) {
            return computeDiffBackwards(time, startingPoint);
        } else {
            RetroMap<K, V> empty = new RetroMap<K, V>();
            empty.setAssociatedLogEntry(startingPoint);
            return empty;
        }
    }

    protected RetroMap<K, V> computeDiffBackwards(
            LogEntry<K, V> startingPoint,
            LogEntry<K, V> endingPoint
    ) throws LogOutTimeBoundsException
    {
        if (startingPoint.getTime().compareTo(endingPoint.getTime()) < 0) {
            throw new LogOutTimeBoundsException("Starting logentry cannot be before or at ending log entry");
        }
        LogEntry<K, V> currentLogItem = startingPoint;
        boolean notEnd = true;
        RetroMap<K, V> diffMap = new RetroMap<K, V>(getLength());

        while (notEnd && currentLogItem != null) {
            if (endingPoint != currentLogItem) {
                diffMap.put(currentLogItem.getKey(), currentLogItem.getFromV());
                if (!currentLogItem.isHead()) {
                    currentLogItem = currentLogItem.getPrev();
                } else {
                    throw new LogOutTimeBoundsException("Could not reach the ending log entry");
                }
            } else {
                notEnd = false;
            }
        }
        diffMap.setAssociatedLogEntry(endingPoint.getPrev());
        return diffMap;
    }

    protected RetroMap<K, V> computeDiffBackwards(Timestamp timeInThePast, LogEntry<K, V> startingPoint) throws LogOutTimeBoundsException
    {
        if (startingPoint == null) {
            throw new LogOutTimeBoundsException("Starting Point does not exist");
        }
        if (timeInThePast.compareTo(startingPoint.getTime()) > 0) {
            throw new LogOutTimeBoundsException("Cannot compute backwards diff, because target time was ahead of the starting point ("
                    + timeInThePast + " > " + startingPoint.getTime());
        }

        LogEntry<K, V> currentLogItem = startingPoint;
        boolean notEnd = true;
        RetroMap<K, V> diffMap = new RetroMap<K, V>(getLength());
        diffMap.setAssociatedLogEntry(currentLogItem);

        while (notEnd && currentLogItem != null) {
            if (timeInThePast.compareTo(currentLogItem.getTime()) < 0) {
                diffMap.put(currentLogItem.getKey(), currentLogItem.getFromV());
                diffMap.setAssociatedLogEntry(currentLogItem.getPrev());
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


        return diffMap;
    }

    protected RetroMap<K, V> computeDiffForwards(
            LogEntry<K, V> startingPoint,
            LogEntry<K, V> endingPoint
    ) throws LogOutTimeBoundsException
    {
        if (startingPoint.getTime().compareTo(endingPoint.getTime()) > 0) {
            throw new LogOutTimeBoundsException("Starting log entry cannot be after or at ending log entry");
        }
        LogEntry<K, V> currentLogItem = startingPoint.getNext();
        boolean notEnd = true;
        RetroMap<K, V> diffMap = new RetroMap<K, V>(getLength());

        while (notEnd && currentLogItem != null) {
            diffMap.put(currentLogItem.getKey(), currentLogItem.getToV());
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
        diffMap.setAssociatedLogEntry(endingPoint);
        return diffMap;
    }

    protected RetroMap<K, V> computeDiffForwards(Timestamp timeInTheFuture, LogEntry<K, V> startingPoint) throws LogOutTimeBoundsException
    {
        if (timeInTheFuture.compareTo(startingPoint.getTime()) < 0) {
            throw new LogOutTimeBoundsException("Cannot compute forward diff, because target time was before of the starting point");
        }
        LogEntry<K, V> currentLogItem = startingPoint.getNext();
        boolean notEnd = true;
        RetroMap<K, V> diffMap = new RetroMap<K, V>(getLength());
        diffMap.setAssociatedLogEntry(startingPoint);
        while (notEnd && currentLogItem != null) {
            if (timeInTheFuture.compareTo(currentLogItem.getTime()) >= 0) {
                diffMap.put(currentLogItem.getKey(), currentLogItem.getToV());
                diffMap.setAssociatedLogEntry(currentLogItem);
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
        return diffMap;
    }

    public Log<K, V> logSlice(long sliceStart, long sliceEnd)
            throws LogOutTimeBoundsException {
        return logSlice(new Timestamp(sliceStart), new Timestamp(sliceEnd));
    }

    public Log<K, V> logSlice(List<K> keys, long sliceStart, long sliceEnd)
            throws LogOutTimeBoundsException {
        return logSlice(keys, new Timestamp(sliceStart), new Timestamp(sliceEnd));
    }

    public Log<K, V> logSlice(Timestamp sliceStart, Timestamp sliceEnd)
            throws LogOutTimeBoundsException {
        LogEntry<K, V> currentOriginalLogItem = this.findEntry(sliceStart);
        LogEntry<K, V> head = null;
        LogEntry<K, V> currentLogItem = null;
        LogEntry<K, V> tempLogItem = null;
        int length = 0;
        while (currentOriginalLogItem != null && currentOriginalLogItem.getTime().compareTo(sliceEnd) <= 0) {
            //add to log slice
            length++;
            tempLogItem = currentLogItem;
            currentLogItem = currentOriginalLogItem.clone();
            if (tempLogItem == null) {
                head = currentLogItem;
            } else {
                tempLogItem.setNext(currentLogItem);
            }
            currentLogItem.setPrev(tempLogItem);
            currentOriginalLogItem = currentOriginalLogItem.getNext();
        }

        Log<K, V> l = new Log<K, V>(this.maxLengthMillis, this.name, this.logCheckpointIntervalMs)
                .setHeadAndTail(head, tempLogItem);
        l.length = length;
        return l;
    }


    public Log<K, V> logSlice(List<K> keys, Timestamp sliceStart, Timestamp sliceEnd)
            throws LogOutTimeBoundsException {
        LogEntry<K, V> currentOriginalLogItem = this.findEntry(sliceStart);

        LogEntry<K, V> head = null;
        LogEntry<K, V> currentLogItem = null;
        LogEntry<K, V> tempLogItem = null;
        int length = 0;
        while (currentOriginalLogItem != null && currentOriginalLogItem.getTime().compareTo(sliceEnd) <= 0) {
            if (keys.contains(currentOriginalLogItem.getKey())) {
                //add to log slice
                length++;
                tempLogItem = currentLogItem;
                currentLogItem = currentOriginalLogItem.clone();
                if (tempLogItem == null) {
                    head = currentLogItem;
                } else {
                    tempLogItem.setNext(currentLogItem);
                }
                currentLogItem.setPrev(tempLogItem);
            }
            currentOriginalLogItem = currentOriginalLogItem.getNext();
        }

        Log<K, V> l = new Log<K, V>(this.maxLengthMillis, this.name, this.logCheckpointIntervalMs)
                .setHeadAndTail(head, tempLogItem);
        l.length = length;
        return l;
    }

    public Log<K, V> logSlice(List<K> keys)
            throws LogOutTimeBoundsException {
        lock.lock();
        Log<K, V> l = logSlice(keys, head.getTime(), tail.getTime());
        lock.unlock();
        return l;
    }

    public int getLength() {
        return length;
    }

    private void countLength() {
        lock.lock();
        LogEntry<K, V> le = head;
        while (le != null) {
            length++;
            le = le.getNext();
        }
        lock.unlock();
    }

    public LogEntry<K, V> getHead() {
        return head;
    }

    public LogEntry<K, V> getTail() {
        return tail;
    }

    public long getMaxLogSize() {
        return maxLengthMillis;
    }

    public long getLogCheckpointIntervalMs() {
        return logCheckpointIntervalMs;
    }

    public void stopTruncating()
    {
        this.originalMaxLengthSeconds = maxLengthMillis;
        maxLengthMillis = Integer.MAX_VALUE; //int max is smaller than long max
    }

    public void startTruncating()
    {
        this.maxLengthMillis = this.originalMaxLengthSeconds;
    }

    public boolean isTruncating() {
        return maxLengthMillis == Integer.MAX_VALUE;
    }

    public void setLogCheckpointIntervalMs(long logCheckpointIntervalMs) {
        this.logCheckpointIntervalMs = logCheckpointIntervalMs;
    }

    public String getName() {
        return name;
    }

    public boolean isTimestampWithinLog(Timestamp time) {
        if ((this.getHead().getTime().compareTo(time) <= 0) &&
                (this.getTail().getTime().compareTo(time)) >= 0) {
            return true;
        }
        return false;
    }

     /*
      * Expose Lock
      */

    public void lock() {
        lock.lock();
    }

    public void unlock() {
        lock.unlock();
    }


    /*
     * To... methods
     */

    public String toString() {
        return Log.class.getName() + " " + name + " of length " + length;
    }

    public Protocol.Log toProtocol() {
        Protocol.Log.Builder protocolMsgBuilder = Protocol.Log.newBuilder();
        protocolMsgBuilder.setName(name)
                .setMaxLengthMillis(this.maxLengthMillis)
                .setLogCheckpointIntervalMillis(this.logCheckpointIntervalMs);

        LogEntry<K, V> logEntry = head;

        while (logEntry != null) {
            Protocol.LogItem.Builder logItemBuilder = Protocol.LogItem.newBuilder();
            logItemBuilder
                    .setKey(ProtocolHelpers.serializableToByteString(logEntry.getKey()))
                    .setHlcTime(logEntry.getTime().toLong());

            if (logEntry.getFromV() != null) {
                logItemBuilder.setValueFrom(ProtocolHelpers.serializableToByteString(logEntry.getFromV().getValue()));
            }
            if (logEntry.getToV().getValue() != null) {
                logItemBuilder.setValueTo(ProtocolHelpers.serializableToByteString(logEntry.getToV().getValue()));
            }
            protocolMsgBuilder.addItems(logItemBuilder);

            logEntry = logEntry.getNext();
        }
        return protocolMsgBuilder.build();
    }


    /*public ByteArray toByteArray() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        LogEntry<K, V> logEntry = head;
        LogEntry<K, V> prevLogEntry = null;

        while (logEntry != null) {



            prevLogEntry = logEntry;
            logEntry = logEntry.getNext();
        }

        new ByteArray(out.toByteArray());
    }*/
}