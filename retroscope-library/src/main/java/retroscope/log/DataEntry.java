package retroscope.log;

import retroscope.hlc.Timestamp;

/**
 * Created by aleksey on 10/17/16.
 *
 * This class represents data item hold in the logs and datamaps
 */
public class DataEntry<V> {

    protected V value;

    protected Timestamp timestamp;

    public DataEntry(V value) {
        this.value = value;
        this.timestamp = null; //may not be safe to do this
    }

    public DataEntry(V value, Timestamp timestamp) {
        this.value = value;
        this.timestamp = timestamp;
    }



    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false; //nothing ot compare to
        }
        if (this == object) {
            return true; //same reference
        }
        if (!(object instanceof DataEntry)) {
            return false;
        }
        try {
            DataEntry<V> dataEntry = (DataEntry<V>) object;
            if (!this.getValue().equals(dataEntry.getValue())) {
                return false;
            }
            if (this.getTimestamp().toLong() != dataEntry.getTimestamp().toLong()) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.value == null) {
            sb.append("NULL");
        } else {
            sb.append(value.toString());
        }
        sb.append(" @ ");
        if (this.timestamp == null) {
            sb.append("NULL");
        } else {
            sb.append(timestamp.toString());
        }
        return sb.toString();
    }
}
