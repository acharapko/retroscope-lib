package retroscope.datamodel.datastruct;

public class Null extends RQLSymbol {

    public Null() {
        super();
    }

    @Override
    public void merge(RQLSymbol mergeSymbol) {

    }

    @Override
    public RQLSymbol clone() {
        return new Null();
    }

    @Override
    public String toRQLString() {
        return this.getName() + ":null";
    }

    @Override
    public String toEmitRQLString() {
        return this.getName() + ":null";
    }

    @Override
    public String toValueString() {
        return "null";
    }
}
