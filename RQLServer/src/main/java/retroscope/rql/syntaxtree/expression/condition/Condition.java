package retroscope.rql.syntaxtree.expression.condition;

import retroscope.datamodel.datastruct.RQLSymbol;
import retroscope.datamodel.datastruct.variables.LongRQLVariable;
import retroscope.rql.syntaxtree.expression.Expression;
import retroscope.rql.syntaxtree.expression.IllegalExpressionException;

public class Condition extends Expression {

    private Expression condition;
    private Ternary options;

    public Condition(Expression condition, Ternary options) {
        this.condition = condition;
        this.options = options;
    }

    @Override
    public void evaluate() throws IllegalExpressionException {
        condition.evaluate();
        RQLSymbol condVal = condition.getValue();
        if (condVal instanceof LongRQLVariable) {
            if (((LongRQLVariable) condVal).getValue() != 0) {
                options.getChoice1().evaluate();
                value = options.getChoice1().getValue();
            } else if (options.getChoice2() != null) {
                options.getChoice2().evaluate();
                value = options.getChoice2().getValue();
            } else {
                
                //skip action treated like TRUE
                this.value = new LongRQLVariable(1);
            }
        }
    }

    @Override
    public Expression clone() {
        return null;
    }
}
