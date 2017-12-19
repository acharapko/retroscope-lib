package retroscope.datamodel.parser;

import org.junit.Test;
import retroscope.datamodel.datastruct.RQLSymbol;
import retroscope.datamodel.datastruct.sets.RQLSet;
import retroscope.datamodel.datastruct.struct.RQLStruct;
import retroscope.datamodel.datastruct.variables.DoubleRQLVariable;
import retroscope.datamodel.datastruct.variables.LongRQLVariable;
import retroscope.datamodel.datastruct.variables.RQLVariable;
import retroscope.hlc.Timestamp;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class DataParserTest {

    @Test
    public void simpleData() throws Exception {
        Timestamp t = new Timestamp();
        String data = "<testlog, " + t.toLong() + ",1>:a:5,b:12.3";
        StringReader q1 = new StringReader(data);
        DataScanner scanner = new DataScanner(q1);
        scanner.yylex();
        DataParser p = new DataParser(scanner);
        p.parse();


        RQLData parsedData = p.getData();
        ArrayList<RQLSymbol> dataItems = parsedData.getDataItems();
        assertTrue(dataItems.size() == 2);

        /*assertTrue(dataItems.get("a") != null);
        assertTrue(dataItems.get("b") != null);
        assertTrue(dataItems.get("b") instanceof DoubleRQLVariable);
        assertTrue(dataItems.get("a") instanceof LongRQLVariable);
        Double b = (Double) ((RQLVariable)dataItems.get("b")).getValue();
        assertTrue(b == 12.3);
        Long a = (Long)((RQLVariable)dataItems.get("a")).getValue();
        assertTrue(a == 5);

        data = "<testlog, " + t.toLong() + ",1>:a:5,c:12.5";
        q1 = new StringReader(data);
        scanner = new DataScanner(q1);
        scanner.yylex();
        p.setScanner(scanner);
        p.parse();

        parsedData = p.getData();
        dataItems = parsedData.getDataItems();
        System.out.println(dataItems.size());
        assertTrue(dataItems.size() == 2);

        assertTrue(dataItems.get("a") != null);
        assertTrue(dataItems.get("c") != null);
        assertTrue(dataItems.get("c") instanceof DoubleRQLVariable);
        assertTrue(dataItems.get("a") instanceof LongRQLVariable);
        Double c = (Double) ((RQLVariable)dataItems.get("c")).getValue();
        assertTrue(c == 12.5);
        a = (Long)((RQLVariable)dataItems.get("a")).getValue();
        assertTrue(a == 5);*/
    }

    @Test
    public void simpleCutData() throws Exception {
        Timestamp t = new Timestamp();
        String data = "<" + t.toLong() + ">:r1:{r1|1,2,3,5:1,r1|4:2}";
        StringReader q1 = new StringReader(data);
        DataScanner scanner = new DataScanner(q1);
        scanner.yylex();
        DataParser p = new DataParser(scanner);
        p.parse();
        RQLData parsedData = p.getData();
        assertTrue(parsedData.getDataItems().size() == 1);
        assertTrue(parsedData.getDataItems().get(0) instanceof RQLSet);
        RQLSet s = (RQLSet) parsedData.getDataItems().get(0);
        assertTrue(s.size() == 2);
        for (RQLSymbol symb: s.getSet()) {
            assertTrue(symb instanceof LongRQLVariable);
            LongRQLVariable l = (LongRQLVariable) symb;
            assertTrue((symb.getNodeIDs().size() == 4 && l.getValue() == 1 )|| (symb.getNodeIDs().size() == 1 && l.getValue() == 2));
        }



    }

    @Test
    public void structHashTest() throws Exception {
        RQLStruct s1 = new RQLStruct();
        long v = 3;
        v = v << 56;
        v = v ^ 75;
        s1.addValue("mid", v);
        s1.addValue("to", 1);

        System.out.println(s1.toValueString());

        System.out.println(s1.hashCode());
    }

}