package retroscope.net.server;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import retroscope.log.DataMapLog;
import retroscope.log.Log;
import retroscope.log.RetroMap;
import retroscope.net.protocol.Protocol;
import retroscope.net.protocol.ProtocolHelpers;
import retroscope.nodeensemble.Ensemble;
import retroscope.nodeensemble.RetroscopeServerEnsembleException;

import java.io.Serializable;

/**
 * Created by Aleksey on 8/13/2016.
 *
 */

@Sharable
public class ServerHandler<K extends Serializable, V extends Serializable> extends ChannelInboundHandlerAdapter {

    static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private Ensemble<K, V> ensemble;

    public ServerHandler(Ensemble<K, V> ensemble) {
        this.ensemble = ensemble;
    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) {

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        //ctx.write(msg);
        //System.out.println("Reading: " + msg);
        Protocol.RetroNodeMsg responseMsg =
                (Protocol.RetroNodeMsg) msg;

        if (responseMsg.hasConnectMsg()) {
            //new node is connecting
            ensemble.processConnect(ctx, responseMsg.getConnectMsg());
        }

        if ((responseMsg.hasData() || responseMsg.hasErrorCode()) && responseMsg.hasRID()) {
            //handle receiving data;
            Protocol.DataMap data = responseMsg.getData();
            int nodeId = responseMsg.getNodeId();
            long rid = responseMsg.getRID();
            String logName = data.getName();
            RetroMap<K, V> dataMap;
            if (responseMsg.hasData()) {
                dataMap = ProtocolHelpers.protocolToRetroMap(responseMsg.getData());
            } else {
                dataMap = null;
            }
            int errcode = 0;
            if (responseMsg.hasErrorCode()) {
                errcode = responseMsg.getErrorCode();
            }
            try {
                long hlcTime = 0;
                if (responseMsg.hasData()) {
                    hlcTime = data.getHlcTime();
                }
                ensemble.handleDataReceive(nodeId, rid, hlcTime, logName, dataMap, errcode);
            } catch (RetroscopeServerEnsembleException ee) {System.err.println(ee.getMessage());}
        }

        if ((responseMsg.hasLog() || responseMsg.hasErrorCode()) && responseMsg.hasRID()) {
            //handle receiving log data;

            int nodeId = responseMsg.getNodeId();
            long rid = responseMsg.getRID();

            Log<K, V> logSlice = null;
            int errcode = 0;
            if (responseMsg.hasErrorCode()) {
                errcode = responseMsg.getErrorCode();
            } else {
                Protocol.Log log = responseMsg.getLog();
                if (log.hasDataMap()) {
                    logSlice = new DataMapLog<K, V>(log);
                } else {
                    logSlice = new Log<K, V>(log);
                }
            }

            try {
                ensemble.handleLogReceive(nodeId, rid, logSlice, errcode);
            } catch (RetroscopeServerEnsembleException ee) {System.err.println(ee.getMessage());}
        }

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }

}