package retroscope.rql.functions;

import retroscope.rql.QueryEnvironment;
import retroscope.rql.Types;
import retroscope.rql.syntaxtree.expression.ExpressionList;
import retroscope.rql.syntaxtree.expression.IllegalExpressionException;
import retroscope.rql.syntaxtree.expression.RQLInterpreterValue;
import retroscope.rql.syntaxtree.expression.Variable;

/**
 * Created by Aleksey on 1/21/2017.
 *
 */
public class Node extends RQLBuiltInFunction {

    public Node(ExpressionList params, QueryEnvironment queryEnvironment) {
        super(params, queryEnvironment);
    }

    public void evaluate() throws IllegalExpressionException {
        value = null;
        if (params.getList().size() == 1) {
            if (params.getList().get(0) instanceof Variable) {
                params.getList().get(0).evaluate();
                RQLInterpreterValue p1Vals = params.getList().get(0).getValue();
                value = new RQLInterpreterValue(Types.INT).setValInt(p1Vals.getSourceNodeId());
            }
        }
        if (value == null) {
            value = new RQLInterpreterValue(Types.NULL);
        }
    }
}
