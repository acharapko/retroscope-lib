package retroscope.log;

import retroscope.RetroscopeException;

/**
 * Created by aleksey on 10/17/16.
 *
 */
public class LogOutTimeBoundsException extends RetroscopeException {
    public LogOutTimeBoundsException(String message) {
        super(RetroscopeException.LOG_OUT_OF_BOUNDS, message);
    }
}
