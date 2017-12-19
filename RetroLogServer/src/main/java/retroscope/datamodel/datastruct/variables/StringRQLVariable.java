package retroscope.datamodel.datastruct.variables;

public class StringRQLVariable extends RQLVariable<String> {

    public StringRQLVariable(String val) {
        super();
        if (val.startsWith("\"") && val.endsWith("\"")) {
            val = val.substring(1, val.length() - 1);
        }
        this.setValue(val);
    }

    public StringRQLVariable(String name, String val) {
        super(name);
        if (val.startsWith("\"") && val.endsWith("\"")) {
            val = val.substring(1, val.length() - 1);
        }
        this.setValue(val);
    }

    public String toRQLString() {
        return this.getName() + ":\"" + value + "\"";
    }

    public String toEmitRQLString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getName());
        sb.append('|');
        for (Long n : getNodeIDs()) {
            sb.append(n);
            sb.append(',');
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(':');
        sb.append('"');
        sb.append(value);
        sb.append('"');
        return sb.toString();
    }

    public StringRQLVariable clone() {
        StringRQLVariable clone = new StringRQLVariable(value);
        clone.setName(this.getName());
        return clone;
    }

    @Override
    public void negate() {
        //do nothing
    }

}
