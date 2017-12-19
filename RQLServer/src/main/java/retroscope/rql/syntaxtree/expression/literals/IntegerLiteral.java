package retroscope.rql.syntaxtree.expression.literals;


import retroscope.datamodel.datastruct.variables.LongRQLVariable;
import retroscope.rql.syntaxtree.expression.Expression;

public class IntegerLiteral extends Expression
{
	public IntegerLiteral(long value)
	{
		this.value = new LongRQLVariable(value);
	}

	public void evaluate() {

	}

	public IntegerLiteral clone() {
		return new IntegerLiteral(((LongRQLVariable)this.value).getValue());
	}

}
