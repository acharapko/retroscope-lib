package retroscope;

import retroscope.log.*;
import retroscope.hlc.HLC;
import retroscope.hlc.Timestamp;
import retroscope.net.Client;
import retroscope.util.ByteHelper;
import retroscope.util.netHLCSerializer;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by aleksey on 10/15/16.
 *
 * this is the main wrapper for all retroscope functionality
 * it holds all necessary retroscope-lib components
 */

public class Retroscope<K extends Serializable, V extends Serializable> {

    public static final int VERSION = 1;
    /*
    retroscope.Retroscope parameters
    */
    private long maxLengthMillis;

    //we checkpoint the retroscope.log every certain time. this is done to
    //avoid full retroscope.log traversal for large intervals and instead being able to quickly find the retroscope.log item
    //and corresponding map checkpoint is map is enabled
    private long logCheckpointIntervalMs = 0;

    /* Retroscope Server interface */
    private Client<K, V> retroClient;

    /*
    Retroscope components
     */

    private HLC hlc;
    private HashMap<String, Log<K, V>> namedLogs;

    public Retroscope() {
        this(100000);
    }

    public Retroscope(long maxLengthMillis) {
        this.maxLengthMillis = maxLengthMillis;

        hlc = new HLC();
        namedLogs = new HashMap<String, Log<K, V>>();
    }

    /*
    Parameter Setters
     */

    /**
     * Sets max log length in milliseconds.
     * @param maxLengthMillis long number of milliseconds to keep the log
     * @return Retroscope this object
     */
    public Retroscope<K, V> setMaxLengthMillis(long maxLengthMillis) {
        this.maxLengthMillis = maxLengthMillis;
        return this;
    }

    /**
     * Sets log checkpointing interval. Checkpoint help find log item and
     * help take snapshots for DataMapLog
     * @param logCheckpointIntervalMs long checkpoint interval
     * @return Retroscope this object
     */
    public Retroscope<K, V> setLogCheckpointIntervalMs(long logCheckpointIntervalMs) {
        this.logCheckpointIntervalMs = logCheckpointIntervalMs;
        return this;
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        Retroscope Server Control
     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

    /**
     * Makes this retroscope node connect to teh retroscope server
     * Server features are still under active development.
     * @param host string host/ip fpr the retroscope server
     * @param port int port number for retroscope server
     * @return Retroscope this object
     */
    public Retroscope<K, V> connectRetroServer(String host, int port) {
        if (retroClient == null) {
            retroClient = new Client<K, V>(host, port, this);
            try {
                retroClient.startClient();
            } catch (Exception e) {
                e.printStackTrace();
            }
            retroClient.connect();
        }
        return this;
    }


    public Retroscope<K, V> disconnectRetroServer() {
        retroClient.close();
        retroClient = null;
        return this;
    }


    /*----------------------------------------------------------
     *
     *   Time Methods
     *
     *---------------------------------------------------------*/

    /**
     * HLC time tick for the local event.
     * Typically this can be used before sending out a message
     * or with any local event that deserves its own timestamp
     * @return Timestamp copy of HLC time for the new time tick
     */
    public Timestamp timeTick() {
        return hlc.now();
    }

    /**
     * HLC time tick for the update event. Typically such events are
     * driven by receiving an inbound message
     * @param t Timestamp that drives this time tick
     * @return Timestamp copy of HLC time for the new time tick
     */
    public Timestamp timeTick(Timestamp t) {
        return hlc.update(t);
    }

    /**
     * Retrieves current HLC time without changing it.
     * @return Timestamp vopy of current HLC time
     */
    public Timestamp getTimestamp() {
        return hlc.getTimestamp();
    }


    /*----------------------------------------------------------
     *
     *   Log Manipulation Methods
     *
     *---------------------------------------------------------*/

    public void createDataMapLog(String logName) {
        Log workingLog = new DataMapLog<K, V>(maxLengthMillis, logName, logCheckpointIntervalMs);
        namedLogs.put(logName, workingLog);
    }

    public void createLog(String logName) {
        Log workingLog = new Log<K, V>(maxLengthMillis, logName, logCheckpointIntervalMs);
        namedLogs.put(logName, workingLog);
    }

    /*
    The methods below are for when we do not keep data ourselves, so we need both old and new values
     */

    /**
     * Appends new data to the window-log with a given name
     * @param logName String log name
     * @param key Key of the item
     * @param oldVal From Value for the Key
     * @param newVal New Value for the Key
     * @return long change in log size. can be 1 for a successful
     * append or less than 1 for an append with some log truncation
     */
    public long appendToLog(String logName, K key, V oldVal, V newVal) throws RetroscopeException {
        return appendToLog(logName, key, oldVal, newVal, true);
    }

    /**
     * Appends new data to the window-log with a given name
     * @param logName String log name
     * @param key Key of the item
     * @param oldVal From Value for the Key
     * @param newVal New Value for the Key
     * @param makeTick whether or not Retroscope makes a new HLC time
     *                 tick fot this append event. Typically we want
     *                 to have a time tick, but sometimes we want to
     *                 write multiple items to the log at the same time
     *                 tick, so this provides teh ability to disable
     *                 time ticks
     * @return long change in log size. can be 1 for a successful
     * append or less than 1 for an append with some log truncation
     */
    public long appendToLog(String logName, K key, V oldVal, V newVal, boolean makeTick)
        throws RetroscopeException {

        Log<K, V> workingLog = getLog(logName);
        if (workingLog instanceof DataMapLog) {
            try {
                return this.appendToLog(logName, key, newVal);
            } catch (RetroscopeException lee) {/*Nothing here*/}
        }
        Timestamp t;
        if (makeTick) {
            t = hlc.now();
        } else {
            t = hlc.getTimestamp();
        }
        DataEntry<V> oldV = new DataEntry<V>(oldVal, t);
        DataEntry<V> newV = new DataEntry<V>(newVal, t);
        LogEntry<K, V> le = new LogEntry<K, V>(key, oldV, newV);
        return workingLog.append(le);
    }

    /**
     * Adds a LegEntry to the list of known entries for the log.
     * In some cases we need to rememebre exact position of the snapshot
     * in the log, for this we add the LogEntry with the item at such position
     * to the list of known LogEntries that represents all the positions we know
     * @param logName String log name
     * @param knownEntry LogEntry to be remembered.
     */
    public void addKnownEntries(String logName, LogEntry<K, V> knownEntry) {
        Log<K, V> workingLog = getLog(logName);
        workingLog.addKnownEntries(knownEntry);
    }

    /**
     * Suspends log truncation, even if the log is beyond the limit
     * @param logName String log name to suspend
     */
    public void suspendTruncation(String logName) {
        Log<K, V> workingLog = getLog(logName);
        workingLog.stopTruncating();
    }

    /**
     * Resumes log truncation
     * @param logName String log name to suspend
     */
    public void resumeTruncation(String logName) {
        Log<K, V> workingLog = getLog(logName);
        workingLog.startTruncating();
    }

    /**
     * checks whether the log's truncation is suspended
     * @param logName String log name to suspend
     * @return boolean true when log's truncation is suspended,
     * false otherwise
     */
    public boolean isTruncationSuspended(String logName) {
        Log<K, V> workingLog = getLog(logName);
        return workingLog.isTruncating();
    }

    /*
    The append methods below are when we keep the values ourselves, so the user supplies us only with new values
     */


    /**
     * Appends new data to the window-log with a given name. This
     * method is used only in datamap mode.
     * @param logName String log name
     * @param key Key of the item
     * @param newVal New Value for the Key
     * @return long change in log size. can be 1 for a successful
     * append or less than 1 for an append with some log truncation
     * @throws RetroscopeException
     */
    public long appendToLog(String logName, K key, V newVal) throws RetroscopeException {
        return appendToLog(logName, key, newVal, true);
    }


    /**
     * Appends new data to the window-log with a given name. This
     * method is used only in datamap mode.
     * @param logName String log name
     * @param key Key of the item
     * @param newVal New Value for the Key
     * @param makeTick whether or not Retroscope makes a new HLC time
     *                 tick fot this append event. Typically we want
     *                 to have a time tick, but sometimes we want to
     *                 write multiple items to the log at the same time
     *                 tick, so this provides teh ability to disable
     *                 time ticks
     * @return long change in log size. can be 1 for a successful
     * append or less than 1 for an append with some log truncation
     * @throws RetroscopeException
     */
    public long appendToLog(String logName, K key, V newVal, boolean makeTick) throws RetroscopeException {

        DataMapLog<K, V> workingLog = getDataMapLog(logName);
        //HashMap<ByteArray, DataEntry<ByteArray>> map = getDataMap(logName);
        Timestamp t;
        if (makeTick) {
            t = hlc.now();
        } else {
            t = hlc.getTimestamp();
        }
        DataEntry<V> newV = new DataEntry<V>(newVal, t);
        return workingLog.append(key, newV);
    }

    /*
     * Log Locks
     */

    /**
     * Locks Log's reentrant lock
     * @param logName String log to lock
     */
    public void lockLog(String logName) {
        Log<K, V> workingLog = getLog(logName);
        workingLog.lock();
    }

    /**
     * Unlocks Log's reentrant lock
     * @param logName String log to unlock
     */
    public void unlockLog(String logName) {
        Log<K, V> workingLog = getLog(logName);
        workingLog.unlock();
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        Log Diff Methods
     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

    public RetroMap<K, V> computeDiff(
            String logName,
            long timeInThePast
    ) throws LogOutTimeBoundsException {
        return this.computeDiff(logName, new Timestamp(timeInThePast));
    }

    public RetroMap<K, V> computeDiff(
            String logName,
            Timestamp timeInThePast
    ) throws LogOutTimeBoundsException {
        Log<K, V> workingLog = getLog(logName);
        return workingLog.computeDiff(timeInThePast);
    }

    public RetroMap<K, V> computeDiff(
            String logName,
            long startTime,
            long endTime
    ) throws LogOutTimeBoundsException {
        return this.computeDiff(logName, new Timestamp(startTime), new Timestamp(endTime));
    }

    public RetroMap<K, V> computeDiff(
            String logName,
            Timestamp startTime,
            Timestamp endTime
    ) throws LogOutTimeBoundsException {
        Log<K, V> workingLog = getLog(logName);
        return workingLog.computeDiff(startTime, endTime);
    }

    public RetroMap<K, V> computeDiff(
            String logName,
            LogEntry<K, V> startLogEntry,
            long endTime
    ) throws LogOutTimeBoundsException {

        return this.computeDiff(logName, startLogEntry, new Timestamp(endTime));
    }

    public RetroMap<K, V> computeDiff(
            String logName,
            LogEntry<K, V> startLogEntry,
            Timestamp endTime
    ) throws LogOutTimeBoundsException {
        Log<K, V> workingLog = getLog(logName);
        return workingLog.computeDiff(endTime, startLogEntry);
    }

    public int addSnapshotPoint(String logName, LogEntry<K, V> knownLogEntry) {
        Log<K, V> workingLog = getLog(logName);
        return workingLog.addKnownEntries(knownLogEntry);
    }

    public LogEntry<K, V> getSnapshotPoint(String logName, int snapshotPointId) {
        Log<K, V> workingLog = getLog(logName);
        return workingLog.getKnownEntry(snapshotPointId);
    }

    /**
     * tells whether a timestamp is withing the log bounds at current time.
     * Important to note, that the log maybe going through changes very rapidly,
     * so the value return by this function can become invalid instantaneously.
     * @param logName String log name
     * @param time long time for testing
     * @param isHlcTime boolean whether the long represetnation of time is HLC time
     * @return whether the test time is within the log bounds
     */
    public boolean isTimeWithinLog(String logName, long time, boolean isHlcTime) {
        if (isHlcTime) {
            return isTimeWithinLog(logName, new Timestamp(time));
        }
        return isTimeWithinLog(logName, new Timestamp(time, (short)0));
    }

    /**
     * tells whether a timestamp is withing the log bounds at current time.
     * Important to note, that the log maybe going through changes very rapidly,
     * so the value return by this function can become invalid instantaneously.
     * @param logName String log name
     * @param time Timestamp HLC time for testing
     * @return whether the test time is within the log bounds
     */
    public boolean isTimeWithinLog(String logName, Timestamp time) {
        Log<K, V> workingLog = getLog(logName);
        return workingLog.isTimestampWithinLog(time);
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        Data Methods
        methods below are used when the Retroscope library keeps the data in the internal KV map
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/


    /**
     * Retrieves an item from the Retroscope's DataMapLog in-memory
     * storage
     * @param logName String log to look for item
     * @param key Key of the item
     * @return DataEntry containing the requested item
     * @throws RetroscopeException
     */
    public DataEntry<V> getItem(String logName, K key) throws RetroscopeException {
        DataMapLog<K, V> workingLog = getDataMapLog(logName);
        return workingLog.getItem(key);
    }

    /**
     * Retrieves an item from the Retroscope's DataMapLog in-memory
     * storage as it existed at provided time
     * @param logName String log to look for item
     * @param key Key of the item
     * @param timestamp Timestamp time of item.
     * @return DataEntry containing the requested item at requested time
     * @throws RetroscopeException
     * @throws LogOutTimeBoundsException
     */
    public DataEntry<V> getItem(
            String logName,
            K key,
            Timestamp timestamp
    ) throws RetroscopeException, LogOutTimeBoundsException {
        DataMapLog<K, V> workingLog = getDataMapLog(logName);
        return workingLog.getItem(key, timestamp);
    }

    /**
     * Retrieves multiple items from the Retroscope's DataMapLog in-memory
     * storage
     * @param logName String log to look for items
     * @param keys Keys for the items
     * @return DataEntry[] containing the requested items
     * @throws RetroscopeException
     * @throws LogOutTimeBoundsException
     */
    public DataEntry<V>[] getItems(
            String logName,
            K keys[]
    ) throws RetroscopeException, LogOutTimeBoundsException {
        DataMapLog<K, V> workingLog = getDataMapLog(logName);
        return workingLog.getItems(keys);
    }

    /**
     * Retrieves multiple items from the Retroscope's DataMapLog in-memory
     * storage at requested time
     * @param logName String log to look for items
     * @param keys Keys for the items
     * @param timestamp Timestamp HLC time for the items
     * @return DataEntry[] containing the requested items as they existed
     * at requested time
     * @throws RetroscopeException
     * @throws LogOutTimeBoundsException
     */
    public DataEntry<V>[] getItems(
            String logName,
            K keys[],
            Timestamp timestamp
    ) throws RetroscopeException, LogOutTimeBoundsException {
        DataMapLog<K, V> workingLog = getDataMapLog(logName);
        return workingLog.getItems(keys, timestamp);
    }

    /**
     * Retrieves multiple items from the Retroscope's DataMapLog in-memory
     * storage. This method differs from getItems in that it returns
     * the map with keys and values, whereas getItems returns only values back
     * @param logName String log to look for items
     * @param keys Keys for the items
     * @return RetroMap with all the key-value pairs requested
     * @throws RetroscopeException
     * @throws LogOutTimeBoundsException
     */
    public RetroMap<K, V> getItemsMap(
            String logName,
            K keys[]
    ) throws RetroscopeException, LogOutTimeBoundsException {
        DataMapLog<K, V> workingLog = getDataMapLog(logName);
        return workingLog.getItemsMap(keys);
    }

    /**
     * Retrieves multiple items from the Retroscope's DataMapLog in-memory
     * storage as requested time. This method differs from getItems in that
     * it returns the map with keys and values, whereas getItems returns
     * only values back
     * @param logName String log to look for items
     * @param keys Keys for the items
     * @param timestamp Timestamp HLC time for the items
     * @return RetroMap with all the key-value pairs requested as they existed
     * at requested time
     * @throws RetroscopeException
     * @throws LogOutTimeBoundsException
     */
    public RetroMap<K, V>  getItemsMap(
            String logName,
            K keys[],
            Timestamp timestamp
    ) throws RetroscopeException, LogOutTimeBoundsException {
        DataMapLog<K, V> workingLog = getDataMapLog(logName);
        return workingLog.getItemsMap(keys, timestamp);
    }

    /**
     * Retrieves multiple items from the Retroscope's DataMapLog in-memory
     * storage as requested time. This method differs from getItems in that
     * it returns the map with keys and values, whereas getItems returns
     * only values back
     * @param logName String log to look for items
     * @param keys Keys for the items
     * @param timestamp long HLC time for the items
     * @return RetroMap with all the key-value pairs requested as they existed
     * at requested time
     * @throws RetroscopeException
     * @throws LogOutTimeBoundsException
     */
    public RetroMap<K, V>  getItemsMap(
            String logName,
            K keys[],
            long timestamp
    ) throws RetroscopeException, LogOutTimeBoundsException {
        DataMapLog<K, V> workingLog = getDataMapLog(logName);
        return workingLog.getItemsMap(keys, new Timestamp(timestamp));
    }


    /**
     * Gets all data in the DataMapLog's storage
     * @param logName String log name
     * @return RetroMap with all the data
     * @throws RetroscopeException
     */
    public RetroMap<K, V> getAllData(String logName) throws RetroscopeException {
        DataMapLog<K, V> workingLog = getDataMapLog(logName);
        return workingLog.getAllData();
    }

    /**
     * Gets all data in the DataMapLog's storage at requested time
     * @param logName String log name
     * @param timestamp long HLC time for the data
     * @return RetroMap with all the data as it existed
     * at requested time
     * @throws RetroscopeException
     * @throws LogOutTimeBoundsException
     */
    public RetroMap<K, V> getAllData(
            String logName,
            long timestamp
    ) throws RetroscopeException, LogOutTimeBoundsException {
        return this.getAllData(logName, new Timestamp(timestamp));
    }

    /**
     * Gets all data in the DataMapLog's storage at requested time
     * @param logName String log name
     * @param timestamp Timestamp HLC time for the data
     * @return RetroMap with all the data as it existed
     * at requested time
     * @throws RetroscopeException
     * @throws LogOutTimeBoundsException
     */
    public RetroMap<K, V> getAllData(
            String logName,
            Timestamp timestamp
    ) throws RetroscopeException, LogOutTimeBoundsException {
        DataMapLog<K, V> workingLog = getDataMapLog(logName);
        return workingLog.getAllData(timestamp);
    }


    /**
     * Gets a slice of the log bounded by two timestamps
     * @param logName String log name
     * @param sliceStart Timestamp HLC time for the beginning of the log slice
     * @param sliceEnd Timestamp HLC time for the end of the log slice
     * @return Log represting the slice of the parent log between start and end times
     * @throws RetroscopeException
     * @throws LogOutTimeBoundsException
     */
    public Log<K, V> getLogSlice(String logName, Timestamp sliceStart, Timestamp sliceEnd)
            throws RetroscopeException, LogOutTimeBoundsException {
        DataMapLog<K, V> workingLog = getDataMapLog(logName);
        return workingLog.logSlice(sliceStart, sliceEnd);
    }

    /**
     * Gets a slice of the log bounded by two timestamps
     * @param logName String log name
     * @param sliceStart long HLC time for the beginning of the log slice
     * @param sliceEnd long HLC time for the end of the log slice
     * @return Log represting the slice of the parent log between start and end times
     * @throws RetroscopeException
     * @throws LogOutTimeBoundsException
     */
    public Log<K, V> getLogSlice(String logName, long sliceStart, long sliceEnd)
            throws RetroscopeException, LogOutTimeBoundsException {
        DataMapLog<K, V> workingLog = getDataMapLog(logName);
        return workingLog.logSlice(sliceStart, sliceEnd);
    }



    /**
     * Takes a snapshot and gives back the snapshot ID. This
     * is similar as getAllData with timestamp, except snapshots
     * are also stored in the Retroscope and can be retrieved for
     * future use or manipulated (rolled)
     * @param logName String log name to snapshot
     * @param snapshotTime long HLC time for the snapshot
     * @return int snapshot ID
     * @throws RetroscopeException
     * @throws LogOutTimeBoundsException
     */
    public int makeSnapshot(String logName, long snapshotTime) throws RetroscopeException, LogOutTimeBoundsException {
        return makeSnapshot(logName, new Timestamp(snapshotTime));
    }

    /**
     * Takes a snapshot and gives back the snapshot ID. This
     * is similar as getAllData with timestamp, except snapshots
     * are also stored in the Retroscope and can be retrieved for
     * future use or manipulated (rolled)
     * @param logName String log name to snapshot
     * @param snapshotTime Timestamp HLC time for the snapshot
     * @return int snapshot ID
     * @throws RetroscopeException
     * @throws LogOutTimeBoundsException
     */
    public int makeSnapshot(String logName, Timestamp snapshotTime) throws RetroscopeException, LogOutTimeBoundsException {
        DataMapLog<K, V> workingLog = getDataMapLog(logName);
        return workingLog.makeSnapshot(snapshotTime);
    }

    /**
     * Retrieves snapshot data by its id.
     * @param logName String log name to snapshot
     * @param snapshotID int ID of the snapshot
     * @return RetroMap with data for a given snapshot
     * @throws RetroscopeException
     */
    public RetroMap<K, V> getSnapshot(String logName, int snapshotID) throws RetroscopeException {
        DataMapLog<K, V> workingLog = getDataMapLog(logName);
        return workingLog.getSnapshot(snapshotID);
    }

    /**
     * Rolls snapshot with a given snapshotID to new time, overriding
     * the snapshot wiht new version at new time
     * @param logName String log name to snapshot
     * @param snapshotID int ID of the snapshot
     * @param newTime new time of the snapshot
     * @throws RetroscopeException
     * @throws RetroscopeException
     * @throws LogOutTimeBoundsException
     */
    public void rollSnapshot(String logName, int snapshotID, Timestamp newTime)
            throws RetroscopeException, RetroscopeException, LogOutTimeBoundsException {
        DataMapLog<K, V> workingLog = getDataMapLog(logName);
        workingLog.rollSnapshot(snapshotID, newTime);
    }


     /*----------------------------------------------------------
      *
      *   Network methods
      *
      *---------------------------------------------------------*/

    /**
     * prepends 8 bytes of HLC to a byte array. This can be used for
     * including HLC in the communication, when communication protocols
     * exchange data as byte arrays
     * @param message original message data
     * @return byte[] messages with HLC
     */
    public byte[] wrapMessageWithHLC(byte[] message) {
        return netHLCSerializer.networkAddHLC(timeTick(), message);
    }

    /**
     * Unwraps the HLC from message and makes a tick
     * @param message byte array message with HLC
     * @return byte[] message with no HLC
     */
    public byte[] unwrapMessageFromHLC(byte[] message) {
        timeTick(netHLCSerializer.networkReadHLC(message));
        return netHLCSerializer.networkUnwrapHLC(message);
    }

    /**
     * gives byte representation of HLC tick used for send messages
     * @return byte[] byte array with ticked for send HLC timestamp
     */
    public byte[] timeTickForSend() {
        return ByteHelper.doubleToBytes(timeTick().toLong());
    }

    /**
     * updates HLC with HLC data received from remote machine
     * @param time Timestamp remote HLC time
     * @return Timestamp new tick for this Retroscope's HLC
     */
    public Timestamp timeTickForReceive(Timestamp time) {
        return timeTick(time);
    }

    /**
     * updates HLC with HLC data received from remote machine
     * @param time long remote HLC time
     * @return Timestamp new tick for this Retroscope's HLC
     */
    public Timestamp timeTickForReceive(long time) {
        return timeTick(new Timestamp(time));
    }

    /**
     * updates HLC with HLC data received from remote machine
     * @param time byte[] remote HLC time as part of the byte array.
     *             reads first 8 bytes
     * @return Timestamp new tick for this Retroscope's HLC
     */
    public Timestamp timeTickForReceive(byte[] time) {
        return timeTick(new Timestamp(ByteHelper.bytesToLong(time)));
    }

    /**
     * updates HLC with HLC data received from remote machine
     * @param receiveData byte[] remote HLC time as part of the byte array.
     *                    reads 8 bytes from the offset
     * @param offset int offset in the byte array to read HLC time
     * @return Timestamp new tick for this Retroscope's HLC
     */
    public Timestamp timeTickForReceive(byte[] receiveData, int offset) {
        return timeTick(new Timestamp(ByteHelper.bytesToLong(receiveData, offset)));
    }

    public Log<K, V> getLog(String logName) {
        return getLog(logName, false);
    }

    /*----------------------------------------------------------
     *
     *  Private Helper methods
     *
     *----------------------------------------------------------*/


    private Log<K, V> getLog(String logName, boolean dataMapLogIfEmpty) {
        Log<K, V> workingLog = null;
        if ((workingLog = namedLogs.get(logName)) == null) {
            if (dataMapLogIfEmpty) {
                workingLog = new DataMapLog<K, V>(maxLengthMillis, logName, logCheckpointIntervalMs);
            } else {
                workingLog = new Log<K, V>(maxLengthMillis, logName, logCheckpointIntervalMs);
            }
            namedLogs.put(logName, workingLog);
        }
        return  workingLog;
    }

    private DataMapLog<K, V> getDataMapLog(String logName) throws RetroscopeException {
        Log<K, V> log = getLog(logName, true);
        if (!(log instanceof DataMapLog)) {
            throw new RetroscopeException("Requested Log is not if type DataMapLog");
        }
        return (DataMapLog<K, V>) log;
    }


}
