package retroscope.rql.syntaxtree;

import retroscope.rql.RQLEnvironment;
import retroscope.rql.Types;
import java.util.ArrayList;

public class Or extends TwoOperandExpression
{

	public Or(RQLEnvironment rqlEnvironment, Expression ex1, Expression ex2)
	{
		super(rqlEnvironment, ex1, ex2);
	}


	public void evaluate() throws IllegalExpressionException {
		ex1.evaluate();
		ex2.evaluate();

		RQLInterpreterValue[] ex1Vals = ex1.getValues();
		RQLInterpreterValue[] ex2Vals = ex2.getValues();
		ArrayList<RQLInterpreterValue> evalVals
				= new ArrayList<RQLInterpreterValue>(ex1Vals.length * ex2Vals.length);
		for (int i = 0; i < ex1Vals.length; i++) {
			for (int j = 0; j < ex2Vals.length; j++) {
				RQLInterpreterValue expressionValue = new RQLInterpreterValue(Types.INT);
				if ((ex1Vals[i].type == Types.INT || ex1Vals[i].type == Types.NULL)
                        && (ex2Vals[j].type == Types.INT || ex2Vals[j].type == Types.NULL)) {
					if (ex1Vals[i].intValue != 0 || ex2Vals[j].intValue != 0) {
						expressionValue.setValInt(1);
					} else {
                        expressionValue.setValInt(0);
                    }
                    expressionValue.addAllLinks(ex1Vals[i].getLinkChain()).addAllLinks(ex2Vals[j].getLinkChain());
                    evalVals.add(expressionValue);
				} else {
					//throw new IllegalExpressionException("Expected int types in logical OR, float or string given");
					RQLRunTimeWarning w = new RQLRunTimeWarning(
							RQLRunTimeWarning.WarningType.INCOMPATIBLE_TYPES,
							this.getClass().getName() + this.hashCode(),
							"Expected int types in logical OR, float or string given"
					);
					rqlEnvironment.addRunTimeWarning(w);
					expressionValue.setValType(Types.NULL);
					evalVals.add(expressionValue);
				}
			}
		}
		if (evalVals.size() == 0) {
		    evalVals.add(new RQLInterpreterValue(Types.NULL));
        }
        this.values = evalVals.toArray(new RQLInterpreterValue[evalVals.size()]);
	}
}
