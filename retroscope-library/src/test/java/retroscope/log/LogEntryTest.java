package retroscope.log;

import retroscope.hlc.Timestamp;
import org.junit.Test;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by aleksey on 10/17/16.
 */
public class LogEntryTest {

    DataEntry<byte[]> d1, d2, d3, d4, d5;
    Timestamp t1, t2, t3, t4, t5;
    LogEntry<byte[], byte[]> le1, le2, le3;

    @org.junit.Before
    public void setUp() throws Exception {
        t1 = new Timestamp();
        d1 = new DataEntry<byte[]>("val1".getBytes(),t1);
        t2 =t1.add(10, (short)0);
        d2 = new DataEntry<byte[]>("val2".getBytes(),t2);
        le1 = new LogEntry<byte[], byte[]>("test".getBytes(), d1, d2);
        t3 = t2.add(10, (short)0);
        t4 = t3.add(5, (short)0);
        d3 = new DataEntry<byte[]>("val3".getBytes(),t3);
        d4 = new DataEntry<byte[]>("val4".getBytes(),t4);
        le2 = new LogEntry<byte[], byte[]>("test2".getBytes(), d3, d4);
        t5 = t4.add(5, (short)0);
        d5 = new DataEntry<byte[]>("val4".getBytes(),t5);
        le3 = new LogEntry<byte[], byte[]>("test2".getBytes(), d4, d5);

        le1.setNext(le2);
        le2.setNext(le3);

        le3.setPrev(le2);
        le2.setPrev(le1);
    }

    @Test
    public void testGetTime() throws Exception {
        assertTrue(le1.getTime().compareTo(t2) == 0);
        assertTrue(le2.getTime().compareTo(t4) == 0);
        assertTrue(le3.getTime().compareTo(t5) == 0);
    }

    @Test
    public void testGetKey() throws Exception {
        assertTrue(Arrays.equals(le1.getKey(), "test".getBytes()));
        assertTrue(Arrays.equals(le2.getKey(), "test2".getBytes()));
        assertTrue(Arrays.equals(le3.getKey(), "test2".getBytes()));
    }

    @Test
    public void testGetNext() throws Exception {
        assertTrue(le1.getNext() == le2);
        assertTrue(le1.getNext().getNext() == le3);
        assertTrue(le1.getNext().getNext().getNext() == null);
    }

    @Test
    public void testGetPrev() throws Exception {
        assertTrue(le3.getPrev() == le2);
        assertTrue(le3.getPrev().getPrev() == le1);
        assertTrue(le3.getPrev().getPrev().getPrev() == null);
    }

    @Test
    public void testIsHead() throws Exception {
        assertTrue(le1.isHead());
        assertTrue(!le2.isHead());
        assertTrue(!le3.isHead());
    }

    @Test
    public void testIsTail() throws Exception {
        assertTrue(!le1.isTail());
        assertTrue(!le2.isTail());
        assertTrue(le3.isTail());
    }
}