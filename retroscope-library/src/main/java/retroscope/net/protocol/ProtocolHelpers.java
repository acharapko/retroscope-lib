package retroscope.net.protocol;

import com.google.protobuf.ByteString;
import retroscope.hlc.Timestamp;
import retroscope.log.DataEntry;
import retroscope.log.RetroMap;

import java.io.*;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Aleksey on 11/2/2016.
 */
public class ProtocolHelpers {

    public static<T extends Serializable> ByteString serializableToByteString(T obj) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(obj);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException e2) {}
        }
        ByteString.Output objOut = ByteString.newOutput();
        try {
            objOut.write(bos.toByteArray());
        } catch (IOException e) {e.printStackTrace();}
        return objOut.toByteString();
    }

    public static<T extends Serializable> T byteStringToSerializable(ByteString byteString){
        try {
            ByteArrayInputStream bin = new ByteArrayInputStream(byteString.toByteArray());
            ObjectInputStream in = new ObjectInputStream(bin);
            return (T) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.err.println("Class Not Found");
        }
        return null;
    }

    public static<K extends Serializable, V extends Serializable> Protocol.DataMap retroMapToProtocol(
            RetroMap<K, V> map,
            String name,
            long hlcTime
    ) {
        Protocol.DataMap.Builder protocolMsgBuilder = Protocol.DataMap.newBuilder();
        protocolMsgBuilder.setHlcTime(hlcTime);
        protocolMsgBuilder.setName(name);

        Iterator<Map.Entry<K, DataEntry<V>>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, DataEntry<V>> pair = it.next();
            Protocol.DataItem.Builder dataBuilder = Protocol.DataItem.newBuilder();

            if (pair.getValue() != null) {
                dataBuilder
                        .setHlcTime(pair.getValue().getTimestamp().toLong())
                        .setValue(serializableToByteString(pair.getValue().getValue()));
            }
            dataBuilder.setKey(serializableToByteString(pair.getKey()));
            protocolMsgBuilder.addItems(dataBuilder);
        }
        return protocolMsgBuilder.build();
    }


    public static<K extends Serializable, V extends Serializable> RetroMap<K, V> protocolToRetroMap(Protocol.DataMap protocolDataMap) {

        RetroMap<K, V>  dataMap = new RetroMap<K, V>(protocolDataMap.getItemsCount());
        for (int i = 0; i < protocolDataMap.getItemsCount(); i++) {
            V val = null;
            if (protocolDataMap.getItems(i).hasValue()) {
                val = byteStringToSerializable(protocolDataMap.getItems(i).getValue());
            }
            K key = byteStringToSerializable(protocolDataMap.getItems(i).getKey());
            Timestamp time = new Timestamp(protocolDataMap.getItems(i).getHlcTime());
            dataMap.put(key, new DataEntry<V>(val, time));
        }

        return dataMap;
    }
}
