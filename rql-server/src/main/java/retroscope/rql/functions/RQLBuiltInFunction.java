package retroscope.rql.functions;

import retroscope.rql.QueryEnvironment;
import retroscope.rql.Valuable;
import retroscope.rql.syntaxtree.expression.ExpressionList;
import retroscope.rql.syntaxtree.expression.RQLInterpreterValue;

/**
 * Created by Aleksey on 1/21/2017.
 *
 */
public abstract class RQLBuiltInFunction implements Valuable {

    protected ExpressionList params;
    protected QueryEnvironment queryEnvironment;
    protected RQLInterpreterValue value;

    public RQLBuiltInFunction(ExpressionList params, QueryEnvironment queryEnvironment) {
        this.params = params;
        this.queryEnvironment = queryEnvironment;
    }

    public RQLInterpreterValue getValue() {
        return value;
    }
}
