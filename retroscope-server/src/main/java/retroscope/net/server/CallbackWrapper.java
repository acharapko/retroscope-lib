package retroscope.net.server;

/**
 * Created by Aleksey on 11/20/2016.
 */
public class CallbackWrapper<K, V> {
    private int leftToReceive;
    private Callbacks.GenericPullCallback<K, V> callback;

    public CallbackWrapper(int leftToreceive, Callbacks.GenericPullCallback<K, V> callback) {
        this.leftToReceive = leftToreceive;
        this.callback = callback;
    }

    public int getLeftToReceive() {
        return leftToReceive;
    }

    public Callbacks.GenericPullCallback<K, V> getCallback() {
        return callback;
    }

    public synchronized void decrementLeftToReceive() {
        leftToReceive--;
    }
}
