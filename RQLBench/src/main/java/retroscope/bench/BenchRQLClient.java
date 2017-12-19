package retroscope.bench;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import retroscope.rql.environment.GlobalCut;
import retroscope.rql.parser.rqlParser;
import retroscope.rql.server.Runner;
import retroscope.rql.server.StatementProcessor;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BenchRQLClient {

    private rqlParser parser;
    private Ignite ignite;
    private String appName;

    private ArrayList<String> queries;
    private int queryRepeat = 1;

    private long totalEvents = -1;

    private ArrayList<QueryResult> queryResults;

    public BenchRQLClient(String igniteConfig, String appName) {
        parser = new rqlParser();

        Ignition.setClientMode(true);
        if (!igniteConfig.isEmpty()) {
            ignite = Ignition.start(igniteConfig);
        } else {
            ignite = Ignition.start();
        }
        this.appName = appName;
        this.queries = new ArrayList<>();
        queryResults = new ArrayList<>();
    }

    public void addQuery(String q) {
        queries.add(q);
    }

    public void setQueries(ArrayList<String> queries) {
        this.queries = queries;
    }

    public void setQueryRepeat(int queryRepeat) {
        this.queryRepeat = queryRepeat;
    }

    public void setTotalEvents(long totalEvents) {
        this.totalEvents = totalEvents;
    }

    public void runWorkload() {
        //warm up
        System.out.println("------------------WARM UP---------------------");
        ArrayList<String> warmup = new ArrayList<>();
        warmup.add(this.queries.get(0));
        runQueries(false, warmup, 8);
        System.out.println("-----------------END WARM UP------------------");
        System.out.println("-------------------TEST RUN-------------------");
        queryResults = new ArrayList<>();
        //actual run
        runQueries(true, this.queries, this.queryRepeat);
        System.out.println("----------------END TEST RUN------------------");
    }

    private void runQueries(boolean print, Collection<String> queries, int queryRepeat) {

        for (String q: queries) {
            for(int i = 0; i < queryRepeat; i++) {
                try {
                    StringReader q1 = new StringReader(q);
                    retroscope.rql.parser.Scanner scanner = new retroscope.rql.parser.Scanner(q1);
                    try {
                        scanner.yylex();
                    } catch (IOException ioe) {
                        System.err.println("io exception: " + ioe.getMessage());
                    }
                    long startTime = System.currentTimeMillis();
                    parser.resetQueries();
                    parser.setScanner(scanner);
                    parser.parse();
                    List<Runner> runners = StatementProcessor.processStatements(
                            parser.getQueries(),
                            appName,
                            ignite
                    );

                    int cuts = 0;
                    for (Runner s: runners) {
                        cuts += s.getEmittedCuts().size();
                    }
                    long endTime = System.currentTimeMillis();
                    queryResults.add(new QueryResult(endTime - startTime, cuts));
                    //displayCuts();
                } catch (Exception e) {
                    System.err.println("Exception (console) " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        if (print) {
            printStats();
        }
    }

    private void printStats() {
        //compute average latency and TP
        long sumLatencies = 0;
        long sumNumCuts = 0;
        double sumTP = 0;
        for (QueryResult r : queryResults) {
            sumLatencies += r.getQueryRuntime();
            sumNumCuts += r.getNumCuts();
            sumTP += ((double) totalEvents) / r.getQueryRuntime();
        }
        double avLat = sumLatencies / (double)queryResults.size();
        double avNumCuts = sumNumCuts / (double)queryResults.size();
        double avTP = sumTP / (double)queryResults.size();
        //compute stdev
        double sumOfSquaresLat = 0;
        double sumOfSquaresNumCuts = 0;
        double sumOfSquaresTP = 0;
        for (QueryResult r : queryResults) {
            sumOfSquaresLat += Math.pow(r.getQueryRuntime() - avLat, 2);
            sumOfSquaresNumCuts += Math.pow(r.getNumCuts() - avNumCuts, 2);
            sumOfSquaresTP += Math.pow(((double) totalEvents) / r.getQueryRuntime() - avTP, 2);
        }
        double stdevLat = Math.sqrt(sumOfSquaresLat / queryResults.size());
        double stdevNumCuts = Math.sqrt(sumOfSquaresNumCuts / queryResults.size());
        double stdevTP = Math.sqrt(sumOfSquaresTP / queryResults.size());
        //display
        System.out.println("------------------TEST STATS------------------");
        System.out.println("Average Latency: " + avLat + " ms");
        System.out.println("St. Dev of Latency: " + stdevLat + " ms");

        System.out.println("Average # of emitted cuts: " + avNumCuts + " cuts");
        System.out.println("St. Dev of # of emitted cuts: " + stdevNumCuts + " cuts");

        if (totalEvents != -1) {
            System.out.println("Average Throughput: " + avTP + " cuts/ms");
            System.out.println("St. Dev of Throughput: " + stdevTP + " cuts/ms");
        }
        System.out.println("------------------END STATS-------------------");

    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *
     *  Main and Static Helpers
     *
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

    public static void main(String[] args) throws Exception {
        int runs = 1;
        int totalEvents = -1;
        String appName = "test";
        String igniteConfig = "retroscope-config.xml";
        String queryFile = "queries.txt";

        if (args.length > 0) {
            appName = args[0];
        }

        if (args.length > 1) {
            runs = Integer.parseInt(args[1]);
        }
        if (args.length > 2) {
            totalEvents = Integer.parseInt(args[2]);
        }
        if (args.length > 3) {
            igniteConfig = args[3];
        }
        if (args.length > 4) {
            queryFile = args[4];
        }

        ArrayList<String> queries = getQueries(queryFile);

        BenchRQLClient bench = new BenchRQLClient(igniteConfig, appName);
        bench.setQueryRepeat(runs);
        bench.setTotalEvents(totalEvents);
        bench.setQueries(queries);

        bench.runWorkload();

    }

    private static ArrayList<String> getQueries(String fileName) throws IOException {
        ArrayList<String> queries = new ArrayList<>();
        File file = new File(fileName);
        System.out.println(file.getAbsolutePath());
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            queries.add(line);
        }
        fileReader.close();
        return queries;
    }
}
