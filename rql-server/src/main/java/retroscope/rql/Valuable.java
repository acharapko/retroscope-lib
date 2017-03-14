package retroscope.rql;

import retroscope.rql.syntaxtree.expression.IllegalExpressionException;
import retroscope.rql.syntaxtree.expression.RQLInterpreterValue;

/**
 * Created by Aleksey on 1/21/2017.
 */
public interface Valuable {

    RQLInterpreterValue getValue();

    void evaluate() throws IllegalExpressionException;
}
