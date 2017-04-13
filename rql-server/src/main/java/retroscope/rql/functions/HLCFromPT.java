package retroscope.rql.functions;

import retroscope.hlc.Timestamp;
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
public class HLCFromPT extends RQLBuiltInFunction {

    public HLCFromPT(ExpressionList params, QueryEnvironment queryEnvironment) {
        super(params, queryEnvironment);
    }

    public void evaluate() throws IllegalExpressionException {

        if (params.getList().size() == 1) {
            params.getList().get(0).evaluate();
            RQLInterpreterValue p1Val = params.getList().get(0).getValue();
            if (p1Val.getType() != Types.INT) {
                queryEnvironment.addRunTimeWarning(
                        new RQLRunTimeWarning(
                                RQLRunTimeWarning.WarningType.INCOMPATIBLE_TYPES,
                                this.hashCode()+"",
                                "HLCFromPT() function operates only on INT64 data type")
                );
                value = new RQLInterpreterValue(Types.NULL);
            } else {
                Timestamp t = new Timestamp(p1Val.getIntVal(), (short) 0 );
                value = new RQLInterpreterValue(Types.INT).setValInt(t.toLong());
            }
        } else {
            RQLRunTimeWarning w = new RQLRunTimeWarning(
                    RQLRunTimeWarning.WarningType.INCOMPATIBLE_TYPES,
                    this.getClass().getName() + this.hashCode(),
                    "Function HLCFromPT must receive exactly one argument"
            );
            queryEnvironment.addRunTimeWarning(w);
            value = new RQLInterpreterValue(Types.NULL);
        }
    }
}
