package retroscope.datamodel.datastruct;

public interface RQLDataStructure extends Cloneable{

    String getName();

    void setName(String name);

    String toRQLString();

}
