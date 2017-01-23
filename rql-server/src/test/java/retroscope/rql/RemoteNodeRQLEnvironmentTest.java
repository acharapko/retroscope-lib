package retroscope.rql;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import retroscope.log.Log;
import retroscope.log.RQLDataMapLog;
import retroscope.net.server.Server;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by ALEKS on 1/16/2017.
 */
public class RemoteNodeRQLEnvironmentTest {

    private Server<String, RQLItem> server;
    private RemoteNodeRQLEnvironment rqlEnvironment;
    @Before
    public void setUp() throws Exception {
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
                        retroscope.appendToLog("test", "key"+random.nextInt(10), item);
                    } catch (Exception ex) {}
                }
            }
        };

        Thread ct = new Thread(clientR);
        ct.start();

        rqlEnvironment = new RemoteNodeRQLEnvironment(server);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void retrieveRemoteLogs() throws Exception {
        Thread.sleep(200); //wait a bit to get the log going
        String[] logs = {"test"};
        rqlEnvironment.retrieveRemoteLogs(logs);
        ArrayList<RQLDataMapLog> logsRetrieved = rqlEnvironment.getLogs();
        assertTrue(logsRetrieved.size() == 1);
        assertTrue(logsRetrieved.get(0).getName().equals("test"));
        assertTrue(logsRetrieved.get(0).getNodeId() == 1);
        assertTrue(logsRetrieved.get(0).getLength() > 0);

        rqlEnvironment.retrieveRemoteLogs(logs);
        ArrayList<RQLDataMapLog> logsRetrieved2 = rqlEnvironment.getLogs();
        assertTrue(logsRetrieved2.size() == 1);
        assertTrue(logsRetrieved2.get(0).getName().equals("test"));
        assertTrue(logsRetrieved2.get(0).getNodeId() == 1);
        assertTrue(logsRetrieved.get(0).getLength() < logsRetrieved2.get(0).getLength());
    }

}