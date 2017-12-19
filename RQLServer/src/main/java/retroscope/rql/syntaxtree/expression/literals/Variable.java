package retroscope.rql.syntaxtree.expression.literals;

import retroscope.datamodel.datastruct.Null;
import retroscope.datamodel.datastruct.RQLSymbol;
import retroscope.datamodel.datastruct.struct.RQLStruct;
import retroscope.rql.environment.EnvironmentStack;
import retroscope.rql.syntaxtree.expression.Expression;
import retroscope.rql.syntaxtree.expression.IllegalExpressionException;

public class Variable extends Expression
{
	private String name;
    private int id = -1;
    private Variable nested;

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
        //if (dirty) {
            if (nested == null) {
                RQLSymbol val = getEnvironment().getSymbol(this.name);
                if (val == null) {
                    val = new Null();
                }
                this.value = val;
            } else {
                RQLSymbol s = getEnvironment().getSymbol(this.name);
                this.value = recursiveFind(s, nested);
            }
            //dirty = false;
        //}
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
}
