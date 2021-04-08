package com.stevenw.chat.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * 服务器空闲检测
 */
public class ServerIdleStateHandler extends IdleStateHandler {
    Logger logger = LoggerFactory.getLogger(ServerIdleStateHandler.class);
    private static final int READER_IDLE_TIME = 30;

    public ServerIdleStateHandler() {
        super(READER_IDLE_TIME, 0, 0, TimeUnit.SECONDS);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        logger.info("{}秒没有读取数据，关闭连接", READER_IDLE_TIME);
        ctx.channel().close();
    }
}
