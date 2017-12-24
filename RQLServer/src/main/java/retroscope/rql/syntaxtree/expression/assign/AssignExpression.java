package retroscope.rql.syntaxtree.expression.assign;

import retroscope.datamodel.datastruct.RQLSymbol;
import retroscope.datamodel.datastruct.variables.LongRQLVariable;
import retroscope.rql.environment.EnvironmentStack;
import retroscope.rql.syntaxtree.expression.Expression;
import retroscope.rql.syntaxtree.expression.IllegalExpressionException;
import retroscope.rql.syntaxtree.expression.literals.Variable;

import java.util.ArrayList;

public class AssignExpression extends Expression {

    private Variable identifier;
    private Expression val;

    public AssignExpression(Variable identifier, Expression val) {
        this.identifier = identifier;
        this.val = val;
        dirty = true;
    }

    @Override
    public void evaluate() throws IllegalExpressionException {
        val.evaluate();
        RQLSymbol s = val.getValue();
        s.setName(identifier.getName());
        getEnvironment().assignToSymbol(identifier.getName(), s);
        this.value = new LongRQLVariable(1);
    }

    public void setEnvironmentStack(EnvironmentStack stack) {
        this.stack = stack;
        val.setEnvironmentStack(stack);
    }

    @Override
    public Expression clone() {
        return new AssignExpression(identifier.clone(), val.clone());
    }

    @Override
    public boolean computeDirty() {
        return dirty;
    }

    @Override
    public ArrayList<Variable> findVars() {
        return val.findVars();
    }
}
