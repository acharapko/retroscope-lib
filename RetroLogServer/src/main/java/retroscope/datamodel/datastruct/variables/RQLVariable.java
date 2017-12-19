package retroscope.datamodel.datastruct.variables;

import retroscope.datamodel.datastruct.RQLSymbol;
import retroscope.datamodel.datastruct.RQLType;

public abstract class RQLVariable<V> extends RQLSymbol {

    V value;

    public RQLVariable() {
        super();
    }

    public RQLVariable(String name) {
        super(name);
    }

    public RQLVariable(String name, V val) {
        super(name);
        this.value = val;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public String toRQLString() {
        if (this.getName() != null) {
            return this.getName() + ":" + value;
        }
        return value.toString();
    }

    public String toValueString() {
        return value.toString();
    }

    public abstract void negate();

    public String toEmitRQLString() {
        StringBuilder sb = new StringBuilder();
        if (this.getName() != null) {
            sb.append(this.getName());
        }
        sb.append('|');
        int l1 = sb.length() - 1;
        for (Long n : getNodeIDs()) {
            sb.append(n);
            sb.append(',');
        }
        sb.deleteCharAt(sb.length() - 1);
        if (l1 != sb.length() || this.getName() != null) {
            sb.append(':');
        }
        sb.append(value);
        return sb.toString();
    }

    @Override
    public void merge(RQLSymbol mergeSymbol) {
        if (mergeSymbol instanceof RQLVariable) {
            this.value = ((RQLVariable<V>) mergeSymbol).value;
        }
    }


    /*
     * Equality is based on the value only and not on additional meta-data, such as name or nodeID
     */
    public boolean equals(Object obj) {
        return obj != null
                && obj instanceof RQLVariable
                && value.equals(((RQLVariable)obj).getValue());
    }

    public int hashCode(){
        return value.hashCode();
    }
}
