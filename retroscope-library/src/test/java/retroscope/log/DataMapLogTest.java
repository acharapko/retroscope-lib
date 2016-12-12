package retroscope.log;

import org.junit.Test;
import retroscope.hlc.Timestamp;
import java.util.HashMap;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by aleksey on 10/19/16.
 */
public class DataMapLogTest {


    DataMapLog<String, String> log;
    int length = 10;
    LogEntry<String, String> head, tail;

    @Test
    public void testConcurrency() throws Exception {
        final long runs = 10000;
        length = 100;
        log = new DataMapLog<String, String>(length * 10, "test123", 50);
        log = populateTheLog(log, 10);
        log.append("test1", new DataEntry<String>("0", new Timestamp(System.currentTimeMillis(), (short)0)));
        Runnable r1 = new Runnable() {
            public void run() {
                for (long i = 0; i < runs; i++) {
                    log.lock();
                    String v1 = log.getItem("test1").getValue();
                    int v = Integer.parseInt(v1);
                    v++;
                    log.append("test1", new DataEntry<String>(v + "", new Timestamp(System.currentTimeMillis(), (short) 0)));
                    int testV = Integer.parseInt(log.getItem("test1").getValue());
                    log.unlock();
                    assertTrue(testV == v);
                }
            }
        };

        Runnable r2 = new Runnable() {
            public void run() {
                for (long i = 0; i < runs; i++) {
                    log.lock();
                    String v1 = log.getItem("test1").getValue();
                    int v = Integer.parseInt(v1);
                    v++;
                    log.append("test1", new DataEntry<String>(v + "", new Timestamp(System.currentTimeMillis(), (short) 0)));
                    int testV = Integer.parseInt(log.getItem("test1").getValue());
                    log.unlock();
                    assertTrue(testV == v);
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
        int totaltRuns = Integer.parseInt(log.getItem("test1").getValue());
        //we expect counter to be certain value
        assertTrue(totaltRuns == runs * 2);
    }


    @Test
    public void computeDiffBackwards() throws Exception {
        length = 100;
        log = new DataMapLog<String, String>(length * 10, "test123", 50);
        log = populateTheLog(log, 10);

        LogEntry<String, String> e;
        LogEntry<String, String> s = log.getTail();
        for (int i = 0; i < length - 1; i++) {
            e = log.getHead();
            for (int j = length - 1; j > i; j--) {
                RetroMap<String, String> diff1 = log.computeDiffBackwards(e.getTime(), s);
                log.setLogCheckpointIntervalMs(0);
                RetroMap<String, String> diff2 = log.computeDiffBackwards(e.getTime(), s);
                log.setLogCheckpointIntervalMs(50);

                /*System.out.println("i = " + i + "; j = " + j);
                System.out.println(diff1);
                System.out.println(diff2);
                System.out.println("---------------");*/

                assertTrue(diff1.equals(diff2));

                e = e.getNext();
            }
            s = s.getPrev();
        }
    }

    @Test
    public void computeDiffForwards() throws Exception {
        length = 100;
        log = new DataMapLog<String, String>(length * 10, "test123", 50);
        log = populateTheLog(log, 10);

        LogEntry<String, String> e;
        LogEntry<String, String> s = log.getHead();
        for (int i = 0; i < length - 1; i++) {
            e = log.getTail();
            for (int j = length - 1; j > i; j--) {
                RetroMap<String, String> diff1 = log.computeDiffForwards(e.getTime(), s);
                log.setLogCheckpointIntervalMs(0);
                RetroMap<String, String> diff2 = log.computeDiffForwards(e.getTime(), s);
                log.setLogCheckpointIntervalMs(50);

                /*System.out.println("1---------------");
                System.out.println("i = " + i + "; j = " + j);
                System.out.println(diff1);
                System.out.println(diff2);*/

                assertTrue(diff1.equals(diff2));
                e = e.getPrev();

            }
            s = s.getNext();
        }

    }

    private RetroMap<String, String> getRetroMapWithKeys(int numKeys, String keyPrefix) {
        RetroMap<String, String> rm = new RetroMap<String, String>(numKeys);
        for (int i = 0; i < numKeys; i++) {
            rm.put(keyPrefix + i, null);
        }
        return rm;
    }

    @Test
    public void getAllData() throws Exception {
        length = 100;
        log = new DataMapLog<String, String>(length * 10, "test123", 50);
        //log = populateTheLog(log, 10);
        Timestamp t = new Timestamp();
        Random rand = new Random(System.nanoTime());

        RetroMap<String, String> maps[] = new RetroMap[length];
        Timestamp[] times = new Timestamp[length];
        for (int i = 0; i < length; i++) {

            t = t.add(2 + rand.nextInt(7), (short)0);
            DataEntry<String> d1
                    = new DataEntry<String>(("val"+i), t);

            int k = rand.nextInt(10);
            String key = ("test" + k);
            //System.out.println(key + " = " + log.getItem(key) + " => " + d1);
            LogEntry<String, String> le
                    = new LogEntry<String, String>(key, log.getItem(key), d1);
            if (i == 0) {
                head = le;
            }
            if (i == length - 1) {
                tail = le;
            }
            log.append(le);
            maps[i] = getRetroMapWithKeys(10, "test");
            maps[i].putAll(log.getAllData());
            times[i] = t.clone();
        }

        //now test snapshots
        for (int i = length-1; i >= 0; i--) {
            RetroMap<String, String> testMap = log.getAllData(times[i]);
            assertTrue(testMap.equals(maps[i]));
        }
        log.setLogCheckpointIntervalMs(0);
        //now test snapshots without checkpoints
        for (int i = length-1; i >= 0; i--) {
            RetroMap<String, String> testMap = log.getAllData(times[i]);
            assertTrue(testMap.equals(maps[i]));
        }

        //now checkpoint again but with times not in any log entries
        log.setLogCheckpointIntervalMs(50);
        for (int i = length-2; i >= 0; i--) {
            RetroMap<String, String> testMap = log.getAllData(times[i].add(1, (short)0));
            assertTrue(testMap.equals(maps[i]));
        }

        //now test snapshots without checkpoints and with times not in any log entries
        log.setLogCheckpointIntervalMs(0);
        for (int i = length-2; i >= 0; i--) {
            RetroMap<String, String> testMap = log.getAllData(times[i].add(1, (short)0));
            assertTrue(testMap.equals(maps[i]));
        }
    }

    @Test
    public void getItems() throws Exception {
        length = 100;
        int keys = 10;
        log = new DataMapLog<String, String>(length * 10, "test123", 50);
        //log = populateTheLog(log, 10);
        Timestamp t = new Timestamp();
        Random rand = new Random(System.nanoTime());

        RetroMap<String, String> maps[] = new RetroMap[length];
        Timestamp[] times = new Timestamp[length];
        for (int i = 0; i < length; i++) {

            t = t.add(2 + rand.nextInt(7), (short)0);
            DataEntry<String> d1
                    = new DataEntry<String>(("val"+i), t);

            int k = rand.nextInt(keys);
            String key = ("test" + k);
            //System.out.println(key + " = " + log.getItem(key) + " => " + d1);
            LogEntry<String, String> le
                    = new LogEntry<String, String>(key, log.getItem(key), d1);
            if (i == 0) {
                head = le;
            }
            if (i == length - 1) {
                tail = le;
            }
            log.append(le);
            maps[i] = getRetroMapWithKeys(keys, "test");
            maps[i].putAll(log.getAllData());
            times[i] = t.clone();
        }


        int keyToRequest = 3;
        for (int i = length-1; i >= 0; i--) {
            String requestKeys[] = new String[keyToRequest];
            for (int j = 0; j < keyToRequest; j++) {
                String key = "test"+rand.nextInt(keys);
                requestKeys[j] = key;
            }
            DataEntry<String> items[] = log.getItems(requestKeys, times[i]);

            for (int k = 0; k < keyToRequest; k++) {
                if (items[k] != null) {
                    //System.out.println(requestKeys[k] + " - " + items[k].getValue());
                    //System.out.println(maps[i]);
                    assertTrue(items[k].getValue().equals(maps[i].get(requestKeys[k]).getValue()));
                } else {
                    assertTrue(items[k] == maps[i].get(requestKeys[k]));
                }
            }
        }

    }

    @Test
    public void makeSnapshot() throws Exception {
        length = 100;
        log = new DataMapLog<String, String>(length * 10, "test123", 50);
        //log = populateTheLog(log, 10);
        Timestamp t = new Timestamp();
        Random rand = new Random(System.nanoTime());

        RetroMap<String, String> maps[] = new RetroMap[length];
        Timestamp[] times = new Timestamp[length];
        for (int i = 0; i < length; i++) {

            t = t.add(2 + rand.nextInt(7), (short)0);
            DataEntry<String> d1
                    = new DataEntry<String>(("val"+i), t);

            int k = rand.nextInt(10);
            String key = ("test" + k);
            //System.out.println(key + " = " + log.getItem(key) + " => " + d1);
            LogEntry<String, String> le
                    = new LogEntry<String, String>(key, log.getItem(key), d1);
            if (i == 0) {
                head = le;
            }
            if (i == length - 1) {
                tail = le;
            }
            log.append(le);
            maps[i] = getRetroMapWithKeys(10, "test");
            maps[i].putAll(log.getAllData());
            times[i] = t.clone();
        }

        for (int i = length-1; i >= 0; i--) {
            int snapshotId = log.makeSnapshot(times[i]);
            RetroMap<String, String> testMap = log.getSnapshot(snapshotId);
            assertTrue(testMap.equals(maps[i]));
        }

    }

    @Test
    public void rollSnapshot() throws Exception {
        length = 100;
        log = new DataMapLog<String, String>(length * 10, "test123", 50);
        //log = populateTheLog(log, 10);
        Timestamp t = new Timestamp();
        Random rand = new Random(System.nanoTime());

        RetroMap<String, String> maps[] = new RetroMap[length];
        Timestamp[] times = new Timestamp[length];
        for (int i = 0; i < length; i++) {

            t = t.add(2 + rand.nextInt(7), (short)0);
            DataEntry<String> d1
                    = new DataEntry<String>(("val"+i), t);

            int k = rand.nextInt(10);
            String key = ("test" + k);
            //System.out.println(key + " = " + log.getItem(key) + " => " + d1);
            LogEntry<String, String> le
                    = new LogEntry<String, String>(key, log.getItem(key), d1);
            if (i == 0) {
                head = le;
            }
            if (i == length - 1) {
                tail = le;
            }
            log.append(le);
            maps[i] = getRetroMapWithKeys(10, "test");
            maps[i].putAll(log.getAllData());
            times[i] = t.clone();
        }

        int snapshotId = log.makeSnapshot(times[9]);
        RetroMap<String, String> testMap = log.getSnapshot(snapshotId);
        LogEntry<String, String> kle = testMap.getAssociatedLogEntry();
        assertTrue(testMap.equals(maps[9]));
        //System.out.println("Start roll");
        for (int i = 10; i < length; i++) {
            log.rollSnapshot(snapshotId, times[i]);
            testMap = log.getSnapshot(snapshotId);
            /*System.out.println(i + "-=-=-=-=-=-=-=-=-=-=-=-");
            System.out.println(testMap);
            System.out.println(maps[i]);*/
            assertTrue(testMap.equals(maps[i]));
        }

        //and some larger jumps
        snapshotId = log.makeSnapshot(times[9]);
        testMap = log.getSnapshot(snapshotId);
        assertTrue(testMap.equals(maps[9]));
        //System.out.println("Start roll");
        for (int i = 10; i < length; i+=5) {
            log.rollSnapshot(snapshotId, times[i]);
            testMap = log.getSnapshot(snapshotId);
            /*System.out.println(i + "-=-=-=-=-=-=-=-=-=-=-=-");
            System.out.println(testMap);
            System.out.println(maps[i]);*/
            assertTrue(testMap.equals(maps[i]));
        }
    }

    private DataMapLog<String, String> populateTheLog(DataMapLog<String, String> log, int maxKeys) throws Exception{
        Timestamp t = new Timestamp();
        Random rand = new Random(System.nanoTime());
        for (int i = 0; i < length; i++) {

            t = t.add(2 + rand.nextInt(7), (short)0);
            DataEntry<String> d1
                    = new DataEntry<String>(("val"+i), t);

            int k = rand.nextInt(maxKeys);
            String key = ("test" + k);
            LogEntry<String, String> le
                    = new LogEntry<String, String>(key, log.getItem(key), d1);
            if (i == 0) {
                head = le;
            }
            if (i == length - 1) {
                tail = le;
            }
            log.append(le);
        }
        return log;
    }

    @Test
    public void computeBidirectionalDiffBackwards() throws Exception {
        length = 100;
        log = new DataMapLog<String, String>(length * 10, "test123", 50);
        log = populateTheLog(log, 1);
        LogEntry<String, String> s = log.getTail();
        LogEntry<String, String> e;
        for (int i = 0; i < length - 1; i++) {
            e = log.getHead();
            for (int j = length - 1; j > i; j--) {
                HashMap<String, LogEntry<String, String>> diff = log.computeBidirectionalDiffBackwards(s, e);
                assertTrue(diff.size() <= 1);
                if (diff.get("test0").getFromV() == null) {
                    assertTrue(diff.get("test0").getFromV() == e.getFromV());
                } else {
                    assertTrue(diff.get("test0").getFromV().getValue().equals(e.getFromV().getValue()));
                }
                assertTrue(diff.get("test0").getToV().getValue().equals(s.getToV().getValue()));

                e = e.getNext();
            }
            s = s.getPrev();
        }


    }

    @Test
    public void getItem() throws Exception {
        length = 10;
        log = new DataMapLog<String, String>(length * 10, "test123", 50);
        log = populateTheLog(log, 1);
        Timestamp t = log.getTail().getTime().add(3, (short) 0);
        Timestamp t2 = t.clone();
        log.append("test2", new DataEntry<String>("val-test2-1", t));
        assertTrue(log.getItem("test0").getValue().equals("val9"));
        assertTrue(log.getItem("test2").getValue().equals("val-test2-1"));
        t = log.getTail().getTime().add(3, (short) 0);
        log.append("test2", new DataEntry<String>("val-test2-2", t));
        assertTrue(log.getItem("test2").getValue().equals("val-test2-2"));

        //test get item from the past. just one little test, as this is very similar to the snapshot test
        assertTrue(log.getItem("test2", t2).getValue().equals("val-test2-1"));
    }

}