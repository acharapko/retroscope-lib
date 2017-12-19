package retroscope.rql.functions;

import retroscope.datamodel.datastruct.RQLSymbol;
import retroscope.datamodel.datastruct.sets.RQLSet;
import retroscope.datamodel.datastruct.variables.RQLVariable;
import retroscope.rql.environment.EnvironmentStack;
import retroscope.rql.functions.RQLBuiltInFunction;
import retroscope.rql.syntaxtree.expression.Expression;
import retroscope.rql.syntaxtree.expression.ExpressionList;
import retroscope.rql.syntaxtree.expression.IllegalExpressionException;

public class Subset extends RQLBuiltInFunction {

    public Subset(ExpressionList params, EnvironmentStack envStack) {
        super(params, envStack);
    }

    public void evaluate() throws IllegalExpressionException {
        if (params.getList().size() == 1) {
            Expression ex= params.getList().get(0);
            ex.evaluate();
            RQLSymbol symb = ex.getValue();
            if (symb instanceof RQLSet) {
                value = new RQLSet();
                computeSubset((RQLSet) symb);
            } else {
                throw new IllegalExpressionException("Subset cannot be applied to " + ex.getValue().getClass().getName());
            }
        } else {
            throw new IllegalExpressionException("Subset requires a single argument of type RQLSet");
        }
    }

    private void computeSubset(RQLSet set) throws IllegalExpressionException {

    }
}
