package retroscope.datamodel.datastruct.sets;

import retroscope.datamodel.datastruct.RQLSymbol;
import retroscope.datamodel.datastruct.RQLType;
import retroscope.datamodel.parser.SetItems;

import java.util.HashSet;
import java.util.Iterator;

public class AppendRQLSet extends RQLSet {

    public AppendRQLSet(String name) {
        super(name);
    }

    public AppendRQLSet(SetItems items) {
        super(items);
    }

    @Override
    public String toRQLString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getName());
        sb.append(':');
        sb.append("...");
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
