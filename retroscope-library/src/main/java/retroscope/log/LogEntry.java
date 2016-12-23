package retroscope.log;


import retroscope.hlc.Timestamp;

/**
 * Created by aleksey on 7/17/16.
 */
public class LogEntry<K,V> implements Cloneable{

    private K key;
    private DataEntry<V> fromV;
    private DataEntry<V> toV;

    private LogEntry<K, V> next;
    private LogEntry<K, V> prev;

    private int snapshotEntryId = -1;

    public LogEntry(K key, DataEntry<V> fromV, DataEntry<V> toV) {
        this.key = key;
        this.toV = toV; //having both toV and froV
        this.fromV = fromV;
    }

    public Timestamp getTime() {
        return toV.getTimestamp();
    }

    public DataEntry<V> getFromV() {
        return fromV;
    }

    public DataEntry<V> getToV() {
        return toV;
    }

    protected void setToV(DataEntry<V> toV) {
        this.toV = toV;
    }

    protected void setFromV(DataEntry<V> fromV) {
        this.fromV = fromV;
    }

    public K getKey() {
        return key;
    }

    public LogEntry<K, V> getNext() {
        return next;
    }

    public LogEntry<K, V> setNext(LogEntry<K, V> next) {
        this.next = next;
        return this;
    }

    public LogEntry<K, V> getPrev() {
        return prev;
    }

    public LogEntry<K, V> setPrev(LogEntry<K, V> prev) {
        this.prev = prev;
        return this;
    }

    public boolean isHead() {
        return prev == null;
    }

    public boolean isTail() {
        return next == null;
    }

    public int getSnapshotEntryId() {
        return snapshotEntryId;
    }

    public void setSnapshotEntryId(int snapshotEntryId) {
        this.snapshotEntryId = snapshotEntryId;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof LogEntry)) {
            return false;
        }
        try {
            LogEntry<K, V> l = (LogEntry<K, V>) object;
            if (!l.getKey().equals(this.getKey())) {
                return false;
            }

            if (!l.getTime().equals(this.getTime())) {
                return false;
            }

            if (!l.getFromV().equals(this.getFromV())) {
                return false;
            }
            if (!l.getToV().equals(this.getToV())) {
                return false;
            }

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public LogEntry<K, V> clone() {
        LogEntry<K, V> clone = new LogEntry<K, V>(this.key, this.fromV, this.toV);
        return clone;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getKey());
        sb.append(": ");
        sb.append(fromV);
        sb.append(" -> ");
        sb.append(toV);
        return sb.toString();
    }

}
