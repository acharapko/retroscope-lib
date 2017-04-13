package retroscope.rql.syntaxtree.expression;

import retroscope.rql.QueryEnvironment;
import retroscope.rql.Types;

public class IntegerLiteral extends Expression
{
	public IntegerLiteral(QueryEnvironment queryEnvironment, int value)
	{
		super(queryEnvironment);
		this.value = new RQLInterpreterValue(Types.INT).setValInt(value);
	}

	public void evaluate() {

	}

}
