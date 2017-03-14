package retroscope.rql.functions;

import retroscope.rql.RQLEnvironment;
import retroscope.rql.Types;
import retroscope.rql.syntaxtree.expression.ExpressionList;
import retroscope.rql.syntaxtree.expression.IllegalExpressionException;
import retroscope.rql.syntaxtree.expression.RQLInterpreterValue;

/**
 * Created by Aleksey on 1/21/2017.
 *
 */
public class Empty extends RQLBuiltInFunction {

    public Empty(ExpressionList params, RQLEnvironment rqlEnvironment) {
        super(params, rqlEnvironment);
    }

    public void evaluate() throws IllegalExpressionException {
        if (params.getList().size() == 1){
            params.getList().get(0).evaluate();
            RQLInterpreterValue p1Val = params.getList().get(0).getValue();
            boolean allempty = true;

            switch (p1Val.getType()) {
                case INT:
                    if (p1Val.getIntVal() != 0) {
                        allempty = false;
                    }
                    break;
                case DOUBLE:
                    if (p1Val.getDoubleVal() != 0) {
                        allempty = false;
                    }
                    break;
                case STRING:
                    if (!p1Val.getStringVal().equals("")) {
                        allempty = false;
                    }
                    break;
            }

            RQLInterpreterValue expressionValue = new RQLInterpreterValue(Types.INT);
            if (allempty) {
                expressionValue.setValInt(1);
            } else {
                expressionValue.setValInt(0);
            }
            value = expressionValue;
        }
    }
}
