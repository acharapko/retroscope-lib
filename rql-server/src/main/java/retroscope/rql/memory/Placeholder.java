package retroscope.rql.memory;

/**
 * Created by Aleksey on 2/25/2017.
 *
 */
public class Placeholder {

    private RQLInterpreterItemWrapper itemWrapper;
    private int id;
    private String symbolName;
    private int version = 0;
    private boolean reset = false;

    public Placeholder(RQLInterpreterItemWrapper item, int id, String symbolName) {
        this.itemWrapper = item;
        this.id = id;
        this.symbolName = symbolName;
    }

    public Placeholder(int id, String symbolName) {
        this.id = id;
        this.symbolName = symbolName;
    }

    public RQLInterpreterItemWrapper getItemWrapper() {
        return itemWrapper;
    }

    public int getId() {
        return id;
    }

    public String getSymbolName() {
        return symbolName;
    }

    public void setSymbolName(String symbolName) {
        this.symbolName = symbolName;
    }

    public void setItemWrapper(RQLInterpreterItemWrapper itemWrapper) {
        this.itemWrapper = itemWrapper;
    }

    public void incrementVersion() {
        version++;
    }

    public void resetVersion() {
        version = 0;
    }

    public void clearAll() {
        version = 0;
        this.itemWrapper = null;
        this.reset = false;
    }

    public int getVersion() {
        return version;
    }

    public boolean isReset() {
        return reset;
    }

    public void setReset(boolean reset) {
        this.reset = reset;
    }
}
