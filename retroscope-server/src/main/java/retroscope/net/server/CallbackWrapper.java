package retroscope.net.server;

import java.io.Serializable;

/**
 * Created by Aleksey on 11/20/2016.
 *
 * This class has holds the callback and information about how many times
 * a callback needs to be invoked(receive data) and how many time it has
 * been called
 */
public class CallbackWrapper<K extends Serializable, V extends Serializable> {
    private int leftToReceive;
    private Callbacks.GenericCallback<K, V> callback;

    public CallbackWrapper(int leftToreceive, Callbacks.GenericCallback<K, V> callback) {
        this.leftToReceive = leftToreceive;
        this.callback = callback;
    }

    public int getLeftToReceive() {
        return leftToReceive;
    }

    public Callbacks.GenericCallback<K, V> getCallback() {
        return callback;
    }

    public synchronized void decrementLeftToReceive() {
        leftToReceive--;
    }
}
