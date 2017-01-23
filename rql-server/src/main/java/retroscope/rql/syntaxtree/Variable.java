package retroscope.rql.syntaxtree;

import retroscope.rql.RQLEnvironment;
import retroscope.rql.Types;
import retroscope.rql.Valuable;

import java.util.HashMap;

public class Variable extends Expression
{
	private String name;
	private HashMap<String, Valuable> symbolTable;
	private RQLEnvironment rqlEnv;
    private boolean isLogParam;
    private boolean hasFullName = true;

    public Variable(String superName, String name, String field, RQLEnvironment rqlEnv, boolean isLogParam)
    {
        super(rqlEnv);
        this.rqlEnv = rqlEnv;
        if (superName != null && !superName.equals("")) {
            cnstrct(superName + "." + name + ":" + field,  rqlEnv, isLogParam);
        } else {
            hasFullName = false;
            cnstrct(name + ":" + field, rqlEnv, isLogParam);
        }
    }

    public Variable(String superName, String name, RQLEnvironment rqlEnv, boolean isLogParam)
    {
        super(rqlEnv);
        cnstrct(superName + "." + name, rqlEnv, isLogParam);
    }

	public Variable(String name, RQLEnvironment rqlEnv, boolean isLogParam)
	{
	    super(rqlEnv);
	    hasFullName = false;
        cnstrct(name, rqlEnv, isLogParam);
	}

	private void cnstrct(String name, RQLEnvironment rqlEnv, boolean isLogParam)
    {
        this.name = name;
        this.rqlEnv = rqlEnv;
        symbolTable = rqlEnv.getSymbolTable();
        this.isLogParam = isLogParam;
        symbolTable.put(name, new SimpleSymbol());
    }

    public RQLInterpreterValue[] getValues() {
        Valuable symbol = symbolTable.get(name);
        RQLInterpreterValue[] v = symbol.getValues();
        if (v == null || v.length == 0 || (v.length == 1 && v[0].type == Types.NULL)) {
            RQLRunTimeWarning w = new RQLRunTimeWarning(
                    RQLRunTimeWarning.WarningType.VARIABLE_UNDEFINED,
                    this.getClass().getName() + this.hashCode(),
                    "Variable " + name + " is undefined for entire or partial log duration"
            );
            rqlEnvironment.addRunTimeWarning(w);
        }
        return v;
    }

    public void evaluate() {
        if (!hasFullName) {
            hasFullName = true;
            name = rqlEnv.getDefaultLogName() + "." + name;
        }

    }
}
