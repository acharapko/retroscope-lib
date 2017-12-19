package retroscope.net.ignite;
import org.apache.ignite.IgniteCache;
import retroscope.hlc.Timestamp;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ALEKS on 7/28/2017.
 * this class represent the log sub-section stored in Ignite.
 * It is pretty much just a sequence (ordered tree) of differences submitted by nodes
 */
public class StateSequence {

    protected Timestamp startingTimestamp; //this is a starting startingTimestamp
    protected Timestamp lastSequenceEnd; //this is a timestamp of last event in previous stateSequence
    protected ConcurrentHashMap<Long, Collection<String>> vals; //<Timestamp, Collection<StateChanges>>
    protected ArrayList<String> initState;
    protected Timer commitTimer;

    public StateSequence(Timestamp startingTime) {
        this.startingTimestamp = startingTime;
        vals = new ConcurrentHashMap<>();
        this.initState = new ArrayList<>();
        lastSequenceEnd = new Timestamp(0);
    }

    public void addInitState(String snap, Timestamp hlc) {
        initState.add(snap);
        if (lastSequenceEnd.compareTo(hlc) < 0) {
            lastSequenceEnd = hlc;
        }
    }

    public ArrayList<String> getInitState() {
        return initState;
    }

    public ConcurrentHashMap<Long, Collection<String>> getVals() {
        return vals;
    }

    /*----------------------------------------------------------
     *
     * Managing Delta When Growing Sequence
     *
     *----------------------------------------------------------*/


    public synchronized void appendItem(EventStreamEntry item) {
        Collection<String> items = vals.get(item.getHLCTime());
        if (items == null) {
            items = new ArrayList<>();
        }
        items.add(item.getEntry());
        vals.put(item.getHLCTime(), items);
    }

    public Timestamp getStartingTimestamp() {
        return startingTimestamp;
    }

    public void commit(IgniteCache<Long, StateSequence> stateSeq, long commitDelay) {
        if (commitTimer != null) {
            commitTimer.cancel();
        }
        StateSequence seq = this;
        commitTimer = new Timer();
        long t = System.currentTimeMillis();
        System.out.println( t + "   commitDelay = " + commitDelay);
        commitTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                stateSeq.put(startingTimestamp.getWallTime(), seq);
                commitTimer.cancel();
                commitTimer = null;
                //System.out.println(seq.toString());
                System.out.println(t + " + 300 = " + System.currentTimeMillis());
            }
        }, commitDelay); //we wait for dalay, which is a heartbeat timeout to make sure we receive everything
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Initially:");
        sb.append(System.lineSeparator());
        for (String init: initState) {
            sb.append(init);
            sb.append(System.lineSeparator());
        }
        sb.append("Changes:");
        sb.append(System.lineSeparator());
        for (Long time: this.vals.keySet()) {
            //sb.append("@ ");
            //sb.append(time);
            //sb.append(System.lineSeparator());
            for (String st:this.vals.get(time)) {
                sb.append(st);
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }



    /*----------------------------------------------------------
     *
     * Setters
     *
     *----------------------------------------------------------*/

    public void setStartingTimestamp(Timestamp startingTimestamp) {
        this.startingTimestamp = startingTimestamp;
    }


}
