package retroscope.rql.syntaxtree.expression.stackedexpression;

import retroscope.datamodel.datastruct.RQLSymbol;
import retroscope.datamodel.datastruct.variables.LongRQLVariable;
import retroscope.rql.environment.Environment;
import retroscope.rql.syntaxtree.IdentifierList;
import retroscope.rql.syntaxtree.expression.Expression;

public class Exists extends SetIteratingExpression {

    public Exists(IdentifierList ids, Expression ex1, Expression ex2) {
        super(ids, ex1, ex2);
    }

    public Exists(IteratingFunc aggregate) {
        this(aggregate.getIds(), aggregate.getEx1(), aggregate.getEx2());
    }

    /**
     *
     * @param exVal the result of an expression. Needs to be a predicate
     * @return whether the check loop needs to be short-circuted
     */

    @Override
    protected boolean checkExpressionVal(RQLSymbol exVal) {
        if (exVal instanceof LongRQLVariable) {
            if (((LongRQLVariable) exVal).getValue() != 0) {
                this.value = new LongRQLVariable(1);
                return true;
            }
        }
        return false;
    }

    @Override
    public Exists clone() {
        return new Exists(getIds(), getEx1().clone(), getEx2().clone());
    }
}
