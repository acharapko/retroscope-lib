package retroscope.rql.server;

import examples.SimpleOneNode;
import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import retroscope.hlc.Timestamp;
import retroscope.rql.environment.GlobalCut;
import retroscope.rql.parser.Scanner;
import retroscope.rql.parser.rqlParser;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class SONTest {

    private Ignite ignite;
    private SimpleOneNode son;
    private static final int DURATION = 10000;

    @Before
    public void setUp() throws Exception {
        IgniteConfiguration ic = new IgniteConfiguration();
        ic.setDataStreamerThreadPoolSize(1);
        ignite = Ignition.start(ic);
        son = new SimpleOneNode(ignite);
        son.start();
        Thread.sleep(DURATION);
        son.stop();
    }

    @After
    public void tearDown() throws Exception {
        ignite.close();
    }


    @Test
    public void fullQueryTestNoCondition() throws Exception {
        Thread.sleep(1100);
        StringReader q1 = new StringReader("SELECT key_1 FROM testlog;");
        long startTime = System.currentTimeMillis();
        Scanner scanner = new Scanner(q1);
        scanner.yylex();

        rqlParser parser = new rqlParser(scanner);
        parser.parse();

        List<Runner> runners = StatementProcessor.processStatements(
                parser.getQueries(),
                "simpleOneNode",
                ignite
        );


        ArrayList times = son.getKeyTimestamps(1);
        for (Runner s: runners) {

            long endEndTime = System.currentTimeMillis();
            System.out.println("Query executed in: " + (endEndTime - startTime) + " ms for " + s.getEmittedCuts().size() + " cuts");
            for (GlobalCut cut : s.getEmittedCuts()) {
                assertTrue(cut.getCutTimes().size() > 0);
                Timestamp t = new Timestamp(cut.getCutTimes().get(0));
                if (times.contains(t.getWallTime())) {
                    times.remove(t.getWallTime());
                }
            }
            System.out.println("expect cuts: " + son.getKeyCount(1));
            System.out.println("missed timestamps: " + times);
            assertTrue(s.getEmittedCuts().size() == son.getKeyCount(1));
        }
    }

    @Test
    public void fullQueryTestCondition() throws Exception {
        Thread.sleep(2000);
        StringReader q1 = new StringReader("SELECT key_1 FROM testlog WHEN EXISTS k IN key_1 : (k = \"val_5\");");
        long startTime = System.currentTimeMillis();
        Scanner scanner = new Scanner(q1);
        scanner.yylex();

        rqlParser parser = new rqlParser(scanner);
        parser.parse();

        List<Runner> runners = StatementProcessor.processStatements(
                parser.getQueries(),
                "simpleOneNode",
                ignite
        );

        ArrayList times = son.getKeyValLastTimestamp(1, 5);
        long lastT = 0;
        for (Runner s: runners) {
            long endEndTime = System.currentTimeMillis();
            System.out.println("Query executed in: " + (endEndTime - startTime) + " ms for " + s.getEmittedCuts().size() + " cuts");
            for (GlobalCut cut : s.getEmittedCuts()) {
                assertTrue(cut.getCutTimes().size() > 0);
                Timestamp t = new Timestamp(cut.getCutTimes().get(0));
                if (times.contains(t.getWallTime())) {
                    times.remove(t.getWallTime());
                }
            }
            System.out.println("expect cuts: " + son.getKeyValCount(1,5));
            System.out.println("missed timestamps: " + times);
            assertTrue(s.getEmittedCuts().size() == son.getKeyValCount(1,5));
        }


    }

}