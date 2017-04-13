package retroscope.net;

import retroscope.RetroscopeException;

/**
 * Created by Aleksey on 12/28/2016.
 * exception cleitn network errors
 */
public class RetroscopeNetworkException extends RetroscopeException {
    public RetroscopeNetworkException(String message) {
        super(RetroscopeException.CLIENT_NETWORK_ERROR, message);
    }
}

