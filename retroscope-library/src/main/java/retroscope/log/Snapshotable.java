package retroscope.log;

import retroscope.RetroscopeException;
import retroscope.hlc.Timestamp;
import retroscope.rql.RQLItem;

import java.util.HashMap;

/**
 * Created by Aleksey on 1/29/2017.
 *
 */
public interface Snapshotable<K, V> {

    /**
     * creates a snapshot and keeps it in memory
     *
     * @param snapshotTime
     * @return snapshot id
     */
    int makeSnapshot(Timestamp snapshotTime) throws LogOutTimeBoundsException;

    void rollSnapshot(int snapshotID, Timestamp newTime) throws RetroscopeException, LogOutTimeBoundsException;

    RHashMap<K, ?, V> getSnapshot(int snapshotID);

}
