package retroscope.rql.syntaxtree.expression;

import retroscope.datamodel.datastruct.RQLSymbol;
import retroscope.rql.environment.EnvironmentStack;
import retroscope.rql.errors.RQLRunTimeException;
import retroscope.rql.functions.RQLBuiltInFunction;
import retroscope.rql.syntaxtree.expression.assign.AssignExpression;
import retroscope.rql.syntaxtree.expression.assign.DeclareExpression;
import retroscope.rql.syntaxtree.expression.literals.Variable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Created by Aleksey on 12/20/2016.
 *
 */
public class FuncCall extends Expression {

    private ExpressionList params;
    private String name;
    private RQLBuiltInFunction thisFunc;

    public FuncCall(String name, ExpressionList params)
    {
        this.params = params;
        this.name = name;
        findFunc();
    }

    private void findFunc() {
        //reflection to invoke built-in flat functions. These functions are not placed on the stack and instead
        //simply produce the results in the current environment

        try {
            Class<?> clazz = Class.forName("retroscope.rql.functions." + name);
            Constructor<?> constructor = clazz.getConstructor(ExpressionList.class, EnvironmentStack.class);
            Object func = constructor.newInstance(params, stack);
            if (func instanceof RQLBuiltInFunction) {
                thisFunc = (RQLBuiltInFunction) func;
            }
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | InvocationTargetException | IllegalAccessException e) {
            //getEnvironment().addRunTimeException(new RQLRunTimeException("No function " + name + " is defined"));
            e.printStackTrace();
        }
    }

    public RQLSymbol getValue() {
        return thisFunc.getValue();
    }


    public void evaluate() throws IllegalExpressionException {
        /*
        Here we evaluate the function.
        First we need to find the function in the list of registered functions for current Environment
        Second we check if parameters needed to function match what we have
        Third, we execute the function (built it function for RQL only at this time)
         */

         thisFunc.evaluate();
    }

    public void setEnvironmentStack(EnvironmentStack stack) {
        this.stack = stack;
        thisFunc.setEnvironmentStack(stack);

    }

    public FuncCall clone() {
        ExpressionList clonedParams = params.clone();
        return new FuncCall(name, clonedParams);
    }

    @Override
    public boolean computeDirty() {
        dirty = false;
        for (Expression ex1: params.getList()) {
            if (ex1.computeDirty()) {
                dirty = true;
            }
        }
        return dirty;
    }

    @Override
    public ArrayList<Variable> findVars() {
        ArrayList<Variable> v = new ArrayList<>();
        for (Expression ex1: params.getList()) {
            v.addAll(ex1.findVars());
        }
        return v;
    }
}
