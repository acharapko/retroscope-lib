package retroscope.rql.syntaxtree.expression.arithmetic;

import retroscope.datamodel.datastruct.variables.DoubleRQLVariable;
import retroscope.datamodel.datastruct.variables.LongRQLVariable;
import retroscope.datamodel.datastruct.variables.RQLVariable;
import retroscope.rql.syntaxtree.expression.Expression;

public class Divide extends ArithmeticExpression
{
	
	public Divide(Expression ex1, Expression ex2)
	{ 
		super(ex1, ex2);
	}

    @Override
    protected RQLVariable arithmeticOperation(long n1, long n2) {
        return new LongRQLVariable(n1 / n2);
    }

    @Override
    protected RQLVariable arithmeticOperation(long n1, double n2) {
        return new DoubleRQLVariable(n1 / n2);
    }

    @Override
    protected RQLVariable arithmeticOperation(double n1, long n2) {
        return new DoubleRQLVariable(n1 / n2);
    }

    @Override
    protected RQLVariable arithmeticOperation(double n1, double n2) {
        return new DoubleRQLVariable(n1 / n2);
    }

    public Divide clone() {
		return new Divide(ex1.clone(), ex2.clone());
	}
}
