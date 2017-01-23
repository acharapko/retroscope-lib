package retroscope.rql.syntaxtree;

import retroscope.rql.RQLEnvironment;
import retroscope.rql.Types;

public class NotExpression extends Expression
{
	private Expression ex1;
	public NotExpression(RQLEnvironment rqlEnvironment, Expression ex1)
	{
		super(rqlEnvironment);
		this.ex1 = ex1;
	}
	
			
	public void evaluate() throws IllegalExpressionException {
		ex1.evaluate();
		RQLInterpreterValue[] ex1Vals = ex1.getValues();
		RQLInterpreterValue[] evalVals = new RQLInterpreterValue[ex1Vals.length];
		for (int i = 0; i < ex1Vals.length; i++) {
			/*if (ex1Vals[i].getType() != Types.INT) {
				throw new IllegalExpressionException("Logical negation can not accept float or string types");
			}*/
			evalVals[i] = new RQLInterpreterValue(Types.INT);
			evalVals[i].addAllLinks(ex1Vals[i].getLinkChain());
			if (ex1Vals[i].getType() == Types.INT) {
				if (ex1Vals[i].getIntVal() != 0) {
					evalVals[i].intValue = 0;
				} else {
					evalVals[i].intValue = 1;
				}
			} else {
				evalVals[i].intValue = 0;
			}
		}
	}
}
