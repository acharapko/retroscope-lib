package retroscope.rql.syntaxtree.expression;

import retroscope.rql.QueryEnvironment;
import retroscope.rql.Types;

public class FloatLiteral extends Expression
{
	public FloatLiteral(QueryEnvironment queryEnvironment, double value)
	{
		super(queryEnvironment);
		this.value = new RQLInterpreterValue(Types.DOUBLE).setValFloat(value);
	}

	public void evaluate() {
		
	}

}
