package retroscope.rql.syntaxtree.expression;

import retroscope.rql.RQLEnvironment;
import retroscope.rql.Types;

public class IntegerLiteral extends Expression
{
	public IntegerLiteral(RQLEnvironment rqlEnvironment, int value)
	{
		super(rqlEnvironment);
		this.value = new RQLInterpreterValue(Types.INT).setValInt(value);
	}

	public void evaluate() {

	}

}
