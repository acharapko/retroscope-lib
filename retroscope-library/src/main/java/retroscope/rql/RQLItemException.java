package retroscope.rql;

import retroscope.RetroscopeException;

/**
 * Created by Aleksey on 12/28/2016.
 * exception for item serialization errors
 */
public class RQLItemException extends RetroscopeException {
    public RQLItemException(String message) {
        super(RetroscopeException.RQL_ITEM_ERROR, message);
    }
}

