package retroscope.rql.syntaxtree.expression;

import retroscope.rql.TypedValue;
import retroscope.rql.Types;
import retroscope.rql.syntaxtree.link.Link;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleksey on 12/24/2016.
 * This class represents teh RQL value as being used by the interpreter. It is somewhat similar to
 * RQLItemValue in regard that it has type and similar value getters, but it also has some additional data,
 * such as which node the value originated.
 */
public class RQLInterpreterValue implements TypedValue {

    protected Types type;
    protected long intValue;
    protected double floatValue;
    protected String stringValue = null;
    protected int sourceNodeId;

    public RQLInterpreterValue(Types type) {
        this.type = type;
    }

    public RQLInterpreterValue(TypedValue original) {
        this.type = original.getType();
        switch (type) {
            case INT:
                this.intValue = original.getIntVal();
                break;
            case DOUBLE:
                this.floatValue = original.getDoubleVal();
                break;
            case STRING:
                this.stringValue = original.getStringVal();
        }
    }

    public RQLInterpreterValue setValInt(long valInt) {
        this.intValue = valInt;
        type = Types.INT;
        return this;
    }

    public RQLInterpreterValue setValFloat(double valFloat) {
        this.floatValue = valFloat;
        this.type = Types.DOUBLE;
        return this;
    }

    public RQLInterpreterValue setValStr(String valStr) {
        this.stringValue = valStr;
        type = Types.STRING;
        return this;
    }

    public RQLInterpreterValue setValType(Types valType) {
        this.type = valType;
        return this;
    }

    public int getSourceNodeId() {
        return sourceNodeId;
    }

    public void setSourceNodeId(int sourceNodeId) {
        this.sourceNodeId = sourceNodeId;
    }

    public Types getType() {
        return this.type;
    }

    public long getIntVal()
    {
        return intValue;
    }

    public double getDoubleVal()
    {
        return floatValue;
    }

    public String getStringVal()
    {
        return stringValue;
    }

    public static Types typeDetect(RQLInterpreterValue ex1, RQLInterpreterValue ex2) {
        if (ex1.type == Types.STRING && ex2.type == Types.STRING) {
            return Types.STRING;
        }
        if (ex1.type == Types.DOUBLE || ex2.type == Types.DOUBLE) {
            return Types.DOUBLE;
        } else {
            return Types.INT;
        }
    }
}
