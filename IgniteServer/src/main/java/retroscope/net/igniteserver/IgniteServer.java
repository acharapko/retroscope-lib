package retroscope.net.igniteserver;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class IgniteServer {

    private Ignite ignite;
    private Logger logger = Logger.getLogger(IgniteServer.class);
    private String igniteConfigPath;

    public IgniteServer(String configPath) {
        this.igniteConfigPath = configPath;
    }

    private void ignite() {
        System.out.println("Starting Ignite Server with igniteConfigPath = " + igniteConfigPath);
        if (!igniteConfigPath.isEmpty()) {

            this.ignite = Ignition.start(igniteConfigPath);
        } else {
            this.ignite = Ignition.start();
        }
    }

    public static void main(String[] args) {
        PropertyConfigurator.configure("log4j.config");
        IgniteServer is;
        if (args.length == 0) {
            is = new IgniteServer("");
        } else {
            is = new IgniteServer(args[0]);
        }
        is.ignite();
    }
}
