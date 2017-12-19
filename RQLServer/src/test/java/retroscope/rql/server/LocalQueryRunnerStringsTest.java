package retroscope.rql.server;

import org.junit.Before;
import org.junit.Test;
import retroscope.datamodel.datastruct.variables.StringRQLVariable;
import retroscope.hlc.Timestamp;
import retroscope.net.ignite.EventStreamEntry;
import retroscope.rql.environment.GlobalCut;
import retroscope.rql.log.RQLStateSequence;
import retroscope.rql.parser.Scanner;
import retroscope.rql.parser.rqlParser;
import retroscope.rql.syntaxtree.Query;

import java.io.StringReader;

import static org.junit.Assert.assertTrue;

public class LocalQueryRunnerStringsTest {

    private RQLStateSequence stateSequence;
    private static final int SEQ_LEN = 150;

    @Before
    public void setUp() throws Exception {
        Timestamp t = new Timestamp();
        stateSequence = new RQLStateSequence(t);

        for (int i = 0; i < SEQ_LEN; i++) {
            t = t.add(1, (short) 0);
            StringRQLVariable v = new StringRQLVariable("a", "\"val_"+(i*i)+"\"");
            String var = "<test," + t.toLong()+",1>:" + v.toRQLString();
            EventStreamEntry entry = new EventStreamEntry(t.toLong(), var);
            stateSequence.appendItem(entry);

            v = new StringRQLVariable("a", "\"val_"+(i+i)+"\"");
            var = "<test," + t.toLong()+",2>:" + v.toRQLString();
            entry = new EventStreamEntry(t.toLong(), var);
            stateSequence.appendItem(entry);
        }
    }

    @Test
    public void testSimpleQueryCard() throws Exception {
        StringReader q1 = new StringReader("SELECT a FROM testlog WHEN Cardinality(a) > 1;");
        Scanner scanner = new Scanner(q1);
        scanner.yylex();

        rqlParser rql = new rqlParser(scanner);
        rql.parse();
        LocalQueryRunner qr = new LocalQueryRunner((Query) rql.getQueries().get(0), stateSequence);
        qr.execute();
        System.out.println(qr.getEmittedCuts().size());

        for (GlobalCut cut: qr.getEmittedCuts()) {
            System.out.println(cut.toOneString());
        }

        assertTrue(qr.getEmittedCuts().size() + 2 == SEQ_LEN);
    }


    @Test
    public void testSimpleQueryExists() throws Exception {
        StringReader q1 = new StringReader("SELECT a FROM testlog WHEN EXISTS aa IN a : (aa = \"val_16\");");
        Scanner scanner = new Scanner(q1);
        scanner.yylex();

        rqlParser rql = new rqlParser(scanner);
        rql.parse();
        LocalQueryRunner qr = new LocalQueryRunner((Query) rql.getQueries().get(0), stateSequence);
        qr.execute();
        System.out.println(qr.getEmittedCuts().size());
        assertTrue(qr.getEmittedCuts().size() == 2);
    }


    @Test
    public void testSimpleQueryForAll() throws Exception {
        StringReader q1 = new StringReader("SELECT a FROM testlog WHEN FORALL aa IN a : (aa = \"val_4\");");
        Scanner scanner = new Scanner(q1);
        scanner.yylex();

        rqlParser rql = new rqlParser(scanner);
        rql.parse();
        LocalQueryRunner qr = new LocalQueryRunner((Query) rql.getQueries().get(0), stateSequence);
        qr.execute();
        System.out.println(qr.getEmittedCuts().size());
        assertTrue(qr.getEmittedCuts().size() == 1);
    }

}