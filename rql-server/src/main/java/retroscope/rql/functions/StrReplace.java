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
public class StrReplace extends RQLBuiltInFunction {

    public StrReplace(ExpressionList params, QueryEnvironment queryEnvironment) {
        super(params, queryEnvironment);
    }

    public void evaluate() throws IllegalExpressionException {
        if (params.getList().size() == 3){
            params.getList().get(0).evaluate();

            RQLInterpreterValue str = params.getList().get(0).getValue();
            RQLInterpreterValue find = params.getList().get(1).getValue();
            RQLInterpreterValue replace = params.getList().get(2).getValue();

            RQLInterpreterValue expressionValue = new RQLInterpreterValue(Types.STRING);

            boolean typeOk = true;
            if (str.getType() != Types.STRING) {
                RQLRunTimeWarning w = new RQLRunTimeWarning(
                        RQLRunTimeWarning.WarningType.INCOMPATIBLE_TYPES,
                        this.getClass().getName() + this.hashCode(),
                        "Function StrReplace needs a String as str argument, instead given " + str.getType()
                );
                queryEnvironment.addRunTimeWarning(w);
                typeOk = false;
            }

            if (find.getType() != Types.STRING) {
                RQLRunTimeWarning w = new RQLRunTimeWarning(
                        RQLRunTimeWarning.WarningType.INCOMPATIBLE_TYPES,
                        this.getClass().getName() + this.hashCode(),
                        "Function StrReplace needs a String as find argument, instead given " + str.getType()
                );
                queryEnvironment.addRunTimeWarning(w);
                typeOk = false;
            }

            if (replace.getType() != Types.STRING) {
                RQLRunTimeWarning w = new RQLRunTimeWarning(
                        RQLRunTimeWarning.WarningType.INCOMPATIBLE_TYPES,
                        this.getClass().getName() + this.hashCode(),
                        "Function StrReplace needs a String as replace argument, instead given " + str.getType()
                );
                queryEnvironment.addRunTimeWarning(w);
                typeOk = false;
            }
            if (typeOk) {
                String s = str.getStringVal().replace(find.getStringVal(), replace.getStringVal());
                expressionValue.setValStr(s);
                value = expressionValue;
            } else {
                value = new RQLInterpreterValue(Types.NULL);
            }
        } else {
            RQLRunTimeWarning w = new RQLRunTimeWarning(
                    RQLRunTimeWarning.WarningType.INCOMPATIBLE_TYPES,
                    this.getClass().getName() + this.hashCode(),
                    "Function Int must receive exactly 3 arguments"
            );
            queryEnvironment.addRunTimeWarning(w);
            value = new RQLInterpreterValue(Types.NULL);
        }
    }
}
