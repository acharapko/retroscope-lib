package retroscope.rql.syntaxtree.expression;

import retroscope.rql.RQLEnvironment;
import retroscope.rql.Types;

public class StringLiteral extends Expression
{
	public StringLiteral(RQLEnvironment rqlEnvironment, String value)
	{
		super(rqlEnvironment);
		this.value = new RQLInterpreterValue(Types.STRING).setValStr(value);
	}

	public void evaluate() {

	}

}
