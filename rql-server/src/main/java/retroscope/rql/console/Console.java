package retroscope.rql.console;

import retroscope.net.server.Server;
import retroscope.rql.RQLItem;
import retroscope.rql.RemoteNodeQueryEnvironment;
import retroscope.rql.rqlParser;

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

    public Console(rqlParser parser) {
        this.parser = parser;
    }
    public Console(rqlParser parser, String outFileName) {
        this.parser = parser;
        this.outFileName = outFileName;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outFileName))) {
            bw.write("New Out \n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        Scanner in = new Scanner(System.in);
        StringBuilder buf = new StringBuilder();
        while(true) {
            System.out.println("RQL Console");
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
                        retroscope.rql.Scanner scanner = new retroscope.rql.Scanner(q1);
                        try {
                            scanner.yylex();
                        } catch (IOException ioe) {
                            System.err.println(ioe.getMessage());
                            buf = new StringBuilder();
                        }
                        parser.setScanner(scanner);
                        parser.parse();

                        //now display stuff, if needed
                        System.out.println(parser.getEnvironment().cutsToString());
                        if (!outFileName.isEmpty()) {
                            try (BufferedWriter bw = new BufferedWriter(new FileWriter(outFileName, true))) {
                                bw.write(parser.getEnvironment().cutsToString());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                        //displayCuts();
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                }
            }
        }
    }

    public static void main(String[] args) {


        Server<String, RQLItem> server = new Server<String, RQLItem>();

        RemoteNodeQueryEnvironment rqlEnvironment = new RemoteNodeQueryEnvironment(server);
        rqlParser rql = new rqlParser().setEnvironment(rqlEnvironment);
        Console c;
        if (args.length > 0) {
            c = new Console(rql, args[0]);
        } else {
            c = new Console(rql);
        }
        Thread consoleThread = new Thread(c);
        consoleThread.start();

        try {
            System.out.println("Starting server...");
            server.startServer();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
