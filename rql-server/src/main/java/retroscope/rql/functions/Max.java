package retroscope.rql.functions;

import retroscope.rql.RQLEnvironment;
import retroscope.rql.Types;
import retroscope.rql.syntaxtree.expression.ExpressionList;
import retroscope.rql.syntaxtree.expression.IllegalExpressionException;
import retroscope.rql.syntaxtree.expression.RQLInterpreterValue;
import retroscope.rql.errors.RQLRunTimeWarning;

/**
 * Created by Aleksey on 1/21/2017.
 *
 */
public class Max extends RQLBuiltInFunction {

    public Max(ExpressionList params, RQLEnvironment rqlEnvironment) {
        super(params, rqlEnvironment);
    }

    public void evaluate() throws IllegalExpressionException {
        if (params.getList().size() >= 1){
            RQLInterpreterValue max = new RQLInterpreterValue(Types.INT).setValInt(Long.MIN_VALUE);
            for (int i = 0; i < params.getList().size(); i++) {
                params.getList().get(i).evaluate();
                RQLInterpreterValue p1Val = params.getList().get(i).getValue();

                switch (p1Val.getType()) {
                    case INT:
                        if (max.getType() == Types.INT) {
                            if (p1Val.getIntVal() > max.getIntVal()) {
                                max.setValInt(p1Val.getIntVal());
                            }
                        } else if (max.getType() == Types.DOUBLE) {
                            if (p1Val.getIntVal() > max.getDoubleVal()) {
                                max.setValInt(p1Val.getIntVal());
                            }
                        }
                        break;
                    case DOUBLE:
                        if (max.getType() == Types.INT) {
                            if (p1Val.getDoubleVal() > max.getIntVal()) {
                                max.setValFloat(p1Val.getDoubleVal());
                            }
                        } else if (max.getType() == Types.DOUBLE) {
                            if (p1Val.getDoubleVal() > max.getDoubleVal()) {
                                max.setValFloat(p1Val.getDoubleVal());
                            }
                        }
                        break;
                    default:
                        RQLRunTimeWarning w = new RQLRunTimeWarning(
                                RQLRunTimeWarning.WarningType.INCOMPATIBLE_TYPES,
                                this.getClass().getName() + this.hashCode(),
                                "Function Max is undefined for " + p1Val.getType()
                        );
                        rqlEnvironment.addRunTimeWarning(w);
                        max.setValType(Types.NULL);
                }

            }
            value = max;
        } else {
            RQLRunTimeWarning w = new RQLRunTimeWarning(
                    RQLRunTimeWarning.WarningType.INCOMPATIBLE_TYPES,
                    this.getClass().getName() + this.hashCode(),
                    "Function Max must receives some arguments"
            );
            rqlEnvironment.addRunTimeWarning(w);
            value = new RQLInterpreterValue(Types.NULL);
        }
    }
}
