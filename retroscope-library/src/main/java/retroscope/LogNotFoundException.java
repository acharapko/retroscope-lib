package retroscope;

/**
 * Created by Aleksey on 12/28/2016.
 * exception cleitn network errors
 */
public class LogNotFoundException extends RetroscopeException {
    public LogNotFoundException(String message) {
        super(RetroscopeException.LOG_DOES_NOT_EXIST, message);
    }
}

