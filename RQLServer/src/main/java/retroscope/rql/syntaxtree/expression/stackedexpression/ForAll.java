package retroscope.rql.syntaxtree.expression.stackedexpression;

import retroscope.datamodel.datastruct.RQLSymbol;
import retroscope.datamodel.datastruct.variables.LongRQLVariable;
import retroscope.rql.syntaxtree.IdentifierList;
import retroscope.rql.syntaxtree.expression.Expression;
import retroscope.rql.syntaxtree.expression.IllegalExpressionException;

public class ForAll extends SetIteratingExpression {

    private long nCompletionsDone;

    public ForAll(IdentifierList ids, Expression ex1, Expression ex2) {
        super(ids, ex1, ex2);
    }

    public ForAll(IteratingFunc aggregate) {
        this(aggregate.getIds(), aggregate.getEx1(), aggregate.getEx2());
    }

    @Override
    public void evaluate() throws IllegalExpressionException {
        nCompletionsDone = 0;
        super.evaluate();
    }

    @Override
    protected boolean checkExpressionVal(RQLSymbol exVal) {
        if (exVal instanceof LongRQLVariable) {
            if (((LongRQLVariable) exVal).getValue() == 1) {
                if (++nCompletionsDone == getMaxCombinations()) {
                    this.value = new LongRQLVariable(1);
                }
            } else {
                return true; //we have a false, so FORALL does not hold, we can short-circuit
            }
        }
        return false;
    }

    @Override
    public ForAll clone() {
        return new ForAll(getIds(), getEx1().clone(), getEx2().clone());
    }
}
