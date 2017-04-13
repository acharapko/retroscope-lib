package retroscope.rql.syntaxtree.expression;

import retroscope.rql.*;
import retroscope.rql.memory.RQLInterpreterItemWrapper;
import retroscope.rql.errors.RQLRunTimeWarning;

public class Variable extends Expression
{
	private String name;
	private QueryEnvironment rqlEnv;
    private boolean isLogParam;
    private boolean hasFullName = true;
    private String field;
    private int id;

    public Variable(String superName, String name, String field, QueryEnvironment rqlEnv, boolean isLogParam)
    {
        super(rqlEnv);
        this.rqlEnv = rqlEnv;
        if (superName != null && !superName.equals("")) {
            cnstrct(superName + "." + name, field,  rqlEnv, isLogParam);
        } else {
            if (name.startsWith("$")) {
                name = name.substring(1);
                this.field = field;
                this.rqlEnv = rqlEnv;
                try {
                    id = Integer.parseInt(name) - 1;
                } catch (Exception e) {
                    RQLRunTimeWarning w = new RQLRunTimeWarning(
                            RQLRunTimeWarning.WarningType.VARIABLE_UNDEFINED,
                            this.getClass().getName() + this.hashCode(),
                            "Variable placeholder $" + name + " is not numeric"
                    );
                    queryEnvironment.addRunTimeWarning(w);
                }
            } else {
                hasFullName = false;
                cnstrct(name, field, rqlEnv, isLogParam);
            }
        }
    }

    public Variable(String superName, String name, QueryEnvironment rqlEnv, boolean isLogParam)
    {
        super(rqlEnv);
        cnstrct(superName + "." + name, "", rqlEnv, isLogParam);
    }

	public Variable(String name, QueryEnvironment rqlEnv, boolean isLogParam)
	{
	    super(rqlEnv);
	    hasFullName = false;
        cnstrct(name, "", rqlEnv, isLogParam);
	}

	private void cnstrct(String name, String field, QueryEnvironment rqlEnv, boolean isLogParam)
    {
        this.name = name;
        this.field = field;
        this.rqlEnv = rqlEnv;
        this.isLogParam = isLogParam;
        this.id = rqlEnv.addPlaceholder(name);
    }

    public RQLInterpreterValue getValue() {
        RQLInterpreterValue val = null;

        RQLInterpreterItemWrapper wrapper = rqlEnv.getPlaceholder(id).getItem();
        if (wrapper != null) {
            RQLItemValue v = wrapper.getItem().getField(field);
            if (v == null) {
                RQLRunTimeWarning w = new RQLRunTimeWarning(
                        RQLRunTimeWarning.WarningType.VARIABLE_UNDEFINED,
                        this.getClass().getName() + this.hashCode(),
                        "Variable " + name + " is undefined for entire or partial log duration"
                );
                queryEnvironment.addRunTimeWarning(w);
            } else {
                val = new RQLInterpreterValue(v);
            }
        } else {
            RQLRunTimeWarning w = new RQLRunTimeWarning(
                    RQLRunTimeWarning.WarningType.VARIABLE_UNDEFINED,
                    this.getClass().getName() + this.hashCode(),
                    "Variable " + name + " is undefined for entire or partial log duration"
            );
            queryEnvironment.addRunTimeWarning(w);
        }
        if (val == null) {
            val = new RQLInterpreterValue(Types.NULL);
        }
        return val;
    }

    public void evaluate() {
        if (!hasFullName) {
            hasFullName = true;
            name = rqlEnv.getDefaultLogName() + "." + name;
        }
    }

    public String getName() {
        return name;
    }
}
