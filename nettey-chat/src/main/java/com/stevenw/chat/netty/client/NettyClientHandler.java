package com.stevenw.chat.netty.client;

import com.stevenw.chat.protocol.message.HeartbeatResponsePacket;
import com.stevenw.chat.protocol.protobuf.MessageBase;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyClientHandler extends SimpleChannelInboundHandler<MessageBase.Message> {

    private static final Logger logger = LoggerFactory.getLogger(NettyClientHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageBase.Message msg) throws Exception {

        if(msg.getCmd().equals(MessageBase.Message.CommandType.HEARTBEAT_REQUEST)){
            logger.info("客户端收到的心跳消息：{}", msg.toString());
        }else if(msg.getCmd().equals(MessageBase.Message.CommandType.HEARTBEAT_RESPONSE)){
            logger.info("客户端收到的心跳消息：{}", msg.toString());
        }else if(msg.getCmd().equals(MessageBase.Message.CommandType.NORMAL)){
            logger.info("客户端收到消息：{}",msg.toString());
        }
    }

    /**
     * 处理异常, 一般将实现异常处理逻辑的Handler放在ChannelPipeline的最后
     * 这样确保所有入站消息都总是被处理，无论它们发生在什么位置，下面只是简单的关闭Channel并打印异常信息
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
