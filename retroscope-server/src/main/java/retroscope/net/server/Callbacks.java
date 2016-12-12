package retroscope.net.server;

import retroscope.log.RetroMap;

/**
 * Created by Aleksey on 11/20/2016.
 */
public class Callbacks {

    public interface GenericPullCallback<K, V> {
        void pullDataComplete(
                long rid,
                int nodeId,
                String logName,
                RetroMap<K, V> data,
                int errorCode
        );
    }


    public interface AggregatePullCallback<K, V> extends GenericPullCallback {
                void pullAllDataComplete(
                long rid,
                int nodeIds[],
                String logName,
                RetroMap<K, V> data[],
                int errorCode[]
        );
    }
}
