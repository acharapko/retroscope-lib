package retroscope.rql.functions;

import retroscope.datamodel.datastruct.RQLSymbol;
import retroscope.datamodel.datastruct.sets.RQLSet;
import retroscope.datamodel.datastruct.variables.DoubleRQLVariable;
import retroscope.datamodel.datastruct.variables.LongRQLVariable;
import retroscope.datamodel.datastruct.variables.RQLVariable;
import retroscope.rql.environment.EnvironmentStack;
import retroscope.rql.functions.RQLBuiltInFunction;
import retroscope.rql.syntaxtree.expression.Expression;
import retroscope.rql.syntaxtree.expression.ExpressionList;
import retroscope.rql.syntaxtree.expression.IllegalExpressionException;

import java.util.logging.Logger;

/**
 * Created by Aleksey on 1/21/2017.
 *
 */
public abstract class AbstractPairwiseAggregateMathFunc extends RQLBuiltInFunction {

    protected boolean expandSet = false;

    public AbstractPairwiseAggregateMathFunc(ExpressionList params, EnvironmentStack stack) {
        super(params, stack);
    }

    public void evaluate() throws IllegalExpressionException {
        if (params.getList().size() >= 1) {
            RQLSet finalAggregate = new RQLSet();
            for (int i = 0; i < params.getList().size(); i++) {
                Expression ex = params.getList().get(i);
                ex.evaluate();
                if (ex.getValue() instanceof RQLSet) {
                    finalAggregate.add(getSetAggregate((RQLSet) ex.getValue()));
                } else if ((ex.getValue() instanceof LongRQLVariable) || (ex.getValue() instanceof DoubleRQLVariable)) {
                    finalAggregate.add(ex.getValue());
                }
            }
            this.value = getSetAggregate(finalAggregate);
        }
    }

    protected abstract long twoVarAction(long n1, long n2);
    protected abstract double twoVarAction(long n1, double n2);
    protected abstract double twoVarAction(double n1, double n2);

    protected RQLVariable getSetAggregate(RQLSet set) throws IllegalExpressionException {
        double aggregateDbl = Double.MIN_VALUE;
        long aggregateLng = Long.MIN_VALUE;
        int expandFactor = 1;
        for (RQLSymbol symbol : set.getSet()) {
            if (expandSet && symbol.getNodeIDs().size() != 0) {
                expandFactor = symbol.getNodeIDs().size();
            }
            if (symbol instanceof RQLSet) {
                RQLVariable v = getSetAggregate((RQLSet) symbol);
                if (v instanceof DoubleRQLVariable) {
                    double temp = ((DoubleRQLVariable) v).getValue();
                    if (aggregateDbl != Double.MIN_VALUE) {
                        aggregateDbl = twoVarAction(temp, aggregateDbl);
                    } else {
                        aggregateDbl = temp;
                    }
                } else if (v instanceof LongRQLVariable) {
                    long temp = ((LongRQLVariable) v).getValue();
                    if (aggregateDbl == Double.MIN_VALUE) {
                        if (aggregateLng != Long.MIN_VALUE) {
                            aggregateLng = twoVarAction(temp, aggregateLng);
                        } else {
                            aggregateLng =temp;
                        }
                    } else {
                        aggregateDbl = twoVarAction(temp, aggregateDbl);
                    }
                }
            } else if (symbol instanceof DoubleRQLVariable) {
                if (aggregateLng != Long.MIN_VALUE) {
                    aggregateDbl = aggregateLng;
                    aggregateLng = Long.MIN_VALUE;
                }

                if (aggregateDbl != Double.MIN_VALUE) {
                    aggregateDbl = twoVarAction(((DoubleRQLVariable) symbol).getValue() * expandFactor, aggregateDbl);
                } else {
                    aggregateDbl = ((DoubleRQLVariable) symbol).getValue() * expandFactor;
                }

            } else if (symbol instanceof LongRQLVariable) {
                if (aggregateDbl == Double.MIN_VALUE) {
                    if (aggregateLng != Long.MIN_VALUE) {
                        aggregateLng = twoVarAction(((LongRQLVariable) symbol).getValue() * expandFactor, aggregateLng);
                    } else {
                        aggregateLng = ((LongRQLVariable) symbol).getValue() * expandFactor;
                    }
                } else {
                    aggregateDbl = twoVarAction(((LongRQLVariable) symbol).getValue() * expandFactor, aggregateDbl);
                }
            }
        }

        if (aggregateLng != Long.MIN_VALUE) {
            return new LongRQLVariable(aggregateLng);
        }
        return new DoubleRQLVariable(aggregateDbl);
    }

    public boolean isExpandSet() {
        return expandSet;
    }

    public void setExpandSet(boolean expandSet) {
        this.expandSet = expandSet;
    }
}
