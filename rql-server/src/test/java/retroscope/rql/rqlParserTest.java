package retroscope.rql;

import org.junit.Test;
import retroscope.RetroscopeException;
import retroscope.hlc.Timestamp;
import retroscope.log.*;
import retroscope.rql.syntaxtree.Expression;
import retroscope.rql.syntaxtree.RQLRunTimeWarning;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by Aleksey on 12/20/2016.
 * some tests
 */
public class rqlParserTest {

    private static  int LOG_LENGTH = 100;
    private static  int MAX_KEYS = 10;

    @Test
    public void simpleSingleNodeLogSearchQuery() throws Exception {

        StringReader q1 = new StringReader("SELECT test.a FROM test WHEN a=5+2*3;");
        Scanner scanner = new Scanner(q1);
        scanner.yylex();

        rqlParser rql = new rqlParser(scanner).setEnvironment(new RQLEnvironment() {
            public void retrieveRemoteLogs(String[] logs) {
                DataMapLog<String, RQLItem> testLog = new DataMapLog<String, RQLItem>(1000000, "test");
                Timestamp t = new Timestamp();
                Random rand = new Random(System.nanoTime());
                int logTime = 7;
                for (int i = 0; i < LOG_LENGTH; i++) {

                    t = t.add(2 + rand.nextInt(logTime), (short)0);
                    RQLItem item = new RQLItem().addField("", i).addField("v2", i*i);
                    DataEntry<RQLItem> d1
                            = new DataEntry<RQLItem>(item, t);

                    //System.out.println(item.getField("").getIntVal());
                    String key = ("a");
                    LogEntry<String, RQLItem> le
                            = new LogEntry<String, RQLItem>(key, testLog.getItem(key), d1);

                    try {
                        //System.out.println(t + " - " + key);
                        testLog.append(le);
                    } catch (RetroscopeException re) {re.printStackTrace();}
                }
                RQLDataMapLog testrqlmap = new RQLDataMapLog(1, testLog);
                this.logs.add(testrqlmap);
            }

            @Override
            public void retrieveRemoteLogs(String[] logs, Timestamp startTime, Timestamp endTime) {

            }

            @Override
            public void retrieveSingleCut(String[] logs, Timestamp cutTime) {

            }

            @Override
            public void retrieveRemoteLogs(String[] logs, Timestamp startTime, Timestamp endTime, int[] nodeList) {

            }
        });



        rql.parse();
        ArrayList<GlobalCut> cuts = rql.getEnvironment().getGlobalCuts();
        assertTrue(cuts.size() == 1);
        assertTrue(cuts.get(0).getLocalSnapshot("test").get("a").getValue().getField("").getIntVal() == 5+2*3);

    }

    @Test
    public void twoLogsAmbiguousExceptionTest() throws Exception {

        StringReader q1 = new StringReader("SELECT log1.a, log2.b FROM log1, log2 WHEN a=5 AND b = 5;");
        Scanner scanner = new Scanner(q1);
        scanner.yylex();

        rqlParser rql = new rqlParser(scanner).setEnvironment(new RQLEnvironment() {
            public void retrieveRemoteLogs(String[] logs) {
                DataMapLog<String, RQLItem> testLog = new DataMapLog<String, RQLItem>(1000000, "log1");
                Timestamp t = new Timestamp();
                Random rand = new Random(System.nanoTime());
                int logTime = 7;
                for (int i = 0; i < LOG_LENGTH; i++) {

                    t = t.add(2 + rand.nextInt(logTime), (short)0);
                    RQLItem item = new RQLItem().addField("", i).addField("v2", i*i);
                    DataEntry<RQLItem> d1
                            = new DataEntry<RQLItem>(item, t);

                    //System.out.println(item.getField("").getIntVal());
                    String key = ("a");
                    LogEntry<String, RQLItem> le
                            = new LogEntry<String, RQLItem>(key, testLog.getItem(key), d1);

                    try {
                        //System.out.println(t + " - " + key);
                        testLog.append(le);
                    } catch (RetroscopeException re) {re.printStackTrace();}
                }
                RQLDataMapLog testrqlmap = new RQLDataMapLog(1, testLog);
                this.logs.add(testrqlmap);


                testLog = new DataMapLog<String, RQLItem>(1000000, "log2");
                t = new Timestamp();
                rand = new Random(System.nanoTime());
                logTime = 7;
                for (int i = 0; i < LOG_LENGTH; i++) {

                    t = t.add(2 + rand.nextInt(logTime), (short)0);
                    RQLItem item = new RQLItem().addField("", i).addField("v2", i*i);
                    DataEntry<RQLItem> d1
                            = new DataEntry<RQLItem>(item, t);

                    //System.out.println(item.getField("").getIntVal());
                    String key = ("b");
                    LogEntry<String, RQLItem> le
                            = new LogEntry<String, RQLItem>(key, testLog.getItem(key), d1);

                    try {
                        //System.out.println(t + " - " + key);
                        testLog.append(le);
                    } catch (RetroscopeException re) {re.printStackTrace();}
                }
                testrqlmap = new RQLDataMapLog(1, testLog);
                this.logs.add(testrqlmap);
            }

            @Override
            public void retrieveRemoteLogs(String[] logs, Timestamp startTime, Timestamp endTime) {

            }

            @Override
            public void retrieveSingleCut(String[] logs, Timestamp cutTime) {

            }

            @Override
            public void retrieveRemoteLogs(String[] logs, Timestamp startTime, Timestamp endTime, int[] nodeList) {

            }
        });

        rql.parse();
        ArrayList<GlobalCut> cuts = rql.getEnvironment().getGlobalCuts();

        assertTrue(rql.getEnvironment().hasRunTimeErrors());
        assertTrue(rql.getEnvironment().getExceptions().size() == 1);
        assertTrue(cuts.size() == 0);


    }

    @Test
    public void simpleSingleNodeLogSearchQueryByNamedParam() throws Exception {

        StringReader q1 = new StringReader("SELECT test.a FROM test WHEN a:v2=25;");
        Scanner scanner = new Scanner(q1);
        scanner.yylex();

        rqlParser rql = new rqlParser(scanner).setEnvironment(new RQLEnvironment() {
            public void retrieveRemoteLogs(String[] logs) {
                DataMapLog<String, RQLItem> testLog = new DataMapLog<String, RQLItem>(1000000, "test");
                Timestamp t = new Timestamp();
                Random rand = new Random(System.nanoTime());
                int logTime = 7;
                for (int i = 0; i < LOG_LENGTH; i++) {

                    t = t.add(2 + rand.nextInt(logTime), (short)0);
                    RQLItem item = new RQLItem().addField("", i).addField("v2", i*i);
                    DataEntry<RQLItem> d1
                            = new DataEntry<RQLItem>(item, t);

                    //System.out.println(item.getField("").getIntVal());
                    String key = ("a");
                    LogEntry<String, RQLItem> le
                            = new LogEntry<String, RQLItem>(key, testLog.getItem(key), d1);

                    try {
                        //System.out.println(t + " - " + key);
                        testLog.append(le);
                    } catch (RetroscopeException re) {re.printStackTrace();}
                }
                RQLDataMapLog testrqlmap = new RQLDataMapLog(1, testLog);
                this.logs.add(testrqlmap);
            }

            @Override
            public void retrieveRemoteLogs(String[] logs, Timestamp startTime, Timestamp endTime) {

            }

            @Override
            public void retrieveSingleCut(String[] logs, Timestamp cutTime) {

            }

            @Override
            public void retrieveRemoteLogs(String[] logs, Timestamp startTime, Timestamp endTime, int[] nodeList) {

            }
        });



        rql.parse();
        ArrayList<GlobalCut> cuts = rql.getEnvironment().getGlobalCuts();
        System.out.println(cuts.size());
        assertTrue(cuts.size() == 1);
        assertTrue(cuts.get(0).getLocalSnapshot("test").get("a").getValue().getField("").getIntVal() == 5);

    }

    @Test
    public void simpleTwoNodeLogSearchQuery() throws Exception {

        StringReader q1 = new StringReader("SELECT test.a, test.b FROM test WHEN test.a=5 OR b=10;");
        Scanner scanner = new Scanner(q1);
        scanner.yylex();

        rqlParser rql = new rqlParser(scanner).setEnvironment(new RQLEnvironment() {
            public void retrieveRemoteLogs(String[] logs) {
                // on this log we append directly to RQLDatamap
                DataMapLog<String, RQLItem> testLog = new DataMapLog<String, RQLItem>(1000000, "test");
                RQLDataMapLog testrqlmap = new RQLDataMapLog(1, testLog);
                Timestamp t = new Timestamp();
                Random rand = new Random(System.nanoTime());
                int logTime = 7;
                for (int i = 0; i < LOG_LENGTH; i++) {

                    t = t.add(2 + rand.nextInt(logTime), (short)0);
                    RQLItem item = new RQLItem().addField("", i).addField("v2", i*i);
                    DataEntry<RQLItem> d1
                            = new DataEntry<RQLItem>(item, t);

                    //System.out.println(item.getField("").getIntVal());
                    String key = ("a");
                    LogEntry<String, RQLItem> le
                            = new LogEntry<String, RQLItem>(key, testLog.getItem(key), d1);

                    try {
                        //System.out.println(t + " - " + key);
                        testrqlmap.append(le);
                    } catch (RetroscopeException re) {re.printStackTrace();}
                }
                this.logs.add(testrqlmap);
                //System.out.println(testLog.getHead().getTime());

                // on this one we create DataMap and then put it to the RQLDataMap
                testLog = new DataMapLog<String, RQLItem>(1000000, "test");
                t = new Timestamp();
                rand = new Random(System.nanoTime());
                for (int i = 0; i < LOG_LENGTH; i++) {

                    t = t.add(2 + rand.nextInt(logTime), (short)0);
                    RQLItem item = new RQLItem().addField("", i).addField("v2", i*i);
                    DataEntry<RQLItem> d1
                            = new DataEntry<RQLItem>(item, t);

                    //System.out.println(item.getField("").getIntVal());
                    String key = ("b");
                    LogEntry<String, RQLItem> le
                            = new LogEntry<String, RQLItem>(key, testLog.getItem(key), d1);

                    try {
                        //System.out.println(t + " - " + key);
                        testLog.append(le);
                    } catch (RetroscopeException re) {re.printStackTrace();}
                }
                testrqlmap = new RQLDataMapLog(2, testLog);
                this.logs.add(testrqlmap);
                System.out.println(testrqlmap.getHead().getTime());
            }

            @Override
            public void retrieveRemoteLogs(String[] logs, Timestamp startTime, Timestamp endTime) {

            }

            @Override
            public void retrieveSingleCut(String[] logs, Timestamp cutTime) {

            }

            @Override
            public void retrieveRemoteLogs(String[] logs, Timestamp startTime, Timestamp endTime, int[] nodeList) {

            }
        });

        rql.parse();
        ArrayList<GlobalCut> cuts = rql.getEnvironment().getGlobalCuts();
        System.out.println("# of cuts: " + cuts.size());
        System.out.println("# of errors: " + rql.getEnvironment().getExceptions().size());
        assertTrue(cuts.size() >= 2);
        System.out.println(cuts.get(0).getLocalSnapshot("test"));
        for(int c = 0; c < cuts.size(); c++) {
            boolean a5 = false;
            boolean b10 = false;
            ArrayList<RetroMap<String, RQLItem>> snaps = cuts.get(c).getLocalSnapshots();
            ArrayList<String> snapNames = cuts.get(c).getLocalSnapshotNames();
            for (int i = 0; i < snapNames.size(); i++) {
                if (snapNames.get(i).equals("test")) {

                    if (snaps.get(i).get("a") != null) {
                        a5 = snaps.get(i).get("a").getValue().getField("").getIntVal() == 5;
                    }
                    if (snaps.get(i).get("b") != null) {
                        b10 = snaps.get(i).get("b").getValue().getField("").getIntVal() == 10;
                    }
                }
            }

            assertTrue(a5 || b10);
        }

    }


    @Test
    public void twoNodeLogLinkQuery() throws Exception {

        StringReader q1 = new StringReader("SELECT test.a, test.b FROM test WHEN (a + b >= 175) LINK a:t = b:t;");
        Scanner scanner = new Scanner(q1);
        scanner.yylex();

        rqlParser rql = new rqlParser(scanner).setEnvironment(new RQLEnvironment() {
            public void retrieveRemoteLogs(String[] logs) {
                Timestamp ts[] = new Timestamp[LOG_LENGTH];
                ts[0] = new Timestamp();
                Random rand = new Random(System.nanoTime());
                int logTime = 7;
                for (int i = 1; i < LOG_LENGTH; i++) {
                    ts[i] = ts[i - 1].add(2 + rand.nextInt(logTime), (short)0);
                }
                // on this log we append directly to RQLDatamap
                DataMapLog<String, RQLItem> testLog = new DataMapLog<String, RQLItem>(1000000, "test");
                RQLDataMapLog testrqlmap = new RQLDataMapLog(1, testLog);

                for (int i = 0; i < LOG_LENGTH; i++) {

                    RQLItem item = new RQLItem().addField("", 2*i).addField("t", i);
                    DataEntry<RQLItem> d1
                            = new DataEntry<RQLItem>(item, ts[i]);

                    //System.out.println(item.getField("").getIntVal());
                    String key = ("a");
                    LogEntry<String, RQLItem> le
                            = new LogEntry<String, RQLItem>(key, testLog.getItem(key), d1);

                    try {
                        //System.out.println(t + " - " + key);
                        testrqlmap.append(le);
                    } catch (RetroscopeException re) {re.printStackTrace();}
                }
                this.logs.add(testrqlmap);
                System.out.println(testrqlmap.getHead().getTime());

                // on this one we create DataMap and then put it to the RQLDataMap
                testLog = new DataMapLog<String, RQLItem>(1000000, "test");
                rand = new Random(System.nanoTime());
                for (int i = 0; i < LOG_LENGTH; i++) {
                    RQLItem item = new RQLItem().addField("", 3*i).addField("t", i);
                    DataEntry<RQLItem> d1
                            = new DataEntry<RQLItem>(item, ts[i]);

                    //System.out.println(item.getField("").getIntVal());
                    String key = ("b");
                    LogEntry<String, RQLItem> le
                            = new LogEntry<String, RQLItem>(key, testLog.getItem(key), d1);

                    try {
                        //System.out.println(t + " - " + key);
                        testLog.append(le);
                    } catch (RetroscopeException re) {re.printStackTrace();}
                }
                testrqlmap = new RQLDataMapLog(2, testLog);
                this.logs.add(testrqlmap);
                System.out.println(testrqlmap.getHead().getTime());
            }

            @Override
            public void retrieveRemoteLogs(String[] logs, Timestamp startTime, Timestamp endTime) {

            }

            @Override
            public void retrieveSingleCut(String[] logs, Timestamp cutTime) {

            }

            @Override
            public void retrieveRemoteLogs(String[] logs, Timestamp startTime, Timestamp endTime, int[] nodeList) {

            }
        });

        rql.parse();
        ArrayList<GlobalCut> cuts = rql.getEnvironment().getGlobalCuts();
        System.out.println("# of cuts: " + cuts.size());
        assertTrue(cuts.size() == LOG_LENGTH - 175/5);
    }


    @Test
    public void simpleSingleNodeLogSearchQueryWithMissingParam() throws Exception {

        StringReader q1 = new StringReader("SELECT test.a, test.b FROM test WHEN test.c=5 OR b=10;");
        Scanner scanner = new Scanner(q1);
        scanner.yylex();

        rqlParser rql = new rqlParser(scanner).setEnvironment(new RQLEnvironment() {
            public void retrieveRemoteLogs(String[] logs) {
                DataMapLog<String, RQLItem> testLog = new DataMapLog<String, RQLItem>(1000000, "test");
                Timestamp t = new Timestamp();
                Random rand = new Random(System.nanoTime());
                int logTime = 7;

                RQLItem item = new RQLItem().addField("", -1).addField("v2", 1);
                DataEntry<RQLItem> d1
                        = new DataEntry<RQLItem>(item, t);

                for (int i = 0; i < LOG_LENGTH; i++) {

                    t = t.add(2 + rand.nextInt(logTime), (short)0);
                    item = new RQLItem().addField("", i).addField("v2", i*i);
                    DataEntry<RQLItem> d2
                            = new DataEntry<RQLItem>(item, t);

                    //System.out.println(item.getField("").getIntVal());
                    String key = ("b");
                    LogEntry<String, RQLItem> le
                            = new LogEntry<String, RQLItem>(key, d1, d2);
                    d1 = d2;
                    try {
                        //System.out.println(t + " - " + key);
                        testLog.append(le);
                    } catch (RetroscopeException re) {re.printStackTrace();}
                }
                RQLDataMapLog testrqlmap = new RQLDataMapLog(1, testLog);
                this.logs.add(testrqlmap);
            }

            @Override
            public void retrieveRemoteLogs(String[] logs, Timestamp startTime, Timestamp endTime) {

            }

            @Override
            public void retrieveSingleCut(String[] logs, Timestamp cutTime) {

            }

            @Override
            public void retrieveRemoteLogs(String[] logs, Timestamp startTime, Timestamp endTime, int[] nodeList) {

            }
        });

        rql.parse();
        ArrayList<GlobalCut> cuts = rql.getEnvironment().getGlobalCuts();
        assertTrue(cuts.size() == 1);
        System.out.println("# of warnings: " + rql.getEnvironment().getWarnings().size());
        for (RQLRunTimeWarning w : rql.getEnvironment().getWarnings()) {
            System.out.println(w.getMessage());
        }
        assertTrue(rql.getEnvironment().getWarnings().size() == 1);
        for(int c = 0; c < cuts.size(); c++) {
            boolean a5 = false;
            boolean b10 = false;
            ArrayList<RetroMap<String, RQLItem>> snaps = cuts.get(c).getLocalSnapshots();
            ArrayList<String> snapNames = cuts.get(c).getLocalSnapshotNames();
            for (int i = 0; i < snapNames.size(); i++) {
                if (snapNames.get(i).equals("test")) {
                    if (snaps.get(i).get("b") != null) {
                        b10 = snaps.get(i).get("b").getValue().getField("").getIntVal() == 10;
                    }
                }
            }
            assertTrue(b10);
        }

    }



    @Test
    public void simpleTwoNodeLogSearchQueryWithMissingParam() throws Exception {

        StringReader q1 = new StringReader("SELECT test.a, test.b FROM test WHEN test.c=5 OR b=10;");
        Scanner scanner = new Scanner(q1);
        scanner.yylex();

        rqlParser rql = new rqlParser(scanner).setEnvironment(new RQLEnvironment() {
            public void retrieveRemoteLogs(String[] logs) {
                DataMapLog<String, RQLItem> testLog = new DataMapLog<String, RQLItem>(1000000, "test");
                Timestamp t = new Timestamp();
                Random rand = new Random(System.nanoTime());
                int logTime = 7;
                for (int i = 0; i < LOG_LENGTH; i++) {

                    t = t.add(2 + rand.nextInt(logTime), (short)0);
                    RQLItem item = new RQLItem().addField("", i).addField("v2", i*i);
                    DataEntry<RQLItem> d1
                            = new DataEntry<RQLItem>(item, t);

                    //System.out.println(item.getField("").getIntVal());
                    String key = ("a");
                    LogEntry<String, RQLItem> le
                            = new LogEntry<String, RQLItem>(key, testLog.getItem(key), d1);

                    try {
                        //System.out.println(t + " - " + key);
                        testLog.append(le);
                    } catch (RetroscopeException re) {re.printStackTrace();}
                }
                RQLDataMapLog testrqlmap = new RQLDataMapLog(1, testLog);
                this.logs.add(testrqlmap);
                testLog = new DataMapLog<String, RQLItem>(1000000, "test");
                t = new Timestamp();
                rand = new Random(System.nanoTime());
                for (int i = 0; i < LOG_LENGTH; i++) {

                    t = t.add(2 + rand.nextInt(logTime), (short)0);
                    RQLItem item = new RQLItem().addField("", i).addField("v2", i*i);
                    DataEntry<RQLItem> d1
                            = new DataEntry<RQLItem>(item, t);

                    //System.out.println(item.getField("").getIntVal());
                    String key = ("b");
                    LogEntry<String, RQLItem> le
                            = new LogEntry<String, RQLItem>(key, testLog.getItem(key), d1);

                    try {
                        //System.out.println(t + " - " + key);
                        testLog.append(le);
                    } catch (RetroscopeException re) {re.printStackTrace();}
                }
                testrqlmap = new RQLDataMapLog(1, testLog);
                this.logs.add(testrqlmap);
            }

            @Override
            public void retrieveRemoteLogs(String[] logs, Timestamp startTime, Timestamp endTime) {

            }

            @Override
            public void retrieveSingleCut(String[] logs, Timestamp cutTime) {

            }

            @Override
            public void retrieveRemoteLogs(String[] logs, Timestamp startTime, Timestamp endTime, int[] nodeList) {

            }
        });

        rql.parse();
        ArrayList<GlobalCut> cuts = rql.getEnvironment().getGlobalCuts();
        assertTrue(cuts.size() >= 1);
        System.out.println("# of warnings: " + rql.getEnvironment().getWarnings().size());
        for (RQLRunTimeWarning w : rql.getEnvironment().getWarnings()) {
            System.out.println(w.getMessage());
        }
        assertTrue(rql.getEnvironment().getWarnings().size() >= 1 && rql.getEnvironment().getWarnings().size() <= 2);
        for(int c = 0; c < cuts.size(); c++) {
            boolean a5 = false;
            boolean b10 = false;
            ArrayList<RetroMap<String, RQLItem>> snaps = cuts.get(c).getLocalSnapshots();
            ArrayList<String> snapNames = cuts.get(c).getLocalSnapshotNames();
            for (int i = 0; i < snapNames.size(); i++) {
                if (snapNames.get(i).equals("test")) {
                    if (snaps.get(i).get("b") != null) {
                        b10 = snaps.get(i).get("b").getValue().getField("").getIntVal() == 10;
                    }
                }
            }
            assertTrue(b10);
        }

    }

    @Test
    public void simpleTwoNodeLogSearchQueryWithMissingLog() throws Exception {

        StringReader q1 = new StringReader("SELECT test.a, test.b FROM test WHEN tst.b=5 OR b=10;");
        Scanner scanner = new Scanner(q1);
        scanner.yylex();

        rqlParser rql = new rqlParser(scanner).setEnvironment(new RQLEnvironment() {
            public void retrieveRemoteLogs(String[] logs) {
                DataMapLog<String, RQLItem> testLog = new DataMapLog<String, RQLItem>(1000000, "test");
                Timestamp t = new Timestamp();
                Random rand = new Random(System.nanoTime());
                int logTime = 7;
                for (int i = 0; i < LOG_LENGTH; i++) {

                    t = t.add(2 + rand.nextInt(logTime), (short)0);
                    RQLItem item = new RQLItem().addField("", i).addField("v2", i*i);
                    DataEntry<RQLItem> d1
                            = new DataEntry<RQLItem>(item, t);

                    //System.out.println(item.getField("").getIntVal());
                    String key = ("a");
                    LogEntry<String, RQLItem> le
                            = new LogEntry<String, RQLItem>(key, testLog.getItem(key), d1);

                    try {
                        //System.out.println(t + " - " + key);
                        testLog.append(le);
                    } catch (RetroscopeException re) {re.printStackTrace();}
                }
                RQLDataMapLog testrqlmap = new RQLDataMapLog(1, testLog);
                this.logs.add(testrqlmap);
                testLog = new DataMapLog<String, RQLItem>(1000000, "test");
                t = new Timestamp();
                rand = new Random(System.nanoTime());
                for (int i = 0; i < LOG_LENGTH; i++) {

                    t = t.add(2 + rand.nextInt(logTime), (short)0);
                    RQLItem item = new RQLItem().addField("", i).addField("v2", i*i);
                    DataEntry<RQLItem> d1
                            = new DataEntry<RQLItem>(item, t);

                    //System.out.println(item.getField("").getIntVal());
                    String key = ("b");
                    LogEntry<String, RQLItem> le
                            = new LogEntry<String, RQLItem>(key, testLog.getItem(key), d1);

                    try {
                        //System.out.println(t + " - " + key);
                        testLog.append(le);
                    } catch (RetroscopeException re) {re.printStackTrace();}
                }
                testrqlmap = new RQLDataMapLog(1, testLog);
                this.logs.add(testrqlmap);
            }

            @Override
            public void retrieveRemoteLogs(String[] logs, Timestamp startTime, Timestamp endTime) {

            }

            @Override
            public void retrieveSingleCut(String[] logs, Timestamp cutTime) {

            }

            @Override
            public void retrieveRemoteLogs(String[] logs, Timestamp startTime, Timestamp endTime, int[] nodeList) {

            }
        });

        rql.parse();
        ArrayList<GlobalCut> cuts = rql.getEnvironment().getGlobalCuts();
        assertTrue(cuts.size() == 0);
        assertTrue(rql.getEnvironment().getExceptions().size() == 1);

    }


    @Test
    public void testExpression() throws Exception {
        StringReader q1 = new StringReader("5+4*3;");
        Scanner scanner = new Scanner(q1);
        scanner.yylex();
        rqlParser rql = new rqlParser(scanner);
        rql.parse();
    }

    @Test
    public void testFunc() throws Exception {
        StringReader q1 = new StringReader("Abs(-5*3);");
        Scanner scanner = new Scanner(q1);
        scanner.yylex();
        rqlParser rql = new rqlParser(scanner).setEnvironment(new RQLEnvironment() {
            @Override
            public void retrieveRemoteLogs(String[] logs) {

            }

            @Override
            public void retrieveRemoteLogs(String[] logs, Timestamp startTime, Timestamp endTime) {

            }

            @Override
            public void retrieveRemoteLogs(String[] logs, Timestamp startTime, Timestamp endTime, int[] nodeList) {

            }

            @Override
            public void retrieveSingleCut(String[] logs, Timestamp cutTime) {

            }
        });
        rql.parse();
    }

    @Test
    public void testMaxFunc() throws Exception {
        StringReader q1 = new StringReader("Max(5, 3, -2, 7, 4.0, 12.1, 8, 2, 3.3);");
        Scanner scanner = new Scanner(q1);
        scanner.yylex();
        rqlParser rql = new rqlParser(scanner).setEnvironment(new RQLEnvironment() {
            @Override
            public void retrieveRemoteLogs(String[] logs) {

            }

            @Override
            public void retrieveRemoteLogs(String[] logs, Timestamp startTime, Timestamp endTime) {

            }

            @Override
            public void retrieveRemoteLogs(String[] logs, Timestamp startTime, Timestamp endTime, int[] nodeList) {

            }

            @Override
            public void retrieveSingleCut(String[] logs, Timestamp cutTime) {

            }
        });
        rql.parse();
    }

}