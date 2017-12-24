package retroscope.rql.syntaxtree.expression.literals;


import retroscope.datamodel.datastruct.variables.LongRQLVariable;
import retroscope.rql.syntaxtree.expression.Expression;

import java.util.ArrayList;

public class IntegerLiteral extends Expression
{
	public IntegerLiteral(long value)
	{
		this.value = new LongRQLVariable(value);
		dirty = false;
	}

	public void evaluate() {

	}

	public IntegerLiteral clone() {
		return new IntegerLiteral(((LongRQLVariable)this.value).getValue());
	}

	// integerLiteral is never dirty
    @Override
    public boolean computeDirty() {
        return dirty;
    }

	@Override
	public ArrayList<Variable> findVars() {
		return new ArrayList<>();
	}

}
