package retroscope.hlc;

import static org.junit.Assert.*;

/**
 * Created by aleksey on 10/16/16.
 */
public class HLCTest {

    private HLC clock;

    @org.junit.Before
    public void setUp() throws Exception {
        long pt = System.currentTimeMillis();
        clock = new HLC(new Timestamp(pt, (short)0));
    }

    @org.junit.Test
    public void testGetTimestamp() throws Exception {
        Timestamp t1 = clock.now();
        Timestamp tget = clock.getTimestamp();
        tget.incrementLogical();
        Timestamp tSide = clock.getTimestamp();

        //we should not see side-effects of messing with a timestamp obtained from HLC
        assertTrue(tget.getLogical() - 1 == tSide.getLogical());
    }

    @org.junit.Test
    public void testReset() throws Exception {
        Timestamp t1 = clock.now();
        clock.reset();
        Timestamp t2 = clock.now();
        assertTrue(t1.getLogical() == t2.getLogical());
        assertTrue(t1.getWallTime() == t2.getWallTime());
    }

    @org.junit.Test
    public void testNow() throws Exception {

        //same pt, must increment logical. should work in mist cases unless we are very un lucky to have clock change
        Timestamp t1 = clock.now();
        Timestamp t2 = clock.now();
        assertTrue(t1.getLogical() + 1 == t2.getLogical());
        assertTrue(t1.getWallTime() == t2.getWallTime());

        //first pt is smaller than the second one, no logical increment
        Thread.sleep(10);
        t1 = clock.now();
        Thread.sleep(10);
        t2 = clock.now();
        assertTrue(t1.getLogical() == 0);
        assertTrue(t1.getLogical() == t2.getLogical());
        assertTrue(t1.getWallTime() < t2.getWallTime());

    }

    @org.junit.Test
    public void testUpdate() throws Exception {
        Timestamp tPast = new Timestamp(System.currentTimeMillis() - 10, (short)0);

        //update with a timestamp with smaller pt should not increment logical (if pt changed) or change wall time
        Timestamp tBase = clock.now();
        Thread.sleep(2);
        Timestamp tCheck = clock.update(tPast);
        assertTrue(tCheck.getWallTime() > tBase.getWallTime());
        assertTrue(tCheck.getWallTime() > tPast.getWallTime());
        assertTrue(tCheck.getLogical() == 0);

        Thread.sleep(1);
        tPast = new Timestamp(System.currentTimeMillis() - 10, (short)0);
        //update with a timestamp with smaller pt should increment logical (pt is same as previos time in HLC)
        // and not change wall time to past
        long t = System.currentTimeMillis();
        tBase = clock.now();
        tCheck = clock.update(tPast);
        assertTrue(tCheck.getWallTime() == tBase.getWallTime());
        assertTrue(tCheck.getWallTime() == t);
        assertTrue(tCheck.getWallTime() > tPast.getWallTime());
        assertTrue(tCheck.getLogical() == 1);

        Thread.sleep(1);
        //update with a timestamp with smaller pt should increment logical (pt is same as previos time in HLC)
        // and not change wall time to past
        t = System.currentTimeMillis();
        tPast.setLogical((short)0);
        tPast.setWallTime(t);

        tBase = clock.now();
        tCheck = clock.update(tPast);
        assertTrue(tCheck.getWallTime() == tBase.getWallTime());
        assertTrue(tCheck.getWallTime() == t);
        assertTrue(tCheck.getWallTime() == tPast.getWallTime());
        assertTrue(tCheck.getLogical() == 1);


        Thread.sleep(1);
        Timestamp tFuture = new Timestamp(System.currentTimeMillis() + 10, (short)0);
        //update with a timestamp with greater pt should increment logical and change change wall time
        tBase = clock.now();
        Thread.sleep(2);
        tCheck = clock.update(tFuture);
        assertTrue(tCheck.getWallTime() > tBase.getWallTime());
        assertTrue(tCheck.getWallTime() > System.currentTimeMillis()); //our HLC pt is greater then current local time
        assertTrue(tCheck.getLogical() == 1);
    }
}