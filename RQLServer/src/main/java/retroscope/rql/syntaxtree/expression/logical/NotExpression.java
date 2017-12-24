package retroscope.rql.syntaxtree.expression.logical;

import retroscope.datamodel.datastruct.RQLSymbol;
import retroscope.datamodel.datastruct.variables.LongRQLVariable;
import retroscope.datamodel.datastruct.variables.RQLVariable;
import retroscope.rql.environment.EnvironmentStack;
import retroscope.rql.syntaxtree.expression.Expression;
import retroscope.rql.syntaxtree.expression.IllegalExpressionException;
import retroscope.rql.syntaxtree.expression.literals.Variable;

import java.util.ArrayList;

public class NotExpression extends Expression
{
	private Expression ex1;
	public NotExpression(Expression ex1)
	{
		this.ex1 = ex1;
	}
	
			
	public void evaluate() throws IllegalExpressionException {
	    if (dirty) {
            ex1.evaluate();

            RQLSymbol ex1Val = ex1.getValue();

            if (ex1Val instanceof RQLVariable) {
                Number n1 = null;
                boolean cmp = false;
                if (((RQLVariable) ex1Val).getValue() instanceof Number) {
                    n1 = (Number) ((RQLVariable) ex1Val).getValue();
                }
                if (n1 != null) {
                    cmp = n1.intValue() == 0;
                } else {
                    throw new IllegalExpressionException("Cannot apply logical NOT to String value");
                }

                value = new LongRQLVariable(cmp ? 1 : 0);
            } else {
                throw new IllegalExpressionException("Cannot apply logical NOT to " + ex1Val.getClass().getName());
            }
        }
	}

	public NotExpression clone() {
		return new NotExpression(this.ex1.clone());
	}

	@Override
	public boolean computeDirty() {
		dirty = ex1.computeDirty();
		return dirty;
	}

	@Override
	public ArrayList<Variable> findVars() {
		ArrayList<Variable> v = new ArrayList<>();
		v.addAll(ex1.findVars());
		return v;
	}

    public void setEnvironmentStack(EnvironmentStack stack) {
        this.stack = stack;
        ex1.setEnvironmentStack(stack);
    }
}
