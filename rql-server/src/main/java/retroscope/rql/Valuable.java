package retroscope.rql;

import retroscope.rql.syntaxtree.IllegalExpressionException;
import retroscope.rql.syntaxtree.RQLInterpreterValue;

/**
 * Created by Aleksey on 1/21/2017.
 */
public interface Valuable {

    public abstract RQLInterpreterValue[] getValues();

    public abstract void evaluate() throws IllegalExpressionException;
}
