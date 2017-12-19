package retroscope.net.ignite;


/**
 * Created by aleksey on 7/17/16.
 */
public class EventStreamEntry implements StreamEntry{

    protected String entry;
    private long hlcTime;

    public EventStreamEntry(long hlcTime, String entry) {
        this.entry = entry;
        this.hlcTime = hlcTime;
    }

    @Override
    public long getHLCTime() {
        return hlcTime;
    }

    @Override
    public String getEntry() {
        return entry;
    }
}
