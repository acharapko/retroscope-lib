package retroscope.rql.environment;

import retroscope.datamodel.datastruct.RQLSymbol;

import java.util.HashMap;
import java.util.List;

public class SymbolTable extends HashMap<String, RQLSymbol> {


    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(String var : this.keySet()) {
            sb.append(this.get(var).toEmitRQLString());
        }

        return sb.toString();
    }

    public String toString(List<String> params) {
        StringBuilder sb = new StringBuilder();
        for(String var : this.keySet()) {
            if (params.contains(var)) {
                sb.append(this.get(var).toEmitRQLString());
                sb.append(',');
            }
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public GlobalCut toGlobalCut(List<String> params, long cutTime) {
        StringBuilder sb = new StringBuilder();
        for(String var : this.keySet()) {
            if (params.contains(var)) {
                sb.append(this.get(var).toEmitRQLString());
            }
        }
        return new GlobalCut(cutTime, sb.toString());
    }


}
