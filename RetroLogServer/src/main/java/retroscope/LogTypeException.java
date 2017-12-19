package retroscope;

/**
 * Created by Aleksey on 12/28/2016.
 * exception cleitn network errors
 */
public class LogTypeException extends RetroscopeException {
    public LogTypeException(String message) {
        super(RetroscopeException.INCORRECT_LOG_TYPE, message);
    }
}

