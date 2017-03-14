package retroscope.rql.errors;

/**
 * Created by ALEKS on 1/12/2017.
 * Exception to be thrown during RQL runtime, if runtime errors arise
 */
public class RQLRunTimeException extends Exception {
    public RQLRunTimeException(String message) {
        super(message);
    }
}
