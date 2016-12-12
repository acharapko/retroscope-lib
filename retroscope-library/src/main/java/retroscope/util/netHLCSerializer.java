package retroscope.util;

import retroscope.hlc.Timestamp;
import retroscope.util.ByteHelper;

import java.util.Arrays;


/**
 * Created by aleksey on 10/15/16.
 */
public class netHLCSerializer {

    public static byte[] networkAddHLC(Timestamp hlcTime, byte[] message) {
        long hlcLong = hlcTime.toLong();

        byte[] hlcMessage = new byte[message.length + 8]; //retroscope.hlc takes 8 bytes
        ByteHelper.longToBytes(hlcMessage, hlcLong, 0);

        return hlcMessage;
    }

    public static Timestamp networkReadHLC(byte[] message) {
        long hlcLong = ByteHelper.bytesToLong(message, 0);
        //byte[] strippedMessage = Arrays.copyOfRange(message, 0, 8);
        //message = strippedMessage;
        return new Timestamp(hlcLong);
    }

    public static byte[] networkUnwrapHLC(byte[] message) {
        byte[] strippedMessage = Arrays.copyOfRange(message, 0, 8);
        return strippedMessage;
    }
}
