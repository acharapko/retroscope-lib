package retroscope.rql.syntaxtree.expression;

import retroscope.rql.errors.RQLRunTimeException;

/**
 * Created by Aleksey on 12/21/2016.
 *
 */
public class IllegalExpressionException extends RQLRunTimeException {
    public IllegalExpressionException(String message) {
        super(message);
    }
}
