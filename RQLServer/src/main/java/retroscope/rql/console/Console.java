package retroscope.rql.console;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.log4j.PropertyConfigurator;
import retroscope.rql.environment.GlobalCut;
import retroscope.rql.parser.rqlParser;
import retroscope.rql.server.Runner;
import retroscope.rql.server.StatementProcessor;
//import retroscope.rql.server.Runner;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

/**
 * Created by Aleksey on 11/2/2016.
 *
 */
public class Console implements Runnable {

    private rqlParser parser;
    private String outFileName = "";
    private Ignite ignite;
    private String appName;
    private boolean silent;


    public Console(rqlParser parser, Ignite ignite, String appName) {
        this.parser = parser;
        this.ignite = ignite;
        this.appName = appName;
    }
    public Console(rqlParser parser, Ignite ignite, String appName, String outFileName) {
        this.parser = parser;
        this.outFileName = outFileName;
        this.ignite = ignite;
        this.appName = appName;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outFileName))) {
            bw.write("New Out \n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setSilent(boolean silent) {
        this.silent = silent;
    }

    public void run() {
        Scanner in = new Scanner(System.in);
        StringBuilder buf = new StringBuilder();

        while(true) {
            System.out.println("RQL Console" );
            System.out.print(">>" );
            String input = in.nextLine().trim();
            if (input.equals("exit")) {
                System.out.println("bye-bye");
                System.exit(0);
            } else {
                buf.append(input);
                if (input.endsWith(";")) {
                    //run;
                    try {
                        StringReader q1 = new StringReader(buf.toString());
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
                        //now display stuff, if needed

                        List<Runner> runners = StatementProcessor.processStatements(
                                parser.getQueries(),
                                appName,
                                ignite
                                );

                        for (Runner s: runners) {
                            System.out.println("Starting to print");
                            if (!silent) {
                                for (GlobalCut cut : s.getEmittedCuts()) {
                                    System.out.println(cut.toOneString());
                                }
                            }
                            if (!outFileName.isEmpty()) {
                                try (BufferedWriter bw = new BufferedWriter(new FileWriter(outFileName, true))) {
                                    for (GlobalCut cut : s.getEmittedCuts()) {
                                        bw.write(cut.toOneString());
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            long endEndTime = System.currentTimeMillis();
                            System.out.println("Query executed and displayed in: " + (endEndTime - startTime) + " ms for " + s.getEmittedCuts().size() + " cuts");
                        }

                        //displayCuts();
                    } catch (Exception e) {
                        System.err.println("Exception (console) " + e.getMessage());
                        e.printStackTrace();
                    } finally {
                        buf = new StringBuilder();
                    }
                }
            }
        }
    }

    //appName, igniteconfig, out_file
    public static void main(String[] args) {
        PropertyConfigurator.configure("log4j.config");
        if (args.length > 0) {
            String appName = args[0];
            String igniteConf = "";
            boolean silent = false;
            if (args.length > 1) {
                igniteConf = args[1];
            }

            Ignite ignite;
            Ignition.setClientMode(true);
            if (!igniteConf.isEmpty()) {
                ignite = Ignition.start(igniteConf);
            } else {
                ignite = Ignition.start();
            }
            if (args.length > 3) {
                int s = Integer.parseInt(args[3]);
                silent = s == 1;
            }

            rqlParser rql = new rqlParser();
            Console c;
            if (args.length > 2) {
                c = new Console(rql, ignite, appName, args[2]);
            } else {
                c = new Console(rql, ignite, appName);
            }
            c.setSilent(silent);
            Thread consoleThread = new Thread(c);
            consoleThread.start();
        } else {
            System.out.println("App name is not provided");
        }
    }
}
