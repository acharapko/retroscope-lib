package retroscope.rql.syntaxtree.expression;

import retroscope.rql.Valuable;
import retroscope.rql.QueryEnvironment;

/**
 * Created by Aleksey on 12/20/2016.
 *
 */
public abstract class Expression implements Valuable{

    //private Variable valID = null;
    protected RQLInterpreterValue value;
    protected QueryEnvironment queryEnvironment;

    public Expression(QueryEnvironment queryEnvironment)
    {
        this.queryEnvironment = queryEnvironment;
    }

    public RQLInterpreterValue getValue() {
        return value;
    }


    public abstract void evaluate() throws IllegalExpressionException;

}
