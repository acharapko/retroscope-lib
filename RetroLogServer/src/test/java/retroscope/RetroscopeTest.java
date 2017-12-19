package retroscope;

import org.junit.Before;
import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by Aleksey on 11/7/2016.
 * Tests on the main library class
 */
public class RetroscopeTest {


    private int runs = 10000;
    private int smallRuns = 100;
    private Random rand;
    private Retroscope retroscope;
    private Ignite ignite;

    @Before
    public void setUp() throws Exception {
        rand = new Random(System.nanoTime());
        retroscope = new Retroscope("testApp");
        ignite = Ignition.ignite();
        retroscope.connectIgnite(ignite);
    }



}