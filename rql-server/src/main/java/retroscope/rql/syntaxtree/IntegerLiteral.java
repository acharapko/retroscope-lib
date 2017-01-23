package retroscope.rql.syntaxtree;

import retroscope.rql.RQLEnvironment;
import retroscope.rql.Types;

public class IntegerLiteral extends Expression
{
	public IntegerLiteral(RQLEnvironment rqlEnvironment, int value)
	{
		super(rqlEnvironment);
		this.values = new RQLInterpreterValue[1];
		this.values[0] = new RQLInterpreterValue(Types.INT).setValInt(value);
	}

	public void evaluate() {

	}

}
