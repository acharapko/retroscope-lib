package retroscope.log;

import retroscope.RetroscopeException;

/**
 * Created by Aleksey on 12/28/2016.
 * exception for snapshots
 */
public class RetroscopeSnapshotException extends RetroscopeException {
    public RetroscopeSnapshotException(String message) {
        super(RetroscopeException.SNAPSHOT_DOES_NOT_EXIST, message);
    }
}

