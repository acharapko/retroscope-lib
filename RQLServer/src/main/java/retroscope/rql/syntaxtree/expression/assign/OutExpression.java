package retroscope.rql.syntaxtree.expression.assign;

import retroscope.datamodel.datastruct.variables.LongRQLVariable;
import retroscope.rql.environment.EnvironmentStack;
import retroscope.rql.syntaxtree.expression.Expression;
import retroscope.rql.syntaxtree.expression.IllegalExpressionException;

public class OutExpression extends Expression {

    private Expression ex1;
    private boolean global = false;

    public OutExpression(Expression expression) {
        this.ex1 = expression;
    }

    @Override
    public void evaluate() throws IllegalExpressionException {
        ex1.evaluate();
        getEnvironment().setSymbol("$out", ex1.getValue());

        this.value = new LongRQLVariable(1);
    }

    public void setEnvironmentStack(EnvironmentStack stack) {
        this.stack = stack;
        ex1.setEnvironmentStack(stack);
    }

    @Override
    public Expression clone() {
        return new OutExpression(ex1.clone());
    }
}
