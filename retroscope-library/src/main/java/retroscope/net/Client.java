package retroscope.net;

import javax.net.ssl.SSLException;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import retroscope.Retroscope;
import retroscope.net.protocol.Protocol;
import java.io.Serializable;

/**
 * Created by Aleksey on 8/13/2016.
 */
public class Client<K extends Serializable, V extends Serializable> {

    static final boolean SSL = System.getProperty("ssl") != null;
    static final int SIZE = Integer.parseInt(System.getProperty("size", "256"));

    private EventLoopGroup group;
    private Channel channel = null;

    private int currentClientMessageID = 0;
    private int port;
    private String host;
    private Retroscope<K, V> retroscope;

    private int id;


    public Client(String host, int port, Retroscope<K, V> retroscope) {
        this(host, port, retroscope, -1);
    }

    public Client(String host, int port, Retroscope<K, V> retroscope, int clientID) {
        this.host = host;
        this.port = port;
        //we need to have retroscope here, so we can use it in the network client
        //in a sense we are creating a circular reference with retroscope having the client
        //and client having the retroscope, but we do it for the ease of use,
        //so that the user does not have to deal with having to keep a separate retroscope network client
        this.retroscope = retroscope;
        if (clientID >= 0) {
            id = clientID;
        }
    }

    public void startClient() throws InterruptedException, SSLException, java.security.cert.CertificateException {

        // Configure SSL.git
        final SslContext sslCtx;
        final Client c = this;
        if (SSL) {
            sslCtx = SslContextBuilder.forClient()
                    .trustManager(InsecureTrustManagerFactory.INSTANCE).build();
        } else {
            sslCtx = null;
        }

        // Configure the client.
        group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline p = ch.pipeline();
                        if (sslCtx != null) {
                            p.addLast(sslCtx.newHandler(ch.alloc(), host, port));
                        }
                        p.addLast("frameDecoder", new ProtobufVarint32FrameDecoder());
                        p.addLast("protobufDecoder", new ProtobufDecoder(Protocol.RetroServerMsg.getDefaultInstance()));

                        p.addLast("frameEncoder", new ProtobufVarint32LengthFieldPrepender());
                        p.addLast("protobufEncoder", new ProtobufEncoder());

                        p.addLast(new ClientHandler(c));
                    }
                });


        // Start the client.
        channel = b.connect(host, port).sync().channel();

    }

    /* ----------- Client Messages --------------- */

    public void connect() {
        Protocol.ConnectMsg.Builder cmb = Protocol.ConnectMsg.newBuilder();
        cmb.setRetroscopeVersion(Retroscope.VERSION);
        if (id >= 0) {
            cmb.setNodeId(id);
        }
        Protocol.RetroNodeMsg message = Protocol.RetroNodeMsg.newBuilder()
                .setConnectMsg(cmb)
                .build();

        channel.writeAndFlush(message);
    }


    /* ----------- Support Functions --------------*/


    public void close() {
        try {
            channel.closeFuture().sync();
        } catch (InterruptedException ie) {
        } finally {
            group.shutdownGracefully();
        }

    }

    private int getNextMessageID()
    {
        if (currentClientMessageID == Integer.MAX_VALUE)
            currentClientMessageID = 0;
        return currentClientMessageID++;
    }

    /* Getters and Setters */

    public int getPort() {
        return port;
    }

    public String getHost() {
        return host;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Retroscope<K, V> getRetroscope() {
        return retroscope;
    }
}
