package retroscope;

import org.apache.ignite.Ignite;
import org.apache.log4j.PropertyConfigurator;
import retroscope.hlc.HLC;
import retroscope.hlc.Timestamp;
import retroscope.log.RetroscopeLog;
import retroscope.net.ignite.IgniteHandler;
import retroscope.net.ignite.NodeMeta;
import retroscope.util.ByteHelper;
import retroscope.util.netHLCSerializer;

import java.util.HashMap;

public class Retroscope {

    private HLC hlc;
    private HashMap<String, RetroscopeLog> logs;
    private IgniteHandler igniteHandler;
    private NodeMeta nodeMeta;

    public Retroscope(String appName) {
        PropertyConfigurator.configure("log4j.config");
        System.out.println("Starting Retroscope");
        hlc = new HLC();
        logs = new HashMap<>();
        this.nodeMeta = new NodeMeta(appName);
    }

    public IgniteHandler getIgniteHandler() {
        return igniteHandler;
    }

    /**
     * This method is to be used for testing only
     * @param ignite
     * @return
     */
    public Retroscope connectIgnite(Ignite ignite) {
        igniteHandler = new IgniteHandler(nodeMeta, hlc);
        igniteHandler.setIgnite(ignite);
        return this;
    }


    public Retroscope connectIgnite(String igniteConfig) {
        igniteHandler = new IgniteHandler(nodeMeta, hlc, igniteConfig);
        igniteHandler.setStartInClientMode(true);
        igniteHandler.connect();
        return this;
    }


    public Retroscope connectIgnite(String igniteConfig, boolean clientMode) {
        igniteHandler = new IgniteHandler(nodeMeta, hlc, igniteConfig);
        igniteHandler.setStartInClientMode(clientMode);
        igniteHandler.connect();
        return this;
    }

    public Retroscope connectIgnite(String igniteConfig, boolean clientMode, int clientID) {
        igniteHandler = new IgniteHandler(nodeMeta, hlc, igniteConfig);
        igniteHandler.setStartInClientMode(clientMode);
        igniteHandler.connect(clientID);
        return this;
    }

    public void closeIgnite() {
        if (igniteHandler != null) {
            igniteHandler.close();
        }
    }

    public void flushStreams() {
        for (String logName : logs.keySet()) {
            igniteHandler.streamCommit(logs.get(logName), this.getTimestamp().toLong());
        }
        igniteHandler.flushStreams();
    }


    /*----------------------------------------------------------
     *
     *   Retroscope Logs
     *
     *---------------------------------------------------------*/

    public RetroscopeLog getLog(String logName) {
        RetroscopeLog log = logs.get(logName);
        if (log == null) {
            log = new RetroscopeLog(logName, igniteHandler, hlc);
            logs.put(logName, log);
            igniteHandler.addStreamer(logName);
        }
        return log;
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
        return ByteHelper.longToBytes(timeTick().toLong());
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


}
