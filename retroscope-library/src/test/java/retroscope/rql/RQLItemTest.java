package retroscope.rql;

import org.junit.Test;
import org.omg.CORBA.DataOutputStream;
import retroscope.util.ByteArray;

import java.io.*;

import static org.junit.Assert.*;

/**
 * Created by Aleksey on 12/28/2016.
 * testing RQLItem
 */
public class RQLItemTest {

    @Test
    public void serializeTest() throws Exception {
        RQLItem item = new RQLItem()
                .addField("int1", 5483)
                .addField("double1", 45.59)
                .addField("str1", "this is a test");

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out;

        out = new ObjectOutputStream(bos);
        out.writeObject(item);
        out.close();
        bos.close();

        byte[] bytes = bos.toByteArray();

        ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
        ObjectInputStream in = new ObjectInputStream(bin);
        RQLItem restored = (RQLItem) in.readObject();

        assertTrue(item.getField("int1").getIntVal() == restored.getField("int1").getIntVal());
        assertTrue(item.getField("double1").getDoubleVal() == restored.getField("double1").getDoubleVal());
        assertTrue(item.getField("str1").getStringVal().equals(restored.getField("str1").getStringVal()));

        item = new RQLItem()
                .addField("", 5623)
                .addField("double1", 45.59)
                .addField("str1", "this is a test");

        bos = new ByteArrayOutputStream();
        out = new ObjectOutputStream(bos);
        out.writeObject(item);
        out.close();
        bos.close();

        bytes = bos.toByteArray();
        //System.out.println("serialized size: " + bytes.length);
        bin = new ByteArrayInputStream(bytes);
        in = new ObjectInputStream(bin);
        restored = (RQLItem) in.readObject();


        assertTrue(item.getField("").getIntVal()
                == restored.getField("").getIntVal());
        assertTrue(item.getField("double1").getDoubleVal() == restored.getField("double1").getDoubleVal());
        assertTrue(item.getField("str1").getStringVal().equals(restored.getField("str1").getStringVal()));

        item = new RQLItem()
                .addField("", 5623)
                .addField("double1", 45.59)
                .addField("str1", "this is a test")
                .addField("", "this is a test 2");

        bos = new ByteArrayOutputStream();
        out = new ObjectOutputStream(bos);
        out.writeObject(item);
        out.close();
        bos.close();

        bytes = bos.toByteArray();

        bin = new ByteArrayInputStream(bytes);
        in = new ObjectInputStream(bin);
        restored = (RQLItem) in.readObject();

        assertTrue(item.getField("").getStringVal().equals(restored.getField("").getStringVal()));
        assertTrue(item.getField("").getStringVal().equals("this is a test 2"));
        assertTrue(item.getField("double1").getDoubleVal() == restored.getField("double1").getDoubleVal());
        assertTrue(item.getField("str1").getStringVal().equals(restored.getField("str1").getStringVal()));



    }



}