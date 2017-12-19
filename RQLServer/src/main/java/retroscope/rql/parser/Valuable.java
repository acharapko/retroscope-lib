package retroscope.rql.parser;

import retroscope.datamodel.datastruct.RQLSymbol;
import retroscope.rql.environment.EnvironmentStack;
import retroscope.rql.syntaxtree.expression.IllegalExpressionException;

/**
 * Created by Aleksey on 1/21/2017.
 */
public interface Valuable {

    RQLSymbol getValue();

    void evaluate() throws IllegalExpressionException;

    void setEnvironmentStack(EnvironmentStack environment);
}
