package retroscope.rql;

import retroscope.Retroscope;
import retroscope.RetroscopeException;
import retroscope.util.ByteArray;


/**
 * Created by aleksey on 12/27/16.
 *
 * RQLRetroscope is the extension of Retroscope for RQL
 */

public class RQLRetroscope extends Retroscope<String, RQLItem> {

    public RQLRetroscope() {
        this(100000);
    }

    public RQLRetroscope(long maxLengthMillis) {
        super(maxLengthMillis);
    }

    public long appendToLog(String logName, String key, RQLItem value) throws RetroscopeException {
        return super.appendToLog(logName, key, value);
    }

    public long appendToLog(String logName, String key, int value) throws RetroscopeException {
        return super.appendToLog(logName, key, new RQLItem().addField(value));
    }

    public long appendToLog(String logName, String key, double value) throws RetroscopeException {
        return super.appendToLog(logName, key, new RQLItem().addField(value));
    }

    public long appendToLog(String logName, String key, String value) throws RetroscopeException {
        return super.appendToLog(logName, key, new RQLItem().addField(value));
    }

    public long appendToLog(String logName, String key, RQLItem oldValue, RQLItem newValue) throws RetroscopeException {
        return super.appendToLog(logName, key, oldValue, newValue);
    }


}
