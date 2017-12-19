package examples;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import retroscope.Retroscope;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class SimpleMultiNode {

    private static final int PORT = 9087;

    private Retroscope retroscope;
    private Random random;
    private boolean doTicks = true;

    private ArrayList<String> connectedIPs;
    private HashMap<Integer, Channel> channels;

    private int myNodeId = 0;
    private int[] counter;

    private long startTime;
    private int sleepInterval;

    private int retroTicks = 0;
    private int eventsSent = 0;
    private int eventsRecvd = 0;

    private String leftover = "";

    private final HashMap<String, String> msgStrings = new HashMap<>();


    private Logger logger = Logger.getLogger(SimpleMultiNode.class);

    public SimpleMultiNode(ArrayList<String> ips, int numvars, int sleepInterval, int id) {
        this(ips, "", numvars, sleepInterval, id);
    }

    public SimpleMultiNode(ArrayList<String> ips, String igniteConfig, int numvars, int sleepInterval, int nodeId) {
        retroscope = new Retroscope("simpleMultiNode");
        retroscope.connectIgnite(igniteConfig);
        this.myNodeId = nodeId;//retroscope.getNodeID();
        random = new Random();
        this.connectedIPs = new ArrayList<>();
        this.sleepInterval = sleepInterval;
        counter = new int[numvars];
        channels = new HashMap<>();
        ExampleServer s = new ExampleServer();
        try {
            s.start(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(5000);
        } catch (Exception e) {}
        for (int i = 0; i < ips.size(); i++) {
            ExampleClient c = new ExampleClient();
            String ipparts[] = ips.get(i).split(" ");
            System.out.println(ipparts[0] + " - " + ipparts[1]);
            int id = Integer.parseInt(ipparts[0]);
            c.start(this, ipparts[1], id, PORT);
        }
    }

    private void onMsgReceive(String msg, Channel channel) {
        if (leftover != "") {
            msg = leftover + msg;
        }
        leftover = "";
        String msgs[] = msg.split(";");
        for (String m : msgs) {
            String msgParts[] = m.split(",");
            try {
                int nodeId = Integer.parseInt(msgParts[0]);
                long hlc = Long.parseLong(msgParts[1]);
                int counterIndex = Integer.parseInt(msgParts[2]);
                int counter = Integer.parseInt(msgParts[3]);
                this.counter[counterIndex] = counter;
                retroscope.timeTickForReceive(hlc);
                retroscope.getLog("smn").setVariable("c" + counterIndex, this.counter[counterIndex]).commit();
                retroTicks++;
                channels.putIfAbsent(nodeId, channel);
                eventsRecvd++;
            } catch (Exception e) {
                leftover = m;
            }
        }
    }


    private void runEvents() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                while (doTicks) {
                    eventTick();
                    try {
                        if (sleepInterval > 0) {
                            Thread.sleep(random.nextInt(sleepInterval * 2));
                        } else if (sleepInterval == 0) {
                            Thread.sleep(random.nextInt(random.nextInt(1)));
                        }
                    } catch (InterruptedException ie) {
                        logger.error(ie.getMessage());
                    }
                }
            }
        };
        Thread t = new Thread(r);
        t.start();
    }

    public void stop() {
        doTicks = false;

        long t = System.currentTimeMillis();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}
        retroscope.flushStreams();

        retroscope.closeIgnite();
        logger.info("-----------------------");
        logger.info("Total time: " + (t - startTime) + " ms");
        logger.info("Total retroscope events: " + retroTicks);
        logger.info("Total sent messages: " + eventsSent);
        logger.info("Total recv messages: " + eventsRecvd);
        logger.info("-----------------------");
        System.exit(0);

    }

    public void start() {
        doTicks = true;
        startTime = System.currentTimeMillis();
        runEvents();
    }

    private void eventTick() {
        //find random channel
        List<Integer> nodeIDs = new ArrayList<>(channels.keySet());
        if (nodeIDs.size() > 0) {
            int r = random.nextInt(nodeIDs.size());
            Channel channel = channels.get(nodeIDs.get(r));
            int varID = random.nextInt(counter.length);
            counter[varID]++;
            if (random.nextInt(100) == 42) {
                counter[varID] = 0; //reset counter 1% chance
            }
            eventsSent++;
            retroTicks++;
            retroscope.getLog("smn").setVariable("c" + varID, counter[varID]).commit();
            String msg = this.myNodeId + "," + retroscope.getTimestamp().toLong() + "," + varID + "," + counter[varID] + ";";
            //System.out.println("Sending msg to " + nodeIDs.get(r) +": " + msg);
            channel.writeAndFlush(msg);
        } else {
            logger.debug("skipping tick: no connections");
        }

    }



    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *
     * Server part of the example
     *
     *--------------------------------------------------------*/

    class ExampleServer {

        public void start(SimpleMultiNode smn) throws IOException, InterruptedException {
            NioEventLoopGroup boosGroup = new NioEventLoopGroup();
            NioEventLoopGroup workerGroup = new NioEventLoopGroup();
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boosGroup, workerGroup);
            bootstrap.channel(NioServerSocketChannel.class);

            final EventExecutorGroup group = new DefaultEventExecutorGroup(10); //thread pool of 1500

            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    //pipeline.addLast("idleStateHandler",new IdleStateHandler(0,0,5)); // add with name
                    pipeline.addLast(new StringEncoder()); // add without name, name auto generated
                    pipeline.addLast(new StringDecoder()); // add without name, name auto generated
                    pipeline.addLast(group,"serverHandler",new ExampleServerHandler(smn));
                }
            });
            logger.debug("Starting server...");
            bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.bind(PORT).sync();
        }
    }

    class ExampleServerHandler extends ChannelInboundHandlerAdapter {

        private SimpleMultiNode smn;

        public ExampleServerHandler(SimpleMultiNode smn) {
            this.smn = smn;
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object m) throws Exception {
            String msg = (String) m;
            logger.debug("Message Received (Server): " + msg);
            Channel c = ctx.pipeline().channel();
            smn.onMsgReceive(msg, c);

        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            // Close the connection when an exception is raised.
            cause.printStackTrace();
            ctx.close();
        }
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *
     * Client part of the example
     *
     *--------------------------------------------------------*/

    class ExampleClient {

        public void start(SimpleMultiNode smn, String IP, int id, int port) {
            NioEventLoopGroup workerGroup = new NioEventLoopGroup();
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);

            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new StringEncoder(),new StringDecoder(),new ExampleClientHandler(smn));
                }
            });
            logger.debug("Starting client connection to " + IP);
            try {
                channels.putIfAbsent(id, b.connect(IP, port).sync().channel());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class ExampleClientHandler extends ChannelInboundHandlerAdapter {

        private SimpleMultiNode smn;

        public ExampleClientHandler(SimpleMultiNode smn) {
            this.smn = smn;
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object m) throws Exception {
            String msg = (String) m;
            logger.debug("Message Received (Client): " + msg);
            Channel c = ctx.pipeline().channel();
            smn.onMsgReceive(msg, c);
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            // Close the connection when an exception is raised.
            cause.printStackTrace();
            ctx.close();
        }
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *
     * GETTERS AND SETTERS
     *
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/



    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *
     *  Main and Static Helpers
     *
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

    public static void main(String[] args) throws Exception {
        PropertyConfigurator.configure("log4j.config");
        int runDuration = 12000;
        int numvars = 10;
        int sleepInterval = 50;
        String ipListPath = "iplist.txt";
        String igniteConfig  = "";
        SimpleMultiNode smn;
        if (args.length > 0) {
            runDuration = Integer.parseInt(args[0]);
        }
        if (args.length > 1) {
            ipListPath = args[1];
        }
        if (args.length > 2) {
            igniteConfig = args[2];
        }
        if (args.length > 3) {
            numvars = Integer.parseInt(args[3]);
        }
        if (args.length > 4) {
            sleepInterval = Integer.parseInt(args[4]);
        }

        int myID = 1;
        if (args.length > 5) {
            myID = Integer.parseInt(args[5]);
        }

        System.out.println("Local ID: " + myID);

        ArrayList<String> nodeIPs = getIPs(ipListPath);


        smn = new SimpleMultiNode(nodeIPs, igniteConfig, numvars, sleepInterval, myID);
        Thread.sleep(100);
        System.out.println("Starting");
        smn.start();

        Thread.sleep(runDuration);
        System.out.println("Stopping");
        smn.stop();
        Thread.sleep(1000);

    }

    private static ArrayList<String> getIPs(String fileName) throws IOException {
        ArrayList<String> IPs = new ArrayList<>();
        File file = new File(fileName);
        System.out.println(file.getAbsolutePath());
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            IPs.add(line);
        }
        fileReader.close();
        return IPs;
    }



}
