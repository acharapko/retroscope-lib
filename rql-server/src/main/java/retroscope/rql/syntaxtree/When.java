package retroscope.rql.syntaxtree;

/**
 * Created by Aleksey on 12/20/2016.
 *
 */
public class When {

    private Expression expression;

    public When(Expression expression) {
        this.expression = expression;
    }

    public Expression getConditions() {
        return expression;
    }

}
