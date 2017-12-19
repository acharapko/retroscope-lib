package retroscope.rql.syntaxtree.expression.stackedexpression;

import retroscope.rql.syntaxtree.IdentifierList;
import retroscope.rql.syntaxtree.expression.Expression;

public class IteratingFunc {

    private IdentifierList ids;
    private Expression ex1, ex2;

    public IteratingFunc(IdentifierList ids, Expression ex1, Expression ex2) {
        this.ids = ids;
        this.ex1 = ex1;
        this.ex2 = ex2;
    }

    public IdentifierList getIds() {
        return ids;
    }

    public Expression getEx1() {
        return ex1;
    }

    public Expression getEx2() {
        return ex2;
    }
}
