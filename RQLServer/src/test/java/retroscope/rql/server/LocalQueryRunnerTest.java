package retroscope.rql.server;

import org.junit.Before;
import org.junit.Test;
import retroscope.datamodel.datastruct.variables.LongRQLVariable;
import retroscope.hlc.Timestamp;
import retroscope.net.ignite.EventStreamEntry;
import retroscope.rql.environment.GlobalCut;
import retroscope.rql.log.RQLStateSequence;
import retroscope.rql.parser.Scanner;
import retroscope.rql.parser.rqlParser;
import retroscope.rql.syntaxtree.Query;

import java.io.StringReader;

import static org.junit.Assert.*;

public class LocalQueryRunnerTest {

    private RQLStateSequence stateSequence;
    private static final int SEQ_LEN = 150;

    @Before
    public void setUp() throws Exception {
        Timestamp t = new Timestamp();
        stateSequence = new RQLStateSequence(t);

        for (int i = 0; i < SEQ_LEN; i++) {
            t = t.add(1, (short) 0);
            LongRQLVariable v = new LongRQLVariable("a", i*i);
            String var = "<test," + t.toLong()+",1>:" + v.toRQLString();
            EventStreamEntry entry = new EventStreamEntry(t.toLong(), var);
            stateSequence.appendItem(entry);

            v = new LongRQLVariable("a", i+i);
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
        LocalQueryRunner qr = new LocalQueryRunner(rql.getQueries().get(0), stateSequence);
        qr.execute();
        System.out.println(qr.getEmittedCuts().size());
        assertTrue(qr.getEmittedCuts().size() + 2 == SEQ_LEN);
    }


    @Test
    public void testSimpleQueryExists() throws Exception {
        StringReader q1 = new StringReader("SELECT a FROM testlog WHEN EXISTS aa IN a : (aa > 1);");
        Scanner scanner = new Scanner(q1);
        scanner.yylex();

        rqlParser rql = new rqlParser(scanner);
        rql.parse();
        LocalQueryRunner qr = new LocalQueryRunner((Query) rql.getQueries().get(0), stateSequence);
        qr.execute();
        System.out.println(qr.getEmittedCuts().size());
        assertTrue(qr.getEmittedCuts().size() + 1 == SEQ_LEN);
    }

    @Test
    public void testSimpleQueryExists3() throws Exception {
        StringReader q1 = new StringReader("SELECT a FROM testlog WHEN EXISTS aa, ab IN a : (aa + ab >= 18);");
        Scanner scanner = new Scanner(q1);
        scanner.yylex();

        rqlParser rql = new rqlParser(scanner);
        rql.parse();
        LocalQueryRunner qr = new LocalQueryRunner((Query) rql.getQueries().get(0), stateSequence);
        qr.execute();
        System.out.println(qr.getEmittedCuts().size());
        assertTrue(qr.getEmittedCuts().size() + 3 == SEQ_LEN);
    }


    @Test
    public void testSimpleQueryCompute() throws Exception {
        StringReader q1 = new StringReader("SELECT a, b FROM testlog COMPUTE GLOBAL b AND (FOREACH aa IN a : (b := b UNION {aa*2})) WHEN EXISTS bb IN b :(bb > 2);");
        Scanner scanner = new Scanner(q1);
        scanner.yylex();

        rqlParser rql = new rqlParser(scanner);
        rql.parse();
        LocalQueryRunner qr = new LocalQueryRunner(rql.getQueries().get(0), stateSequence);
        qr.execute();
        System.out.println(qr.getEmittedCuts().size());
        assertTrue(qr.getEmittedCuts().size() + 1 == SEQ_LEN);
    }

    @Test
    public void testSimpleQueryComputeSum() throws Exception {
        StringReader q1 = new StringReader("SELECT a, sum FROM testlog COMPUTE GLOBAL sum AND sum := NodeSum(a) WHEN sum > 5;");
        Scanner scanner = new Scanner(q1);
        scanner.yylex();

        rqlParser rql = new rqlParser(scanner);
        rql.parse();
        LocalQueryRunner qr = new LocalQueryRunner(rql.getQueries().get(0), stateSequence);
        qr.execute();
        System.out.println(qr.getEmittedCuts().size());
        for (GlobalCut cut: qr.getEmittedCuts()) {
            System.out.println(cut);
        }
        assertTrue(qr.getEmittedCuts().size() + 2 == SEQ_LEN);
    }

    @Test
    public void testSimpleQueryExistsEq() throws Exception {
        StringReader q1 = new StringReader("SELECT a FROM testlog WHEN EXISTS aa IN a : (aa = 16);");
        Scanner scanner = new Scanner(q1);
        scanner.yylex();

        rqlParser rql = new rqlParser(scanner);
        rql.parse();
        LocalQueryRunner qr = new LocalQueryRunner(rql.getQueries().get(0), stateSequence);
        qr.execute();
        System.out.println(qr.getEmittedCuts().size());
        assertTrue(qr.getEmittedCuts().size()  == 2);
    }


    @Test
    public void testSimpleQueryForAll() throws Exception {
        StringReader q1 = new StringReader("SELECT a FROM testlog WHEN FORALL aa IN a : (aa > 1);");
        Scanner scanner = new Scanner(q1);
        scanner.yylex();

        rqlParser rql = new rqlParser(scanner);
        rql.parse();
        LocalQueryRunner qr = new LocalQueryRunner(rql.getQueries().get(0), stateSequence);
        qr.execute();
        System.out.println(qr.getEmittedCuts().size());
        assertTrue(qr.getEmittedCuts().size() + 2 == SEQ_LEN);
    }

    @Test
    public void testSimpleQueryIsSubset() throws Exception {
        StringReader q1 = new StringReader("SELECT a FROM testlog WHEN a ISSUBSET {6,9};");
        Scanner scanner = new Scanner(q1);
        scanner.yylex();

        rqlParser rql = new rqlParser(scanner);
        rql.parse();
        LocalQueryRunner qr = new LocalQueryRunner(rql.getQueries().get(0), stateSequence);
        qr.execute();
        System.out.println(qr.getEmittedCuts().size());
        assertTrue(qr.getEmittedCuts().size() == 1);
    }

    @Test
    public void testSimpleQuerySetUnion() throws Exception {
        StringReader q1 = new StringReader("SELECT a FROM testlog WHEN Cardinality(a UNION {6,9}) > 3;");
        Scanner scanner = new Scanner(q1);
        scanner.yylex();

        rqlParser rql = new rqlParser(scanner);
        rql.parse();
        LocalQueryRunner qr = new LocalQueryRunner(rql.getQueries().get(0), stateSequence);
        qr.execute();
        System.out.println(qr.getEmittedCuts().size());
        assertTrue(qr.getEmittedCuts().size() + 3 == SEQ_LEN);
    }

    @Test
    public void testSimpleQuerySetDifference() throws Exception {
        StringReader q1 = new StringReader("SELECT a FROM testlog WHEN Cardinality(a \\ {16,36}) > 1;");
        Scanner scanner = new Scanner(q1);
        scanner.yylex();

        rqlParser rql = new rqlParser(scanner);
        rql.parse();
        LocalQueryRunner qr = new LocalQueryRunner(rql.getQueries().get(0), stateSequence);
        qr.execute();
        System.out.println(qr.getEmittedCuts().size());
        assertTrue(qr.getEmittedCuts().size() + 6 == SEQ_LEN);
    }

    @Test
    public void testSimpleQuerySetIntersect() throws Exception {
        StringReader q1 = new StringReader("SELECT a FROM testlog WHEN Cardinality(a INTERSECT {16,36}) > 0;");
        Scanner scanner = new Scanner(q1);
        scanner.yylex();

        rqlParser rql = new rqlParser(scanner);
        rql.parse();
        LocalQueryRunner qr = new LocalQueryRunner((Query) rql.getQueries().get(0), stateSequence);
        qr.execute();
        System.out.println(qr.getEmittedCuts().size());
        assertTrue(qr.getEmittedCuts().size() == 4);
    }

    @Test
    public void testSimpleQuerySetInOperator() throws Exception {
        StringReader q1 = new StringReader("SELECT a FROM testlog WHEN 16 IN a;");
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
    public void testSimpleQuerySetNotInOperator() throws Exception {
        StringReader q1 = new StringReader("SELECT a FROM testlog WHEN 16 NOTIN a;");
        Scanner scanner = new Scanner(q1);
        scanner.yylex();

        rqlParser rql = new rqlParser(scanner);
        rql.parse();
        LocalQueryRunner qr = new LocalQueryRunner((Query) rql.getQueries().get(0), stateSequence);
        qr.execute();
        System.out.println(qr.getEmittedCuts().size());
        assertTrue(qr.getEmittedCuts().size() + 2  == SEQ_LEN);
        for (GlobalCut cut: qr.getEmittedCuts()) {
            System.out.println(cut);
        }
    }

    @Test
    public void testSimpleQueryExists2() throws Exception {
        StringReader q1 = new StringReader("SELECT a FROM testlog WHEN EXISTS aa IN a : (aa >= " + (SEQ_LEN-1) * (SEQ_LEN-1) + ");");
        Scanner scanner = new Scanner(q1);
        scanner.yylex();

        rqlParser rql = new rqlParser(scanner);
        rql.parse();
        LocalQueryRunner qr = new LocalQueryRunner((Query) rql.getQueries().get(0), stateSequence);
        qr.execute();
        System.out.println(qr.getEmittedCuts().size());
        assertTrue(qr.getEmittedCuts().size() == 1);
        for (GlobalCut cut: qr.getEmittedCuts()) {
            System.out.println(cut);
        }
    }


}