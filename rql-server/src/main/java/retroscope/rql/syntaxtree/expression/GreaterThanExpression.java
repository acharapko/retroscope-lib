package retroscope.rql.syntaxtree.expression;

import retroscope.rql.QueryEnvironment;
import retroscope.rql.Types;

public class GreaterThanExpression extends TwoOperandExpression
{
	public GreaterThanExpression(QueryEnvironment queryEnvironment, Expression ex1, Expression ex2)
	{
		super(queryEnvironment, ex1, ex2);
	}

	public void evaluate() throws IllegalExpressionException {
		ex1.evaluate();
		ex2.evaluate();

		RQLInterpreterValue ex1Val = ex1.getValue();
		RQLInterpreterValue ex2Val = ex2.getValue();
	
		RQLInterpreterValue expressionValue = new RQLInterpreterValue(Types.INT);

		if (ex1Val.type == Types.STRING || ex2Val.type == Types.STRING) {
			//throw new IllegalExpressionException("Operation '>' is undefined for string type");
		} else {
			if (ex1Val.type == Types.INT && ex2Val.type == Types.INT) {
				if (ex1Val.getIntVal() > ex2Val.getIntVal())
					expressionValue.setValInt(1);
				else
					expressionValue.setValInt(0);
			} else if (ex1Val.type == Types.DOUBLE && ex2Val.getType() == Types.DOUBLE) {
				if (ex1Val.getDoubleVal() > ex2Val.getDoubleVal())
					expressionValue.setValInt(1);
				else
					expressionValue.setValInt(0);
			} else if (ex1Val.type == Types.DOUBLE && ex2Val.getType() == Types.INT) {
				if (ex1Val.getDoubleVal() > ex2Val.getIntVal())
					expressionValue.setValInt(1);
				else
					expressionValue.setValInt(0);
			} else if (ex1Val.type == Types.INT && ex2Val.getType() == Types.DOUBLE) {
				if (ex1Val.getIntVal() > ex2Val.getDoubleVal())
					expressionValue.setValInt(1);
				else
					expressionValue.setValInt(0);
			}
		}
		this.value = expressionValue;
	}
}
