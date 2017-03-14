package retroscope.rql.functions;

import retroscope.rql.RQLEnvironment;
import retroscope.rql.Types;
import retroscope.rql.syntaxtree.expression.ExpressionList;
import retroscope.rql.syntaxtree.expression.IllegalExpressionException;
import retroscope.rql.syntaxtree.expression.RQLInterpreterValue;
import retroscope.rql.syntaxtree.expression.Variable;
import retroscope.rql.syntaxtree.link.Link;

import java.util.List;

/**
 * Created by Aleksey on 1/21/2017.
 *
 */
public class Node extends RQLBuiltInFunction {

    public Node(ExpressionList params, RQLEnvironment rqlEnvironment) {
        super(params, rqlEnvironment);
    }

    public void evaluate() throws IllegalExpressionException {
        value = null;
        if (params.getList().size() == 1) {
            if (params.getList().get(0) instanceof Variable) {
                params.getList().get(0).evaluate();
                RQLInterpreterValue p1Vals = params.getList().get(0).getValue();
            }
        }
        if (value == null) {
            value = new RQLInterpreterValue(Types.NULL);
        }
    }
}
