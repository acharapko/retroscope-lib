package retroscope.rql.syntaxtree.expression;

import retroscope.rql.environment.EnvironmentStack;
import retroscope.rql.syntaxtree.expression.literals.Variable;

import java.util.ArrayList;

public abstract class TwoOperandExpression extends Expression
{
	protected Expression ex1, ex2;

	public TwoOperandExpression(Expression ex1, Expression ex2)
	{
		this.ex1 = ex1;
		this.ex2 = ex2;
	}

	public Expression getEx1() {
		return ex1;
	}

	public Expression getEx2() {
		return ex2;
	}

	public void setEnvironmentStack(EnvironmentStack stack) {
		this.stack = stack;
		ex1.setEnvironmentStack(stack);
		ex2.setEnvironmentStack(stack);
	}

	protected void evaluateOperands() throws IllegalExpressionException {
		if (dirty) {
			ex1.evaluate();
			ex2.evaluate();
		}
	}

	@Override
	public boolean computeDirty()
	{
		dirty = ex1.computeDirty() || ex2.computeDirty();
		return dirty;
	}

	@Override
	public ArrayList<Variable> findVars() {
		ArrayList<Variable> v = new ArrayList<>();
		v.addAll(ex1.findVars());
		v.addAll(ex2.findVars());
		return v;
	}
}
