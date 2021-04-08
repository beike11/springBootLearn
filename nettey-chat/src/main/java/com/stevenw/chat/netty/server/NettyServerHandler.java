package com.stevenw.chat.netty.server;

import com.stevenw.chat.protocol.message.HeartbeatResponsePacket;
import com.stevenw.chat.protocol.protobuf.MessageBase;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.plugin2.message.HeartbeatMessage;

/**
 * 服务器接收控制
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<MessageBase.Message> {
    private static final Logger logger = LoggerFactory.getLogger(NettyServerHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageBase.Message msg) throws Exception {
            if(msg.getCmd().equals(MessageBase.Message.CommandType.HEARTBEAT_REQUEST)){
                logger.info("心跳消息：{}", msg.toString());
                channelHandlerContext.writeAndFlush(MessageBase.Message.newBuilder().setCmd(MessageBase.Message.CommandType.HEARTBEAT_RESPONSE).build());
            }else if(msg.getCmd().equals(MessageBase.Message.CommandType.NORMAL)){
                logger.info("收到消息：{}",msg.toString());
            }
    }
}
