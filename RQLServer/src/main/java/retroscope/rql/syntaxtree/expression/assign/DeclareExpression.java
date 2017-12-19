package retroscope.rql.syntaxtree.expression.assign;

import retroscope.datamodel.datastruct.Null;
import retroscope.datamodel.datastruct.variables.LongRQLVariable;
import retroscope.rql.syntaxtree.expression.Expression;
import retroscope.rql.syntaxtree.expression.IllegalExpressionException;
import retroscope.rql.syntaxtree.expression.literals.Variable;

public class DeclareExpression extends Expression {

    private Variable identifier;
    private boolean global = false;

    public DeclareExpression(Variable identifier, boolean global) {
        this.identifier = identifier;
        this.global = global;
    }

    @Override
    public void evaluate() throws IllegalExpressionException {
        if (!global) {
            if (getEnvironment().getSymbolInThisEnv(identifier.getName()) == null) {
                getEnvironment().putSymbol(identifier.getName(), new Null());
            }
        } else {
            if (getEnvironment().getGlobal().getSymbol(identifier.getName()) == null) {
                getEnvironment().getGlobal().putSymbol(identifier.getName(), new Null());
            }
        }

        this.value = new LongRQLVariable(1);
    }

    @Override
    public Expression clone() {
        return new DeclareExpression(identifier.clone(), global);
    }
}
