package retroscope.rql.environment;

import retroscope.rql.errors.RQLRunTimeException;
import retroscope.rql.errors.RQLRunTimeWarning;
import retroscope.datamodel.datastruct.RQLSymbol;
import retroscope.rql.syntaxtree.expression.Expression;
import retroscope.rql.syntaxtree.expression.IllegalExpressionException;

import java.util.ArrayList;

/**
 * Created by Aleksey on 4/9/2017.
 *
 */
public class Environment {

    //memory stuff
    private SymbolTable symbolTable;
    protected Environment parentEnv;

    protected Environment globalEnv;

    //global view of the environment stack
    protected EnvironmentStack stack;

    //commands
    protected Expression expression;

    //error stuff
    protected ArrayList<RQLRunTimeException> exceptions;
    protected ArrayList<RQLRunTimeWarning> warnings;

    public Environment() {
        this(new EnvironmentStack());
    }

    public Environment(EnvironmentStack stack) {
        symbolTable = new SymbolTable();
        exceptions = new ArrayList<>();
        warnings = new ArrayList<>();
        if (!stack.isEmpty()) {
            this.parentEnv = stack.peek();
        }
        stack.push(this);
        this.stack = stack;
    }

    public Environment getGlobal() {
        if (globalEnv == null) {
            globalEnv = this;
            while (globalEnv.parentEnv != null) {
                globalEnv = globalEnv.parentEnv;
            }
        }
        return globalEnv;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public ArrayList<RQLRunTimeException> getExceptions() {
        return exceptions;
    }

    public void addRunTimeException(RQLRunTimeException e) {
        exceptions.add(e);
    }

    public boolean hasRunTimeErrors() {
        return exceptions.size() >= 1;
    }

    public ArrayList<RQLRunTimeWarning> getWarnings() {
        return warnings;
    }

    public void addRunTimeWarning(RQLRunTimeWarning e) {
        boolean exists = false;
        for (RQLRunTimeWarning w : warnings) {
            if (w.equals(e)) {
                exists = true;
            }
        }
        if (!exists) {
            warnings.add(e);
        }
    }

    public void resetErrors() {
        exceptions = new ArrayList<>();
        warnings = new ArrayList<>();
    }

    public Environment getParentEnv() {
        return parentEnv;
    }

    public boolean isPredecessor(Environment p) {
        if (p == this) {
            return true;
        } else if (this.parentEnv != null) {
            return this.getParentEnv().isPredecessor(p);
        }
        return false;
    }



    public void addExpression(Expression ex) {
        ex.setEnvironmentStack(stack);
        this.expression = ex;
    }

    public Expression getExpression() {
        return expression;
    }

    public void evaluate() throws IllegalExpressionException {
        expression.evaluate();
    }

    public Environment invoke(Expression ex) throws IllegalExpressionException {
        Environment topEnv = new Environment(this.stack);
        topEnv.addExpression(ex);
        topEnv.evaluate();
        return topEnv;
    }


    /*----------------------------------------------
     * Symbol Table stuff
     *---------------------------------------------*/

    public RQLSymbol getSymbolInThisEnv(String key) {
        return symbolTable.get(key);
    }

    public RQLSymbol getSymbol(String key) {
        RQLSymbol s = symbolTable.get(key);
        if (s == null && parentEnv != null) {
            s = parentEnv.getSymbol(key);
        }
        return s;
    }

    public void clearSymbolTable(Environment cleaner) {
        if (isPredecessor(cleaner)) {
            symbolTable.clear();
        }
    }

    public void setSymbol(String key, RQLSymbol val) {
        this.putSymbol(key, val);
    }

    public void assignToSymbol(String key, RQLSymbol val) {
        RQLSymbol s = symbolTable.get(key);
        if (s == null && parentEnv != null) {
            parentEnv.assignToSymbol(key, val);
        } else if (s != null) {
            this.symbolTable.put(key, val);
        }
    }

    public void putSymbol(String key, RQLSymbol val) {
        this.symbolTable.put(key, val);
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    public void terminate() {
        if (stack.peek().equals(this)) {
            stack.pop();
        }
    }
}
