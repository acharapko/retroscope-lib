package retroscope.rql.console;

import retroscope.log.*;
import retroscope.net.server.Server;
import retroscope.rql.RQLItem;
import retroscope.rql.RemoteNodeRQLEnvironment;
import retroscope.rql.rqlParser;

import java.io.IOException;
import java.io.StringReader;
import java.util.*;

/**
 * Created by Aleksey on 11/2/2016.
 *
 */
public class Console implements Runnable {

    private rqlParser parser;

    public Console(rqlParser parser) {
        this.parser = parser;
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
                }
            }
        }
    }

    private void displayCuts() {
        StringBuilder sb = new StringBuilder();
        // Send all output to the Appendable object sb
        Formatter formatter = new Formatter(sb, Locale.US);

        if (parser.getEnvironment().getEmittedGlobalCuts().size() > 0) {
            for (GlobalCut cut : parser.getEnvironment().getEmittedGlobalCuts()) {
                sb.append(String.format("%80s", "").replace(" ", "-"));
                sb.append(System.getProperty("line.separator"));
                formatter.format("%38s %-15s", "Cut @ HLC :", cut.getCutTime().toString());
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

    public static void main(String[] args) {

        Server<String, RQLItem> server = new Server<String, RQLItem>();
        try {
            System.out.println("Starting server");
            server.startServer();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        RemoteNodeRQLEnvironment rqlEnvironment = new RemoteNodeRQLEnvironment(server);
        rqlParser rql = new rqlParser().setEnvironment(rqlEnvironment);
        Console c = new Console(rql);
        Thread consoleThread = new Thread(c);
        consoleThread.start();
    }
}
