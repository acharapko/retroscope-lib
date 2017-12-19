package retroscope.net.ignite;

public interface StreamEntry extends Cloneable {

    long getHLCTime();

    String getEntry();
}
