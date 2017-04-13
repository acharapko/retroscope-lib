package retroscope.rql.functions;

import retroscope.rql.QueryEnvironment;
import retroscope.rql.Types;
import retroscope.rql.syntaxtree.expression.ExpressionList;
import retroscope.rql.syntaxtree.expression.IllegalExpressionException;
import retroscope.rql.syntaxtree.expression.RQLInterpreterValue;

/**
 * Created by Aleksey on 1/21/2017.
 *
 */
public class Now extends RQLBuiltInFunction {

    public Now(ExpressionList params, QueryEnvironment queryEnvironment) {
        super(params, queryEnvironment);
    }

    public void evaluate() throws IllegalExpressionException {
        value = new RQLInterpreterValue(Types.INT).setValInt(System.currentTimeMillis());
    }
}
