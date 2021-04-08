package com.stevenw.chat.protocol.message;

public abstract class Packet {

    private Byte version = 1;

    public abstract Byte getCommond();
}
