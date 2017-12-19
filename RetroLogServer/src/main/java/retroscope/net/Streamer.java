package retroscope.net;

import retroscope.log.RetroscopeLog;
import retroscope.net.ignite.StreamEntry;

/**
 * Created by Aleksey on 7/23/2017.
 *
 */
public interface Streamer {


    boolean connect();

    boolean connect(int nodeID);

    void stream(StreamEntry streamEntry, RetroscopeLog log, long blockTime);

    void streamCommit(RetroscopeLog log, long lastEntryHLC);

    void addStreamer(String logName);

    void close();

    void flushStreams();

}
