package retroscope.rql.syntaxtree.expression;

import retroscope.rql.*;
import retroscope.rql.memory.RQLInterpreterItemWrapper;
import retroscope.rql.errors.RQLRunTimeWarning;

public class Variable extends Expression
{
	private String name;
	private QueryEnvironment rqlEnv;
    private boolean hasFullName = true;
    private String field;
    private int id;
    private Expression nodeExp;

    public Variable(String superName, String name, String field, QueryEnvironment rqlEnv, Expression nodeExp)
    {
        super(rqlEnv);
        this.rqlEnv = rqlEnv;
        if (superName != null && !superName.equals("")) {
            cnstrct(superName + "." + name, field,  rqlEnv, nodeExp);
        } else {
            if (name.startsWith("$")) {
                name = name.substring(1);
                this.field = field;
                this.rqlEnv = rqlEnv;
                try {
                    id = Integer.parseInt(name);
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
                cnstrct(name, field, rqlEnv, nodeExp);
            }
        }
    }

    public Variable(String superName, String name, QueryEnvironment rqlEnv,  Expression nodeExp)
    {
        this(superName, name, "", rqlEnv, nodeExp);
    }

	public Variable(String name, QueryEnvironment rqlEnv, Expression nodeExp)
	{
        this(null, name, "", rqlEnv, nodeExp);
	}

	private void cnstrct(String name, String field, QueryEnvironment rqlEnv,  Expression nodeExp)
    {
        this.name = name;
        this.field = field;
        this.rqlEnv = rqlEnv;
        this.nodeExp = nodeExp;
        this.id = rqlEnv.addPlaceholder(name);
    }

    public RQLInterpreterValue getValue() {
        RQLInterpreterValue val = null;

        RQLInterpreterItemWrapper wrapper = rqlEnv.getPlaceholder(id).getItemWrapper();
        if (wrapper != null && wrapper.getItem() != null) {

            if (nodeExp != null && nodeExp.getValue() != null) {
                if (nodeExp.getValue().getType() != Types.INT) {
                    RQLRunTimeWarning w = new RQLRunTimeWarning(
                            RQLRunTimeWarning.WarningType.VARIABLE_UNDEFINED,
                            this.getClass().getName() + this.hashCode(),
                            "Variable " + name + " node specifier must be integer"
                    );
                    queryEnvironment.addRunTimeWarning(w);
                }
                long nodeId = nodeExp.getValue().getIntVal();
                if (nodeId != wrapper.getNodeId()) {
                    val = new RQLInterpreterValue(Types.NULL);
                    return val;
                }
            }
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
                val.setSourceNodeId(wrapper.getNodeId());
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

    public void evaluate() throws IllegalExpressionException {
        if (!hasFullName) {
            hasFullName = true;
            name = rqlEnv.getDefaultLogName() + "." + name;
        }
        if (nodeExp != null) {
            nodeExp.evaluate();
        }
    }

    public String getName() {
        return name;
    }
}
