package retroscope.net;

import com.google.protobuf.ByteString;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import retroscope.Retroscope;
import retroscope.RetroscopeException;
import retroscope.log.Log;
import retroscope.log.LogOutTimeBoundsException;
import retroscope.log.RetroMap;
import retroscope.net.protocol.Protocol;
import retroscope.net.protocol.ProtocolHelpers;

import java.io.*;

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
                    K[] keyArray = (K[]) new Serializable[msg.getKeysCount()];
                    for (int i = 0; i < msg.getKeysCount(); i++) {
                        keyArray[i] = ProtocolHelpers.byteStringToSerializable(keys[i]);
                    }
                    RetroMap<K, V> data = retroscope.getItemsMap(
                            msg.getLogName(),
                            keyArray //this is ugly und pretty much restricts K to be string types
                    );
                    dataMsgBuilder.setData(ProtocolHelpers.retroMapToProtocol(data, msg.getLogName(), 0));
                } catch (RetroscopeException re) {
                    dataMsgBuilder.setErrorCode(1);
                } catch (LogOutTimeBoundsException lotbe) {
                    dataMsgBuilder.setErrorCode(2);
                }

            }
        } else {
            if (msg.getKeysCount() == 0) {
                try {
                    RetroMap<K, V> data
                            = retroscope.getAllData(msg.getLogName(), msg.getHlcTime());
                    dataMsgBuilder.setData(ProtocolHelpers.retroMapToProtocol(data, msg.getLogName(), msg.getHlcTime() ));
                } catch (RetroscopeException re) {
                    dataMsgBuilder.setErrorCode(1);
                } catch (LogOutTimeBoundsException re) {
                    dataMsgBuilder.setErrorCode(2);
                }
            } else {
                try {
                    ByteString keys[] = new ByteString[msg.getKeysCount()];
                    keys = msg.getKeysList().toArray(keys);
                    K[] keyArray = (K[]) new Serializable[msg.getKeysCount()];
                    for (int i = 0; i < msg.getKeysCount(); i++) {
                        keyArray[i] = ProtocolHelpers.byteStringToSerializable(keys[i]);
                    }
                    RetroMap<K, V> data = retroscope.getItemsMap(
                            msg.getLogName(),
                            keyArray,
                            msg.getHlcTime()
                    );
                    dataMsgBuilder.setData(ProtocolHelpers.retroMapToProtocol(data, msg.getLogName(), msg.getHlcTime()));
                } catch (RetroscopeException re) {
                    dataMsgBuilder.setErrorCode(1);
                } catch (LogOutTimeBoundsException lotbe) {
                    dataMsgBuilder.setErrorCode(2);
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
        try {
            Log<K, V> slice = retroscope.getLogSlice(msg.getLogName(), msg.getHLCstartTime(), msg.getHLCendTime());
            logMsgBuilder.setLog(slice.toProtocol());
        } catch (RetroscopeException re) {
            logMsgBuilder.setErrorCode(1);
        } catch (LogOutTimeBoundsException lotbe) {
            logMsgBuilder.setErrorCode(2);
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
