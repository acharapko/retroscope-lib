package retroscope.rql.functions;

import retroscope.datamodel.datastruct.sets.RQLSet;
import retroscope.datamodel.datastruct.variables.LongRQLVariable;
import retroscope.rql.environment.EnvironmentStack;
import retroscope.rql.syntaxtree.expression.ExpressionList;
import retroscope.rql.syntaxtree.expression.IllegalExpressionException;

import java.util.Set;

/**
 * Created by Aleksey on 1/21/2017.
 *
 */
public class Node extends RQLBuiltInFunction {

    public Node(ExpressionList params, EnvironmentStack stack) {
        super(params, stack);
    }

    public void evaluate() throws IllegalExpressionException {
        value = null;
        if (params.getList().size() == 1) {
            Set<Long> nodeIDs = params.getList().get(0).getValue().getNodeIDs();
            RQLSet returnSet = new RQLSet();
            for (long id: nodeIDs) {
                returnSet.add(new LongRQLVariable(id));
            }
            this.value = returnSet;
        } else {
            throw new IllegalExpressionException("Node function is undefined for many arguments");
        }
    }
}
