package retroscope.util;

/**
 * Created by aleksey on 10/15/16.
 */
public class ByteHelper {

    public static final int LONG_SIZE = 8;
    public static final int INT_SIZE = 4;
    public static final int SHORT_SIZE = 2;
    public static final int DOUBLE_SZIE = 8;
    public static final int FLOAT_SZIE = 4;

    public static void longToBytes(byte[] bytes, long value, int offset) {
        bytes[offset] = (byte) (0xFF & (value >> 56));
        bytes[offset + 1] = (byte) (0xFF & (value >> 48));
        bytes[offset + 2] = (byte) (0xFF & (value >> 40));
        bytes[offset + 3] = (byte) (0xFF & (value >> 32));
        bytes[offset + 4] = (byte) (0xFF & (value >> 24));
        bytes[offset + 5] = (byte) (0xFF & (value >> 16));
        bytes[offset + 6] = (byte) (0xFF & (value >> 8));
        bytes[offset + 7] = (byte) (0xFF & value);
    }

    public static byte[] longToBytes(long value) {
        byte[] bytes = new byte[LONG_SIZE];
        ByteHelper.longToBytes(bytes, value, 0);
        return bytes;
    }

    public static long bytesToLong(byte[] bytes) {
        return bytesToLong(bytes, 0);
    }

    public static long bytesToLong(byte[] bytes, int offset) {
        return (((long) (bytes[offset + 0] & 0xff) << 56)
                | ((long) (bytes[offset + 1] & 0xff) << 48)
                | ((long) (bytes[offset + 2] & 0xff) << 40)
                | ((long) (bytes[offset + 3] & 0xff) << 32)
                | ((long) (bytes[offset + 4] & 0xff) << 24)
                | ((long) (bytes[offset + 5] & 0xff) << 16)
                | ((long) (bytes[offset + 6] & 0xff) << 8) | ((long) bytes[offset + 7] & 0xff));
    }

    public static void intToBytes(byte[] bytes, int value, int offset) {
        bytes[offset] = (byte) (0xFF & (value >> 24));
        bytes[offset + 1] = (byte) (0xFF & (value >> 16));
        bytes[offset + 2] = (byte) (0xFF & (value >> 8));
        bytes[offset + 3] = (byte) (0xFF & value);
    }

    public static byte[] intToBytes(int value) {
        byte[] bytes = new byte[INT_SIZE];
        ByteHelper.intToBytes(bytes, value, 0);
        return bytes;
    }

    public static int bytesToInt(byte[] bytes) {
        return bytesToInt(bytes, 0);
    }

    public static int bytesToInt(byte[] bytes, int offset) {
        return (((int) (bytes[offset + 0] & 0xff) << 24)
                | ((int) (bytes[offset + 1] & 0xff) << 16)
                | ((int) (bytes[offset + 2] & 0xff) << 8)
                | ((int) (bytes[offset + 3] & 0xff)));
    }

    public static byte[] doubleToBytes(double value) {
        byte[] bytes = new byte[DOUBLE_SZIE];
        ByteHelper.doubleToBytes(bytes, value, 0);
        return bytes;
    }

    public static void doubleToBytes(byte[] bytes, double value, int offset) {
        long bits = Double.doubleToLongBits(value);
        longToBytes(bytes, bits, offset);
    }

    public static double bytesToDouble(byte[] bytes) {
        return bytesToDouble(bytes, 0);
    }

    public static double bytesToDouble(byte[] bytes, int offset) {
        long bits = bytesToLong(bytes, offset);
        double d = Double.longBitsToDouble(bits);
        return d;
    }




    public static String arrayToHexSring(byte[] array) {
        return arrayToHexSring(array, 0, array.length);
    }

    public static String arrayToHexSring(byte[] array, int offset, int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = offset; i < length; i++) {
            sb.append(String.format("%02x", array[i]).toUpperCase());
            sb.append(" ");
        }
        return sb.toString();
    }


}
