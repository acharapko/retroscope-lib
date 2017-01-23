package retroscope.rql.syntaxtree;

import retroscope.rql.Valuable;
import retroscope.rql.RQLEnvironment;
import retroscope.rql.syntaxtree.link.Link;

import java.util.ArrayList;

/**
 * Created by Aleksey on 12/20/2016.
 *
 */
public abstract class Expression implements Valuable{

    //private Variable valID = null;
    protected RQLInterpreterValue values[];
    protected RQLEnvironment rqlEnvironment;

    public Expression(RQLEnvironment rqlEnvironment)
    {
        this.rqlEnvironment = rqlEnvironment;
    }

    public RQLInterpreterValue[] getValues() {
        return values;
    }


    public abstract void evaluate() throws IllegalExpressionException;


}
