package retroscope.datamodel.datastruct.sets;

import retroscope.datamodel.datastruct.RQLSymbol;
import retroscope.datamodel.parser.SetItems;

public class RemoveRQLSet extends RQLSet {

    public RemoveRQLSet(String name) {
        super(name);
    }

    public RemoveRQLSet(SetItems items) {
        super(items);
    }

    @Override
    public String toRQLString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getName());
        sb.append(':');
        sb.append("--");
        sb.append('{');
        for (RQLSymbol s: getSet()) {
            sb.append(s.toRQLString());
            sb.append(',');
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append('}');
        return sb.toString();
    }


}
