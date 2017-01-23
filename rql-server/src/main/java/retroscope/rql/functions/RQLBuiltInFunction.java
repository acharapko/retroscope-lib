package retroscope.rql.functions;

import retroscope.rql.RQLEnvironment;
import retroscope.rql.Valuable;
import retroscope.rql.syntaxtree.ExpressionList;
import retroscope.rql.syntaxtree.RQLInterpreterValue;

import java.util.ArrayList;

/**
 * Created by Aleksey on 1/21/2017.
 *
 */
public abstract class RQLBuiltInFunction implements Valuable {

    protected ExpressionList params;
    protected RQLEnvironment rqlEnvironment;
    protected ArrayList<RQLInterpreterValue> evaluatedVals;

    public RQLBuiltInFunction(ExpressionList params, RQLEnvironment rqlEnvironment) {
        this.params = params;
        this.rqlEnvironment = rqlEnvironment;
    }

}
