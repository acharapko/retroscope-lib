package retroscope.rql.syntaxtree.expression.stackedexpression;

import retroscope.datamodel.datastruct.RQLSymbol;
import retroscope.datamodel.datastruct.variables.LongRQLVariable;
import retroscope.rql.syntaxtree.IdentifierList;
import retroscope.rql.syntaxtree.expression.Expression;
import retroscope.rql.syntaxtree.expression.IllegalExpressionException;

public class ForEach extends SetIteratingExpression {

    public ForEach(IdentifierList ids, Expression ex1, Expression ex2) {
        super(ids, ex1, ex2);
    }

    public ForEach(IteratingFunc aggregate) {
        this(aggregate.getIds(), aggregate.getEx1(), aggregate.getEx2());
    }

    @Override
    public void evaluate() throws IllegalExpressionException {
        super.evaluate();
        this.value = new LongRQLVariable(1);
    }

    @Override
    protected boolean checkExpressionVal(RQLSymbol exVal) {
        return false;
    }

    @Override
    public ForEach clone() {
        return new ForEach(getIds(), getEx1().clone(), getEx2().clone());
    }
}
