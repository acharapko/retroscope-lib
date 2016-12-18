package retroscope.net.server;

import javax.net.ssl.SSLException;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import retroscope.console.Console;
import retroscope.net.protocol.Protocol;
import retroscope.nodeensemble.Ensemble;

import java.io.Serializable;


public class Server<K extends Serializable, V extends Serializable> {

    static final boolean SSL = System.getProperty("ssl") != null;
    static final int PORT = Integer.parseInt(System.getProperty("port", "8007"));

    private SyncRunner syncRunner;
    private Ensemble<K, V> ensemble;

    public static void main(String[] args) {
        //TODO server starts here
        System.out.println("Starting Server");
        Server<String, String> s = new Server<String, String>();
        Console c = new Console(s.ensemble);
        Thread t = new Thread(c);
        t.start();
        try {
            s.startServer();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Server() {
        ensemble = new Ensemble<K, V>();
    }

    public void startServer() throws InterruptedException, SSLException, java.security.cert.CertificateException {
        // Configure SSL.
        final SslContext sslCtx;
        if (SSL) {
            SelfSignedCertificate ssc = new SelfSignedCertificate();
            sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
        } else {
            sslCtx = null;
        }

        // Configure the server.
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            if (sslCtx != null) {
                                p.addLast(sslCtx.newHandler(ch.alloc()));
                            }
                            //p.addLast(new LoggingHandler(LogLevel.INFO));
                            p.addLast("frameDecoder", new ProtobufVarint32FrameDecoder());
                            p.addLast("protobufDecoder", new ProtobufDecoder(Protocol.RetroNodeMsg.getDefaultInstance()));

                            p.addLast("frameEncoder", new ProtobufVarint32LengthFieldPrepender());
                            p.addLast("protobufEncoder", new ProtobufEncoder());

                            p.addLast(new ServerHandler<K, V>(ensemble));
                        }
                    });

            // Start the server.
            ChannelFuture f =  b.bind(PORT).sync();
            // Wait until the server socket is closed.
            f.channel().closeFuture().sync();
        } finally {
            // Shut down all event loops to terminate all threads.
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
        System.out.println("done");
    }

    public Ensemble<K, V> getEnsemble() {
        return this.ensemble;
    }

    public void close() {
        syncRunner.stop();
        //this.channel.close();
    }


}
