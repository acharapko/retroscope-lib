package retroscope.log;

import retroscope.hlc.Timestamp;
import org.junit.Test;
import retroscope.net.protocol.Protocol;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by aleksey on 10/17/16.
 * Tests for the basic Log class
 */
public class LogTest {


    Log<String, String> log;
    int length = 10;
    LogEntry<String, String> head, tail;

    @org.junit.Before
    public void setUp() throws Exception {
        log = new Log<String, String>(length * 10, "test123");
        log = populateTheLog(log, length, length);
    }

    private Log<String, String> populateTheLog(Log<String, String> log, int length, int mod) throws Exception{
        Timestamp t = new Timestamp();
        Random rand = new Random(System.nanoTime());
        DataEntry<String> dp
                = new DataEntry<String>("val1", t);
        for (int i = 0; i < length; i++) {

            t = t.add(2 + rand.nextInt(7), (short)0);
            DataEntry<String> d1
                    = new DataEntry<String>("val" + i % mod, t);

            LogEntry<String, String> le = new LogEntry<String, String>("test", dp, d1);
            if (i == 0) {
                head = le;
            }
            if (i == length - 1) {
                tail = le;
            }
            log.append(le);
            dp = d1;
        }
        return log;
    }

    @Test
    public void findEntriesByKey() throws Exception {
        int mod = 20;
        length = 100;
        log = new Log<String, String>(length * 20, "test123");
        populateTheLog(log, length, mod);
        Timestamp t = log.getTail().getTime();
        Random rand = new Random(System.nanoTime());
        DataEntry<String> dp = log.getTail().getToV();

        for (int i = 0; i < mod; i++) {

            t = t.add(2 + rand.nextInt(7), (short)0);
            DataEntry<String> d1
                    = new DataEntry<String>("val" + i % mod, t);

            LogEntry<String, String> le = new LogEntry<String, String>("test1", dp, d1);

            log.append(le);
            dp = d1;
        }

        for(int i = 0; i < mod; i++) {
            String v = "val" + i;
            ArrayList<LogEntry<String, String>> results = log.findEntriesByKey("test", v, new Comparator<String>() {
                public int compare(String o1, String o2) {
                    if (o1.equals(o2)) {
                        return 0;
                    }
                    return -1;
                }
            }, 0);

            assertTrue(results.size() == length / mod);
            for (LogEntry<String, String> le : results) {
                assertTrue(le.getKey().equals("test"));
                assertTrue(le.getToV().getValue().equals(v));
            }

            results = log.findEntriesByKey("test", v, new Comparator<String>() {
                public int compare(String o1, String o2) {
                    if (o1.equals(o2)) {
                        return 0;
                    }
                    return -1;
                }
            }, -1); // not equal

            assertTrue(results.size() == length - (length / mod));
            for (LogEntry<String, String> le : results) {
                assertTrue(le.getKey().equals("test"));
                assertTrue(!le.getToV().getValue().equals(v));
            }

            results = log.findEntriesByKey("test1", v, new Comparator<String>() {
                public int compare(String o1, String o2) {
                    if (o1.equals(o2)) {
                        return 0;
                    }
                    return -1;
                }
            }, 0);

            assertTrue(results.size() == 1);
            for (LogEntry<String, String> le : results) {
                assertTrue(le.getKey().equals("test1"));
                assertTrue(le.getToV().getValue().equals(v));
            }
        }
    }

    @Test
    public void testProtocolConversion() throws Exception {
        Protocol.Log protoLog = log.toProtocol();
        Log<String, String> recreatedlog = new Log<String, String>(protoLog);
        assertTrue(log.getLength() == recreatedlog.getLength());

        LogEntry<String, String> origLogEntry = log.getHead();
        LogEntry<String, String> recreatedLogEntry = recreatedlog.getHead();
        while (origLogEntry != null) {
            assertTrue(origLogEntry.getTime().compareTo(recreatedLogEntry.getTime()) == 0);
            assertTrue(origLogEntry.getKey().equals(recreatedLogEntry.getKey()));
            assertTrue(origLogEntry.getFromV().getValue().equals(recreatedLogEntry.getFromV().getValue()));
            assertTrue(origLogEntry.getToV().getValue().equals(recreatedLogEntry.getToV().getValue()));
            origLogEntry = origLogEntry.getNext();
            recreatedLogEntry = recreatedLogEntry.getNext();
        }
    }

    @Test
    public void testAddKnownEntries() throws Exception {
        LogEntry<String, String> fifthLog = log.getHead().getNext().getNext().getNext().getNext();
        assertTrue(log.addKnownEntries(fifthLog) == 1);
        LogEntry<String, String> seventhLog = fifthLog.getNext().getNext();
        assertTrue(log.addKnownEntries(seventhLog) == 2);
    }

    @Test
    public void testGetKnownEntry() throws Exception {
        LogEntry<String, String> fifthLog = log.getHead().getNext().getNext().getNext().getNext();
        int ke5 = log.addKnownEntries(fifthLog);
        LogEntry<String, String> seventhLog = fifthLog.getNext().getNext();
        int ke7 = log.addKnownEntries(seventhLog);

        LogEntry<String, String> known7 = log.getKnownEntry(ke7);

        assertTrue(known7 == seventhLog); //must be the same reference!

        //now let us forget the fifth;
        long t1 = log.getHead().getTime().getWallTime();
        long t5 = fifthLog.getTime().getWallTime();
        long tdiff = (t5 - t1) + (length * 10 - (log.getTail().getTime().getWallTime() - t1)) + 1;
        Timestamp t11 = log.getTail().getTime().add(tdiff, (short)0);
        DataEntry<String> d1
                = new DataEntry<String>("val1", t11);

        LogEntry<String, String> le = new LogEntry<String, String>("test", log.getHead().getToV(), d1);
        log.append(le);

        LogEntry<String, String> known5 = log.getKnownEntry(ke5);
        //System.out.println(tdiff + " | t1 = " + t1 + " | t11 = " + t11.getWallTime());
        assertTrue(known5 == null);

    }

    @Test
    public void testFindEntry() throws Exception {
        //simple find:
        LogEntry<String, String> findCheck = log.getHead();
        for (int checkNum = 0; checkNum < length; checkNum++) {
            findCheck = log.getHead();
            for (int i = 0; i < checkNum; i++) {
                findCheck = findCheck.getNext();
            }

            long t = findCheck.getTime().getWallTime();
            LogEntry<String, String> find1 = log.findEntry(t);
            assertTrue(findCheck == find1); //must be referencing same object

            try {
                t = findCheck.getTime().getWallTime() + 1;
                LogEntry<String, String> find2 = log.findEntry(t);
                assertTrue(findCheck == find2); //still must be referencing same object

            } catch (LogOutTimeBoundsException e) {
                assertTrue(checkNum == length - 1);
            }
            try {
                t = findCheck.getTime().getWallTime() - 1;
                LogEntry<String, String> find3 = log.findEntry(t);
                assertTrue(findCheck.getPrev() == find3); //still must be referencing prev
            } catch (LogOutTimeBoundsException e) {
                assertTrue(checkNum == 0);
            }

    }
        //now lets do some searching with checkpoints

        //populate the retroscope.log first
        length = 100;
        log = new Log<String, String>(length * 10, "test123", 50);
        log = populateTheLog(log, length, length);
        for (int checkNum = 0; checkNum < length; checkNum++) {
            findCheck = log.getHead();
            for (int i = 0; i < checkNum; i++) {
                findCheck = findCheck.getNext();
            }

            long t = findCheck.getTime().getWallTime();
            LogEntry<String, String> find4 = log.findEntry(t);
            assertTrue(findCheck == find4); //must be referencing same object

            try {
                t = findCheck.getTime().getWallTime() + 1;
                LogEntry<String, String> find5 = log.findEntry(t);
                assertTrue(findCheck == find5); //must be referencing same object
            } catch (LogOutTimeBoundsException e) {
                assertTrue(checkNum == length - 1);
            }

            t = findCheck.getTime().getWallTime() - 1;
            try {
                LogEntry<String, String> find6 = log.findEntry(t);
                assertTrue(findCheck.getPrev() == find6); //must be referencing same object
            } catch (LogOutTimeBoundsException e) {
                assertTrue(checkNum == 0);
            }
        }

    }

    @Test
    public void testAppend() throws Exception {
        //append is tested every time retroscope.log is created and populated. no separate test
    }

    @Test
    public void testComputeDiff() throws Exception {

        length = 100;
        int start;
        int end;
        
        for (start = 0; start < length; start++) {
            for (end = start; end < length; end++) {
                //more comprehensive population
                Random rand = new Random(System.nanoTime());

                Log<String, String> log = new Log<String, String>(length * 10, "test123");
                Timestamp t = new Timestamp();
                DataEntry<String> dp
                        = new DataEntry<String>("val1", t);
                LogEntry<String, String> startLE = null;
                LogEntry<String, String> endLE = null;


                RetroMap<String, String> referenceMapForward
                        = new RetroMap<String, String>();

                RetroMap<String, String> referenceMapBackwards
                        = new RetroMap<String, String>();


                for (int i = 0; i < length; i++) {

                    int k = rand.nextInt(10);
                    String key = ("test" + k);
                    String val = ("val" + i);
                    t = t.add(1 + rand.nextInt(8), (short) 0);
                    DataEntry<String> d1
                            = new DataEntry<String>(val, t);

                    LogEntry<String, String> le = new LogEntry<String, String>(key, dp, d1);
                    if (i == start) {
                        startLE = le;
                    }
                    if (i == end) {
                        endLE = le;
                    }

                    if (i > start && i <= end ) {
                        referenceMapForward.put(key, d1);
                        if (referenceMapBackwards.get(key) == null) {
                            referenceMapBackwards.put(key, dp);
                        }
                    }

                    log.append(le);
                    dp = d1;
                }

                //diff forward
                RetroMap<String, String> diff = log.computeDiff(endLE.getTime(), startLE);
                RetroMap<String, String> diff2;
                try {
                    diff2 = log.computeDiffForwards(startLE, endLE);
                } catch (LogOutTimeBoundsException lotbe) {
                    diff2 = new RetroMap<String, String>();
                }
                LogEntry<String, String> kle = diff.getAssociatedLogEntry();

                /*System.out.println(diff2.size() + " - " + referenceMapForward.size());
                System.out.println(endLE.getTime() + " - " + startLE.getTime());
                System.out.println("diff-----------------");
                Iterator it = diff.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry)it.next();
                    System.out.println(pair.getKey() + " = " + pair.getValue());
                    //it.remove(); // avoids a ConcurrentModificationException
                }
                System.out.println("diff2-----------------");
                it = diff2.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry)it.next();
                    System.out.println(pair.getKey() + " = " + pair.getValue());
                    //it.remove(); // avoids a ConcurrentModificationException
                }
                System.out.println("ref-----------------");
                Iterator it2 = referenceMapForward.entrySet().iterator();
                while (it2.hasNext()) {
                    Map.Entry pair = (Map.Entry)it2.next();
                    System.out.println(pair.getKey() + " = " + pair.getValue());
                    //it2.remove(); // avoids a ConcurrentModificationException
                }
                System.out.println("----------------- " + diff.equals(referenceMapForward));
                */

                assertTrue(kle.getTime().compareTo(endLE.getTime()) == 0);
                assertTrue(diff.equals(referenceMapForward));
                assertTrue(diff2.equals(referenceMapForward));

                //and backwards
                diff = log.computeDiff(startLE.getTime(), endLE);
                try {
                    diff2 = log.computeDiffBackwards(endLE, startLE);
                } catch (LogOutTimeBoundsException lotbe) {
                    diff2 = new RetroMap<String, String>();
                }
                kle = diff.getAssociatedLogEntry();

                /*System.out.println(diff.size() + " - " + referenceMapBackwards.size());
                System.out.println(endLE.getTime() + " - " + startLE.getTime());
                System.out.println("diff-----------------");
                Iterator it = diff.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry)it.next();
                    System.out.println(pair.getKey() + " = " + pair.getValue());
                    //it.remove(); // avoids a ConcurrentModificationException
                }
                System.out.println("diff2-----------------");
                it = diff2.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry)it.next();
                    System.out.println(pair.getKey() + " = " + pair.getValue());
                    //it.remove(); // avoids a ConcurrentModificationException
                }
                System.out.println("ref-----------------");
                it = referenceMapBackwards.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry)it.next();
                    System.out.println(pair.getKey() + " = " + pair.getValue());
                    //it2.remove(); // avoids a ConcurrentModificationException
                }
                System.out.println("----------------- " + diff.equals(referenceMapBackwards));
                */
                assertTrue(kle.getTime().compareTo(startLE.getTime()) == 0);
                assertTrue(diff.equals(referenceMapBackwards));
                assertTrue(diff2.equals(referenceMapBackwards));
            }
        }
    }

    @Test
    public void testGetLength() throws Exception {
        assertTrue(log.getLength() == length);
    }

    @Test
    public void testGetHead() throws Exception {
        assertTrue(log.getHead().isHead());
        assertTrue(log.getHead() == head); //must be the same reference
    }

    @Test
    public void testGetTail() throws Exception {
        assertTrue(log.getTail().isTail());
        assertTrue(log.getTail() == tail); //must be the same reference
    }

    @Test
    public void testGetMaxLogSize() throws Exception {
        assertTrue(log.getMaxLogSize() == length * 10);
    }

    @Test
    public void testStopTruncating() throws Exception {
        log.stopTruncating();

        Timestamp t = log.getTail().getTime().add(length * 5, (short) 0);

        Random rand = new Random(System.nanoTime());
        DataEntry<String> dp
                = new DataEntry<String>("val1", t);
        for (int i = 0; i < length; i++) {

            t = t.add(1 + rand.nextInt(8), (short)0);
            DataEntry<String> d1
                    = new DataEntry<String>("val1", t);

            LogEntry<String, String> le = new LogEntry<String, String>("test", dp, d1);
            if (i == 0) {
                head = le;
            }
            if (i == length - 1) {
                tail = le;
            }
            log.append(le);
            dp = d1;
        }

        assertTrue(log.getLength() == length * 2);
        assertTrue(log.getTail().getTime().getWallTime() - log.getHead().getTime().getWallTime()
                > length * 10);
    }

    @Test
    public void testStartTruncating() throws Exception {
        testStopTruncating();
        log.startTruncating();

        Timestamp t = log.getTail().getTime().add(length * 5, (short) 0);

        Random rand = new Random(System.nanoTime());
        DataEntry<String> dp
                = new DataEntry<String>("val1", t);
        for (int i = 0; i < length; i++) {

            t = t.add(1 + rand.nextInt(8), (short)0);
            DataEntry<String> d1
                    = new DataEntry<String>("val1", t);

            LogEntry<String, String> le = new LogEntry<String, String>("test", dp, d1);
            if (i == 0) {
                head = le;
            }
            if (i == length - 1) {
                tail = le;
            }
            log.append(le);
            dp = d1;
        }

        assertTrue(log.getTail().getTime().getWallTime() - log.getHead().getTime().getWallTime()
                <= length * 10);

    }

    @Test
    public void testGetName() throws Exception {
        assertTrue(log.getName().equals("test123"));
    }

    @Test
    public void logSliceAsArray() throws Exception {
        Log<String, String> log = new Log<String, String>(1000, "test");
        Random rand = new Random(System.nanoTime());
        Timestamp t = new Timestamp();
        int len = 100;
        for (int i = 0; i < len; i++) {
            t = t.add(2 + rand.nextInt(2), (short)0);
            DataEntry<String> dp
                    = new DataEntry<String>("val" + (i - 1), t);

            DataEntry<String> d1
                    = new DataEntry<String>("val " + i, t);

            log.append(new LogEntry<String, String>("key", dp, d1));
        }

        LogEntry<String, String> logEntry = log.getHead();
        for (int i = 0; i < len - 10 ; i++) {
            LogEntry<String, String> sliceEnd = logEntry;
            for (int j = 0; j < 10 ; j++) {
                sliceEnd = sliceEnd.getNext();
            }
            Timestamp sliceStartTime = logEntry.getTime();
            int r = rand.nextInt(1);
            sliceStartTime = sliceStartTime.add(r, (short) 0);

            Timestamp sliceEndTime = sliceEnd.getTime();
            r = rand.nextInt(1);
            sliceEndTime = sliceEndTime.add(r, (short) 0);

            Log<String, String> slice = log.logSlice(sliceStartTime, sliceEndTime);

            assertTrue(slice.getHead().equals(logEntry));
            assertTrue(slice.getTail().equals(sliceEnd));
            assertTrue(slice.getMaxLogSize() == log.getMaxLogSize());
            assertTrue(slice.getLogCheckpointIntervalMs() == log.getLogCheckpointIntervalMs());

            logEntry = logEntry.getNext();
        }


    }

    @Test
    public void logSlicePartialKeysAsArray() throws Exception {
        Log<String, String> log = new Log<String, String>(1000, "test");
        Random rand = new Random(System.nanoTime());
        Timestamp t = new Timestamp();
        int len = 100;
        for (int i = 0; i < len; i++) {
            t = t.add(2 + rand.nextInt(2), (short)0);
            DataEntry<String> dp
                    = new DataEntry<String>("val" + (i - 1), t);

            DataEntry<String> d1
                    = new DataEntry<String>("val " + i, t);

            log.append(new LogEntry<String, String>("key" + (i % 10), dp, d1));
        }



        for (int k = 0; k < 9; k++) {
            LogEntry<String, String> logEntry = log.getHead();
            for (int i = 0; i < len - 19; i++) {
                LogEntry<String, String> sliceEnd = logEntry;
                for (int j = 0; j < 19; j++) {
                    sliceEnd = sliceEnd.getNext();
                }
                Timestamp sliceStartTime = logEntry.getTime();
                int r = rand.nextInt(1);
                sliceStartTime = sliceStartTime.add(r, (short) 0);

                Timestamp sliceEndTime = sliceEnd.getTime();
                r = rand.nextInt(1);
                sliceEndTime = sliceEndTime.add(r, (short) 0);

                ArrayList<String> keys = new ArrayList<String>();
                keys.add("key"+k);
                keys.add("key"+(k+1));

                Log<String, String> slice = log.logSlice(keys, sliceStartTime, sliceEndTime);

                //System.out.println(slice);

                LogEntry<String, String> le = slice.getHead();
                int l = 0;
                while (le != null) {
                    //System.out.println("   " + le);
                    assertTrue(keys.contains(le.getKey()));

                    le = le.getNext();
                    l++;
                }
                assertTrue(slice.getLength() == 4);
                assertTrue(slice.getLength() == l);

                logEntry = logEntry.getNext();
            }
        }


    }

    @Test
    public void toProtocol() throws Exception {

    }
}