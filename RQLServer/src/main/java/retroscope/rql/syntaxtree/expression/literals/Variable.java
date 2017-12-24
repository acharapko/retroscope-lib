package retroscope.rql.syntaxtree.expression.literals;

import retroscope.datamodel.datastruct.Null;
import retroscope.datamodel.datastruct.RQLSymbol;
import retroscope.datamodel.datastruct.struct.RQLStruct;
import retroscope.rql.environment.EnvironmentStack;
import retroscope.rql.syntaxtree.expression.Expression;
import retroscope.rql.syntaxtree.expression.IllegalExpressionException;

import java.util.ArrayList;

public class Variable extends Expression
{
	private String name;
    private int id = -1;
    private Variable nested;

    RQLSymbol val;

    public Variable(String name)
    {
        this.name = name;
    }

    public Variable(String name, Variable nested)
    {
        this.name = name;
        this.nested = nested;
    }

    @Override
    public void setEnvironmentStack(EnvironmentStack stack) {
        super.setEnvironmentStack(stack);
    }

    public RQLSymbol getValue() {
        return this.value;
    }

    private RQLSymbol recursiveFind(RQLSymbol symb, Variable nestedVar) {
        if (nestedVar == null) {
            return symb;
        }
        if (symb instanceof RQLStruct) {
            return recursiveFind(((RQLStruct) symb).getElement(nestedVar.getName()), nestedVar.getNested());
        }
        return new Null();
    }

    public void evaluate() throws IllegalExpressionException {
        if (dirty) {
            retrieveValFromEnv();
            if (nested == null) {
                this.value = val;
            } else {
                this.value = recursiveFind(val, nested);
            }
        }
    }

    private void retrieveValFromEnv() {
        val = getEnvironment().getSymbol(this.name);
        if (val == null) {
            val = new Null();
        }
    }
    @Override
    public boolean computeDirty() {

        retrieveValFromEnv();
        dirty = val.isDirty();
        return dirty;
    }

    public String getName() {
        return name;
    }

    public Variable getNested() {
        return nested;
    }

    public Variable clone() {
        Variable nc = null;
        if (nested != null) {
            nc = nested.clone();
        }
        Variable clone = new Variable(name, nc);
        clone.id = this.id;
        return clone;
    }

    @Override
    public ArrayList<Variable> findVars() {
        ArrayList<Variable> v = new ArrayList<>();
        v.add(this);
        return v;
    }
}
