package retroscope.net.ignite;

import retroscope.hlc.Timestamp;

/**
 * Created by aleksey on 10/30/76.
 *
 */
public class CommitStreamEntry implements StreamEntry{

    protected int nodeID;
    protected Timestamp timestamp;
    protected String commitSnap;


    public CommitStreamEntry(Timestamp timestamp, String commitSnap, int nodeID) {
        this.timestamp = timestamp;
        this.commitSnap = commitSnap;
        this.nodeID = nodeID;
    }

    public void setNodeID(int nodeID) {
        this.nodeID = nodeID;
    }

    public int getNodeID() {
        return nodeID;
    }

    public CommitStreamEntry clone() {
        return new CommitStreamEntry(this.timestamp.clone(), commitSnap, this.nodeID);
    }

    @Override
    public long getHLCTime() {
        return timestamp.toLong();
    }

    @Override
    public String getEntry() {
        return commitSnap;
    }
}
