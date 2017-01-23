package retroscope.rql.syntaxtree;

import retroscope.rql.RQLEnvironment;
import retroscope.rql.Types;

public class StringLiteral extends Expression
{
	public StringLiteral(RQLEnvironment rqlEnvironment, String value)
	{
		super(rqlEnvironment);
		this.values = new RQLInterpreterValue[1];
		this.values[0] = new RQLInterpreterValue(Types.STRING).setValStr(value);
	}

	public void evaluate() {

	}

}
