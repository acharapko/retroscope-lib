package retroscope.datamodel.datastruct.variables;

public class DoubleRQLVariable extends RQLVariable<Double> {

    public DoubleRQLVariable(double val) {
        super();
        this.setValue(val);
    }

    public DoubleRQLVariable(String name, double val) {
        super(name, val);
    }

    public DoubleRQLVariable clone() {
        DoubleRQLVariable clone = new DoubleRQLVariable(value);
        clone.setName(this.getName());
        return clone;
    }

    @Override
    public void negate() {
        this.value = - this.value;
    }
}
