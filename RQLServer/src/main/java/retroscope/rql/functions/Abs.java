package retroscope.rql.functions;

import retroscope.datamodel.datastruct.RQLSymbol;
import retroscope.datamodel.datastruct.variables.DoubleRQLVariable;
import retroscope.datamodel.datastruct.variables.LongRQLVariable;
import retroscope.rql.environment.EnvironmentStack;
import retroscope.rql.syntaxtree.expression.ExpressionList;
/**
 * Created by Aleksey on 1/21/2017.
 *
 */
public class Abs extends AbstractMathFunc {

    public Abs(ExpressionList params, EnvironmentStack stack) {
        super(params, stack);
    }

    @Override
    protected RQLSymbol longVarOperation(long val) {
        return new LongRQLVariable(Math.abs(val));
    }

    @Override
    protected RQLSymbol doubleVarOperation(double val) {
        return new DoubleRQLVariable(Math.abs(val));
    }
}
