package retroscope.rql.syntaxtree.expression.logical;

import retroscope.datamodel.datastruct.RQLSymbol;
import retroscope.datamodel.datastruct.variables.LongRQLVariable;
import retroscope.datamodel.datastruct.variables.RQLVariable;
import retroscope.rql.syntaxtree.expression.Expression;
import retroscope.rql.syntaxtree.expression.IllegalExpressionException;
import retroscope.rql.syntaxtree.expression.TwoOperandExpression;

public class Or extends TwoOperandExpression
{

	public Or(Expression ex1, Expression ex2)
	{
		super(ex1, ex2);
	}

	@Override
	public void evaluate() throws IllegalExpressionException {
		ex1.evaluate();
		RQLSymbol ex1Val = ex1.getValue();
		Number n1 = null;
		if (ex1Val instanceof RQLVariable) {
			if (((RQLVariable) ex1Val).getValue() instanceof Number) {
				n1 = (Number) ((RQLVariable) ex1Val).getValue();
			}
			boolean shortCircuit = false;
			if (n1 != null) {
				shortCircuit = (n1.intValue() != 0);
			} else {
				throw new IllegalExpressionException("Cannot apply logical operators to " + ex1Val.getClass().getName());
			}

			if (shortCircuit) {
				value = new LongRQLVariable(1);
			} else {
				ex2.evaluate();
				RQLSymbol ex2Val = ex2.getValue();
				n1 = null;
				if (((RQLVariable) ex2Val).getValue() instanceof Number) {
					n1 = (Number) ((RQLVariable) ex2Val).getValue();
				}
				if (n1 != null) {
					shortCircuit = (n1.intValue() != 0);
				} else {
					throw new IllegalExpressionException("Cannot apply logical operators to " + ex1Val.getClass().getName()
							+ " and " + ex2Val.getClass().getName());
				}

				value = new LongRQLVariable(shortCircuit ? 1 : 0);
			}
		}
	}

	public Or clone() {
		return new Or(ex1.clone(), ex2.clone());
	}
}
