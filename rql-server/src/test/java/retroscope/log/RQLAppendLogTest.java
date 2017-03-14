package retroscope.log;

import org.junit.Test;
import retroscope.hlc.Timestamp;
import retroscope.rql.RQLItem;

import java.util.HashMap;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by Aleksey on 1/29/2017.
 *
 */
public class RQLAppendLogTest {

    RQLAppendLog log;
    int length = 100;

    @org.junit.Before
    public void setUp() throws Exception {
        Log<String, RQLItem> log = new Log<String, RQLItem>(length * 10, "test123");
        log = populateTheLog(log, length);
        this.log = new RQLAppendLog(1, log);
    }

    private Log<String, RQLItem> populateTheLog(Log<String, RQLItem> log, int length) throws Exception{
        Timestamp t = new Timestamp();
        Random rand = new Random(System.nanoTime());
        RQLItem item = new RQLItem()
                .addField("", "val");
        DataEntry<RQLItem> dp
                = new DataEntry<RQLItem>(item, t);
        for (int i = 0; i < length; i++) {

            t = t.add(2 + rand.nextInt(7), (short)0);
            String k = "keytest";
            item = new RQLItem()
                    .addField("", "val" + (i));
            DataEntry<RQLItem> d1
                    = new DataEntry<RQLItem>(item, t);

            //System.out.println(d1);
            LogEntry<String, RQLItem> le = new LogEntry<String, RQLItem>(k, dp, d1);

            log.append(le);
            dp = d1;
        }
        return log;
    }

    @Test
    public void checkLog() throws Exception {
        assertTrue(log.getLength() == 100);
        LogEntry<String, RQLItem> le = log.getHead();
        int c = 0;
        while(le != null) {
            String v = "val" + (c);
            assertTrue(le.getToV().getValue().getField("").getStringVal().equals(v));
            c++;
            le = le.getNext();
        }
    }

    @Test
    public void makeSnapshot() throws Exception {
        LogEntry<String, RQLItem> le = log.getHead();
        int prevSID = -1;
        int c = 0;
        while(le != null) {
            int sid = log.makeSnapshot(le.getTime());
            assertTrue(sid > prevSID);
            RQLSetMap snap = log.getSnapshot(sid);

            //System.out.println(snap.get("keytest").size());
            assertTrue(snap.get("keytest").size() == c + 1);

            HashMap<String, Integer> check = new HashMap<String, Integer>(c);
            for (int i = 0; i <= c; i++) {
                check.put("val"+i, i);
            }

            for (DataEntry<RQLItem> r : snap.get("keytest")) {
                assertTrue(check.get(r.getValue().getField("").getStringVal()) != null);
                check.remove(r.getValue().getField("").getStringVal());
            }
            prevSID = sid;
            c++;
            le = le.getNext();
        }
    }

    @Test
    public void getSnapshot() throws Exception {
        LogEntry<String, RQLItem> le = log.getHead();
        int prevSID = -1;
        int c = 0;
        while(le != null) {
            int sid = log.makeSnapshot(le.getTime());
            assertTrue(sid > prevSID);
            prevSID = sid;
            c++;
            le = le.getNext();
        }

        for (int j = 0; j <= prevSID; j++) {
            RQLSetMap snap = log.getSnapshot(j);

            //System.out.println(snap.get("keytest").size());
            assertTrue(snap.get("keytest").size() == j + 1);

            HashMap<String, Integer> check = new HashMap<String, Integer>(c);
            for (int i = 0; i <= j; i++) {
                check.put("val"+i, i);
            }

            for (DataEntry<RQLItem> r : snap.get("keytest")) {
                assertTrue(check.get(r.getValue().getField("").getStringVal()) != null);
                check.remove(r.getValue().getField("").getStringVal());
            }
        }
    }

}