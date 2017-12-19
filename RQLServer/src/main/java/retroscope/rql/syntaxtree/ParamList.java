package retroscope.rql.syntaxtree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Aleksey on 12/20/2016.
 *
 */
public class ParamList {

    //private Param[] params;

    private ArrayList<Param> params;

    public ParamList(Param p) {
        params = new ArrayList<>();
        params.add(p);
    }

    public ParamList(Param p, ParamList pList) {
        params = pList.params;
        params.add(p);
    }

    public ArrayList<Param> getParams() {
        return params;
    }



    /*public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (Param p : params) {
            sb.append(p.toString());
            sb.append(", ");
        }
        return sb.toString();
    }*/
}
