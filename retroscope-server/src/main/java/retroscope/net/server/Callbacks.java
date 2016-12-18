package retroscope.net.server;

import retroscope.log.Log;
import retroscope.log.RetroMap;

import java.io.Serializable;

/**
 * Created by Aleksey on 11/20/2016.
 */
public class Callbacks {

    public interface GenericCallback<K extends Serializable, V extends Serializable> {

    }

    public interface PullDataCallback<K extends Serializable, V extends Serializable>
            extends GenericCallback<K, V>
    {
            void pullDataComplete(
                    long rid,
                    int nodeId,
                    String logName,
                    RetroMap<K, V> data,
                    int errorCode
            );
            void pullAllDataComplete(
                long rid,
                int nodeIds[],
                String logName,
                RetroMap<K, V> data[],
                int errorCode[]
            );
    }

    public interface PullLogSliceCallback<K extends Serializable, V extends Serializable>
            extends GenericCallback<K, V>{
        void pullDataComplete(
                long rid,
                int nodeId,
                Log<K, V> log,
                int errorCode
        );
        void pullAllDataComplete(
                long rid,
                int nodeIds[],
                Log<K, V> data[],
                int errorCode[]
        );
    }
}
