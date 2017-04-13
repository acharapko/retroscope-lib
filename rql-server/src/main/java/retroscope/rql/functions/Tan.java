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
public class Tan extends RQLBuiltInFunction {

    public Tan(ExpressionList params, QueryEnvironment queryEnvironment) {
        super(params, queryEnvironment);
    }

  
    public void evaluate() throws IllegalExpressionException {
        if (params.getList().size() == 1){
            params.getList().get(0).evaluate();
            RQLInterpreterValue p1Val = params.getList().get(0).getValue();

            RQLInterpreterValue expressionValue = new RQLInterpreterValue(p1Val.getType());

            switch (p1Val.getType()) {
                case INT:
                    expressionValue.setValFloat(Math.tan(p1Val.getIntVal()));
                    break;
                case DOUBLE:
                    expressionValue.setValFloat(Math.tan(p1Val.getDoubleVal()));
                    break;
                default:
                    RQLRunTimeWarning w = new RQLRunTimeWarning(
                            RQLRunTimeWarning.WarningType.INCOMPATIBLE_TYPES,
                            this.getClass().getName() + this.hashCode(),
                            "Function Tan is undefined for " + p1Val.getType()
                    );
                    queryEnvironment.addRunTimeWarning(w);
                    expressionValue.setValType(Types.NULL);
            }
            value = expressionValue;
        } else {
            RQLRunTimeWarning w = new RQLRunTimeWarning(
                    RQLRunTimeWarning.WarningType.INCOMPATIBLE_TYPES,
                    this.getClass().getName() + this.hashCode(),
                    "Function Tan must receive exactly one argument"
            );
            queryEnvironment.addRunTimeWarning(w);
            value = new RQLInterpreterValue(Types.NULL);
        }
    }
}
