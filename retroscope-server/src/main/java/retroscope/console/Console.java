package retroscope.console;

import retroscope.log.DataEntry;
import retroscope.log.RetroMap;
import retroscope.net.server.Callbacks;
import retroscope.nodeensemble.Ensemble;

import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Aleksey on 11/2/2016.
 */
public class Console implements Runnable {

    private Ensemble ensemble;

    public Console(Ensemble ensemble) {
        this.ensemble = ensemble;
    }

    private Callbacks.AggregatePullCallback<String, String> callback
            = new Callbacks.AggregatePullCallback<String, String>() {

        public void pullDataComplete(long rid, int nodeId, String logName, RetroMap data, int errorCode) {
            System.out.println(rid + " @ " + nodeId + " for log: " + logName);
            System.out.println(data);
        }

        public void pullAllDataComplete(long rid, int[] nodeIds, String logName, RetroMap<String, String>[] data, int[] errorCode) {
            for (int i = 0; i < nodeIds.length; i++) {
                System.out.println("All Data:");
                System.out.println(rid + " @ " + nodeIds[i] + " for log: " + logName);
                System.out.println(data[i]);
            }
        }
    };

    public void run() {
        Scanner in = new Scanner(System.in);
        while(true) {
            System.out.println("Input");
            String input = in.nextLine();
            if (input.equals("exit")) {
                System.exit(0);
            }
            if (input.equals("gettime")) {
                System.out.println(System.currentTimeMillis());
            }
            String[] pieces = input.split(" ");

            if (pieces[0].equals("getdata")) {
                if (pieces.length > 1) {
                    if (pieces.length > 2) {
                        long time = Long.parseLong(pieces[2]);
                        ensemble.pullData(pieces[1], time, callback);
                    } else {
                        ensemble.pullData(pieces[1], callback);
                    }
                } else {
                    System.err.println("getdata needs more stuff");
                }
            }

            if (pieces[0].equals("getitem")) {
                if (pieces.length > 2) {
                    if (pieces.length == 4) {
                        long time = Long.parseLong(pieces[3]);
                        ensemble.pullData(pieces[1], pieces[2], time, callback);
                    } else {
                        ensemble.pullData(pieces[1], pieces[2], callback);
                    }
                } else {
                    System.err.println("getitem needs more stuff");
                }
            }
        }
    }
}
