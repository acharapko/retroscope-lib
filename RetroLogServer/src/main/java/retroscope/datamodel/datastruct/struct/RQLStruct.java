package retroscope.datamodel.datastruct.struct;

import retroscope.datamodel.datastruct.RQLSymbol;
import retroscope.datamodel.datastruct.variables.DoubleRQLVariable;
import retroscope.datamodel.datastruct.variables.LongRQLVariable;
import retroscope.datamodel.datastruct.variables.StringRQLVariable;
import retroscope.datamodel.parser.SetItems;

import java.util.HashMap;
import java.util.Map;

public class RQLStruct extends RQLSymbol {

    private HashMap<String, RQLSymbol> elements;

    public RQLStruct() {
        super();
        elements = new HashMap<>();
    }

    public RQLStruct(String name) {
        super(name);
        this.elements = new HashMap<>();
    }

    public RQLStruct(SetItems items) {
        super();
        this.elements = new HashMap<>();
        for (RQLSymbol s: items.getSetItems()) {
            elements.put(s.getName(), s);
        }

    }

    public RQLSymbol getElement(String name) {
        return elements.get(name);
    }

    @Override
    public void merge(RQLSymbol mergeSymbol) {

    }

    @Override
    public RQLSymbol clone() {
        RQLStruct clone = new RQLStruct(this.getName());
        for (Map.Entry<String, RQLSymbol> s: elements.entrySet()) {
            clone.addElement(s.getValue().clone());
        }
        return clone;
    }

    @Override
    public String toEmitRQLString() {
        StringBuilder sb = new StringBuilder();
        if (getName() != null) {
            sb.append(this.getName());
            sb.append('|');
        }
        for (Long n : getNodeIDs()) {
            sb.append(n);
            sb.append(',');
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
            sb.append(':');
        }
        sb.append('[');
        for (Map.Entry<String, RQLSymbol> s: elements.entrySet()) {
            sb.append(s.getValue().toEmitRQLString());
            sb.append(',');
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(']');
        return sb.toString();
    }

    @Override
    public String toRQLString() {
        StringBuilder sb = new StringBuilder();
        if (getName() != null) {
            sb.append(this.getName());
            sb.append(':');
        }
        sb.append('[');
        int l = sb.length();
        for (Map.Entry<String, RQLSymbol> s: elements.entrySet()) {
            sb.append(s.getValue().toRQLString());
            sb.append(',');
        }
        if (l != sb.length()) {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append(']');
        return sb.toString();
    }

    @Override
    public String toValueString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        int l = sb.length();
        for (Map.Entry<String, RQLSymbol> s: elements.entrySet()) {
            sb.append(s.getValue().toRQLString());
            sb.append(',');
        }
        if (l != sb.length()) {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append(']');
        return sb.toString();
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Helpers to add vars
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

    public RQLStruct addElement(RQLSymbol e) {
        if (e.getName() != null) {
            elements.put(e.getName(), e);
        }
        return this;
    }

    public RQLStruct addValue(String name, long val) {
        LongRQLVariable var = new LongRQLVariable(name, val);
        addElement(var);
        return this;
    }

    public RQLStruct addValue(String name, double val) {
        DoubleRQLVariable var = new DoubleRQLVariable(name, val);
        addElement(var);
        return this;
    }

    public RQLStruct addValue(String name, String val) {
        StringRQLVariable var = new StringRQLVariable(name, val);
        addElement(var);
        return this;
    }

    /*
     * Equality is based on the value only and not on additional meta-data, such as name or nodeID
     */
    public boolean equals(Object obj) {
        boolean eq = obj != null && obj instanceof RQLStruct;

        if (eq) {
            //TODO: fix this! Sometimes this results in a collision!
            eq = hashCode() == obj.hashCode();
        }
        return eq;
    }

    public int hashCode()
    {
        int val = 0;
        for (Map.Entry<String, RQLSymbol> s: elements.entrySet()) {
            val = val << 4;
            val = val ^ (s.getValue().hashCode());
        }
        return val;
    }
}
