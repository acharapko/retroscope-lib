package retroscope.rql;

import retroscope.util.ByteArray;
import retroscope.util.ByteHelper;
import java.nio.ByteBuffer;

/**
 * Created by Aleksey on 12/27/2016.
 * this class represents one value of an RQLItem as being stored in the logs
 */
public class RQLItemValue implements TypedValue {

    // max size for an RQL Item value is 320 bytes:
    //   1 byte type and name header
    // + 63 bytes max name length
    // + 1 byte for payload size (for strings)
    // + 255 bytes max String payload
    //----------
    //   320 bytes
    public static final int MAX_SIZE = 320;

    private Types type;
    private double doubleVal;
    private long intVal;
    private String stringVal;
    private String name;

    public RQLItemValue(Types type) {
        this.type = type;
    }

    public RQLItemValue(ByteArray bytes) throws RQLItemException {
        this(bytes.get());
    }

    public RQLItemValue(byte[] bytes) throws RQLItemException {
        this(ByteBuffer.wrap(bytes));
    }

    public RQLItemValue(ByteBuffer buf) throws RQLItemException {
        // find type
        byte typeAndName = buf.get();
        byte tpy = (byte) ((typeAndName >> 6) & 0x03);
        switch (tpy) {
            case 0x00:
                type = Types.INT;
                break;
            case 0x01:
                type = Types.DOUBLE;
                break;
            case 0x02:
                type = Types.STRING;
                break;
            default:
                throw new RQLItemException("Undefined data type");
        }
        int nameLen = typeAndName & 0x3F;
        if (nameLen >= buf.remaining()) {
            throw new RQLItemException("Corrupted byte stream");
        }
        byte[] nameBytes = new byte[nameLen];
        buf.get(nameBytes, 0, nameLen);
        name = new String(nameBytes);
        //switch on type again
        byte[] valueBytes;
        switch (type) {
            case INT:
                if (ByteHelper.LONG_SIZE > buf.remaining()) {
                    throw new RQLItemException("Corrupted byte stream");
                }
                valueBytes = new byte[ByteHelper.LONG_SIZE];
                buf.get(valueBytes, 0, ByteHelper.LONG_SIZE);
                intVal = ByteHelper.bytesToLong(valueBytes);
                break;
            case DOUBLE:
                if (ByteHelper.DOUBLE_SZIE > buf.remaining()) {
                    throw new RQLItemException("Corrupted byte stream");
                }
                valueBytes = new byte[ByteHelper.DOUBLE_SZIE];
                buf.get(valueBytes, 0, ByteHelper.DOUBLE_SZIE);
                doubleVal = ByteHelper.bytesToDouble(valueBytes);
                break;
            case STRING:
                int stringLen = buf.get() & 0xFF;
                if (stringLen > buf.remaining()) {
                    throw new RQLItemException("Corrupted byte stream");
                }
                valueBytes = new byte[stringLen];
                buf.get(valueBytes, 0, stringLen);
                stringVal = new String(valueBytes);
        }

    }

    public RQLItemValue(String name, double val) {
        type = Types.DOUBLE;
        doubleVal = val;
        setName(name);
    }

    public RQLItemValue(String name, long val) {
        type = Types.INT;
        intVal = val;
        setName(name);
    }

    public RQLItemValue(String name, String val) {
        type = Types.STRING;
        if (val.length() > 255) {
            val = val.substring(0, 255);
        }
        stringVal = val;
        setName(name);
    }

    private void setName(String name) {
        this.name = name;
        if (name.length() > 63) {
            this.name = this.name.substring(0, 63);
        }
    }

    public Types getType() {
        return type;
    }

    public double getDoubleVal() {
        return doubleVal;
    }

    public long getIntVal() {
        return intVal;
    }

    public String getStringVal() {
        return new String(stringVal);
    }

    public RQLItemValue setValInt(long valInt) {
        this.intVal = valInt;
        type = Types.INT;
        return this;
    }

    public RQLItemValue setValFloat(double valFloat) {
        this.doubleVal = valFloat;
        this.type = Types.DOUBLE;
        return this;
    }

    public RQLItemValue setValStr(String valStr) {
        this.stringVal = valStr;
        type = Types.STRING;
        return this;
    }

    public RQLItemValue setValType(Types valType) {
        this.type = valType;
        return this;
    }

    public String getName() {
        return name;
    }

    public byte[] toBytes() {
        ByteBuffer bb = ByteBuffer.allocate(MAX_SIZE);
        toByteBuffer(bb);
        byte[] bytes = new byte[(bb.limit() - bb.remaining())];
        bb.rewind();
        bb.get(bytes, 0, bytes.length);
        return bytes;
    }

    public void toByteBuffer(ByteBuffer bb) {
        byte typeAndName;
        byte[] valBytes;
        switch (type) {
            case DOUBLE:
                typeAndName = 0x01;
                valBytes = ByteHelper.doubleToBytes(doubleVal);
                break;
            case STRING:
                typeAndName = 0x02;
                valBytes = stringVal.getBytes();
                break;
            default:
            case INT:
                typeAndName = 0x00;
                valBytes = ByteHelper.longToBytes(intVal);
                break;
        }
        typeAndName = (byte) (typeAndName << 6);

        byte[] nameBytes = name.getBytes();
        byte nameLength = (byte) nameBytes.length;
        typeAndName = (byte) (typeAndName ^ nameLength);

        bb.put(typeAndName);
        bb.put(nameBytes);

        if (type == Types.STRING) {
            bb.put((byte)stringVal.length());
        }
        bb.put(valBytes);
    }

}
