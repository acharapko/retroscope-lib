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
public class Sin extends RQLBuiltInFunction {

    public Sin(ExpressionList params, RQLEnvironment rqlEnvironment) {
        super(params, rqlEnvironment);
    }

    public void evaluate() throws IllegalExpressionException {
        if (params.getList().size() == 1){
            params.getList().get(0).evaluate();
            RQLInterpreterValue p1Val = params.getList().get(0).getValue();

            RQLInterpreterValue expressionValue = new RQLInterpreterValue(p1Val.getType());

            switch (p1Val.getType()) {
                case INT:
                    expressionValue.setValFloat(Math.sin(p1Val.getIntVal()));
                    break;
                case DOUBLE:
                    expressionValue.setValFloat(Math.sin(p1Val.getDoubleVal()));
                    break;
                default:
                    RQLRunTimeWarning w = new RQLRunTimeWarning(
                            RQLRunTimeWarning.WarningType.INCOMPATIBLE_TYPES,
                            this.getClass().getName() + this.hashCode(),
                            "Function Sin is undefined for " + p1Val.getType()
                    );
                    rqlEnvironment.addRunTimeWarning(w);
                    expressionValue.setValType(Types.NULL);
            }
            value = expressionValue;
        } else {
            RQLRunTimeWarning w = new RQLRunTimeWarning(
                    RQLRunTimeWarning.WarningType.INCOMPATIBLE_TYPES,
                    this.getClass().getName() + this.hashCode(),
                    "Function Sin must receive exactly one argument"
            );
            rqlEnvironment.addRunTimeWarning(w);
            value = new RQLInterpreterValue(Types.NULL);
        }
    }
}
