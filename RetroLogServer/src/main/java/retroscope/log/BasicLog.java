package retroscope.log;

/**
 * Created by Aleksey on 1/31/2017.
 *
 */
public interface BasicLog<K, V> {

    String getName();

    //LogEntry<K, V> getHead();

    //LogEntry<K, V> getTail();

    int getLength();
}
