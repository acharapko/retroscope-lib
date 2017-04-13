package retroscope.rql.syntaxtree.expression;

import retroscope.rql.QueryEnvironment;
import retroscope.rql.Types;

public class StringLiteral extends Expression
{
	public StringLiteral(QueryEnvironment queryEnvironment, String value)
	{
		super(queryEnvironment);
		this.value = new RQLInterpreterValue(Types.STRING).setValStr(value);
	}

	public void evaluate() {

	}

}
