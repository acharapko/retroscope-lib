package retroscope.rql.syntaxtree.expression.sets;

import retroscope.datamodel.datastruct.Null;
import retroscope.datamodel.datastruct.RQLSymbol;
import retroscope.datamodel.datastruct.sets.RQLSet;
import retroscope.datamodel.datastruct.variables.RQLVariable;
import retroscope.rql.syntaxtree.expression.Expression;
import retroscope.rql.syntaxtree.expression.IllegalExpressionException;
import retroscope.rql.syntaxtree.expression.TwoOperandExpression;
import retroscope.rql.syntaxtree.expression.literals.Variable;

public abstract class SetOperator extends TwoOperandExpression {

    public SetOperator(Expression ex1, Expression ex2) {
        super(ex1, ex2);
    }

    @Override
    public void evaluate() throws IllegalExpressionException {
        evaluateOperands();
        RQLSymbol ex1Val = ex1.getValue();
        RQLSymbol ex2Val = ex2.getValue();

        if (ex1Val instanceof Null) {
            ex1Val = resolveNull(ex1Val, ex1);
        }

        if (ex2Val instanceof Null) {
            ex2Val = resolveNull(ex2Val, ex2);
        }

        if (ex1Val instanceof RQLSet && ex2Val instanceof RQLSet) {
            this.value = setOperation((RQLSet)ex1Val, (RQLSet)ex2Val);
        } else {
            throw new IllegalExpressionException("Cannot use set operators with " + ex1Val.getClass().getName()
                    + " and " + ex2Val.getClass().getName());
        }
    }

    private RQLSymbol resolveNull(RQLSymbol exVal, Expression ex) {
        RQLSymbol retSym = exVal;
        if (ex instanceof Variable) {
            retSym = new RQLSet();
            getEnvironment().assignToSymbol(((Variable) ex).getName(), retSym);
        }
        return retSym;
    }

    protected abstract RQLSymbol setOperation(RQLSet s1, RQLSet s2) throws IllegalExpressionException;

}
