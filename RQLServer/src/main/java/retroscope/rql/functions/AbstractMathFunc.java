package retroscope.rql.functions;

import retroscope.datamodel.datastruct.RQLSymbol;
import retroscope.datamodel.datastruct.sets.RQLSet;
import retroscope.datamodel.datastruct.variables.DoubleRQLVariable;
import retroscope.datamodel.datastruct.variables.LongRQLVariable;
import retroscope.rql.environment.EnvironmentStack;
import retroscope.rql.functions.RQLBuiltInFunction;
import retroscope.rql.syntaxtree.expression.Expression;
import retroscope.rql.syntaxtree.expression.ExpressionList;
import retroscope.rql.syntaxtree.expression.IllegalExpressionException;

/**
 * Created by Aleksey on 1/21/2017.
 *
 */
public abstract class AbstractMathFunc extends RQLBuiltInFunction {

    public AbstractMathFunc(ExpressionList params, EnvironmentStack stack) {
        super(params, stack);
    }

    public void evaluate() throws IllegalExpressionException {
        if (params.getList().size() == 1){
            Expression ex = params.getList().get(0);
            ex.evaluate();
            if (ex.getValue() instanceof LongRQLVariable) {
                this.value = longVarOperation(((LongRQLVariable) ex.getValue()).getValue());
            } else if (ex.getValue() instanceof DoubleRQLVariable) {
                this.value = doubleVarOperation(((DoubleRQLVariable) ex.getValue()).getValue());
            } else if (ex.getValue() instanceof RQLSet) {
                this.value = setProc((RQLSet) ex.getValue());
            } else {
                throw new IllegalExpressionException("Function Abs is not defined for this type (String?)");
            }
        }
    }

    protected abstract RQLSymbol longVarOperation(long val);

    protected abstract RQLSymbol doubleVarOperation(double val);


    private RQLSet setProc(RQLSet set) throws IllegalExpressionException {
        RQLSet retSet = new RQLSet();
        for (RQLSymbol s: set.getSet()) {
            if (s instanceof LongRQLVariable) {
                retSet.add(longVarOperation(((LongRQLVariable) s).getValue()));
            } else if (s instanceof DoubleRQLVariable) {
                retSet.add(doubleVarOperation(((DoubleRQLVariable) s).getValue()));
            } else if (s instanceof RQLSet) {
                retSet.add(setProc((RQLSet)s));
            } else {
                throw new IllegalExpressionException("Function Abs is not defined for this type (String?)");
            }
        }
        return retSet;
    }
}
