package retroscope.log;

import retroscope.rql.RQLItem;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by ALEKS on 10/23/2016.
 *
 */
public class RQLSetMap extends RHashMap<String, Set<DataEntry<RQLItem>>, RQLItem> {


    public RQLSetMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public RQLSetMap(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * This constructor converts regular RetroMap into RQLSetMap
     * for the easy of operation
     * @param retroMap initial RetroMap
     */
    public RQLSetMap(RetroMap<String, RQLItem> retroMap) {
        super(retroMap.size());
        Iterator<Entry<String, DataEntry<RQLItem>>> it = retroMap.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, DataEntry<RQLItem>> pair = it.next();
            if (pair.getValue() != null) {
                Set<DataEntry<RQLItem>> s = new HashSet<DataEntry<RQLItem>>(1);
                s.add(pair.getValue());
                super.put(pair.getKey(), s);
            }
        }
    }

    public RQLSetMap() {
    }

    public RQLSetMap(Map<? extends String, ? extends Set<DataEntry<RQLItem>>> m) {
        super(m);
    }

    public RQLSetMap(RQLSetMap m) {
        super(m);
        this.setAssociatedLogEntry(m.getAssociatedLogEntry());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<Entry<String, Set<DataEntry<RQLItem>>>> it = entrySet().iterator();
        sb.append("RetroMap");
        //sb.append(this.getAssociatedLogEntry());
        sb.append("\n");
        while (it.hasNext()) {
            Entry<String, Set<DataEntry<RQLItem>>> pair = it.next();
            sb.append(pair.getKey());
            sb.append(" = ");
            sb.append(pair.getValue());
            sb.append("\n");
        }
        return sb.toString();
    }

}
