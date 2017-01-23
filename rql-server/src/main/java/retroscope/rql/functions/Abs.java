package retroscope.rql.functions;

import retroscope.rql.RQLEnvironment;
import retroscope.rql.syntaxtree.ExpressionList;
import retroscope.rql.syntaxtree.IllegalExpressionException;
import retroscope.rql.syntaxtree.RQLInterpreterValue;
import retroscope.rql.syntaxtree.RQLRunTimeWarning;

import java.util.ArrayList;

/**
 * Created by Aleksey on 1/21/2017.
 *
 */
public class Abs extends RQLBuiltInFunction {

    public Abs(ExpressionList params, RQLEnvironment rqlEnvironment) {
        super(params, rqlEnvironment);
    }

    public RQLInterpreterValue[] getValues() {
        return evaluatedVals.toArray(new RQLInterpreterValue[evaluatedVals.size()]);
    }

    public void evaluate() throws IllegalExpressionException {
        evaluatedVals = new ArrayList<RQLInterpreterValue>();
        if (params.getList().size() == 1){
            params.getList().get(0).evaluate();
            RQLInterpreterValue[] p1Vals = params.getList().get(0).getValues();

            for (int i = 0; i < p1Vals.length; i++) {
                RQLInterpreterValue expressionValue = new RQLInterpreterValue(p1Vals[i].getType());

                switch (p1Vals[i].getType()) {
                    case INT:
                        expressionValue.setValInt(Math.abs(p1Vals[i].getIntVal()));
                        break;
                    case DOUBLE:
                        expressionValue.setValFloat(Math.abs(p1Vals[i].getDoubleVal()));
                        break;
                    default:
                        RQLRunTimeWarning w = new RQLRunTimeWarning(
                                RQLRunTimeWarning.WarningType.INCOMPATIBLE_TYPES,
                                this.getClass().getName() + this.hashCode(),
                                "Function Abs is undefined for " + p1Vals[i].getType()
                        );
                        rqlEnvironment.addRunTimeWarning(w);
                }
                evaluatedVals.add(expressionValue);
            }
        }
    }
}
