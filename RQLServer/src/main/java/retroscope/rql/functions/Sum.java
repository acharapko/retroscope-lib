package retroscope.rql.functions;

import retroscope.rql.environment.EnvironmentStack;
import retroscope.rql.syntaxtree.expression.ExpressionList;

/**
 * Created by Aleksey on 1/21/2017.
 *
 */
public class Sum extends AbstractPairwiseAggregateMathFunc {

    public Sum(ExpressionList params, EnvironmentStack stack) {
        super(params, stack);
    }

    @Override
    protected long twoVarAction(long n1, long n2) {
        return n1 + n2;
    }

    @Override
    protected double twoVarAction(long n1, double n2) {
        return n1 + n2;
    }

    @Override
    protected double twoVarAction(double n1, double n2) {
        return n1 + n2;
    }
}
