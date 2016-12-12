package retroscope.hlc;
/**
 * Created by aleksey on 10/15/16.

 * This class is the based on java reimplementation of HLC from cockroachDB
 * https://github.com/cockroachdb/cockroach/tree/master/pkg/util/hlc
 */

public class HLC
{

    private Timestamp clockState = new Timestamp(System.currentTimeMillis(), (short)0);
    private long lastPT = 0;

    public HLC() {
        this.reset();
    }

    public HLC(Timestamp timestamp) {
        this.clockState = timestamp.clone();
    }

    public synchronized Timestamp getTimestamp()
    {
        return clockState.clone();
    }

    public long getPT()
    {
        this.lastPT = System.currentTimeMillis();
        return this.lastPT;
    }

    public void reset()
    {
        this.clockState = new Timestamp(System.currentTimeMillis(), (short)0);
        this.lastPT = System.currentTimeMillis();
    }

    /** Now returns a timestamp associated with an event from
        the local machine that may be sent to other members
        of the distributed network. This is the counterpart
        of Update, which is passed a timestamp received from
        another member of the distributed network.
    */
    public synchronized Timestamp now()
    {
        long pt = this.getPT();
        if (clockState.getWallTime() >= pt) {
            clockState.incrementLogical();
        } else {
            clockState.setWallTime(pt);
            clockState.resetLogical();
        }
        return this.getTimestamp();
    }

    public synchronized long physicalNow()
    {
        return this.getPT();
    }

    /*
    // Update takes a hybrid timestamp, usually originating from
    // an event received from another member of a distributed
    // system. The clock is updated and the hybrid timestamp
    // associated to the receipt of the event returned.
    // An error may only occur if offset checking is active and
    // the remote timestamp was rejected due to clock offset,
    // in which case the state of the clock will not have been
    // altered.
    // To timestamp events of local origin, use Now instead.
    */
    public synchronized Timestamp update(Timestamp remoteTime) {
        long pt = this.getPT();

        if (pt > clockState.getWallTime() && pt > remoteTime.getWallTime()) {
            // Our physical clock is ahead of both wall times. It is used
            // as the new wall time and the logical clock is reset.
            clockState.setWallTime(pt);
            clockState.resetLogical();
            return clockState.clone();
        }

        if (remoteTime.getWallTime() > clockState.getWallTime()) {
            //long offset =
            clockState.setWallTime(remoteTime.getWallTime());
            clockState.setLogical((short)(remoteTime.getLogical() + 1));
        } else if (clockState.getWallTime() > remoteTime.getWallTime()) {
            clockState.incrementLogical();
        } else {
            if (remoteTime.getLogical() > clockState.getLogical()) {
                clockState.setLogical(remoteTime.getLogical());
            }
            clockState.incrementLogical();
        }
        return clockState.clone();
    }

}