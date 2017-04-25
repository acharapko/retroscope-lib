package retroscope.rql.functions;

import retroscope.rql.QueryEnvironment;
import retroscope.rql.Types;
import retroscope.rql.errors.RQLRunTimeWarning;
import retroscope.rql.syntaxtree.expression.ExpressionList;
import retroscope.rql.syntaxtree.expression.IllegalExpressionException;
import retroscope.rql.syntaxtree.expression.RQLInterpreterValue;

/**
 * Created by Aleksey on 1/21/2017.
 *
 */
public class Int extends RQLBuiltInFunction {

    public Int(ExpressionList params, QueryEnvironment queryEnvironment) {
        super(params, queryEnvironment);
    }

    public void evaluate() throws IllegalExpressionException {
        if (params.getList().size() == 1){
            params.getList().get(0).evaluate();
            RQLInterpreterValue p1Val = params.getList().get(0).getValue();

            RQLInterpreterValue expressionValue = new RQLInterpreterValue(Types.INT);

            switch (p1Val.getType()) {
                case STRING:
                    try {
                        expressionValue.setValInt(Long.parseLong(p1Val.getStringVal()));
                    } catch (NumberFormatException nfe) {
                        RQLRunTimeWarning w = new RQLRunTimeWarning(
                                RQLRunTimeWarning.WarningType.INCOMPATIBLE_TYPES,
                                this.getClass().getName() + this.hashCode(),
                                "String could not be parsed to int"
                        );
                        queryEnvironment.addRunTimeWarning(w);
                    }
                    break;
                case DOUBLE:
                    expressionValue.setValInt((int)(p1Val.getDoubleVal()));
                    break;
                case INT:
                    break;
                default:
                    RQLRunTimeWarning w = new RQLRunTimeWarning(
                            RQLRunTimeWarning.WarningType.INCOMPATIBLE_TYPES,
                            this.getClass().getName() + this.hashCode(),
                            "Function Int is undefined for " + p1Val.getType()
                    );
                    queryEnvironment.addRunTimeWarning(w);
                    expressionValue.setValType(Types.NULL);
            }
            value = expressionValue;
        } else {
            RQLRunTimeWarning w = new RQLRunTimeWarning(
                    RQLRunTimeWarning.WarningType.INCOMPATIBLE_TYPES,
                    this.getClass().getName() + this.hashCode(),
                    "Function Int must receive exactly one argument"
            );
            queryEnvironment.addRunTimeWarning(w);
            value = new RQLInterpreterValue(Types.NULL);
        }
    }
}
