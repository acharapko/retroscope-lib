package retroscope;

/**
 * Created by aleksey on 10/17/16.
 *
 */
public abstract class RetroscopeException extends Exception {

    public final static int UNIDENTIFIED_ERROR= 1;
    public final static int LOG_OUT_OF_BOUNDS= 2;
    public final static int LOG_DOES_NOT_EXIST = 3;
    public final static int RQL_ITEM_ERROR = 4;
    public final static int SNAPSHOT_DOES_NOT_EXIST = 5;
    public final static int CLIENT_NETWORK_ERROR = 6;
    public final static int INCORRECT_LOG_TYPE = 7;

    private int type = 1;

    public RetroscopeException(String message) {
        this(1, message);
    }

    public RetroscopeException(int type, String message) {
        super(message);
        type = this.type;
    }

    public RetroscopeException(int type) {
        super("");
        type = this.type;
    }

    public int getType() {
        return type;
    }
}
