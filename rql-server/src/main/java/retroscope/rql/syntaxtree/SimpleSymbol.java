package retroscope.rql.syntaxtree;

import retroscope.rql.Valuable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleksey on 1/21/2017.
 *
 */
public class SimpleSymbol implements Valuable{

    private ArrayList<RQLInterpreterValue> values;

    public SimpleSymbol() {
        values = new ArrayList<RQLInterpreterValue>();
    }

    public void add(RQLInterpreterValue s) {
        values.add(s);
    }

    public void addAll(List<RQLInterpreterValue> valueList) {
        values.addAll(valueList);
    }

    public void setAll(List<RQLInterpreterValue> valueList) {
        values.clear();
        values.addAll(valueList);
    }

    public RQLInterpreterValue[] getValues() {
        return values.toArray(new RQLInterpreterValue[values.size()]);
    }

    public void evaluate() throws IllegalExpressionException {
        //nothing here, this is a simply symbol that holds only values
    }
}
