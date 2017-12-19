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

import static org.junit.Assert.*;

public class DistributedQueryRunnerTest {

    private static final int LEN = 30;

    private Retroscope retroscope;
    private Ignite ignite;


    @Before
    public void setUp() throws Exception {

        IgniteConfiguration ic = new IgniteConfiguration();
        //ic.setDataStreamerThreadPoolSize(1);
        ignite = Ignition.start(ic);
        Random r = new Random();
        retroscope = new Retroscope("testapp");
        retroscope.connectIgnite(ignite);
        RetroscopeLog log = retroscope.getLog("testlog");

        for (int i = 0; i < LEN; i++) {
            log.setVariable("a", i*i).commit();
            Thread.sleep(r.nextInt(40)+10);
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
        StringReader q1 = new StringReader("SELECT a FROM testlog WHEN EXISTS aa IN a : (aa >= "+((LEN-3)*(LEN-3))+");");
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

        assertTrue(totalCuts == 3);

    }

    @Test
    public void testSimpleQueryComputeSum() throws Exception {
        StringReader q1 = new StringReader("SELECT a, sum FROM testlog COMPUTE GLOBAL sum AND sum := Sum(a) WHEN sum > 5;");
        Scanner scanner = new Scanner(q1);
        scanner.yylex();

        rqlParser rql = new rqlParser(scanner);
        rql.parse();


        List<Runner> runners = StatementProcessor.processStatements(
                rql.getQueries(),
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
        }

        assertTrue(totalCuts == LEN - 3);
    }

    @Test
    public void testSimpleDistributedQuery2() throws Exception {
        StringReader q1 = new StringReader("SELECT a FROM testlog WHEN EXISTS aa IN a : (aa < 100);");
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

        assertTrue(totalCuts == 10);

    }

}