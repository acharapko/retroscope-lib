package retroscope.rql.syntaxtree.expression.literals;

import retroscope.datamodel.datastruct.variables.StringRQLVariable;
import retroscope.rql.syntaxtree.expression.Expression;

public class StringLiteral extends Expression
{
	public StringLiteral(String value)
	{
		this.value = new StringRQLVariable(value);
	}

	public void evaluate() {

	}

	public StringLiteral clone() {
		return new StringLiteral(((StringRQLVariable)this.value).getValue());
	}

}
