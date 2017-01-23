package retroscope.rql.syntaxtree;

/**
 * Created by Aleksey on 12/20/2016.
 *
 */
public class Conditions {

    private Expression[] expressions;

    public Conditions(Expression e) {
        expressions = new Expression[1];
        expressions[0] = e;
    }

    public Conditions(Expression e, Conditions eList) {
        expressions = new Expression[eList.getExpressions().length + 1];
        System.arraycopy(eList.getExpressions(), 0, expressions, 1, eList.getExpressions().length);
        expressions[0] = e;
    }

    public Expression[] getExpressions() {
        return expressions;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (Expression p : expressions) {
            sb.append(p.toString());
            sb.append(", ");
        }
        return sb.toString();
    }
}
