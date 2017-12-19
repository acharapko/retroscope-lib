package retroscope.rql.server;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import retroscope.Retroscope;
import retroscope.log.RetroscopeLog;
import retroscope.rql.environment.GlobalCut;
import retroscope.rql.parser.Scanner;
import retroscope.rql.parser.rqlParser;

import java.io.StringReader;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertTrue;

public class DistributedQuerySetTest {

    private static final int LEN = 30;

    private Retroscope retroscope;
    private Ignite ignite;


    @Before
    public void setUp() throws Exception {

        IgniteConfiguration ic = new IgniteConfiguration();
        //ic.setDataStreamerThreadPoolSize(1);
        ignite = Ignition.start(ic);
        retroscope = new Retroscope("testapp");
        retroscope.connectIgnite(ignite);
        RetroscopeLog log = retroscope.getLog("testlog");
        log.createSet("a", 200);
        Thread.sleep(300);
        for (int i = -3; i < LEN - 3; i++) {
            log.addToSet("a", i).commit();
            Thread.sleep(50);
        }
        retroscope.flushStreams();
        Thread.sleep(2000);
    }


    @After
    public void tearDown() throws Exception {
        ignite.close();
    }


    @Test
    public void testSimpleDistributedQuery() throws Exception {
        StringReader q1 = new StringReader("SELECT a FROM testlog WHEN Cardinality(Flatten(a)) > 2;");
        long startTime = System.currentTimeMillis();
        Scanner scanner = new Scanner(q1);
        scanner.yylex();

        rqlParser parser = new rqlParser(scanner);
        parser.parse();

        List<Runner> runners = StatementProcessor.processStatements(
                parser.getQueries(),
                "testapp",
                ignite
        );

        int totalCuts = 0;
        for (Runner s: runners) {
            for (GlobalCut cut : s.getEmittedCuts()) {
                System.out.println(cut);
            }
            //System.out.println("out \n" + s.);
            totalCuts += s.getEmittedCuts().size();

            long endEndTime = System.currentTimeMillis();
            System.out.println("Query executed in: " + (endEndTime - startTime) + " ms for " + s.getEmittedCuts().size() + " cuts");
        }

        assertTrue(totalCuts == LEN - 2);

    }

    @Test
    public void testSimpleDistributedQuery2() throws Exception {
        StringReader q1 = new StringReader("SELECT a FROM testlog WHEN EXISTS aa IN Flatten(a) : (aa < 7);");
        long startTime = System.currentTimeMillis();
        Scanner scanner = new Scanner(q1);
        scanner.yylex();

        rqlParser parser = new rqlParser(scanner);
        parser.parse();

        List<Runner> runners = StatementProcessor.processStatements(
                parser.getQueries(),
                "testapp",
                ignite
        );

        int totalCuts = 0;
        for (Runner s: runners) {
            for (GlobalCut cut : s.getEmittedCuts()) {
                System.out.println(cut);
            }
            //System.out.println("out \n" + s.);
            totalCuts += s.getEmittedCuts().size();
            long endEndTime = System.currentTimeMillis();
            System.out.println("Query executed in: " + (endEndTime - startTime) + " ms for " + s.getEmittedCuts().size() + " cuts");
        }

        assertTrue(totalCuts >= 10);
        assertTrue(totalCuts <= 14);

    }

}