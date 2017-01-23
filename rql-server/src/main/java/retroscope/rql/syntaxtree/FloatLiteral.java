package retroscope.rql.syntaxtree;

import retroscope.rql.RQLEnvironment;
import retroscope.rql.Types;

public class FloatLiteral extends Expression
{
	public FloatLiteral(RQLEnvironment rqlEnvironment, double value)
	{
		super(rqlEnvironment);
		this.values = new RQLInterpreterValue[1];
		this.values[0] = new RQLInterpreterValue(Types.DOUBLE).setValFloat(value);
	}

	public void evaluate() {
		
	}

}
