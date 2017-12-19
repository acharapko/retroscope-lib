package retroscope.rql.functions;

import retroscope.datamodel.datastruct.sets.RQLSet;
import retroscope.datamodel.datastruct.variables.LongRQLVariable;
import retroscope.rql.environment.EnvironmentStack;
import retroscope.rql.functions.RQLBuiltInFunction;
import retroscope.rql.syntaxtree.expression.Expression;
import retroscope.rql.syntaxtree.expression.ExpressionList;
import retroscope.rql.syntaxtree.expression.IllegalExpressionException;

public class Cardinality extends RQLBuiltInFunction {

    public Cardinality(ExpressionList params, EnvironmentStack envStack) {
        super(params, envStack);
    }

    public void evaluate() throws IllegalExpressionException {
        if (params.getList().size() == 1) {
            Expression ex= params.getList().get(0);
            ex.evaluate();
            if (ex.getValue() instanceof RQLSet) {
               // System.out.println(((RQLSet) ex.getValue()).size());
                value = new LongRQLVariable(((RQLSet) ex.getValue()).size());
            } else {
                if (ex.getValue() != null) {
                    throw new IllegalExpressionException("Cardinality cannot be applied to " + ex.getValue().getClass().getName());
                }
            }
        } else {
            RQLSet cardinalitySet = new RQLSet();
            for (Expression ex: params.getList()) {
                ex.evaluate();
                if (ex.getValue() instanceof RQLSet) {
                    cardinalitySet.add(new LongRQLVariable(((RQLSet) ex.getValue()).size()));
                } else {
                    throw new IllegalExpressionException("Cardinality cannot be applied to " + ex.getValue().getClass().getName());
                }
            }
            value = cardinalitySet;
        }
    }
}
