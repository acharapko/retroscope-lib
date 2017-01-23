package retroscope.rql.syntaxtree;

import java.util.ArrayList;

import retroscope.rql.RQLEnvironment;
import retroscope.rql.Types;

public class EQExpression extends TwoOperandExpression
{
	
	public EQExpression(RQLEnvironment rqlEnvironment, Expression ex1, Expression ex2)
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

                if (ex1Vals[i].type == Types.INT && ex2Vals[j].type == Types.INT) {
                    if (ex1Vals[i].getIntVal() == ex2Vals[j].getIntVal())
                        expressionValue.setValInt(1);
                    else
                        expressionValue.setValInt(0);
                } else if (ex1Vals[i].type == Types.DOUBLE && ex2Vals[j].getType() == Types.DOUBLE) {
                    if (ex1Vals[i].getDoubleVal() == ex2Vals[j].getDoubleVal())
                        expressionValue.setValInt(1);
                    else
                        expressionValue.setValInt(0);
                } else if (ex1Vals[i].type == Types.DOUBLE && ex2Vals[j].getType() == Types.INT) {
                    if (ex1Vals[i].getDoubleVal() == ex2Vals[j].getIntVal())
                        expressionValue.setValInt(1);
                    else
                        expressionValue.setValInt(0);
                } else if (ex1Vals[i].type == Types.INT && ex2Vals[j].getType() == Types.DOUBLE) {
                    if (ex1Vals[i].getIntVal() == ex2Vals[j].getDoubleVal())
                        expressionValue.setValInt(1);
                    else
                        expressionValue.setValInt(0);
                } else if (ex1Vals[i].type == Types.DOUBLE && ex2Vals[j].getType() == Types.INT) {
                    if (ex1Vals[i].getDoubleVal() == ex2Vals[j].getIntVal())
                        expressionValue.setValInt(1);
                    else
                        expressionValue.setValInt(0);
                } else if (ex1Vals[i].type == Types.STRING && ex2Vals[j].getType() == Types.STRING) {
                    if (ex1Vals[i].getStringVal().equals(ex2Vals[j].getStringVal()))
                        expressionValue.setValInt(1);
                    else
                        expressionValue.setValInt(0);
                } else if (ex1Vals[i].type == Types.STRING && ex2Vals[j].getType() == Types.INT) {
                    if (ex1Vals[i].getStringVal().equals(ex2Vals[j].getIntVal() + ""))
                        expressionValue.setValInt(1);
                    else
                        expressionValue.setValInt(0);
                } else if (ex1Vals[i].type == Types.INT && ex2Vals[j].getType() == Types.STRING) {
                    if (ex2Vals[j].getStringVal().equals(ex1Vals[i].getIntVal() + ""))
                        expressionValue.setValInt(1);
                    else
                        expressionValue.setValInt(0);
                } else if (ex1Vals[i].type == Types.STRING && ex2Vals[j].getType() == Types.DOUBLE) {
                    if (ex1Vals[i].getStringVal().equals(ex2Vals[j].getDoubleVal() + ""))
                        expressionValue.setValInt(1);
                    else
                        expressionValue.setValInt(0);
                } else if (ex1Vals[i].type == Types.DOUBLE && ex2Vals[j].getType() == Types.STRING) {
                    if (ex2Vals[j].getStringVal().equals(ex1Vals[i].getDoubleVal() + ""))
                        expressionValue.setValInt(1);
                    else
                        expressionValue.setValInt(0);
                }
                expressionValue.addAllLinks(ex1Vals[i].getLinkChain()).addAllLinks(ex2Vals[j].getLinkChain());
                evalVals.add(expressionValue);
            }
        }

        if (evalVals.size() == 0) {
            evalVals.add(new RQLInterpreterValue(Types.NULL));
        }

        this.values = evalVals.toArray(new RQLInterpreterValue[evalVals.size()]);
	}
}
