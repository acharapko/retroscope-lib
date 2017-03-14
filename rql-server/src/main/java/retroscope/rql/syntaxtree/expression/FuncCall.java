package retroscope.rql.syntaxtree.expression;

import retroscope.rql.RQLEnvironment;
import retroscope.rql.functions.*;

/**
 * Created by Aleksey on 12/20/2016.
 *
 */
public class FuncCall extends Expression {

    private ExpressionList params;
    private String name;
    private RQLBuiltInFunction thisFunc;

    public FuncCall(RQLEnvironment rqlEnvironment, String name,  ExpressionList params)
    {
        super(rqlEnvironment);
        this.params = params;
        this.name = name;
        findFunc();
    }

    private void findFunc() {
        if (name.equals("Empty")) {
            thisFunc = new Empty(params, rqlEnvironment);
        } else if (name.equals("Node")) {
            thisFunc = new Node(params, rqlEnvironment);
        } else if (name.equals("Sin")) {
            thisFunc = new Sin(params, rqlEnvironment);
        } else if (name.equals("Cos")) {
            thisFunc =  new Cos(params, rqlEnvironment);
        } else if (name.equals("Tan")) {
            thisFunc = new Tan(params, rqlEnvironment);
        } else if (name.equals("Abs")) {
            thisFunc = new Abs(params, rqlEnvironment);
        } else if (name.equals("Max")) {
            thisFunc = new Max(params, rqlEnvironment);
        } else if (name.equals("Now")) {
            thisFunc = new Now(params, rqlEnvironment);
        } else if (name.equals("HLCFromPT")) {
            thisFunc = new HLCFromPT(params, rqlEnvironment);
        }
    }

    public RQLInterpreterValue getValue() {
        return thisFunc.getValue();
    }


    public void evaluate() throws IllegalExpressionException {
        /*
        Here we evaluate the function.
        First we need to find the function in the list of registered functions for current Environemnt
        Second we check if parameters needed to function match what we have
        Third, we execute the function (built it funcion for RQL only at this time)
         */
        thisFunc.evaluate();

    }
}
