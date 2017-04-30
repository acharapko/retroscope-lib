package retroscope.net.server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Aleksey on 11/20/2016.
 *
 * This class has holds the callback and information about how many times
 * a callback needs to be invoked(receive data) and how many time it has
 * been called
 */
public class CallbackWrapper<K extends Serializable, V extends Serializable> {
    private Set<Integer> receiveList;
    private Callbacks.GenericCallback<K, V> callback;

    public CallbackWrapper(int leftToreceive, Callbacks.GenericCallback<K, V> callback) {
        this.callback = callback;
        receiveList = new HashSet<>(leftToreceive);
    }

    public boolean receivedAll(Set<Integer> ensembleNodes) {
        return receiveList.containsAll(ensembleNodes);
    }

    public Callbacks.GenericCallback<K, V> getCallback() {
        return callback;
    }

    public synchronized void decrementLeftToReceive(int nodeId) {
        receiveList.add(nodeId);
    }


}
