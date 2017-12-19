package retroscope.rql.syntaxtree.expression.sets;

import retroscope.datamodel.datastruct.RQLSymbol;
import retroscope.datamodel.datastruct.sets.RQLSet;
import retroscope.rql.syntaxtree.expression.Expression;
import retroscope.rql.syntaxtree.expression.IllegalExpressionException;
import retroscope.rql.syntaxtree.expression.TwoOperandExpression;

public abstract class SymbolAndSetOperator extends TwoOperandExpression {

    public SymbolAndSetOperator(Expression ex1, Expression ex2) {
        super(ex1, ex2);
    }

    @Override
    public void evaluate() throws IllegalExpressionException {
        evaluateOperands();
        RQLSymbol ex1Val = ex1.getValue();
        RQLSymbol ex2Val = ex2.getValue();

        if (ex2Val instanceof RQLSet) {
            this.value = symbolAndSetOperation(ex1Val, (RQLSet)ex2Val);
        } else {
            throw new IllegalExpressionException("Cannot use set operators with " + ex1Val.getClass().getName()
                    + " and " + ex2Val.getClass().getName());
        }
    }

    protected abstract RQLSymbol symbolAndSetOperation(RQLSymbol s1, RQLSet s2) throws IllegalExpressionException;

}
