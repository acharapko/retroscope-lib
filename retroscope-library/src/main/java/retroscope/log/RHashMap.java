package retroscope.log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Aleksey on 1/29/2017.
 *
 */
public abstract class RHashMap<K, V, E> extends HashMap<K, V> {

    protected LogEntry<K, E> associatedLogEntry;

    public RHashMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public RHashMap(int initialCapacity) {
        super(initialCapacity);
    }

    public RHashMap() {
    }

    public RHashMap(Map<? extends K, ? extends V> m) {
        super(m);
    }

    public RHashMap(RHashMap<K, V, E> m) {
        super(m);
        this.setAssociatedLogEntry(m.getAssociatedLogEntry());
    }

    public LogEntry<K, E> getAssociatedLogEntry() {
        return associatedLogEntry;
    }

    public void setAssociatedLogEntry(LogEntry<K, E> associatedLogEntry) {
        this.associatedLogEntry = associatedLogEntry;
    }
}
