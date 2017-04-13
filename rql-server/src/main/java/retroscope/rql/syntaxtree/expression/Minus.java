package retroscope.rql.syntaxtree.expression;

import retroscope.rql.QueryEnvironment;
import retroscope.rql.Types;

public class Minus extends TwoOperandExpression
{
	public Minus(QueryEnvironment queryEnvironment, Expression ex1, Expression ex2)
	{ 
		super(queryEnvironment, ex1, ex2);

	}
	
	public Minus(QueryEnvironment queryEnvironment, Expression ex2)
	{
        super(queryEnvironment, new IntegerLiteral(queryEnvironment, 0), ex2);
	}

	public void evaluate() throws IllegalExpressionException {
		ex1.evaluate();
		ex2.evaluate();

		RQLInterpreterValue ex1Val = ex1.getValue();
		RQLInterpreterValue ex2Val = ex2.getValue();

		Types type = RQLInterpreterValue.typeDetect(ex1Val, ex2Val);
		RQLInterpreterValue expressionValue = new RQLInterpreterValue(type);
		switch (type) {
			case INT:

				if (ex1Val.type == Types.INT && ex2Val.type == Types.INT) {
					expressionValue.setValInt(ex1Val.intValue - ex2Val.intValue);
				} else {
					emitUndefinedDataTypeWarning("-", ex1Val.type, ex2Val.type);
					expressionValue.setValType(Types.NULL);
				}
				break;
			case DOUBLE:
				if (ex1Val.type == Types.DOUBLE && ex2Val.type == Types.DOUBLE) {
					expressionValue.setValFloat(ex1Val.intValue - ex2Val.intValue);
				} else if (ex1Val.type == Types.DOUBLE) {
					expressionValue.setValFloat(ex1Val.floatValue - ex2Val.intValue);
				} else if (ex2Val.type == Types.DOUBLE) {
					expressionValue.setValFloat(ex1Val.intValue - ex2Val.floatValue);
				} else {
					emitUndefinedDataTypeWarning("-", ex1Val.type, ex2Val.type);
					expressionValue.setValType(Types.NULL);
				}
				break;
			default:
				emitUndefinedDataTypeWarning("-", ex1Val.type, ex2Val.type);
				expressionValue.setValType(Types.NULL);
		}

		this.value = expressionValue;
	}
}
