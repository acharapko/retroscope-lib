package retroscope.rql.functions;

import retroscope.datamodel.datastruct.RQLSymbol;
import retroscope.datamodel.datastruct.sets.RQLSet;
import retroscope.datamodel.datastruct.struct.RQLStruct;
import retroscope.datamodel.datastruct.variables.LongRQLVariable;
import retroscope.datamodel.datastruct.variables.RQLVariable;
import retroscope.rql.environment.EnvironmentStack;
import retroscope.rql.functions.RQLBuiltInFunction;
import retroscope.rql.syntaxtree.expression.Expression;
import retroscope.rql.syntaxtree.expression.ExpressionList;
import retroscope.rql.syntaxtree.expression.IllegalExpressionException;

public class Flatten extends RQLBuiltInFunction {

    public Flatten(ExpressionList params, EnvironmentStack envStack) {
        super(params, envStack);
    }

    public void evaluate() throws IllegalExpressionException {
        value = new RQLSet();
        if (params.getList().size() == 1) {
            Expression ex= params.getList().get(0);
            ex.evaluate();
            RQLSymbol symb = ex.getValue();
            if (symb instanceof RQLSet) {
                recursiveFlatten((RQLSet) symb);
            } else {
                throw new IllegalExpressionException("Flatten cannot be applied to " + ex.getValue().getClass().getName());
            }
        } else {
            for (Expression ex: params.getList()) {
                ex.evaluate();
                RQLSymbol symb = ex.getValue();
                if (symb instanceof RQLSet) {
                    recursiveFlatten((RQLSet) symb);
                } else if (symb instanceof RQLVariable) {
                    ((RQLSet) value).add(symb);
                } else {
                    throw new IllegalExpressionException("Flatten cannot be applied to " + ex.getValue().getClass().getName());
                }
            }
        }
        //System.out.println("Flatten");
    }

    private void recursiveFlatten(RQLSet set) throws IllegalExpressionException {
        for (RQLSymbol s: set.getSet()) {
            if (s instanceof RQLVariable || s instanceof RQLStruct) {
                ((RQLSet) value).add(s);
            } else if (s instanceof RQLSet) {
                recursiveFlatten((RQLSet) s);
            } else {
                throw new IllegalExpressionException("Flatten cannot be applied to " + s.getClass().getName());
            }
        }
    }
}
