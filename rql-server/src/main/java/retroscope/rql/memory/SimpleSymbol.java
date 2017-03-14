package retroscope.rql.memory;

import retroscope.log.DataEntry;
import retroscope.rql.RQLItem;
import retroscope.rql.syntaxtree.expression.IllegalExpressionException;

import java.util.*;

/**
 * Created by Aleksey on 1/21/2017.
 *
 */
public class SimpleSymbol{

    private ArrayList<RQLInterpreterItemWrapper> values;

    public SimpleSymbol() {
        values = new ArrayList<RQLInterpreterItemWrapper>();
    }

    public void set(RQLInterpreterItemWrapper item) {
        values.add(item);
    }

    public void set(List<RQLInterpreterItemWrapper> items) {
        values.addAll(items);
    }

    public void set(int nodeId, Set<DataEntry<RQLItem>> items) {
        for (DataEntry<RQLItem> de_item : items) {
            RQLInterpreterItemWrapper wrapper = new RQLInterpreterItemWrapper(de_item.getValue(), nodeId);
            values.add(wrapper);
        }
    }

    public void reset() {
        values = new ArrayList<RQLInterpreterItemWrapper>();
    }

    public ArrayList<RQLInterpreterItemWrapper> getValues() {
        return values;
    }

    public RQLInterpreterItemWrapper get(int index) {
        return values.get(index);
    }

    public int size() {
        return values.size();
    }

    public void evaluate() throws IllegalExpressionException {
        //nothing here, this is a simply symbol that holds only values
    }
}
