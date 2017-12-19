package retroscope.rql.syntaxtree.expression.condition;

import retroscope.rql.syntaxtree.expression.Expression;

public class Ternary {

    private Expression choice1;
    private Expression choice2;

    public Ternary(Expression choice1, Expression choice2) {
        this.choice1 = choice1;
        this.choice2 = choice2;
    }

    public Ternary(Expression choice1) {
        this.choice1 = choice1;
    }

    public Expression getChoice1() {
        return choice1;
    }

    public Expression getChoice2() {
        return choice2;
    }
}
