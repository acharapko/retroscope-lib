package examples;

import org.apache.ignite.Ignite;
import org.apache.log4j.Logger;
import retroscope.Retroscope;

import java.util.ArrayList;
import java.util.Random;

public class SimpleOneNode {

    private Retroscope retroscope;
    private int maxKeyId = 10;
    private int maxVal = 10;
    private Random random;
    private boolean doTicks = true;

    private int key_val_counts[][];
    private ArrayList key_val_times[][];

    private int totalTicks = 0;
    private long startTime = 0;


    public SimpleOneNode() {
        this("");
    }

    private Logger logger = Logger.getLogger(SimpleOneNode.class);

    public SimpleOneNode(String igniteConfig) {
        retroscope = new Retroscope("simpleOneNode");
        retroscope.connectIgnite(igniteConfig);
        random = new Random();
        key_val_counts = new int[maxKeyId][maxVal];
        key_val_times = new ArrayList[maxKeyId][maxVal];

    }

    public SimpleOneNode(Ignite ignite) {
        retroscope = new Retroscope("simpleOneNode");
        retroscope.connectIgnite(ignite);
        random = new Random();
        key_val_counts = new int[maxKeyId][maxVal];
        key_val_times = new ArrayList[maxKeyId][maxVal];
    }


    private void runEvents() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                while (doTicks) {
                    eventTick();
                    /*try {
                        Thread.sleep(1);
                    } catch (InterruptedException ie) {
                        logger.error(ie.getMessage());
                    }*/
                }
            }
        };
        Thread t = new Thread(r);
        t.start();
    }

    public void stop() {
        doTicks = false;
        long t = System.currentTimeMillis();
        retroscope.flushStreams();

        System.out.println("-------------------------");

        int c = 0;
        for(int i = 0; i < maxVal; i++) {
            c += key_val_counts[1][i];
        }

        System.out.println(" key[1] count = " + c);
        System.out.println(" key[1] = 5 count = " + key_val_counts[1][5]);
        System.out.println(" total ticks " + totalTicks);
        System.out.println(" stream time " + (t - startTime) + " ms");
        System.out.println(" total time " + (System.currentTimeMillis() - startTime) + " ms");

        System.out.println("-------------------------");
    }

    public void start() {
        doTicks = true;
        startTime = System.currentTimeMillis();
        runEvents();
    }

    private void eventTick() {
        int k = random.nextInt(maxKeyId);
        int v = random.nextInt(maxVal);
        String key = "key_" + k;
        String val = "val_" + v;

        //logger.debug("tick @ " + System.currentTimeMillis() + " : [" + key + "] = " + val );
        key_val_counts[k][v]++;
        ArrayList l = key_val_times[k][v];
        if (l == null) {
            l = new ArrayList();
            key_val_times[k][v] = l;
        }
        l.add(System.currentTimeMillis());
        //long t = System.currentTimeMillis();
        retroscope.getLog("testlog").setVariable(key, val).commit();
        //System.out.println("appendToLog Complete in " + (System.currentTimeMillis() - t) + " ms");
        totalTicks++;

    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *
     * GETTERS AND SETTERS
     *
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

    public int getMaxKeyId() {
        return maxKeyId;
    }

    public void setMaxKeyId(int maxKeyId) {
        this.maxKeyId = maxKeyId;
    }

    public int getMaxVal() {
        return maxVal;
    }

    public void setMaxVal(int maxVal) {
        this.maxVal = maxVal;
    }



    public static void main(String[] args) throws Exception {
        int runDuration = 12000;
        SimpleOneNode sin;
        if (args.length == 0) {
            sin = new SimpleOneNode();
        } else {
            System.out.println(args[0]);
            sin = new SimpleOneNode(args[0]);

        }

        sin.start();

        Thread.sleep(runDuration);
        System.out.println("Stopping");
        sin.stop();
    }

    public int getKeyValCount(int key, int val) {
        return key_val_counts[key][val];
    }

    public int getKeyCount(int key) {
        int c = 0;
        for(int i = 0; i < maxVal; i++) {
            c += key_val_counts[key][i];
        }
        return c;
    }

    public ArrayList getKeyValLastTimestamp(int key, int val) {
        return key_val_times[key][val];
    }

    public ArrayList getKeyTimestamps(int key) {

        ArrayList ts = new ArrayList();

        for (int i = 0; i < maxVal; i++) {
            ts.addAll(key_val_times[key][i]);
        }
        return ts;
    }

}
