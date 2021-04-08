package com.stevenw.chat.protocol.message.command;

public interface Command {
    Byte HEARTBEAT_REQUEST = 1;
    Byte HEARTBEAT_RESPONSE = 2;
}
