package retroscope.datamodel.datastruct.struct;

import org.junit.Test;
import retroscope.datamodel.datastruct.RQLSymbol;
import retroscope.datamodel.datastruct.sets.RQLSet;
import retroscope.datamodel.datastruct.variables.DoubleRQLVariable;
import retroscope.datamodel.datastruct.variables.LongRQLVariable;
import retroscope.datamodel.datastruct.variables.StringRQLVariable;

import static org.junit.Assert.*;

public class RQLStructTest {

    @Test
    public void testStruct() throws Exception {
        RQLStruct struct = new RQLStruct("test");
        struct.addValue("var1", 5);
        struct.addValue("var2", 5.3);
        struct.addValue("var3", "testval");
        LongRQLVariable c = new LongRQLVariable("var4", 42);
        struct.addElement(c);


        assertTrue(struct.getName().equals("test"));
        RQLSymbol v1 = struct.getElement("var1");
        assertTrue(v1 instanceof LongRQLVariable);
        assertTrue(((LongRQLVariable) v1).getValue() == 5);
        RQLSymbol v2 = struct.getElement("var2");
        assertTrue(v2 instanceof DoubleRQLVariable);
        assertTrue(((DoubleRQLVariable) v2).getValue() == 5.3);
        RQLSymbol v3 = struct.getElement("var3");
        assertTrue(v3 instanceof StringRQLVariable);
        assertTrue(((StringRQLVariable) v3).getValue().equals("testval"));
        RQLSymbol v4 = struct.getElement("var4");
        assertTrue(v4 instanceof LongRQLVariable);
        assertTrue(((LongRQLVariable) v4).getValue() == 42);
    }


    @Test
    public void testStructEquals() throws Exception {
        RQLStruct struct1 = new RQLStruct("test");
        struct1.addValue("var1", 5);
        struct1.addValue("var2", 5.3);
        struct1.addValue("var3", "testval");

        RQLStruct struct2 = new RQLStruct("test2");
        struct2.addValue("var1", 5);
        struct2.addValue("var2", 5.3);
        struct2.addValue("var3", "testval");

        assertTrue(struct1.hashCode() == struct2.hashCode());
        assertTrue(struct1.equals(struct2));
    }

    @Test
    public void testStructNotEquals() throws Exception {
        RQLStruct struct1 = new RQLStruct("test");
        struct1.addValue("var1", 5);
        struct1.addValue("var2", 5.31);
        struct1.addValue("var3", "testval");

        RQLStruct struct2 = new RQLStruct("test2");
        struct2.addValue("var1", 5);
        struct2.addValue("var2", 5.3);
        struct2.addValue("var3", "testval");

        assertTrue(struct1.hashCode() != struct2.hashCode());
        assertTrue(!struct1.equals(struct2));
    }

    @Test
    public void testStructSet() throws Exception {
        RQLSet set = new RQLSet("testset");
        RQLStruct struct1 = new RQLStruct("test");
        struct1.addValue("var1", 5);
        struct1.addValue("var2", 5.3);
        struct1.addValue("var3", "testval");
        set.add(struct1);

        RQLStruct struct2 = new RQLStruct("test2");
        struct2.addValue("var1", 5);
        struct2.addValue("var2", 5.3);
        struct2.addValue("var3", "testval");
        set.add(struct2);

        RQLStruct struct3 = new RQLStruct("test2");
        struct3.addValue("var1", 5);
        struct3.addValue("var2", 5.31);
        struct3.addValue("var3", "testval");
        set.add(struct3);

        assertTrue(set.size() == 2);
    }

}