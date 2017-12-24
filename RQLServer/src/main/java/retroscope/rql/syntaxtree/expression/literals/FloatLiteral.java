package retroscope.rql.syntaxtree.expression.literals;


import retroscope.datamodel.datastruct.variables.DoubleRQLVariable;
import retroscope.rql.syntaxtree.expression.Expression;

import java.util.ArrayList;

public class FloatLiteral extends Expression
{
	public FloatLiteral(double value)
	{
		this.value = new DoubleRQLVariable(value);
		dirty = false;
	}

	public void evaluate() {
		
	}

	public FloatLiteral clone() {
		return new FloatLiteral(((DoubleRQLVariable)this.value).getValue());
	}

	// FloatLiteral is never dirty
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
