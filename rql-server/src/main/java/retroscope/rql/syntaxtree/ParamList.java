package retroscope.rql.syntaxtree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleksey on 12/20/2016.
 *
 */
public class ParamList {

    private Param[] params;

    public ParamList(Param p) {
        params = new Param[1];
        params[0] = p;
    }

    public ParamList(Param p, ParamList pList) {
        params = new Param[pList.getParams().length + 1];
        System.arraycopy(pList.getParams(), 0, params, 1, pList.getParams().length);
        params[0] = p;
    }

    public Param[] getParams() {
        return params;
    }

    public List<String> getParamIds() {
        ArrayList<String> paramList = new ArrayList<String>();
        for (Param param : params) {
            paramList.add(param.toString());
        }
        return paramList;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (Param p : params) {
            sb.append(p.toString());
            sb.append(", ");
        }
        return sb.toString();
    }
}
