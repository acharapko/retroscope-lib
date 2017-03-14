package retroscope.rql.syntaxtree.expression;

import retroscope.rql.RQLEnvironment;
import retroscope.rql.Types;

public class NotExpression extends Expression
{
	private Expression ex1;
	public NotExpression(RQLEnvironment rqlEnvironment, Expression ex1)
	{
		super(rqlEnvironment);
		this.ex1 = ex1;
	}
	
			
	public void evaluate() throws IllegalExpressionException {
		ex1.evaluate();

		RQLInterpreterValue ex1Val = ex1.getValue();
		RQLInterpreterValue expressionValue = new RQLInterpreterValue(Types.INT);

		if (ex1Val.getType() == Types.INT) {
			if (ex1Val.getIntVal() != 0) {
				expressionValue.intValue = 0;
			} else {
				expressionValue.intValue = 1;
			}
		} else {
			expressionValue.intValue = 0;
		}
		this.value = expressionValue;

	}
}
