package retroscope.rql.syntaxtree.expression.stackedexpression;

import retroscope.datamodel.datastruct.RQLSymbol;
import retroscope.datamodel.datastruct.variables.LongRQLVariable;
import retroscope.rql.syntaxtree.IdentifierList;
import retroscope.rql.syntaxtree.expression.Expression;
import retroscope.rql.syntaxtree.expression.IllegalExpressionException;

public class ForSome extends SetIteratingExpression {

    protected long nCompletions;
    private long nCompletionsDone;
    private Expression exCompletions;

    public ForSome(IdentifierList ids, Expression ex1, Expression ex2, Expression ex3) {
        super(ids, ex1, ex2);
        exCompletions = ex3;
    }

    public ForSome(IdentifierList ids, Expression ex1, Expression ex2, long nCompletions) {
        super(ids, ex1, ex2);
        this.nCompletions = nCompletions;
    }

    @Override
    public void evaluate() throws IllegalExpressionException {
        nCompletionsDone = 0;
        if (exCompletions != null) {
            exCompletions.evaluate();
            if (exCompletions.getValue() instanceof LongRQLVariable) {
                nCompletions = ((LongRQLVariable) exCompletions.getValue()).getValue();
            } else {
                throw new IllegalExpressionException("Number of completions must be an integer");
            }
        }
        super.evaluate();
    }

    @Override
    protected boolean checkExpressionVal(RQLSymbol exVal) {
        if (exVal instanceof LongRQLVariable) {
            if (((LongRQLVariable) exVal).getValue() == 1) {
                if (++nCompletionsDone >= nCompletions) {
                    this.value = new LongRQLVariable(1);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public ForSome clone() {
        if (exCompletions != null) {
            return new ForSome(getIds(), getEx1(), getEx2(), exCompletions);
        } else {
            return new ForSome(getIds(), getEx1(), getEx2(), nCompletions);
        }
    }
}
