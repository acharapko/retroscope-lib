package retroscope.rql.syntaxtree.expression;

import retroscope.rql.RQLEnvironment;
import retroscope.rql.Types;
import retroscope.rql.errors.RQLRunTimeWarning;

public class And extends TwoOperandExpression {

    public And(RQLEnvironment rqlEnvironment, Expression ex1, Expression ex2) {
        super(rqlEnvironment, ex1, ex2);
    }

    public void evaluate() throws IllegalExpressionException {
        RQLInterpreterValue expressionValue = new RQLInterpreterValue(Types.INT);
        ex1.evaluate();
        RQLInterpreterValue ex1Val = ex1.getValue();
        //short circuit
        boolean shortCircuit = false;
        if (ex1Val.getType() == Types.NULL
                || (ex1Val.getType() == Types.INT && ex1Val.getIntVal() == 0)) {
            shortCircuit = true;
        }

        if (!shortCircuit) {
            ex2.evaluate();
            RQLInterpreterValue ex2Val = ex2.getValue();

            if ((ex1Val.getType() == Types.INT || ex1Val.getType() == Types.NULL)
                    && (ex2Val.getType() == Types.INT || ex2Val.getType() == Types.NULL)) {
                if (ex1Val.getIntVal() != 0 && ex2Val.getIntVal() != 0) {
                    expressionValue.setValInt(1);
                } else {
                    expressionValue.setValInt(0);
                }
            } else {
                RQLRunTimeWarning w = new RQLRunTimeWarning(
                        RQLRunTimeWarning.WarningType.INCOMPATIBLE_TYPES,
                        this.getClass().getName() + this.hashCode(),
                        "Expected int types in logical AND, float or string given"
                );
                rqlEnvironment.addRunTimeWarning(w);
                expressionValue.setValType(Types.NULL);
            }
        }
        this.value = expressionValue;
    }
}
