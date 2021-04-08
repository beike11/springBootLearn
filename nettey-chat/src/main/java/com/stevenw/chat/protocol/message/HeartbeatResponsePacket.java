package com.stevenw.chat.protocol.message;

import com.stevenw.chat.protocol.message.command.Command;

public class HeartbeatResponsePacket extends Packet{
    @Override
    public Byte getCommond() {
        return Command.HEARTBEAT_RESPONSE;
    }
}
