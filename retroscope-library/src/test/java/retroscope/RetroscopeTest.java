package retroscope;

import org.junit.Before;
import org.junit.Test;
import retroscope.hlc.Timestamp;
import retroscope.log.RetroMap;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by Aleksey on 11/7/2016.
 *
 */
public class RetroscopeTest {


    private int runs = 10000;
    private int smallRuns = 100;
    private Random rand;

    @Before
    public void setUp() throws Exception {
        rand = new Random(System.nanoTime());
    }

    /*Similar to DataMapLog concurrency test */

    @Test
    public void testConcurrency() throws Exception {
        //final long runs = 10000;
        final Retroscope<String, String> retroscope = new Retroscope<String, String>(300000);

        retroscope.appendToLog("log1", "test1", "0");
        Runnable r1 = new Runnable() {
            public void run() {
                for (long i = 0; i < runs; i++) {
                    try {
                        retroscope.lockLog("log1");
                        Timestamp t1= retroscope.getTimestamp();
                        String v1 = retroscope.getItem("log1", "test1").getValue();
                        int v = Integer.parseInt(v1);
                        v++;
                        Timestamp t2 = retroscope.timeTick();
                        retroscope.appendToLog("log1", "test1", v + "");
                        int testV = Integer.parseInt(retroscope.getItem("log1", "test1").getValue());
                        retroscope.unlockLog("log1");
                        assertTrue(testV == v);
                        assertTrue(t1.compareTo(t2) < 0); // logical or wall time has incremented
                        if (t1.getWallTime() == t2.getWallTime()) {
                            assertTrue(t1.getLogical() + 1 == t2.getLogical()); //logical can only change by 1
                        }
                    } catch (RetroscopeException re) {}
                }
            }
        };

        Runnable r2 = new Runnable() {
            public void run() {
                for (long i = 0; i < runs; i++) {
                    try {
                        retroscope.lockLog("log1");
                        Timestamp t1= retroscope.getTimestamp();
                        String v1 = retroscope.getItem("log1", "test1").getValue();
                        int v = Integer.parseInt(v1);
                        v++;
                        Timestamp t2 = retroscope.timeTick();
                        retroscope.appendToLog("log1", "test1", v + "");
                        int testV = Integer.parseInt(retroscope.getItem("log1", "test1").getValue());
                        retroscope.unlockLog("log1");
                        assertTrue(testV == v);
                        assertTrue(t1.compareTo(t2) < 0); // logical or wall time has incremented
                        if (t1.getWallTime() == t2.getWallTime()) {
                            assertTrue(t1.getLogical() + 1 == t2.getLogical()); //logical can only change by 1
                        }
                    } catch (RetroscopeException re) {}
                }
            }
        };

        Thread t1 = new Thread(r1);
        t1.start();
        Thread t2 = new Thread(r2);
        t2.start();
        //stupid way to wait
        while (t1.isAlive()) {
            Thread.sleep(100);
        }
        while (t2.isAlive()) {
            Thread.sleep(100);
        }
        int totaltRuns = Integer.parseInt(retroscope.getItem("log1", "test1").getValue());
        //we expect counter to be certain value
        assertTrue(totaltRuns == runs * 2);
    }

    @Test
    public void testTime() throws Exception {
        Retroscope<String, String> retroscope = new Retroscope<String, String>(300000);

        Timestamp old = retroscope.timeTick();
        for (int i = 0; i < 100; i++) {
            Timestamp newt = retroscope.timeTick();
            assertTrue(newt.compareTo(old) > 0);
            old = newt;
        }

        rand = new Random(System.nanoTime());

        old = retroscope.timeTick().add(rand.nextInt(10), (short)0);
        for (int i = 0; i < 100; i++) {
            Timestamp newt = retroscope.timeTick(old);
            assertTrue(newt.compareTo(old) > 0);
            assertTrue(newt.getLogical() - 1 == old.getLogical());
            old = newt.add(rand.nextInt(10), (short)0);
        }

        old = retroscope.timeTick().add(0, (short)rand.nextInt(10));
        for (int i = 0; i < 100; i++) {
            Timestamp newt = retroscope.timeTick(old);
            assertTrue(newt.compareTo(old) > 0);
            assertTrue(newt.getLogical() - 1 == old.getLogical());
            old = newt.add(0, (short)rand.nextInt(10));
        }
    }

    @Test
    public void testGetItem() throws Exception {
        Retroscope<String, String> retroscope = new Retroscope<String, String>(300000);

        for (int i = 0; i < runs; i++) {
            retroscope.appendToLog("log1", "test", "val" + i);
            assertTrue(retroscope.getItem("log1", "test").getValue().equals("val" + i));
        }

        Timestamp times[] = new Timestamp[smallRuns];
        String vals[] = new String[smallRuns];

        for (int i = 0; i < smallRuns; i++) {
            times[i] = retroscope.getTimestamp();
            vals[i] = "val" + i;
            retroscope.appendToLog("log1", "test", vals[i]);
            assertTrue(retroscope.getItem("log1", "test").getValue().equals(vals[i]));
            Thread.sleep(rand.nextInt(3));
        }

        //now check
        for (int i = 1; i < smallRuns; i++) {
            //time is recorded before we append a value to the log, so at such recorded time we still have previous value
            assertTrue(retroscope.getItem("log1", "test", times[i]).getValue().equals(vals[i-1]));
        }
    }

    /*Similar to the makeSnapshot in DataMapLog*/
    @Test
    public void makeSnapshot() throws Exception {
        Retroscope<String, String> retroscope = new Retroscope<String, String>(300000);


        RetroMap<String, String> maps[] = new RetroMap[smallRuns];
        Timestamp[] times = new Timestamp[smallRuns];
        for (int i = 0; i < smallRuns; i++) {

            Thread.sleep(rand.nextInt(3));
            int k = rand.nextInt(10);
            String key = ("test" + k);
            String val = "val"+i;
            retroscope.appendToLog("log1", key, val);

            maps[i] = getRetroMapWithKeys(10, "test");
            maps[i].putAll(retroscope.getAllData("log1"));
            times[i] = retroscope.getTimestamp();
        }
        Thread.sleep(3);

        for (int i = smallRuns-1; i >= 0; i--) {
            int snapshotId = retroscope.makeSnapshot("log1", times[i]);
            RetroMap<String, String> testMap = retroscope.getSnapshot("log1", snapshotId);
            assertTrue(testMap.equals(maps[i]));
        }

    }

    private RetroMap<String, String> getRetroMapWithKeys(int numKeys, String keyPrefix) {
        RetroMap<String, String> rm = new RetroMap<String, String>(numKeys);
        for (int i = 0; i < numKeys; i++) {
            rm.put(keyPrefix + i, null);
        }
        return rm;
    }
}