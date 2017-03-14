package retroscope.rql.syntaxtree.expression;

import retroscope.rql.RQLEnvironment;
import retroscope.rql.Types;

public class FloatLiteral extends Expression
{
	public FloatLiteral(RQLEnvironment rqlEnvironment, double value)
	{
		super(rqlEnvironment);
		this.value = new RQLInterpreterValue(Types.DOUBLE).setValFloat(value);
	}

	public void evaluate() {
		
	}

}
