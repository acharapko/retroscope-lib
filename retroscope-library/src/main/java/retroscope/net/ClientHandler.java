package retroscope.net;

import com.google.protobuf.ByteString;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import retroscope.Retroscope;
import retroscope.RetroscopeException;
import retroscope.log.Log;
import retroscope.log.RetroMap;
import retroscope.net.protocol.Protocol;
import retroscope.net.protocol.ProtocolHelpers;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Aleksey on 8/13/2016.
 * Handles communication on client (retroscope-library) side
 */
class ClientHandler<K extends Serializable, V extends Serializable> extends ChannelInboundHandlerAdapter {
    // private final ByteBuf firstMessage;

    private Client<K, V> client;

    /**
     * Creates a client-side handler.
     */
    ClientHandler(Client<K, V> client) {
        this.client = client;
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        //ctx.write(msg);
        //System.out.println("Reading @ Client!!!" + msg);
        Protocol.RetroServerMsg protocolServerMessage
                = (Protocol.RetroServerMsg) msg;

        if (protocolServerMessage.hasConnectResponse()) {
            //we got connection response
            client.setId(protocolServerMessage.getConnectResponse().getNodeID());
        }

        if (protocolServerMessage.hasDataRequest() && protocolServerMessage.hasRID()) {
            handleGetDataMessage(
                    ctx,
                    protocolServerMessage.getDataRequest(),
                    protocolServerMessage.getRID()
            );
        }

        if (protocolServerMessage.hasLogSliceRequest() && protocolServerMessage.hasRID()) {
            //handle obtaining a log slice
            handleGetLogSlice(
                    ctx,
                    protocolServerMessage.getLogSliceRequest(),
                    protocolServerMessage.getRID()
            );
        }

        //System.out.println("Done Reading!!!");
    }

    /*-------------------------------------------
     *
     * Handlers
     *
     ---------------------------------------------*/

    private void handleGetDataMessage(ChannelHandlerContext ctx, Protocol.GetData msg, long rid) {
        Retroscope<K, V> retroscope = client.getRetroscope();
        Protocol.RetroNodeMsg.Builder dataMsgBuilder = Protocol.RetroNodeMsg.newBuilder();
        dataMsgBuilder.setRID(rid); // return rid back
        dataMsgBuilder.setNodeId(client.getId());
        if (!msg.hasHlcTime()) {
            if (msg.getKeysCount() == 0) {
                try {
                    RetroMap<K, V> data = retroscope.getAllData(msg.getLogName());
                    dataMsgBuilder.setData(ProtocolHelpers.retroMapToProtocol(data, msg.getLogName(), 0));
                } catch (RetroscopeException re) {
                    dataMsgBuilder.setErrorCode(1);
                }
            } else {
                try {
                    ByteString keys[] = new ByteString[msg.getKeysCount()];
                    keys = msg.getKeysList().toArray(keys);
                    List<K> keyList = new ArrayList<>(msg.getKeysCount());
                    for (int i = 0; i < msg.getKeysCount(); i++) {
                        keyList.add(ProtocolHelpers.byteStringToSerializable(keys[i]));
                    }
                    RetroMap<K, V> data = retroscope.getItemsMap(
                            msg.getLogName(),
                            keyList //this is ugly und pretty much restricts K to be string types
                    );
                    dataMsgBuilder.setData(ProtocolHelpers.retroMapToProtocol(data, msg.getLogName(), 0));
                } catch (RetroscopeException re) {
                    dataMsgBuilder.setErrorCode(re.getType());
                }

            }
        } else {
            if (msg.getKeysCount() == 0) {
                try {
                    RetroMap<K, V> data
                            = retroscope.getAllData(msg.getLogName(), msg.getHlcTime());
                    dataMsgBuilder.setData(ProtocolHelpers.retroMapToProtocol(data, msg.getLogName(), msg.getHlcTime() ));
                } catch (RetroscopeException re) {
                    dataMsgBuilder.setErrorCode(re.getType());
                }
            } else {
                try {
                    ByteString keys[] = new ByteString[msg.getKeysCount()];
                    keys = msg.getKeysList().toArray(keys);
                    List<K> keyList = new ArrayList<>(msg.getKeysCount());
                    for (int i = 0; i < msg.getKeysCount(); i++) {
                        keyList.add(ProtocolHelpers.byteStringToSerializable(keys[i]));
                    }
                    RetroMap<K, V> data = retroscope.getItemsMap(
                            msg.getLogName(),
                            keyList,
                            msg.getHlcTime()
                    );
                    dataMsgBuilder.setData(ProtocolHelpers.retroMapToProtocol(data, msg.getLogName(), msg.getHlcTime()));
                } catch (RetroscopeException re) {
                    dataMsgBuilder.setErrorCode(re.getType());
                }
            }
        }
        ctx.writeAndFlush(dataMsgBuilder.build());
    }

    public void handleGetLogSlice(ChannelHandlerContext ctx, Protocol.GetLog msg, long rid) {
        Retroscope<K, V> retroscope = client.getRetroscope();
        Protocol.RetroNodeMsg.Builder logMsgBuilder = Protocol.RetroNodeMsg.newBuilder();
        logMsgBuilder.setRID(rid); // return rid back
        logMsgBuilder.setNodeId(client.getId());
        List<K> keyList = null;
        if (msg.getParameterNamesCount() > 0) {
            ByteString keys[] = new ByteString[msg.getParameterNamesCount()];
            keys = msg.getParameterNamesList().toArray(keys);
            keyList = new ArrayList<>(msg.getParameterNamesCount());
            for (int i = 0; i < msg.getParameterNamesCount(); i++) {
                keyList.add(ProtocolHelpers.byteStringToSerializable(keys[i]));
            }
        }

        try {
            Log<K, V> slice = null;
            if (msg.hasHLCendTime() && msg.hasHLCstartTime()) {
                if (keyList != null) {
                    slice = retroscope.getLogSlice(msg.getLogName(), keyList, msg.getHLCstartTime(), msg.getHLCendTime());
                } else {
                    slice = retroscope.getLogSlice(msg.getLogName(), msg.getHLCstartTime(), msg.getHLCendTime());
                }
            } else {
                if (keyList != null) {
                    slice = retroscope.getLogSlice(msg.getLogName(), keyList);
                } else {
                    slice = retroscope.getLog(msg.getLogName());
                }
            }
            logMsgBuilder.setLog(slice.toProtocol());
        } catch (RetroscopeException re) {
            logMsgBuilder.setErrorCode(re.getType());
        }
        ctx.writeAndFlush(logMsgBuilder.build());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }


}
