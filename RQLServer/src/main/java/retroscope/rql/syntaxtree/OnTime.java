package retroscope.rql.syntaxtree;

import retroscope.rql.syntaxtree.expression.Expression;

/**
 * Created by Aleksey on 12/20/2016.
 */
public class OnTime {

   private Expression timeEx1, timeEx2;

    public OnTime(Expression ex1, Expression ex2) {
        timeEx1 = ex1;
        timeEx2 = ex2;
    }

    public Expression getTimeEx1() {
        return timeEx1;
    }

    public Expression getTimeEx2() {
        return timeEx2;
    }
}
