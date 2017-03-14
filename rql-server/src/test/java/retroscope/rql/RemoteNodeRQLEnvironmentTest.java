package retroscope.rql;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import retroscope.RetroscopeException;
import retroscope.hlc.Timestamp;
import retroscope.log.*;
import retroscope.net.server.Server;

import java.io.StringReader;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by ALEKS on 1/16/2017.
 */
public class RemoteNodeRQLEnvironmentTest {

    private static final String keyPrefix = "key";

    private Server<String, RQLItem> server;
    private RemoteNodeRQLEnvironment rqlEnvironment;
    private HashMap<String, Integer> counts;
    private ArrayList<Timestamp> sliceTimes;

    Thread ct;

    @Before
    public void setUp() throws Exception {
        sliceTimes = new ArrayList<Timestamp>(20);
        counts = new HashMap<String, Integer>(15);
        for (int i = 0; i < 11; i++) {
            counts.put(keyPrefix + i, 0);
        }
        server = new Server();
        Runnable serverR = new Runnable() {
            public void run() {
                try {
                    server.startServer();
                } catch (Exception e) {}
            }
        };
        Thread st = new Thread(serverR);
        st.start();
        // now start one client
        final RQLRetroscope retroscope = new RQLRetroscope();
        retroscope
                .setLogCheckpointIntervalMs(1000)
                .connectRetroServer("127.0.0.1", 8007);
        Runnable clientR = new Runnable() {
            public void run() {
                Random random = new Random(System.currentTimeMillis());
                for (int i = 0; i < 100; i++) {
                    try {
                        Thread.sleep(random.nextInt(10));
                        RQLItem item = new RQLItem().addField(i);
                        String key = keyPrefix+random.nextInt(10);
                        counts.put(key, counts.get(key) + 1);
                        retroscope.appendToLog("test", key, item);
                        if (i % 10 == 0) {sliceTimes.add(retroscope.getTimestamp().clone());}
                    } catch (Exception ex) {}
                }
            }
        };

        ct = new Thread(clientR);
        ct.start();

        rqlEnvironment = new RemoteNodeRQLEnvironment(server);
    }

    @After
    public void tearDown() throws Exception {
        server.close();
        ct.join();
        System.out.println("ct join");
    }

    @Test
    public void retrieveRemoteLogs() throws Exception {
        Thread.sleep(200); //wait a bit to get the log going
        String[] logs = {"test"};
        RQLRetrieveParam retrieveParam = new RQLRetrieveParam().setLogs(Arrays.asList(logs));
        rqlEnvironment.retrieveRemoteLogs(retrieveParam);
        ArrayList<RQLLog> logsRetrieved = rqlEnvironment.getLogs();
        assertTrue(logsRetrieved.size() == 1);
        assertTrue(logsRetrieved.get(0).getName().equals("test"));
        //assertTrue(logsRetrieved.get(0).getNodeId() == 1);
        assertTrue(logsRetrieved.get(0).getLength() > 0);
        Thread.sleep(10); //wait a bit to get the log going
        rqlEnvironment.retrieveRemoteLogs(retrieveParam);
        ArrayList<RQLLog> logsRetrieved2 = rqlEnvironment.getLogs();
        assertTrue(logsRetrieved2.size() == 1);
        assertTrue(logsRetrieved2.get(0).getName().equals("test"));
        assertTrue(logsRetrieved.get(0).getLength() < logsRetrieved2.get(0).getLength());
    }

    @Test
    public void retrieveRemoteLogsPartialKeySet() throws Exception {
        Thread.sleep(1000); //wait a bit to get the log going
        String[] logs = {"test"};
        for (int i = 0; i < 8; i++) {
            ArrayList<String> keys = new ArrayList<String>();
            keys.add(keyPrefix + i);
            keys.add(keyPrefix + (i + 1));
            RQLRetrieveParam retrieveParam = new RQLRetrieveParam().setLogs(Arrays.asList(logs)).setKeys(keys);
            rqlEnvironment.retrieveRemoteLogs(retrieveParam);
            ArrayList<RQLLog> logsRetrieved = rqlEnvironment.getLogs();
            assertTrue(logsRetrieved.size() == 1);
            assertTrue(logsRetrieved.get(0).getName().equals("test"));
            int c = counts.get(keys.get(0))
                    + counts.get(keys.get(1));
            assertTrue(logsRetrieved.get(0).getLength() == c);
        }
    }

    @Test
    public void retrieveRemoteLogsTimeSlices() throws Exception {
        Thread.sleep(1000); //wait a bit to get the log going
        String[] logs = {"test"};
        for (int i = 0; i < sliceTimes.size() - 1; i++) {

            RQLRetrieveParam retrieveParam = new RQLRetrieveParam()
                    .setLogs(Arrays.asList(logs))
                    .setStartTime(sliceTimes.get(i))
                    .setEndTime(sliceTimes.get(i + 1)); //times are inclusive
            rqlEnvironment.retrieveRemoteLogs(retrieveParam);
            ArrayList<RQLLog> logsRetrieved = rqlEnvironment.getLogs();
            assertTrue(logsRetrieved.size() == 1);
            assertTrue(logsRetrieved.get(0).getName().equals("test"));
            assertTrue(logsRetrieved.get(0).getLength() == 11);
        }
    }

    @Test
    public void retrieveCutAtTime() throws Exception {
        Thread.sleep(1000); //wait a bit to get the log going
        String[] logs = {"test"};
        for (int i = 0; i < sliceTimes.size() - 1; i++) {

            RQLRetrieveParam retrieveParam = new RQLRetrieveParam()
                    .setLogs(Arrays.asList(logs))
                    .setStartTime(sliceTimes.get(i));
            rqlEnvironment.retrieveSingleCut(retrieveParam);
            ArrayList<RQLLog> logsRetrieved = rqlEnvironment.getLogs();
            assertTrue(logsRetrieved.size() == 0);
            assertTrue(rqlEnvironment.tempGlobalCuts.size() == 1);
        }
    }

}