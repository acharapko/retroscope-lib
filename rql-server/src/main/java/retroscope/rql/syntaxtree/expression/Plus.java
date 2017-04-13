package retroscope.rql.syntaxtree.expression;

import retroscope.rql.QueryEnvironment;
import retroscope.rql.Types;

public class Plus extends TwoOperandExpression
{
	
	public Plus(QueryEnvironment queryEnvironment, Expression ex1, Expression ex2)
	{ 		
		super(queryEnvironment, ex1, ex2);
	}

	public void evaluate() throws IllegalExpressionException {
		ex1.evaluate();
		ex2.evaluate();

		RQLInterpreterValue ex1Val = ex1.getValue();
		RQLInterpreterValue ex2Val = ex2.getValue();

		if (ex1Val.getType() == Types.STRING || ex2Val.getType() == Types.STRING) {

			Types type = RQLInterpreterValue.typeDetect(ex1Val, ex2Val);
			RQLInterpreterValue expressionValue = new RQLInterpreterValue(type);

			if (ex1Val.type == Types.STRING && ex2Val.type == Types.STRING) {
				expressionValue.setValStr(ex1Val.stringValue + ex2Val.stringValue);
			} else if (ex1Val.type == Types.STRING && ex2Val.type == Types.DOUBLE) {
				expressionValue.setValStr(ex1Val.stringValue + ex2Val.floatValue);
			} else if (ex1Val.type == Types.STRING && ex2Val.type == Types.INT) {
				expressionValue.setValStr(ex1Val.stringValue + ex2Val.intValue);
			} else if (ex1Val.type == Types.INT && ex2Val.type == Types.STRING) {
				expressionValue.setValStr( ex1Val.intValue + ex2Val.stringValue);
			} else if (ex1Val.type == Types.DOUBLE && ex2Val.type == Types.STRING) {
				expressionValue.setValStr(ex1Val.floatValue + ex2Val.stringValue);
			}
			this.value = expressionValue;
			return;
		}
		Types type = RQLInterpreterValue.typeDetect(ex1Val, ex2Val);
		RQLInterpreterValue expressionValue = new RQLInterpreterValue(type);
		switch (type) {
			case INT:
				if (ex1Val.type == Types.INT && ex2Val.type == Types.INT) {
					expressionValue.setValInt(ex1Val.intValue + ex2Val.intValue);
				}
				break;
			case DOUBLE:
				if (ex1Val.type == Types.DOUBLE && ex2Val.type == Types.DOUBLE) {
					expressionValue.setValFloat(ex1Val.intValue + ex2Val.intValue);
				} else if (ex1Val.type == Types.DOUBLE) {
					expressionValue.setValFloat(ex1Val.floatValue + ex2Val.intValue);
				} else if (ex2Val.type == Types.DOUBLE) {
					expressionValue.setValFloat(ex1Val.intValue + ex2Val.floatValue);
				}
				break;
		}
		this.value = expressionValue;
	}
}
