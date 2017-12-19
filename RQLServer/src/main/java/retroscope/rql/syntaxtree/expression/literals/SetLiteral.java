package retroscope.rql.syntaxtree.expression.literals;


import retroscope.datamodel.datastruct.sets.RQLSet;
import retroscope.datamodel.datastruct.variables.DoubleRQLVariable;
import retroscope.rql.environment.EnvironmentStack;
import retroscope.rql.syntaxtree.expression.Expression;
import retroscope.rql.syntaxtree.expression.ExpressionList;
import retroscope.rql.syntaxtree.expression.IllegalExpressionException;

public class SetLiteral extends Expression
{
	protected ExpressionList expressions;

	public SetLiteral(ExpressionList expList)
	{
		expressions = expList;
	}

	public void evaluate() throws IllegalExpressionException {
		RQLSet set = new RQLSet();
		for (Expression ex1: expressions.getList()) {
			ex1.evaluate();
			set.add(ex1.getValue());
		}
		this.value = set;
	}

	public void setEnvironmentStack(EnvironmentStack stack) {
		this.stack = stack;
		for (Expression ex : expressions.getList()) {
			ex.setEnvironmentStack(stack);
		}
	}

	public SetLiteral clone() {
		return new SetLiteral(expressions);
	}

}
