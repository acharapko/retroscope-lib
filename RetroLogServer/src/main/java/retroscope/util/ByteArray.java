package retroscope.util;

import java.io.Serializable;
import java.util.Arrays;
import org.apache.commons.codec.binary.Base64;

/**
 * Created by aleksey on 10/18/16.
 * This class is borrowed from project Voldemort
 */
/**
 * A byte array container that provides an equals and hashCode pair based on the
 * contents of the byte array. This is useful as a key for Maps.
 */
public final class ByteArray implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final ByteArray EMPTY = new ByteArray();

    private final byte[] underlying;

    public ByteArray(byte... underlying) {
        this.underlying = underlying;
    }

    public ByteArray(String base64) {
        this.underlying = Base64.decodeBase64(base64.getBytes());
    }

    public byte[] get() {
        return underlying;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(underlying);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(!(obj instanceof ByteArray))
            return false;
        ByteArray other = (ByteArray) obj;
        return Arrays.equals(underlying, other.underlying);
    }

    public int length() {
        return underlying.length;
    }

    public String toString() {
        //Base64
        byte[] encodedBytes = Base64.encodeBase64(underlying);
        return new String(encodedBytes);
    }

}
