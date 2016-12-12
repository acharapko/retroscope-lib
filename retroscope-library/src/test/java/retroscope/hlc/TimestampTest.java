package retroscope.hlc;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by aleksey on 10/16/16.
 *
 * some basic HLC timestamp tests
 */
public class TimestampTest {
    Random rand;

    @Before
    public void setUp() throws Exception {
        //nothing to setup for now
        rand = new Random(System.nanoTime());
    }

    @Test
    public void testClone() throws Exception {
        //clone should have no side-effects
        Timestamp t = new Timestamp();
        t.setLogical((short)1);
        Timestamp tClone = t.clone();
        tClone.setLogical((short)3);
        assertTrue(t.getLogical() != tClone.getLogical());
        t.setLogical((short)2);
        assertTrue(t.getLogical() != tClone.getLogical());
    }

    @Test
    public void testAdd() throws Exception {
        Timestamp t = new Timestamp();
        short l = (short) rand.nextInt(100);
        t.setLogical((short)l);
        long wt = t.getWallTime();
        int wtIncrement = rand.nextInt(100);
        short logicalIncrement = (short)rand.nextInt(100);
        Timestamp tNew = t.add(wtIncrement, logicalIncrement);
        assertTrue(tNew.getLogical() == l + logicalIncrement);
        assertTrue(tNew.getWallTime() == wt + wtIncrement);
    }

    @Test
    public void testAdd1() throws Exception {
        Timestamp t = new Timestamp();
        short l = (short) rand.nextInt(100);
        t.setLogical((short)l);
        long wt = t.getWallTime();
        int wtIncrement = rand.nextInt(100);
        short logicalIncrement = (short)rand.nextInt(100);
        Timestamp tInc = new Timestamp(wtIncrement, logicalIncrement);
        Timestamp tNew = t.add(tInc);
        assertTrue(tNew.getLogical() == l + logicalIncrement);
        assertTrue(tNew.getWallTime() == wt + wtIncrement);
    }

    @Test
    public void testIncrementLogical() throws Exception {
        Timestamp t = new Timestamp();
        short l = (short) rand.nextInt(100);
        t.setLogical((short)l);
        t.incrementLogical();
        assertTrue(t.getLogical() == l + 1);
    }

    @Test
    public void testResetLogical() throws Exception {
        Timestamp t = new Timestamp();
        short l = (short) rand.nextInt(100);
        t.setLogical((short)l);
        t.incrementLogical();
        t.resetLogical();
        assertTrue(t.getLogical() == 0);
    }

    @Test
    public void testToLong() throws Exception {
        Timestamp t = new Timestamp();
        long tLong = t.toLong();
        Timestamp t2 = t.add(1, (short)0);
        long t2Long = t2.toLong();
        Timestamp t3 = t.add(0, (short)1);
        long t3Long = t3.toLong();

        //make sure the order holds in 64-bit notation
        assertTrue(tLong < t2Long);
        assertTrue(tLong < t3Long);

        Timestamp tRecon = new Timestamp(tLong);
        Timestamp t2Recon = new Timestamp(t2Long);
        Timestamp t3Recon = new Timestamp(t3Long);
        //make sure we reconstruct to the same time
        assertTrue(t.getWallTime() == tRecon.getWallTime());
        assertTrue(t.getLogical() == tRecon.getLogical());

        assertTrue(t2.getWallTime() == t2Recon.getWallTime());
        assertTrue(t2.getLogical() == t2Recon.getLogical());

        assertTrue(t3.getWallTime() == t3Recon.getWallTime());
        assertTrue(t3.getLogical() == t3Recon.getLogical());

    }

    @Test
    public void testCompareTo() throws Exception {
        Timestamp t = new Timestamp();
        t.setLogical((short)2);
        Timestamp tfuture1 = t.add(1, (short)0);
        Timestamp tfuture2 = t.add(0, (short)1);
        Timestamp tpast1 = t.add(-1, (short)1);
        Timestamp tpast2 = t.add(0, (short)-1);
        Timestamp tSame = t.clone();

        assertTrue(t.compareTo(tfuture1) < 0);
        assertTrue(t.compareTo(tfuture2) < 0);
        assertTrue(t.compareTo(tpast1) > 0);
        assertTrue(t.compareTo(tpast2) > 0);
        assertTrue(t.compareTo(tSame) == 0);
    }
}