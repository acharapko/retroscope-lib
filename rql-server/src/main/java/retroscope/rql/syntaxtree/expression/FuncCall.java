package retroscope.rql.syntaxtree.expression;

import retroscope.rql.QueryEnvironment;
import retroscope.rql.functions.*;

/**
 * Created by Aleksey on 12/20/2016.
 *
 */
public class FuncCall extends Expression {

    private ExpressionList params;
    private String name;
    private RQLBuiltInFunction thisFunc;

    public FuncCall(QueryEnvironment queryEnvironment, String name, ExpressionList params)
    {
        super(queryEnvironment);
        this.params = params;
        this.name = name;
        findFunc();
    }

    private void findFunc() {
        if (name.equals("Empty")) {
            thisFunc = new Empty(params, queryEnvironment);
        } else if (name.equals("Node")) {
            thisFunc = new Node(params, queryEnvironment);
        } else if (name.equals("Sin")) {
            thisFunc = new Sin(params, queryEnvironment);
        } else if (name.equals("Cos")) {
            thisFunc =  new Cos(params, queryEnvironment);
        } else if (name.equals("Tan")) {
            thisFunc = new Tan(params, queryEnvironment);
        } else if (name.equals("Abs")) {
            thisFunc = new Abs(params, queryEnvironment);
        } else if (name.equals("Max")) {
            thisFunc = new Max(params, queryEnvironment);
        } else if (name.equals("Now")) {
            thisFunc = new Now(params, queryEnvironment);
        } else if (name.equals("HLCFromPT")) {
            thisFunc = new HLCFromPT(params, queryEnvironment);
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
