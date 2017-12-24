package retroscope.rql.syntaxtree.expression.literals;

import retroscope.datamodel.datastruct.variables.StringRQLVariable;
import retroscope.rql.syntaxtree.expression.Expression;

import java.util.ArrayList;

public class StringLiteral extends Expression
{
	public StringLiteral(String value)
	{
		this.value = new StringRQLVariable(value);
		dirty = false;
	}

	public void evaluate() {

	}

	public StringLiteral clone() {
		return new StringLiteral(((StringRQLVariable)this.value).getValue());
	}

	// StringLiteral is never dirty
    @Override
    public boolean computeDirty() {
        return dirty;
    }

    @Override
	public ArrayList<Variable> findVars() {
		return new ArrayList<>();
	}

}
