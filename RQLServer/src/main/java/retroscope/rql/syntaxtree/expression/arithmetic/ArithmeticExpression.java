package retroscope.rql.syntaxtree.expression.arithmetic;


import retroscope.datamodel.datastruct.RQLSymbol;
import retroscope.datamodel.datastruct.variables.LongRQLVariable;
import retroscope.datamodel.datastruct.variables.RQLVariable;
import retroscope.rql.syntaxtree.expression.Expression;
import retroscope.rql.syntaxtree.expression.IllegalExpressionException;
import retroscope.rql.syntaxtree.expression.TwoOperandExpression;

public abstract class ArithmeticExpression extends TwoOperandExpression
{
	public ArithmeticExpression(Expression ex1, Expression ex2)
	{ 
		super(ex1, ex2);
	}

	public void evaluate() throws IllegalExpressionException {
		evaluateOperands();
		RQLSymbol ex1Val = ex1.getValue();
		RQLSymbol ex2Val = ex2.getValue();

		if (ex1Val instanceof RQLVariable && ex2Val instanceof RQLVariable) {
			Long l1 = null;
			Long l2 = null;
            Double d1 = null;
            Double d2 = null;
			RQLVariable opResult;
			if (((RQLVariable) ex1Val).getValue() instanceof Long) {
				l1 = (Long) ((RQLVariable) ex1Val).getValue();
			}
			if (((RQLVariable) ex2Val).getValue() instanceof Long) {
				l2 = (Long) ((RQLVariable) ex2Val).getValue();
			}
			if (((RQLVariable) ex1Val).getValue() instanceof Double) {
				d1 = (Double) ((RQLVariable) ex1Val).getValue();
			}
			if (((RQLVariable) ex2Val).getValue() instanceof Double) {
				d2 = (Double) ((RQLVariable) ex2Val).getValue();
			}

			if (l1 != null && l2 != null) {
				opResult = arithmeticOperation(l1, l2);
			} else if (d1 != null && d2 != null) {
			    opResult = arithmeticOperation(d1, d2);
            } else if (l1 != null && d2 != null) {
			    opResult = arithmeticOperation(l1, d2);
            } else if (d1 != null && l2 != null) {
			    opResult = arithmeticOperation(d1, l2);
            } else {
                throw new IllegalExpressionException("Cannot do math with strings!");
			}

			if (opResult != null) {
				value = opResult;
			} else {
				throw new IllegalExpressionException("Something went wrong while computing stuff");
			}

		} else {
			throw new IllegalExpressionException("Cannot use arithmetic operators with " + ex1Val.getClass().getName()
					+ " and " + ex2Val.getClass().getName());
		}
	}

	protected abstract RQLVariable arithmeticOperation(long n1, long n2);

	protected abstract RQLVariable arithmeticOperation(long n1, double n2);

	protected abstract RQLVariable arithmeticOperation(double n1, long n2);

	protected abstract RQLVariable arithmeticOperation(double n1, double n2);

}
