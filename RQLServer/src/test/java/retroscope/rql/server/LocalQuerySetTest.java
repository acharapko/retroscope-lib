package retroscope.rql.server;

import org.junit.Before;
import org.junit.Test;
import retroscope.datamodel.datastruct.sets.AppendRQLSet;
import retroscope.datamodel.datastruct.sets.RQLSet;
import retroscope.datamodel.datastruct.sets.RemoveRQLSet;
import retroscope.datamodel.datastruct.sets.TimerRQLSet;
import retroscope.datamodel.datastruct.variables.LongRQLVariable;
import retroscope.hlc.Timestamp;
import retroscope.net.ignite.EventStreamEntry;
import retroscope.rql.environment.GlobalCut;
import retroscope.rql.log.RQLStateSequence;
import retroscope.rql.parser.Scanner;
import retroscope.rql.parser.rqlParser;
import retroscope.rql.syntaxtree.Query;

import java.io.StringReader;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class LocalQuerySetTest {

    private RQLStateSequence stateSequence;
    private static final int SEQ_LEN = 30;

    @Before
    public void setUp() throws Exception {
        Timestamp t = new Timestamp();
        stateSequence = new RQLStateSequence(t);


        for (int i = 0; i < SEQ_LEN; i++) {
            t = t.add(1, (short) 0);
            AppendRQLSet aSet = new AppendRQLSet("a");
            RemoveRQLSet rSet = new RemoveRQLSet("a");
            aSet.add(new LongRQLVariable(i*i));
            if (i >= 5) {
                int j = i - 5;
                rSet.add(new LongRQLVariable(j*j));
            }

            String var = "<test," + t.toLong()+",1>:" + aSet.toRQLString();
            if (rSet.size() > 0) {
                var += "," + rSet.toRQLString();
            }

            System.out.println(var);
            EventStreamEntry entry = new EventStreamEntry(t.toLong(), var);
            stateSequence.appendItem(entry);
        }
    }

    @Test
    public void testSimpleQueryComputeSum() throws Exception {
        StringReader q1 = new StringReader("SELECT a, sum FROM testlog COMPUTE GLOBAL sum AND sum := Sum(Flatten(a))  WHEN sum > 5;");
        Scanner scanner = new Scanner(q1);
        scanner.yylex();

        rqlParser rql = new rqlParser(scanner);
        rql.parse();
        LocalQueryRunner qr = new LocalQueryRunner(rql.getQueries().get(0), stateSequence);
        qr.execute();
        for (GlobalCut cut : qr.getEmittedCuts()) {
            System.out.println(cut);
        }
        System.out.println(qr.getEmittedCuts().size());
        assertTrue(qr.getEmittedCuts().size() + 3 == SEQ_LEN);
    }

    @Test
    public void testSimpleQueryComputeSum2() throws Exception {
        StringReader q1 = new StringReader("SELECT a, sum FROM testlog COMPUTE GLOBAL sum AND sum := Sum(Flatten(a)) - Sum(Flatten(a)) WHEN sum > 5;");
        Scanner scanner = new Scanner(q1);
        scanner.yylex();

        rqlParser rql = new rqlParser(scanner);
        rql.parse();
        LocalQueryRunner qr = new LocalQueryRunner(rql.getQueries().get(0), stateSequence);
        qr.execute();
        System.out.println(qr.getEmittedCuts().size());
        assertTrue(qr.getEmittedCuts().size() == 0);
    }

    @Test
    public void testSimpleQueryComputeSetUnion() throws Exception {
        StringReader q1 = new StringReader("SELECT a, s FROM testlog COMPUTE GLOBAL s AND (s := {}) AND FOREACH aa IN a : (s := s UNION aa);");
        Scanner scanner = new Scanner(q1);
        scanner.yylex();

        rqlParser rql = new rqlParser(scanner);
        rql.parse();
        LocalQueryRunner qr = new LocalQueryRunner(rql.getQueries().get(0), stateSequence);
        qr.execute();
        for (GlobalCut cut : qr.getEmittedCuts()) {
            System.out.println(cut);
        }
        System.out.println(qr.getEmittedCuts().size());
        assertTrue(qr.getEmittedCuts().size() == SEQ_LEN);
    }

    @Test
    public void testSimpleQueryComputeSetUnion2() throws Exception {
        StringReader q1 = new StringReader("SELECT a, s FROM testlog COMPUTE GLOBAL s AND (s := {}) AND ((FORALL aa IN a : (Cardinality(aa) > 2)) AND (s := s UNION a)) WHEN Cardinality(s) > 0;");
        Scanner scanner = new Scanner(q1);
        scanner.yylex();

        rqlParser rql = new rqlParser(scanner);
        rql.parse();
        LocalQueryRunner qr = new LocalQueryRunner(rql.getQueries().get(0), stateSequence);
        qr.execute();
        for (GlobalCut cut : qr.getEmittedCuts()) {
            System.out.println(cut);
        }
        System.out.println(qr.getEmittedCuts().size());
        assertTrue(qr.getEmittedCuts().size() + 2 == SEQ_LEN);
    }

    @Test
    public void testSimpleQueryComputeFENull() throws Exception {
        StringReader q1 = new StringReader("SELECT a, s FROM testlog COMPUTE GLOBAL s AND (s := 1) AND FOREACH aa IN {} : (s := s + 1);");
        Scanner scanner = new Scanner(q1);
        scanner.yylex();

        rqlParser rql = new rqlParser(scanner);
        rql.parse();
        LocalQueryRunner qr = new LocalQueryRunner(rql.getQueries().get(0), stateSequence);
        qr.execute();
        for (GlobalCut cut : qr.getEmittedCuts()) {
            System.out.println(cut);
        }
        System.out.println(qr.getEmittedCuts().size());
        assertTrue(qr.getEmittedCuts().size() == SEQ_LEN);
    }


    @Test
    public void testSimpleDistributedQuery() throws Exception {
        StringReader q1 = new StringReader("SELECT a FROM testlog WHEN Cardinality(Flatten(a)) = 5;");
        Scanner scanner = new Scanner(q1);
        scanner.yylex();

        rqlParser rql = new rqlParser(scanner);
        rql.parse();
        LocalQueryRunner qr = new LocalQueryRunner(rql.getQueries().get(0), stateSequence);
        qr.execute();
        System.out.println(qr.getEmittedCuts().size());

        for (GlobalCut cut : qr.getEmittedCuts()) {
            System.out.println(cut);
        }

        assertTrue(qr.getEmittedCuts().size() == SEQ_LEN - 4);

    }

}