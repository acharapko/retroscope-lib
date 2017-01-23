package retroscope.rql.functions;

import retroscope.rql.RQLEnvironment;
import retroscope.rql.Types;
import retroscope.rql.syntaxtree.ExpressionList;
import retroscope.rql.syntaxtree.IllegalExpressionException;
import retroscope.rql.syntaxtree.RQLInterpreterValue;
import retroscope.rql.syntaxtree.RQLRunTimeWarning;

import java.util.ArrayList;

/**
 * Created by Aleksey on 1/21/2017.
 *
 */
public class Min extends RQLBuiltInFunction {

    public Min(ExpressionList params, RQLEnvironment rqlEnvironment) {
        super(params, rqlEnvironment);
    }

    public RQLInterpreterValue[] getValues() {
        return evaluatedVals.toArray(new RQLInterpreterValue[evaluatedVals.size()]);
    }

    public void evaluate() throws IllegalExpressionException {
        evaluatedVals = new ArrayList<RQLInterpreterValue>();
        if (params.getList().size() >= 1){
            RQLInterpreterValue max = new RQLInterpreterValue(Types.INT).setValInt(Integer.MAX_VALUE);
            for (int i = 0; i < params.getList().size(); i++) {
                params.getList().get(i).evaluate();
                RQLInterpreterValue[] p1Vals = params.getList().get(i).getValues();
                for (int j = 0; j < p1Vals.length; j++) {
                    switch (p1Vals[j].getType()) {
                        case INT:
                            if (max.getType() == Types.INT) {
                                if (p1Vals[j].getIntVal() < max.getIntVal()) {
                                    max.setValInt(p1Vals[j].getIntVal());
                                }
                            } else if (max.getType() == Types.DOUBLE) {
                                if (p1Vals[j].getIntVal() < max.getDoubleVal()) {
                                    max.setValInt(p1Vals[j].getIntVal());
                                }
                            }
                            break;
                        case DOUBLE:
                            if (max.getType() == Types.INT) {
                                if (p1Vals[j].getDoubleVal() < max.getIntVal()) {
                                    max.setValFloat(p1Vals[j].getDoubleVal());
                                }
                            } else if (max.getType() == Types.DOUBLE) {
                                if (p1Vals[j].getDoubleVal() < max.getDoubleVal()) {
                                    max.setValFloat(p1Vals[j].getDoubleVal());
                                }
                            }
                            break;
                        default:
                            RQLRunTimeWarning w = new RQLRunTimeWarning(
                                    RQLRunTimeWarning.WarningType.INCOMPATIBLE_TYPES,
                                    this.getClass().getName() + this.hashCode(),
                                    "Function Max is undefined for " + p1Vals[i].getType()
                            );
                            rqlEnvironment.addRunTimeWarning(w);
                    }
                }
            }
            evaluatedVals.add(max);
        }
    }
}
