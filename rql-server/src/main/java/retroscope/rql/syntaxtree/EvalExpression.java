package retroscope.rql.syntaxtree;

import retroscope.rql.Types;

public class EvalExpression
{
	public EvalExpression(Expression ex1)
	{
		try {
			ex1.evaluate();
			RQLInterpreterValue[] ex1Vals = ex1.getValues();
			for (int i = 0; i < ex1Vals.length; i++) {
				if (ex1Vals[i].getType() == Types.DOUBLE) {
					System.out.println(ex1Vals[i].getDoubleVal());
				} else if (ex1Vals[i].getType() == Types.INT) {
					System.out.println(ex1Vals[i].getIntVal());
				}
				if (ex1Vals[i].getType() == Types.STRING) {
					System.out.println(ex1Vals[i].getStringVal());
				}
			}
		} catch (IllegalExpressionException e) {System.err.println(e.getMessage());}

	}

	public void evaluate() {

	}

}
