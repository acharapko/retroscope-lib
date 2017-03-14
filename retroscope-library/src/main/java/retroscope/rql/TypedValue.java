package retroscope.rql;

/**
 * Created by Aleksey on 1/22/2017.
 * interface representing typed value in RQL
 */
public interface TypedValue {

    Types getType();

    double getDoubleVal();

    long getIntVal();

    String getStringVal();

}
