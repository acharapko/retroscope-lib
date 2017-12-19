package retroscope.rql.syntaxtree.expression.literals;


import retroscope.datamodel.datastruct.variables.DoubleRQLVariable;
import retroscope.rql.syntaxtree.expression.Expression;

public class FloatLiteral extends Expression
{
	public FloatLiteral(double value)
	{
		this.value = new DoubleRQLVariable(value);
	}

	public void evaluate() {
		
	}

	public FloatLiteral clone() {
		return new FloatLiteral(((DoubleRQLVariable)this.value).getValue());
	}

}
