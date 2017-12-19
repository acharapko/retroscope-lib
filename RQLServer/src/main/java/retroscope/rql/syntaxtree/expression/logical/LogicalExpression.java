package retroscope.rql.syntaxtree.expression.logical;


import retroscope.datamodel.datastruct.RQLSymbol;
import retroscope.datamodel.datastruct.variables.LongRQLVariable;
import retroscope.datamodel.datastruct.variables.RQLVariable;
import retroscope.rql.syntaxtree.expression.Expression;
import retroscope.rql.syntaxtree.expression.IllegalExpressionException;
import retroscope.rql.syntaxtree.expression.TwoOperandExpression;

public abstract class LogicalExpression extends TwoOperandExpression
{
	public LogicalExpression(Expression ex1, Expression ex2)
	{ 
		super(ex1, ex2);
	}

	public void evaluate() throws IllegalExpressionException {
		/*ex1.evaluate();
		RQLSymbol ex1Val = ex1.getValue();
		Number n1 = null;
		if (ex1Val instanceof RQLVariable) {
			if (((RQLVariable) ex1Val).getValue() instanceof Number) {
				n1 = (Number) ((RQLVariable) ex1Val).getValue();
			}
			boolean shortCircuit
		}
		ex2.evaluate();
		RQLSymbol ex2Val = ex2.getValue();

		if (ex1Val instanceof RQLVariable && ex2Val instanceof RQLVariable) {
			Number n1 = null;
			Number n2 = null;
			boolean cmp = false;
			if (((RQLVariable) ex1Val).getValue() instanceof Number) {
				n1 = (Number) ((RQLVariable) ex1Val).getValue();
			}
			if (((RQLVariable) ex2Val).getValue() instanceof Number) {
				n2 = (Number) ((RQLVariable) ex2Val).getValue();
			}
			if (n1 != null && n2 != null) {
				cmp = logicalAction(n1, n2);
			} else {
                throw new IllegalExpressionException("Cannot apply logical operators String values");
			}

			value = new LongRQLVariable(cmp ? 1 : 0);
		} else {
			throw new IllegalExpressionException("Cannot apply logical operators to " + ex1Val.getClass().getName()
					+ " and " + ex2Val.getClass().getName());
		}*/
	}

	protected abstract boolean logicalAction(Number n1, Number n2);

	protected abstract boolean op1ShortCircuit(Number n1);

}
