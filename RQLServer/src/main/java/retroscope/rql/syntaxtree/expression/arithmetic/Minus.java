package retroscope.rql.syntaxtree.expression.arithmetic;

import retroscope.datamodel.datastruct.variables.DoubleRQLVariable;
import retroscope.datamodel.datastruct.variables.LongRQLVariable;
import retroscope.datamodel.datastruct.variables.RQLVariable;
import retroscope.rql.syntaxtree.expression.Expression;
import retroscope.rql.syntaxtree.expression.literals.IntegerLiteral;

public class Minus extends ArithmeticExpression
{
	public Minus(Expression ex1, Expression ex2)
	{ 
		super(ex1, ex2);
	}
	
	public Minus(Expression ex2)
	{
        super(new IntegerLiteral( 0), ex2);
	}

	@Override
	protected RQLVariable arithmeticOperation(long n1, long n2) {
		return new LongRQLVariable(n1 - n2);
	}

	@Override
	protected RQLVariable arithmeticOperation(long n1, double n2) {
		return new DoubleRQLVariable(n1 - n2);
	}

	@Override
	protected RQLVariable arithmeticOperation(double n1, long n2) {
		return new DoubleRQLVariable(n1 - n2);
	}

	@Override
	protected RQLVariable arithmeticOperation(double n1, double n2) {
		return new DoubleRQLVariable(n1 - n2);
	}


	public Minus clone() {
		return new Minus(ex1.clone(), ex2.clone());
	}
}
