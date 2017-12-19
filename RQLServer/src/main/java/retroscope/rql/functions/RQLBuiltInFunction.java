package retroscope.rql.functions;

import retroscope.datamodel.datastruct.RQLSymbol;
import retroscope.rql.environment.EnvironmentStack;
import retroscope.rql.parser.Valuable;
import retroscope.rql.syntaxtree.expression.Expression;
import retroscope.rql.syntaxtree.expression.ExpressionList;

/**
 * Created by Aleksey on 1/21/2017.
 *
 */
public abstract class RQLBuiltInFunction implements Valuable {

    protected ExpressionList params;
    protected EnvironmentStack environmentStack;
    protected RQLSymbol value;

    public RQLBuiltInFunction(ExpressionList params, EnvironmentStack environmentStack) {
        this.params = params;
        this.environmentStack = environmentStack;
    }

    public RQLSymbol getValue() {
        return value;
    }

    public void setEnvironmentStack(EnvironmentStack environmentStack) {
        this.environmentStack = environmentStack;
        for (Expression ex: params.getList()) {
            ex.setEnvironmentStack(environmentStack);
        }
    }
}
