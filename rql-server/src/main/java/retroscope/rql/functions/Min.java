package retroscope.rql.functions;

import retroscope.rql.QueryEnvironment;
import retroscope.rql.Types;
import retroscope.rql.syntaxtree.expression.ExpressionList;
import retroscope.rql.syntaxtree.expression.IllegalExpressionException;
import retroscope.rql.syntaxtree.expression.RQLInterpreterValue;
import retroscope.rql.errors.RQLRunTimeWarning;

/**
 * Created by Aleksey on 1/21/2017.
 *
 */
public class Min extends RQLBuiltInFunction {

    public Min(ExpressionList params, QueryEnvironment queryEnvironment) {
        super(params, queryEnvironment);
    }

    public void evaluate() throws IllegalExpressionException {       
        if (params.getList().size() >= 1){
            RQLInterpreterValue min = new RQLInterpreterValue(Types.INT).setValInt(Long.MAX_VALUE);
            for (int i = 0; i < params.getList().size(); i++) {
                params.getList().get(i).evaluate();
                RQLInterpreterValue p1Val = params.getList().get(i).getValue();

                switch (p1Val.getType()) {
                    case INT:
                        if (min.getType() == Types.INT) {
                            if (p1Val.getIntVal() < min.getIntVal()) {
                                min.setValInt(p1Val.getIntVal());
                            }
                        } else if (min.getType() == Types.DOUBLE) {
                            if (p1Val.getIntVal() < min.getDoubleVal()) {
                                min.setValInt(p1Val.getIntVal());
                            }
                        }
                        break;
                    case DOUBLE:
                        if (min.getType() == Types.INT) {
                            if (p1Val.getDoubleVal() < min.getIntVal()) {
                                min.setValFloat(p1Val.getDoubleVal());
                            }
                        } else if (min.getType() == Types.DOUBLE) {
                            if (p1Val.getDoubleVal() < min.getDoubleVal()) {
                                min.setValFloat(p1Val.getDoubleVal());
                            }
                        }
                        break;
                    default:
                        RQLRunTimeWarning w = new RQLRunTimeWarning(
                                RQLRunTimeWarning.WarningType.INCOMPATIBLE_TYPES,
                                this.getClass().getName() + this.hashCode(),
                                "Function Max is undefined for " + p1Val.getType()
                        );
                        queryEnvironment.addRunTimeWarning(w);
                        min.setValType(Types.NULL);
                }

            }
            value = min;
        } else {
            RQLRunTimeWarning w = new RQLRunTimeWarning(
                    RQLRunTimeWarning.WarningType.INCOMPATIBLE_TYPES,
                    this.getClass().getName() + this.hashCode(),
                    "Function Min must receives some arguments"
            );
            queryEnvironment.addRunTimeWarning(w);
            value = new RQLInterpreterValue(Types.NULL);
        }
    }
}
