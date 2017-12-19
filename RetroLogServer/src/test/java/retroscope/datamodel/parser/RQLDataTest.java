package retroscope.datamodel.parser;

import org.junit.Before;
import org.junit.Test;
import retroscope.datamodel.datastruct.RQLSymbol;
import retroscope.datamodel.datastruct.variables.LongRQLVariable;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class RQLDataTest {

    private static final int LEN = 50;

    private RQLData rqlData;

    @Before
    public void setUp() {
        rqlData = new RQLData();
        for (int i = 0; i < LEN; i++) {
            RQLSymbol sym = new LongRQLVariable("key" + (i%5), i);
            rqlData.appendDataItem(sym);
        }
    }

    @Test
    public void restrict() throws Exception {
        ArrayList<String> params = new ArrayList<>();
        params.add("key0");
        params.add("key3");
        rqlData.restrict(params);
        System.out.println(rqlData.getDataItems().size());
        assertTrue(rqlData.getDataItems().size() == 20);
    }

}