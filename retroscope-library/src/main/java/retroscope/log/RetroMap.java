package retroscope.log;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by ALEKS on 10/23/2016.
 *
 */
public class RetroMap<K, V> extends RHashMap<K, DataEntry<V>, V> implements Cloneable {



    public RetroMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public RetroMap(int initialCapacity) {
        super(initialCapacity);
    }

    public RetroMap() {
    }

    public RetroMap(Map<? extends K, ? extends DataEntry<V>> m) {
        super(m);
    }

    public RetroMap(RetroMap<K, V> m) {
        super(m);
        this.setAssociatedLogEntry(m.getAssociatedLogEntry());
    }


    public void putAllLogEntriesForward(HashMap<K, LogEntry<K, V>> entriesMap) {
        Iterator<Entry<K, LogEntry<K,V>>> it = entriesMap.entrySet().iterator();
        while (it.hasNext()) {
            Entry<K, LogEntry<K,V>> pair = it.next();
            put(pair.getKey(), pair.getValue().getToV());
        }
    }

    public void putAllLogEntriesBackwards(HashMap<K, LogEntry<K, V>> entriesMap) {
        Iterator<Entry<K, LogEntry<K,V>>> it = entriesMap.entrySet().iterator();
        while (it.hasNext()) {
            Entry<K, LogEntry<K,V>> pair = it.next();
            put(pair.getKey(), pair.getValue().getFromV());
            //System.out.println(pair.getKey() + " = (FromV: " +pair.getValue().getFromV() + ", ToV: "+ pair.getValue().getToV() + ")");
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<Entry<K, DataEntry<V>>> it = entrySet().iterator();
        sb.append("RetroMap");
        //sb.append(this.getAssociatedLogEntry());
        sb.append("\n");
        while (it.hasNext()) {
            Entry<K, DataEntry<V>> pair = it.next();
            sb.append(pair.getKey());
            sb.append(" = ");
            sb.append(pair.getValue());
            sb.append("\n");
        }
        return sb.toString();
    }

    /*public RetroMap<K, V> clone() {
        return
    }*/
}
