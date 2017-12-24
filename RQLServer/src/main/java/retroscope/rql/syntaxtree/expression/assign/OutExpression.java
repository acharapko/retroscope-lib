package retroscope.rql.syntaxtree.expression.assign;

import retroscope.datamodel.datastruct.variables.LongRQLVariable;
import retroscope.rql.environment.EnvironmentStack;
import retroscope.rql.syntaxtree.expression.Expression;
import retroscope.rql.syntaxtree.expression.IllegalExpressionException;
import retroscope.rql.syntaxtree.expression.literals.Variable;

import java.util.ArrayList;

public class OutExpression extends Expression {

    private Expression ex1;
    private boolean global = false;

    public OutExpression(Expression expression) {
        this.ex1 = expression;
        dirty = true;
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

    @Override
    public boolean computeDirty() {
        return dirty;
    }

    @Override
    public ArrayList<Variable> findVars() {
        return new ArrayList<>();
    }
}
