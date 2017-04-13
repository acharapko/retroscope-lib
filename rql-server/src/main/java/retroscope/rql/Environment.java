package retroscope.rql;

import retroscope.rql.errors.RQLRunTimeException;
import retroscope.rql.errors.RQLRunTimeWarning;
import retroscope.rql.memory.SimpleSymbol;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Aleksey on 4/9/2017.
 *
 */
public class Environment {

    //memory stuff
    private HashMap<String, SimpleSymbol> symbolTable;
    protected Environment parentEnv;

    //error stuff
    protected ArrayList<RQLRunTimeException> exceptions;
    protected ArrayList<RQLRunTimeWarning> warnings;

    public Environment() {
        this(null);
    }

    public Environment(Environment parent) {
        symbolTable = new HashMap<String, SimpleSymbol>();
        exceptions = new ArrayList<RQLRunTimeException>();
        warnings = new ArrayList<RQLRunTimeWarning>();
        this.parentEnv = parent;
    }

    public HashMap<String, SimpleSymbol> getSymbolTable() {
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

    public SimpleSymbol getSymbol(String key) {
        SimpleSymbol s = symbolTable.get(key);
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

    public void setSymbol(String key, SimpleSymbol val) {
        this.putSymbol(key, val);
    }

    public void putSymbol(String key, SimpleSymbol val) {
        this.symbolTable.put(key, val);
    }
}
