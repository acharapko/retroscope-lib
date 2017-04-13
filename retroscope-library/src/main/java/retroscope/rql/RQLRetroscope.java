package retroscope.rql;

import retroscope.LogNotFoundException;
import retroscope.LogTypeException;
import retroscope.Retroscope;
import retroscope.RetroscopeException;
import retroscope.log.LogOutTimeBoundsException;


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

    public long appendToLog(String logName, String key, RQLItem value)
            throws LogNotFoundException, LogOutTimeBoundsException, LogTypeException {
        return super.appendToLog(logName, key, value);
    }

    public long appendToLog(String logName, String key, int value)
            throws LogNotFoundException, LogOutTimeBoundsException, LogTypeException {
        return super.appendToLog(logName, key, new RQLItem().addField(value));
    }

    public long appendToLog(String logName, String key, double value)
            throws LogNotFoundException, LogOutTimeBoundsException, LogTypeException {
        return super.appendToLog(logName, key, new RQLItem().addField(value));
    }

    public long appendToLog(String logName, String key, String value)
            throws LogNotFoundException, LogOutTimeBoundsException, LogTypeException {
        return super.appendToLog(logName, key, new RQLItem().addField(value));
    }

    public long appendToLog(String logName, String key, RQLItem oldValue, RQLItem newValue)
            throws LogNotFoundException, LogOutTimeBoundsException {
        return super.appendToLog(logName, key, oldValue, newValue);
    }


}
