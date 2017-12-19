package retroscope.datamodel.datastruct.variables;

public class LongRQLVariable extends RQLVariable<Long> {

    public LongRQLVariable(long val) {
        super();
        this.setValue(val);
    }

    public LongRQLVariable(String name, long val) {
        super(name, val);
    }

    public LongRQLVariable clone() {
        LongRQLVariable clone = new LongRQLVariable(value);
        clone.setName(this.getName());
        return clone;
    }

    @Override
    public void negate() {
        this.value = - this.value;
    }

}
