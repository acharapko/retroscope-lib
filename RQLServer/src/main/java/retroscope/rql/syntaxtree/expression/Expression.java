package retroscope.rql.syntaxtree.expression;

import retroscope.datamodel.datastruct.Null;
import retroscope.datamodel.datastruct.RQLSymbol;
import retroscope.rql.environment.Environment;
import retroscope.rql.environment.EnvironmentStack;
import retroscope.rql.parser.Valuable;
import retroscope.rql.syntaxtree.expression.literals.Variable;

import java.util.ArrayList;

/**
 * Created by Aleksey on 12/20/2016.
 *
 */
public abstract class Expression implements Valuable, Cloneable{

    //private Variable valID = null;
    protected RQLSymbol value;
    protected EnvironmentStack stack;
    protected boolean dirty = true;

    public Expression() {}

    public RQLSymbol getValue() {
        if (value == null) {
            value = new Null();
        }
        return value;
    }

    public Environment getEnvironment() {
        return stack.peek();
    }

    public EnvironmentStack getEnvironmentStack() {
        return stack;
    }

    public void setEnvironmentStack(EnvironmentStack stack) {
        this.stack = stack;
    }

    public abstract void evaluate() throws IllegalExpressionException;

    public abstract Expression clone();

    public boolean isDirty() {
        return dirty;
    }

    public abstract boolean computeDirty();

    public abstract ArrayList<Variable> findVars();

}
