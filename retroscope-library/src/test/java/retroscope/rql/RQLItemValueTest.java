package retroscope.rql;

import org.junit.Test;
import retroscope.util.ByteHelper;

import static org.junit.Assert.*;

/**
 * Created by Aleksey on 12/28/2016.
 * testing RQLItemValue
 */
public class RQLItemValueTest {

    @Test
    public void toBytes() throws Exception {
        RQLItemValue valint0 = new RQLItemValue("", 98132);
        byte [] val0b = valint0.toBytes();

        assertTrue(val0b.length == 5);
        assertTrue(val0b[0] == 0);

        RQLItemValue valint1 = new RQLItemValue("test", 25);
        byte [] val1b = valint1.toBytes();

        assertTrue(val1b.length == 9);
        assertTrue(val1b[0] == 4);
        assertTrue(ByteHelper.arrayToHexSring(val1b, 1, 5)
                .equals(ByteHelper.arrayToHexSring("test".getBytes())));
        assertTrue(ByteHelper.bytesToInt(val1b, 5) == 25);

        RQLItemValue valint2 = new RQLItemValue("another test", 4578);
        byte [] val2b = valint2.toBytes();

        assertTrue(val2b.length == 17);
        assertTrue(val2b[0] == 12);
        assertTrue(ByteHelper.arrayToHexSring(val2b, 1, 13)
                .equals(ByteHelper.arrayToHexSring("another test".getBytes())));
        assertTrue(ByteHelper.bytesToInt(val2b, 13) == 4578);

        String longName = "this is a long name that exceeds 63 characters allowed for the name";
        RQLItemValue valint3 = new RQLItemValue(longName, Integer.MAX_VALUE);
        byte [] val3b = valint3.toBytes();

        assertTrue(val3b.length == 68);
        assertTrue(val3b[0] == 63);
        assertTrue(ByteHelper.arrayToHexSring(val3b, 1, 64)
                .equals(ByteHelper.arrayToHexSring(longName.substring(0, 63).getBytes())));
        assertTrue(ByteHelper.bytesToInt(val3b, 64) == Integer.MAX_VALUE);


        RQLItemValue valdouble1 = new RQLItemValue("test", 25.51);
        byte [] vald1b = valdouble1.toBytes();

        assertTrue(vald1b.length == 13);
        assertTrue(vald1b[0] == 0x44);
        assertTrue(ByteHelper.arrayToHexSring(vald1b, 1, 5)
                .equals(ByteHelper.arrayToHexSring("test".getBytes())));
        assertTrue(ByteHelper.bytesToDouble(vald1b, 5) == 25.51);

        RQLItemValue valdouble2 = new RQLItemValue("this is a bigger test", Double.MAX_VALUE);
        byte [] vald2b = valdouble2.toBytes();

        assertTrue(vald2b.length == 30);
        assertTrue(vald2b[0] == 0x55);
        assertTrue(ByteHelper.arrayToHexSring(vald2b, 1, 22)
                .equals(ByteHelper.arrayToHexSring("this is a bigger test".getBytes())));
        assertTrue(ByteHelper.bytesToDouble(vald2b, 22) == Double.MAX_VALUE);


        RQLItemValue valstr1 = new RQLItemValue("test", "this is a test");
        byte [] vals1b = valstr1.toBytes();

        assertTrue(vals1b.length == 20);
        assertTrue(vals1b[0] == (byte)0x84);
        assertTrue(ByteHelper.arrayToHexSring(vals1b, 1, 5)
                .equals(ByteHelper.arrayToHexSring("test".getBytes())));
        assertTrue(ByteHelper.arrayToHexSring(vals1b, 6, 20)
                .equals(ByteHelper.arrayToHexSring("this is a test".getBytes())));

    }

    @Test
    public void fromBytes() throws Exception {
        RQLItemValue valint0 = new RQLItemValue("", 98132);
        byte [] val0b = valint0.toBytes();

        RQLItemValue fromi0 = new RQLItemValue(val0b);
        assertTrue(fromi0.getType() == valint0.getType());
        assertTrue(fromi0.getName().equals(valint0.getName()));
        assertTrue(fromi0.getIntVal() == valint0.getIntVal());


        RQLItemValue valint1 = new RQLItemValue("test", 25);
        byte [] val1b = valint1.toBytes();

        RQLItemValue fromi1 = new RQLItemValue(val1b);
        assertTrue(fromi1.getType() == valint1.getType());
        assertTrue(fromi1.getName().equals(valint1.getName()));
        assertTrue(fromi1.getIntVal() == valint1.getIntVal());



        RQLItemValue valint2 = new RQLItemValue("another test", 4578);
        byte [] val2b = valint2.toBytes();

        RQLItemValue fromi2 = new RQLItemValue(val2b);
        assertTrue(fromi2.getType() == valint2.getType());
        assertTrue(fromi2.getName().equals(valint2.getName()));
        assertTrue(fromi2.getIntVal() == valint2.getIntVal());

        String longName = "this is a long name that exceeds 63 characters allowed for the name";
        RQLItemValue valint3 = new RQLItemValue(longName, Integer.MAX_VALUE);
        byte [] val3b = valint3.toBytes();

        RQLItemValue fromi3 = new RQLItemValue(val3b);
        assertTrue(fromi3.getType() == valint3.getType());
        assertTrue(fromi3.getName().equals(valint3.getName()));
        assertTrue(fromi3.getName().equals(longName.substring(0, 63)));
        assertTrue(fromi3.getIntVal() == valint3.getIntVal());


        RQLItemValue valdouble1 = new RQLItemValue("test", 25.51);
        byte [] vald1b = valdouble1.toBytes();

        RQLItemValue fromd1 = new RQLItemValue(vald1b);
        assertTrue(fromd1.getType() == valdouble1.getType());
        assertTrue(fromd1.getName().equals(valdouble1.getName()));
        assertTrue(fromd1.getDoubleVal() == valdouble1.getDoubleVal());

        RQLItemValue valdouble2 = new RQLItemValue("this is a bigger test", Double.MAX_VALUE);
        byte [] vald2b = valdouble2.toBytes();

        RQLItemValue fromd2 = new RQLItemValue(vald2b);
        assertTrue(fromd2.getType() == valdouble2.getType());
        assertTrue(fromd2.getName().equals(valdouble2.getName()));
        assertTrue(fromd2.getDoubleVal() == valdouble2.getDoubleVal());


        RQLItemValue valstr1 = new RQLItemValue("test", "this is a test");
        byte [] vals1b = valstr1.toBytes();

        RQLItemValue froms1 = new RQLItemValue(vals1b);
        assertTrue(froms1.getType() == valstr1.getType());
        assertTrue(froms1.getName().equals(valstr1.getName()));
        assertTrue(froms1.getStringVal().equals(valstr1.getStringVal()));

        String bigVal = "The Byte class wraps a value of primitive type byte in an object. An object of type Byte contains a single field whose type is byte.";
        RQLItemValue valstr2 = new RQLItemValue("test", bigVal); // string of length greater than Bytes.MAX_VALUE
        byte [] vals2b = valstr2.toBytes();

        RQLItemValue froms2 = new RQLItemValue(vals2b);
        assertTrue(froms2.getType() == valstr2.getType());
        assertTrue(froms2.getName().equals(valstr2.getName()));
        assertTrue(froms2.getStringVal().equals(valstr2.getStringVal()));


        RQLItemValue valstr3 = new RQLItemValue("test", ""); //0 length string
        byte [] vals3b = valstr3.toBytes();

        RQLItemValue froms3 = new RQLItemValue(vals3b);
        assertTrue(froms3.getType() == valstr3.getType());
        assertTrue(froms3.getName().equals(valstr3.getName()));
        assertTrue(froms3.getStringVal().equals(valstr3.getStringVal()));

        RQLItemValue junk = null;
        try {
            byte[] valsJunk = {0x0A, -0x12, 0x55, 0x7F, -0x3A, 0x00, 0x37, -0x6C, 0x02};
            junk = new RQLItemValue(valsJunk);
        } catch (RQLItemException re) {
            assertTrue(re.getMessage().equals("Corrupted byte stream"));
        }
        assertTrue(junk == null);


        try {
            byte[] valsJunk = {0x4A, -0x12, 0x55, 0x7F, -0x3A, 0x00, 0x37, -0x6C, 0x02};
            junk = new RQLItemValue(valsJunk);
        } catch (RQLItemException re) {
            assertTrue(re.getMessage().equals("Corrupted byte stream"));
        }
        assertTrue(junk == null);

        try {
            byte[] valsJunk = {(byte)0xFF, -0x12, 0x55, 0x7F, -0x3A, 0x00, 0x37, -0x6C, 0x02};
            junk = new RQLItemValue(valsJunk);
        } catch (RQLItemException re) {
            assertTrue(re.getMessage().equals("Undefined data type"));
        }
        assertTrue(junk == null);



    }

}