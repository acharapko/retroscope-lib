package retroscope.rql;

import org.junit.Test;
import retroscope.RetroscopeException;
import retroscope.hlc.Timestamp;
import retroscope.log.*;
import retroscope.rql.errors.RQLRunTimeWarning;

import java.io.StringReader;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by Aleksey on 12/20/2016.
 * some tests
 */
public class rqlParserTest {

    private static  int LOG_LENGTH = 100;
    private static  int RUNS = 20;

    /*--------------------------------
    /* Simple Single Node RQL tests
    /*-------------------------------*/
    @Test
    public void simpleSingleNodeLogNoWHENCondition() throws Exception {
        for (int i = 0; i < RUNS; i++) {
            StringReader q1 = new StringReader("SELECT test.a FROM test;");
            Scanner scanner = new Scanner(q1);
            scanner.yylex();

            rqlParser rql = new rqlParser(scanner).setEnvironment(new QueryEnvironment() {
                public void retrieveRemoteLogs(RQLRetrieveParam rqlRetrieveParam) {
                    DataMapLog<String, RQLItem> testLog = new DataMapLog<String, RQLItem>(1000000, "test");
                    Timestamp t = new Timestamp();
                    int logTime = 7;
                    for (int i = 0; i < LOG_LENGTH; i++) {
                        Random rand = new Random(System.nanoTime());
                        t = t.add(2 + rand.nextInt(logTime), (short) 0);
                        RQLItem item = new RQLItem().addField("", i).addField("v2", i * i);
                        DataEntry<RQLItem> d1
                                = new DataEntry<RQLItem>(item, t);
                        //System.out.println(item.getField("").getIntVal());
                        String key = ("a");
                        LogEntry<String, RQLItem> le
                                = new LogEntry<String, RQLItem>(key, testLog.getItem(key), d1);
                        try {
                            //System.out.println(t + " - " + key);
                            testLog.append(le);
                        } catch (RetroscopeException re) {
                            re.printStackTrace();
                        }
                    }
                    RQLDataMapLog testrqlmap = new RQLDataMapLog(1, testLog);
                    this.logs.add(testrqlmap);
                }

                @Override
                public void retrieveSingleCut(RQLRetrieveParam rqlRetrieveParam) {
                }
            });
            rql.parse();
            ArrayList<GlobalCut> cuts = rql.getEnvironment().getEmittedGlobalCuts();
            assertTrue(cuts.size() == LOG_LENGTH);
        }
    }

    @Test
    public void simpleSingleNodeLogSearchQuery() throws Exception {
        for (int i = 0; i < RUNS; i++) {
            Random rand = new Random(System.nanoTime());
            int a = rand.nextInt(LOG_LENGTH / RUNS) + i * LOG_LENGTH / RUNS;
            StringReader q1 = new StringReader("SELECT test.a FROM test WHEN a=" + a +";");
            Scanner scanner = new Scanner(q1);
            scanner.yylex();

            rqlParser rql = new rqlParser(scanner).setEnvironment(new QueryEnvironment() {
                public void retrieveRemoteLogs(RQLRetrieveParam rqlRetrieveParam) {
                    DataMapLog<String, RQLItem> testLog = new DataMapLog<String, RQLItem>(1000000, "test");
                    Timestamp t = new Timestamp();
                    int logTime = 7;
                    for (int i = 0; i < LOG_LENGTH; i++) {
                        Random rand = new Random(System.nanoTime());
                        t = t.add(2 + rand.nextInt(logTime), (short) 0);
                        RQLItem item = new RQLItem().addField("", i).addField("v2", i * i);
                        DataEntry<RQLItem> d1
                                = new DataEntry<RQLItem>(item, t);
                        //System.out.println(item.getField("").getIntVal());
                        String key = ("a");
                        LogEntry<String, RQLItem> le
                                = new LogEntry<String, RQLItem>(key, testLog.getItem(key), d1);
                        try {
                            //System.out.println(t + " - " + key);
                            testLog.append(le);
                        } catch (RetroscopeException re) {
                            re.printStackTrace();
                        }
                    }
                    RQLDataMapLog testrqlmap = new RQLDataMapLog(1, testLog);
                    this.logs.add(testrqlmap);
                }

                @Override
                public void retrieveSingleCut(RQLRetrieveParam rqlRetrieveParam) {
                }
            });
            rql.parse();
            ArrayList<GlobalCut> cuts = rql.getEnvironment().getEmittedGlobalCuts();
            //System.out.println(i+  ") " + cuts.size() + " - " + a[i]);
            assertTrue(cuts.size() == 1);
            Set<DataEntry<RQLItem>> aset = cuts.get(0).getLocalSnapshot("test").get("a");
            boolean atrue = false;
            if (aset.size() == 1) { //we expect only one element
                for (DataEntry<RQLItem> r : aset) {
                    atrue = r.getValue().getField("").getIntVal() == a;
                }
            }
            assertTrue(atrue);
        }
    }

    @Test
    public void simpleSingleNodeLogSearchQueryWithArithmeticCondition() throws Exception {
        for (int i = 0; i < RUNS; i++) {
            Random rand = new Random(System.nanoTime());
            int a1 = rand.nextInt(LOG_LENGTH / RUNS) + i * LOG_LENGTH / RUNS / 2;
            int a2 = rand.nextInt(2) + 1;
            int a3 = rand.nextInt(5);
            StringReader q1 = new StringReader("SELECT test.a FROM test WHEN a=" + a1 + "*" + a2 + "+" + a3 +";");
            Scanner scanner = new Scanner(q1);
            scanner.yylex();

            rqlParser rql = new rqlParser(scanner).setEnvironment(new QueryEnvironment() {
                public void retrieveRemoteLogs(RQLRetrieveParam rqlRetrieveParam) {
                    DataMapLog<String, RQLItem> testLog = new DataMapLog<String, RQLItem>(1000000, "test");
                    Timestamp t = new Timestamp();
                    int logTime = 7;
                    for (int i = 0; i < LOG_LENGTH; i++) {
                        Random rand = new Random(System.nanoTime());
                        t = t.add(2 + rand.nextInt(logTime), (short) 0);
                        RQLItem item = new RQLItem().addField("", i).addField("v2", i * i);
                        DataEntry<RQLItem> d1
                                = new DataEntry<RQLItem>(item, t);
                        String key = ("a");
                        LogEntry<String, RQLItem> le
                                = new LogEntry<String, RQLItem>(key, testLog.getItem(key), d1);
                        try {
                            //System.out.println(t + " - " + key);
                            testLog.append(le);
                        } catch (RetroscopeException re) {
                            re.printStackTrace();
                        }
                    }
                    RQLDataMapLog testrqlmap = new RQLDataMapLog(1, testLog);
                    this.logs.add(testrqlmap);
                }

                @Override
                public void retrieveSingleCut(RQLRetrieveParam rqlRetrieveParam) {
                }
            });
            rql.parse();
            ArrayList<GlobalCut> cuts = rql.getEnvironment().getEmittedGlobalCuts();
            //System.out.println(i+  ") " + cuts.size() + " - " + a[i]);
            if (a1*a2+a3 < LOG_LENGTH) {
                assertTrue(cuts.size() == 1);
                Set<DataEntry<RQLItem>> aset = cuts.get(0).getLocalSnapshot("test").get("a");
                boolean atrue = false;
                if (aset.size() == 1) { //we expect only one element
                    for (DataEntry<RQLItem> r : aset) {
                        atrue = r.getValue().getField("").getIntVal() == a1*a2+a3;
                    }
                }
                assertTrue(atrue);
            } else {
                assertTrue(cuts.size() == 0);
            }
        }
    }

    @Test
    public void simpleSingleNodeLogSearchQueryByNamedParam() throws Exception {
        for (int i = 0; i < RUNS; i++) {
            Random rand = new Random(System.nanoTime());
            int a = rand.nextInt(LOG_LENGTH / RUNS) + i * LOG_LENGTH / RUNS;
            a = a * a;
            StringReader q1 = new StringReader("SELECT test.a FROM test WHEN a:v2 = " + a+";");
            Scanner scanner = new Scanner(q1);
            scanner.yylex();

            rqlParser rql = new rqlParser(scanner).setEnvironment(new QueryEnvironment() {
                public void retrieveRemoteLogs(RQLRetrieveParam rqlRetrieveParam) {
                    DataMapLog<String, RQLItem> testLog = new DataMapLog<String, RQLItem>(1000000, "test");
                    Timestamp t = new Timestamp();
                    Random rand = new Random(System.nanoTime());
                    int logTime = 7;
                    for (int i = 0; i < LOG_LENGTH; i++) {

                        t = t.add(2 + rand.nextInt(logTime), (short) 0);
                        RQLItem item = new RQLItem().addField("", i).addField("v2", i * i);
                        DataEntry<RQLItem> d1
                                = new DataEntry<RQLItem>(item, t);
                        //System.out.println(item.getField("").getIntVal());
                        String key = ("a");
                        LogEntry<String, RQLItem> le
                                = new LogEntry<String, RQLItem>(key, testLog.getItem(key), d1);
                        try {
                            //System.out.println(t + " - " + key);
                            testLog.append(le);
                        } catch (RetroscopeException re) {
                            re.printStackTrace();
                        }
                    }
                    RQLDataMapLog testrqlmap = new RQLDataMapLog(1, testLog);
                    this.logs.add(testrqlmap);
                }

                @Override
                public void retrieveSingleCut(RQLRetrieveParam rqlRetrieveParam) {
                }
            });
            rql.parse();
            ArrayList<GlobalCut> cuts = rql.getEnvironment().getEmittedGlobalCuts();
            //System.out.println(cuts.size());
            assertTrue(cuts.size() == 1);
            Set<DataEntry<RQLItem>> aset = cuts.get(0).getLocalSnapshot("test").get("a");
            boolean atrue = false;
            if (aset.size() == 1) { //we expect only one element
                for (DataEntry<RQLItem> r : aset) {
                    atrue = r.getValue().getField("").getIntVal() == Math.sqrt(a);
                }
            }
            assertTrue(atrue);
        }
    }

    @Test
    public void testAnd() throws Exception {
        for (int i = 0; i < RUNS; i++) {
            Random rand = new Random(System.nanoTime());
            int a =  rand.nextInt(LOG_LENGTH / RUNS) + i * LOG_LENGTH / RUNS;
            StringReader q1 = new StringReader("SELECT test.a FROM test WHEN a >= " + a + " AND a:v2 = 4;");
            Scanner scanner = new Scanner(q1);
            scanner.yylex();

            rqlParser rql = new rqlParser(scanner).setEnvironment(new QueryEnvironment() {
                public void retrieveRemoteLogs(RQLRetrieveParam rqlRetrieveParam) {
                    DataMapLog<String, RQLItem> testLog = new DataMapLog<String, RQLItem>(1000000, "test");
                    Timestamp t = new Timestamp();
                    Random rand = new Random(System.nanoTime());
                    int logTime = 7;
                    for (int i = 0; i < LOG_LENGTH; i++) {

                        t = t.add(2 + rand.nextInt(logTime), (short) 0);
                        RQLItem item = new RQLItem().addField("", i).addField("v2", i % 5);
                        DataEntry<RQLItem> d1
                                = new DataEntry<RQLItem>(item, t);
                        //System.out.println(item.getField("").getIntVal());
                        String key = ("a");
                        LogEntry<String, RQLItem> le
                                = new LogEntry<String, RQLItem>(key, testLog.getItem(key), d1);
                        try {
                            //System.out.println(t + " - " + key);
                            testLog.append(le);
                        } catch (RetroscopeException re) {
                            re.printStackTrace();
                        }
                    }
                    RQLDataMapLog testrqlmap = new RQLDataMapLog(1, testLog);
                    this.logs.add(testrqlmap);
                }

                @Override
                public void retrieveSingleCut(RQLRetrieveParam rqlRetrieveParam) {
                }
            });
            rql.parse();
            ArrayList<GlobalCut> cuts = rql.getEnvironment().getEmittedGlobalCuts();
            //System.out.println(a + " ; " + cuts.size());
            int numCuts = (int) Math.ceil((LOG_LENGTH - a) / 5.0);
            assertTrue(cuts.size() == numCuts);
        }
    }

    @Test
    public void testOR() throws Exception {
        for (int i = 0; i < RUNS; i++) {
            Random rand = new Random(System.nanoTime());
            int a =  rand.nextInt(LOG_LENGTH / RUNS) + i * LOG_LENGTH / RUNS;
            StringReader q1 = new StringReader("SELECT test.a FROM test WHEN a > " + a + " OR a:v2 = 4;");
            Scanner scanner = new Scanner(q1);
            scanner.yylex();

            rqlParser rql = new rqlParser(scanner).setEnvironment(new QueryEnvironment() {
                public void retrieveRemoteLogs(RQLRetrieveParam rqlRetrieveParam) {
                    DataMapLog<String, RQLItem> testLog = new DataMapLog<String, RQLItem>(1000000, "test");
                    Timestamp t = new Timestamp();
                    Random rand = new Random(System.nanoTime());
                    int logTime = 7;
                    for (int i = 0; i < LOG_LENGTH; i++) {

                        t = t.add(2 + rand.nextInt(logTime), (short) 0);
                        RQLItem item = new RQLItem().addField("", i).addField("v2", i % 5);
                        DataEntry<RQLItem> d1
                                = new DataEntry<RQLItem>(item, t);
                        //System.out.println(item.getField("").getIntVal());
                        String key = ("a");
                        LogEntry<String, RQLItem> le
                                = new LogEntry<String, RQLItem>(key, testLog.getItem(key), d1);
                        try {
                            //System.out.println(t + " - " + key);
                            testLog.append(le);
                        } catch (RetroscopeException re) {
                            re.printStackTrace();
                        }
                    }
                    RQLDataMapLog testrqlmap = new RQLDataMapLog(1, testLog);
                    this.logs.add(testrqlmap);
                }

                @Override
                public void retrieveSingleCut(RQLRetrieveParam rqlRetrieveParam) {
                }
            });
            rql.parse();
            ArrayList<GlobalCut> cuts = rql.getEnvironment().getEmittedGlobalCuts();
            int c1 = LOG_LENGTH - a - 1;
            int c2 = (int) Math.floor((a+1) / 5.0);
            //System.out.println(a + " ; " + cuts.size() + " (" + c1 + " + " + c2 + ")");
            assertTrue(cuts.size() == c1 + c2);
        }
    }

    /*--------------------------------
    /* Simple Two Node RQL tests
    /*-------------------------------*/

    @Test
    public void simpleTwoNodeLogSearchQuery() throws Exception {

        StringReader q1 = new StringReader("SELECT test.a, test.b FROM test WHEN test.a=5 OR b=10;");
        Scanner scanner = new Scanner(q1);
        scanner.yylex();

        rqlParser rql = new rqlParser(scanner).setEnvironment(new QueryEnvironment() {
            public void retrieveRemoteLogs(RQLRetrieveParam rqlRetrieveParam) {
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
                        //System.out.println(t + " - " + key + " = " + i);
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
                        //System.out.println(t + " - " + key + " = " + i);
                        testLog.append(le);
                    } catch (RetroscopeException re) {re.printStackTrace();}
                }
                testrqlmap = new RQLDataMapLog(2, testLog);
                this.logs.add(testrqlmap);
                System.out.println(testrqlmap.getHead().getTime());
            }
            @Override
            public void retrieveSingleCut(RQLRetrieveParam rqlRetrieveParam) {
            }
        });

        rql.parse();
        ArrayList<GlobalCut> cuts = rql.getEnvironment().getEmittedGlobalCuts();
        System.out.println("\n# of cuts: " + cuts.size());
        System.out.println("# of errors: " + rql.getEnvironment().getExceptions().size());
        System.out.println("# of warn: " + rql.getEnvironment().getWarnings().size());
        for (RQLRunTimeWarning w : rql.getEnvironment().getWarnings()) {
            System.out.println(w.getMessage());
        }
        assertTrue(cuts.size() >= 2);
        System.out.println(cuts.get(0).getLocalSnapshot("test"));
        for(int c = 0; c < cuts.size(); c++) {
            boolean a5 = false;
            boolean b10 = false;
            ArrayList<RQLSetMap> snaps = cuts.get(c).getLocalSnapshots();
            ArrayList<String> snapNames = cuts.get(c).getLocalSnapshotNames();
            for (int i = 0; i < snapNames.size(); i++) {
                if (snapNames.get(i).equals("test")) {
                    if (snaps.get(i).get("a") != null) {
                        Set<DataEntry<RQLItem>> aset = snaps.get(i).get("a");
                        if (aset.size() == 1) { //we expect only one element
                            for (DataEntry<RQLItem> r : aset) {
                                a5 = r.getValue().getField("").getIntVal() == 5;
                            }
                        }
                    }
                    if (snaps.get(i).get("b") != null) {
                        Set<DataEntry<RQLItem>> bset = snaps.get(i).get("b");
                        if (bset.size() == 1) { //we expect only one element
                            for (DataEntry<RQLItem> r : bset) {
                                b10 = r.getValue().getField("").getIntVal() == 10;
                            }
                        }
                    }
                }
            }
            assertTrue(a5 || b10);
        }
        displayCuts(rql);
    }

    @Test
    public void simpleTwoNodeLogSearchQueryWithStrReplaceAndCast() throws Exception {

        StringReader q1 = new StringReader("SELECT r1 FROM setd WHEN Int(StrReplace(r1, \"tst\", \"\")) - Int(StrReplace(r1, \"tst\", \"\")) > 1;");
        Scanner scanner = new Scanner(q1);
        scanner.yylex();

        rqlParser rql = new rqlParser(scanner).setEnvironment(new QueryEnvironment() {
            public void retrieveRemoteLogs(RQLRetrieveParam rqlRetrieveParam) {
                // on this log we append directly to RQLDatamap
                DataMapLog<String, RQLItem> testLog = new DataMapLog<String, RQLItem>(1000000, "setd");
                RQLDataMapLog testrqlmap = new RQLDataMapLog(1, testLog);
                Timestamp t = new Timestamp();
                Random rand = new Random(System.nanoTime());
                int logTime = 7;
                for (int i = 0; i < LOG_LENGTH; i++) {

                    t = t.add(2 + rand.nextInt(logTime), (short)0);
                    RQLItem item = new RQLItem().addField("", "tst" + i);
                    DataEntry<RQLItem> d1
                            = new DataEntry<RQLItem>(item, t);

                    //System.out.println(item.getField("").getIntVal());
                    String key = ("r1");
                    LogEntry<String, RQLItem> le
                            = new LogEntry<String, RQLItem>(key, testLog.getItem(key), d1);

                    try {
                        //System.out.println(t + " - " + key + " = " + i);
                        testrqlmap.append(le);
                    } catch (RetroscopeException re) {re.printStackTrace();}
                }
                this.logs.add(testrqlmap);
                //System.out.println(testLog.getHead().getTime());

                // on this one we create DataMap and then put it to the RQLDataMap
                testLog = new DataMapLog<String, RQLItem>(1000000, "setd");
                t = new Timestamp();
                rand = new Random(System.nanoTime());
                for (int i = 1; i < LOG_LENGTH; i++) {

                    t = t.add(2 + rand.nextInt(logTime), (short)0);
                    RQLItem item = new RQLItem().addField("", "tst" + i);
                    DataEntry<RQLItem> d1
                            = new DataEntry<RQLItem>(item, t);

                    //System.out.println(item.getField("").getIntVal());
                    String key = ("r1");
                    LogEntry<String, RQLItem> le
                            = new LogEntry<String, RQLItem>(key, testLog.getItem(key), d1);

                    try {
                        //System.out.println(t + " - " + key + " = " + i);
                        testLog.append(le);
                    } catch (RetroscopeException re) {re.printStackTrace();}
                }
                testrqlmap = new RQLDataMapLog(2, testLog);
                this.logs.add(testrqlmap);
                //System.out.println(testrqlmap.getHead().getTime());
            }
            @Override
            public void retrieveSingleCut(RQLRetrieveParam rqlRetrieveParam) {
            }
        });

        rql.parse();
        ArrayList<GlobalCut> cuts = rql.getEnvironment().getEmittedGlobalCuts();
        /*System.out.println("\n# of cuts: " + cuts.size());
        System.out.println("# of errors: " + rql.getEnvironment().getExceptions().size());
        System.out.println("# of warn: " + rql.getEnvironment().getWarnings().size());
        for (RQLRunTimeWarning w : rql.getEnvironment().getWarnings()) {
            System.out.println(w.getMessage());
        }*/
        assertTrue(cuts.size() >= 0);
        for(int c = 0; c < cuts.size(); c++) {
            ArrayList<RQLSetMap> snaps = cuts.get(c).getLocalSnapshots();
            ArrayList<String> snapNames = cuts.get(c).getLocalSnapshotNames();
            int v[] = new int[2];
            int cntr = 0;
            assertTrue(snapNames.size() == 2);
            for (int i = 0; i < snapNames.size(); i++) {
                if (snapNames.get(i).equals("setd")) {
                    if (snaps.get(i).get("r1") != null) {
                        Set<DataEntry<RQLItem>> aset = snaps.get(i).get("r1");
                        assertTrue(aset.size() == 1);
                        for (DataEntry<RQLItem> r : aset) {
                            v[cntr++] = Integer.parseInt(r.getValue().getField("").getStringVal().replace("tst", ""));
                        }
                    }
                }
            }
            assertTrue(Math.abs(v[0] - v[1]) > 1);
        }
    }


    @Test
    public void twoNodeLogLink() throws Exception {
        StringReader q1 = new StringReader("SELECT test.a, test.b FROM test WHEN a >= 20 AND b <= 80 LINK($0, $1);");
        Scanner scanner = new Scanner(q1);
        scanner.yylex();

        rqlParser rql = new rqlParser(scanner).setEnvironment(new QueryEnvironment() {
            public void retrieveRemoteLogs(RQLRetrieveParam rqlRetrieveParam) {
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
                    String key;
                    LogEntry<String, RQLItem> le;
                    int ii = i - 50;
                    if (ii % 3 == 0) {
                        RQLItem item = new RQLItem().addField("", 2*ii);
                        DataEntry<RQLItem> d1
                                = new DataEntry<RQLItem>(item, ts[i]);
                        key = ("b");
                        le = new LogEntry<String, RQLItem>(key, testLog.getItem(key), d1);
                    } else {
                        RQLItem item = new RQLItem().addField("", ii);
                        DataEntry<RQLItem> d1
                                = new DataEntry<RQLItem>(item, ts[i]);
                        key = ("a");
                        le = new LogEntry<String, RQLItem>(key, testLog.getItem(key), d1);
                    }
                    try {
                        //System.out.println(ts[i] + " - " + key);
                        testrqlmap.append(le);
                    } catch (RetroscopeException re) {re.printStackTrace();}
                }
                this.logs.add(testrqlmap);
                //System.out.println(testrqlmap.getHead().getTime());
                // on this one we create DataMap and then put it to the RQLDataMap
                testLog = new DataMapLog<String, RQLItem>(1000000, "test");
                for (int i = 0; i < LOG_LENGTH; i++) {

                    String key;
                    LogEntry<String, RQLItem> le;
                    if (i % 4 == 0) {
                        RQLItem item = new RQLItem().addField("", 3*i);
                        DataEntry<RQLItem> d1
                                = new DataEntry<RQLItem>(item, ts[i]);
                        key = ("b");
                        le = new LogEntry<String, RQLItem>(key, testLog.getItem(key), d1);
                    } else {
                        RQLItem item = new RQLItem().addField("", i);
                        DataEntry<RQLItem> d1
                                = new DataEntry<RQLItem>(item, ts[i]);
                        key = ("a");
                        le = new LogEntry<String, RQLItem>(key, testLog.getItem(key), d1);
                    }
                    try {
                        //System.out.println(ts[i] + " - " + key);
                        testLog.append(le);
                    } catch (RetroscopeException re) {re.printStackTrace();}
                }
                testrqlmap = new RQLDataMapLog(2, testLog);
                this.logs.add(testrqlmap);
                //System.out.println(testrqlmap.getHead().getTime());
            }
            @Override
            public void retrieveSingleCut(RQLRetrieveParam rqlRetrieveParam) {
            }
        });

        rql.parse();
        ArrayList<GlobalCut> cuts = rql.getEnvironment().getEmittedGlobalCuts();
        System.out.println("# of cuts: " + cuts.size());
        assertTrue(cuts.size() == 29);

    }

    @Test
    public void simpleTwoNodeLogSearchQueryWithForAllLinkDifferentNodes() throws Exception {

        StringReader q1 = new StringReader("SELECT test.a, test.b FROM test WHEN a + b > 40 FA_LINK($0, $1);");
        Scanner scanner = new Scanner(q1);
        scanner.yylex();

        rqlParser rql = new rqlParser(scanner).setEnvironment(new QueryEnvironment() {
            public void retrieveRemoteLogs(RQLRetrieveParam rqlRetrieveParam) {
                // on this log we append directly to RQLDatamap
                DataMapLog<String, RQLItem> testLog = new DataMapLog<String, RQLItem>(1000000, "test");
                RQLDataMapLog testrqlmap = new RQLDataMapLog(1, testLog);
                Timestamp t = new Timestamp();
                Random rand = new Random(System.nanoTime());
                int logTime = 7;
                for (int i = 0; i < LOG_LENGTH; i++) {
                    t = t.add(2 + rand.nextInt(logTime), (short)0);
                    RQLItem item = new RQLItem().addField("", i).addField("v2", i*i);
                    DataEntry<RQLItem> d1 = new DataEntry<RQLItem>(item, t);
                    String key = ("a");
                    LogEntry<String, RQLItem> le = new LogEntry<String, RQLItem>(key, testLog.getItem(key), d1);
                    try {
                        testrqlmap.append(le);
                    } catch (RetroscopeException re) {re.printStackTrace();}
                }
                this.logs.add(testrqlmap);

                // on this one we create DataMap and then put it to the RQLDataMap
                testLog = new DataMapLog<String, RQLItem>(1000000, "test");
                t = new Timestamp();
                rand = new Random(System.nanoTime());
                for (int i = 0; i < LOG_LENGTH; i++) {

                    t = t.add(2 + rand.nextInt(logTime), (short)0);
                    RQLItem item = new RQLItem().addField("", i).addField("v2", i*i);
                    DataEntry<RQLItem> d1 = new DataEntry<RQLItem>(item, t);
                    String key = ("b");
                    LogEntry<String, RQLItem> le = new LogEntry<String, RQLItem>(key, testLog.getItem(key), d1);
                    try {
                        testLog.append(le);
                    } catch (RetroscopeException re) {re.printStackTrace();}
                }
                testrqlmap = new RQLDataMapLog(2, testLog);
                this.logs.add(testrqlmap);
            }
            @Override
            public void retrieveSingleCut(RQLRetrieveParam rqlRetrieveParam) {
            }
        });

        rql.parse();
        ArrayList<GlobalCut> cuts = rql.getEnvironment().getEmittedGlobalCuts();
        assertTrue(cuts.size() == 0); //a and b are on different nodes in this one.
    }

    @Test
    public void simpleTwoNodeLogSearchQueryWithForAllLink() throws Exception {

        for (int i = 0; i < RUNS; i++) {
            int condition = i * 5;

            StringReader q1 = new StringReader("SELECT test.a, test.b FROM test WHEN a + b > " + condition + " FA_LINK($0, $1);");
            Scanner scanner = new Scanner(q1);
            scanner.yylex();

            rqlParser rql = new rqlParser(scanner).setEnvironment(new QueryEnvironment() {
                public void retrieveRemoteLogs(RQLRetrieveParam rqlRetrieveParam) {
                    Timestamp ts[] = new Timestamp[LOG_LENGTH];
                    ts[0] = new Timestamp();
                    Random rand = new Random(System.nanoTime());
                    int logTime = 7;
                    for (int i = 1; i < LOG_LENGTH; i++) {
                        ts[i] = ts[i - 1].add(2 + rand.nextInt(logTime), (short) 0);
                    }
                    // on this log we append directly to RQLDatamap
                    DataMapLog<String, RQLItem> testLog = new DataMapLog<String, RQLItem>(1000000, "test");
                    RQLDataMapLog testrqlmap = new RQLDataMapLog(1, testLog);

                    for (int i = 0; i < LOG_LENGTH; i++) {
                        String key;
                        LogEntry<String, RQLItem> le;
                        int ii = i - 50;
                        if (ii % 3 == 0) {
                            RQLItem item = new RQLItem().addField("", 2 * ii);
                            DataEntry<RQLItem> d1 = new DataEntry<RQLItem>(item, ts[i]);
                            key = ("b");
                            le = new LogEntry<String, RQLItem>(key, testLog.getItem(key), d1);
                        } else {
                            RQLItem item = new RQLItem().addField("", ii);
                            DataEntry<RQLItem> d1 = new DataEntry<RQLItem>(item, ts[i]);
                            key = ("a");
                            le = new LogEntry<String, RQLItem>(key, testLog.getItem(key), d1);
                        }
                        try {
                            testrqlmap.append(le);
                        } catch (RetroscopeException re) {
                            re.printStackTrace();
                        }
                    }
                    this.logs.add(testrqlmap);

                    // on this one we create DataMap and then put it to the RQLDataMap
                    testLog = new DataMapLog<String, RQLItem>(1000000, "test");
                    for (int i = 0; i < LOG_LENGTH; i++) {
                        String key;
                        LogEntry<String, RQLItem> le;
                        if (i % 4 == 0) {
                            RQLItem item = new RQLItem().addField("", 3 * i);
                            DataEntry<RQLItem> d1 = new DataEntry<RQLItem>(item, ts[i]);
                            key = ("b");
                            le = new LogEntry<String, RQLItem>(key, testLog.getItem(key), d1);
                        } else {
                            RQLItem item = new RQLItem().addField("", i);
                            DataEntry<RQLItem> d1 = new DataEntry<RQLItem>(item, ts[i]);
                            key = ("a");
                            le = new LogEntry<String, RQLItem>(key, testLog.getItem(key), d1);
                        }
                        try {
                            testLog.append(le);
                        } catch (RetroscopeException re) {
                            re.printStackTrace();
                        }
                    }
                    testrqlmap = new RQLDataMapLog(2, testLog);
                    this.logs.add(testrqlmap);
                }

                @Override
                public void retrieveSingleCut(RQLRetrieveParam rqlRetrieveParam) {
                }
            });

            rql.parse();
            ArrayList<GlobalCut> cuts = rql.getEnvironment().getEmittedGlobalCuts();
            for (int c = 0; c < cuts.size(); c++) {
                Set<Integer> nodeids = cuts.get(c).getKnownNodes();
                for (Integer n : nodeids) {
                    long a = 0;
                    long b = 0;
                    Set<DataEntry<RQLItem>> aset = cuts.get(c).getLocalSnapshot("test", n).get("a");
                    Set<DataEntry<RQLItem>> bset = cuts.get(c).getLocalSnapshot("test", n).get("b");
                    if (aset.size() == 1) { //we expect only one element
                        for (DataEntry<RQLItem> r : aset) {
                            a = r.getValue().getField("").getIntVal();
                        }
                    }
                    if (bset.size() == 1) { //we expect only one element
                        for (DataEntry<RQLItem> r : bset) {
                            b = r.getValue().getField("").getIntVal();
                        }
                    }
                    assertTrue(a + b > condition);
                }
            }
        }
    }

    @Test
    public void twoNodeLogPlaceholder() throws Exception {
        StringReader q1 = new StringReader("SELECT test.a, test.b FROM test WHEN a >= 40 AND $0:v <= 99;");
        Scanner scanner = new Scanner(q1);
        scanner.yylex();

        rqlParser rql = new rqlParser(scanner).setEnvironment(new QueryEnvironment() {
            public void retrieveRemoteLogs(RQLRetrieveParam rqlRetrieveParam) {
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

                    RQLItem item = new RQLItem().addField("", i).addField("v", 2*i);
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
                //System.out.println(testrqlmap.getHead().getTime());
                // on this one we create DataMap and then put it to the RQLDataMap
                testLog = new DataMapLog<String, RQLItem>(1000000, "test");
                for (int i = 0; i < LOG_LENGTH; i++) {
                    RQLItem item = new RQLItem().addField("", i).addField("v", 3*i);
                    DataEntry<RQLItem> d1
                            = new DataEntry<RQLItem>(item, ts[i]);
                    //System.out.println(item.getField("").getIntVal());
                    String key = ("a");
                    LogEntry<String, RQLItem> le
                            = new LogEntry<String, RQLItem>(key, testLog.getItem(key), d1);
                    try {
                        //System.out.println(t + " - " + key);
                        testLog.append(le);
                    } catch (RetroscopeException re) {re.printStackTrace();}
                }
                testrqlmap = new RQLDataMapLog(2, testLog);
                this.logs.add(testrqlmap);
                //System.out.println(testrqlmap.getHead().getTime());
            }
            @Override
            public void retrieveSingleCut(RQLRetrieveParam rqlRetrieveParam) {
            }
        });

        rql.parse();
        ArrayList<GlobalCut> cuts = rql.getEnvironment().getEmittedGlobalCuts();
        //System.out.println("# of cuts: " + cuts.size());
        assertTrue(cuts.size() == 10);
    }



    /*-----------------------------
    /* Error Conditions Tests
    /*-----------------------------*/

    @Test
    public void twoLogsAmbiguousExceptionTest() throws Exception {
        StringReader q1 = new StringReader("SELECT log1.a, log2.b FROM log1, log2 WHEN a=5 AND b = 5;");
        Scanner scanner = new Scanner(q1);
        scanner.yylex();

        rqlParser rql = new rqlParser(scanner).setEnvironment(new QueryEnvironment() {
            public void retrieveRemoteLogs(RQLRetrieveParam rqlRetrieveParam) {
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
            public void retrieveSingleCut(RQLRetrieveParam rqlRetrieveParam) {
            }
        });

        rql.parse();
        ArrayList<GlobalCut> cuts = rql.getEnvironment().getEmittedGlobalCuts();
        assertTrue(rql.getEnvironment().hasRunTimeErrors());
        assertTrue(rql.getEnvironment().getExceptions().size() == 1);
        assertTrue(cuts.size() == 0);
    }

    @Test
    public void simpleTwoNodeLogSearchQueryWithMissingLog() throws Exception {

        StringReader q1 = new StringReader("SELECT test.a, test.b FROM test WHEN tst.b=5 OR b=10;");
        Scanner scanner = new Scanner(q1);
        scanner.yylex();

        rqlParser rql = new rqlParser(scanner).setEnvironment(new QueryEnvironment() {
            @Override
            public void retrieveRemoteLogs(RQLRetrieveParam rqlRetrieveParam) {
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
            public void retrieveSingleCut(RQLRetrieveParam rqlRetrieveParam) {
            }
        });
        rql.parse();
        ArrayList<GlobalCut> cuts = rql.getEnvironment().getEmittedGlobalCuts();
        assertTrue(cuts.size() > 0);
        System.out.println("# of warnings: " + rql.getEnvironment().getWarnings().size());
        for (RQLRunTimeWarning w : rql.getEnvironment().getWarnings()) {
            System.out.println(w.getMessage());
        }
        assertTrue(rql.getEnvironment().getWarnings().size() >= 1);
    }

    @Test
    public void simpleTwoNodeLogSearchQueryWithMissingParam() throws Exception {

        StringReader q1 = new StringReader("SELECT test.a, test.b FROM test WHEN test.c=5 OR b=10;");
        Scanner scanner = new Scanner(q1);
        scanner.yylex();

        rqlParser rql = new rqlParser(scanner).setEnvironment(new QueryEnvironment() {
            public void retrieveRemoteLogs(RQLRetrieveParam rqlRetrieveParam) {
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
            public void retrieveSingleCut(RQLRetrieveParam rqlRetrieveParam) {
            }
        });

        rql.parse();
        ArrayList<GlobalCut> cuts = rql.getEnvironment().getEmittedGlobalCuts();
        assertTrue(cuts.size() >= 1);
        System.out.println("# of warnings: " + rql.getEnvironment().getWarnings().size());
        for (RQLRunTimeWarning w : rql.getEnvironment().getWarnings()) {
            System.out.println(w.getMessage());
        }
        assertTrue(rql.getEnvironment().getWarnings().size() >= 1);
        for(int c = 0; c < cuts.size(); c++) {
            boolean b10 = false;
            ArrayList<RQLSetMap> snaps = cuts.get(c).getLocalSnapshots();
            ArrayList<String> snapNames = cuts.get(c).getLocalSnapshotNames();
            for (int i = 0; i < snapNames.size(); i++) {
                if (snapNames.get(i).equals("test")) {
                    if (snaps.get(i).get("b") != null) {
                        Set<DataEntry<RQLItem>> bset = snaps.get(i).get("b");
                        if (bset.size() == 1) { //we expect only one element
                            for (DataEntry<RQLItem> r : bset) {
                                b10 = r.getValue().getField("").getIntVal() == 10;
                            }
                        }
                    }
                }
            }
            assertTrue(b10);
        }
    }

    /*------------------------------
    /* Miscellaneous
    /*-----------------------------*/

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
        rqlParser rql = new rqlParser(scanner).setEnvironment(new QueryEnvironment() {
            @Override
            public void retrieveRemoteLogs(RQLRetrieveParam rqlRetrieveParam) {
            }
            @Override
            public void retrieveSingleCut(RQLRetrieveParam rqlRetrieveParam) {
            }
        });
        rql.parse();
    }

    @Test
    public void testMaxFunc() throws Exception {
        StringReader q1 = new StringReader("Max(5, 3, -2, 7, 4.0, 12.1, 8, 2, 3.3);");
        Scanner scanner = new Scanner(q1);
        scanner.yylex();
        rqlParser rql = new rqlParser(scanner).setEnvironment(new QueryEnvironment() {
            @Override
            public void retrieveRemoteLogs(RQLRetrieveParam rqlRetrieveParam) {
            }
            @Override
            public void retrieveSingleCut(RQLRetrieveParam rqlRetrieveParam) {
            }
        });
        rql.parse();
    }

    @Test
    public void testHLCandNow() throws Exception {
        StringReader q1 = new StringReader("HLCFromPT(Now() - 100);");
        Scanner scanner = new Scanner(q1);
        scanner.yylex();
        rqlParser rql = new rqlParser(scanner).setEnvironment(new QueryEnvironment() {
            @Override
            public void retrieveRemoteLogs(RQLRetrieveParam rqlRetrieveParam) {
            }
            @Override
            public void retrieveSingleCut(RQLRetrieveParam rqlRetrieveParam) {
            }
        });
        rql.parse();
    }

    @Test
    public void testStrReplace() throws Exception {
        StringReader q1 = new StringReader("StrReplace(\"tst5\", \"tst\", \"\");");
        Scanner scanner = new Scanner(q1);
        scanner.yylex();
        rqlParser rql = new rqlParser(scanner).setEnvironment(new QueryEnvironment() {
            @Override
            public void retrieveRemoteLogs(RQLRetrieveParam rqlRetrieveParam) {
            }
            @Override
            public void retrieveSingleCut(RQLRetrieveParam rqlRetrieveParam) {
            }
        });
        rql.parse();
    }


    private void displayCuts(rqlParser parser) {
        StringBuilder sb = new StringBuilder();
        // Send all output to the Appendable object sb
        Formatter formatter = new Formatter(sb, Locale.US);

        if (parser.getEnvironment().getEmittedGlobalCuts().size() > 0) {
            for (GlobalCut cut : parser.getEnvironment().getEmittedGlobalCuts()) {
                sb.append(String.format("%80s", "").replace(" ", "-"));
                sb.append(System.getProperty("line.separator"));
                formatter.format("%38s %-15s","Cut @ HLC :", cut.getCutTime().toString());
                sb.append(System.getProperty("line.separator"));

                Set<String> logs = new HashSet<String>();
                logs.addAll(cut.getLocalSnapshotNames());
                List<Integer> nodes = cut.getNodeIds();

                for (String logName : logs) {
                    sb.append(String.format("%80s", "").replace(" ", "-"));
                    sb.append(System.getProperty("line.separator"));
                    formatter.format("%42s %-30s", "Log : ", logName);
                    sb.append(System.getProperty("line.separator"));
                    for (int id : nodes) {
                        RHashMap<String, ?, RQLItem> tsnap = cut.getLocalSnapshot(logName, id);
                        if (tsnap != null) {
                            sb.append(String.format("%80s", "").replace(" ", "*"));
                            sb.append(System.getProperty("line.separator"));
                            formatter.format("%42s %-10d", "Node :", id);
                            sb.append(System.getProperty("line.separator"));
                            if (tsnap instanceof RetroMap) {
                                RetroMap<String, RQLItem> snap = (RetroMap<String, RQLItem>) tsnap;
                                Iterator<Map.Entry<String, DataEntry<RQLItem>>> it = snap.entrySet().iterator();
                                while (it.hasNext()) {
                                    Map.Entry<String, DataEntry<RQLItem>> pair = it.next();
                                    RQLItem item = pair.getValue().getValue();
                                    sb.append(item.toFriendlyString(pair.getKey()));
                                    sb.append(System.getProperty("line.separator"));
                                }
                            } else if (tsnap instanceof RQLSetMap) {
                                RQLSetMap snap = (RQLSetMap) tsnap;
                                Iterator<Map.Entry<String, Set<DataEntry<RQLItem>>>> it = snap.entrySet().iterator();
                                while (it.hasNext()) {
                                    Map.Entry<String, Set<DataEntry<RQLItem>>> pair = it.next();
                                    Set<DataEntry<RQLItem>> items = pair.getValue();
                                    for (DataEntry<RQLItem> item : items) {
                                        sb.append(item.getValue().toFriendlyString(pair.getKey()));
                                        sb.append(System.getProperty("line.separator"));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(sb);
    }
}