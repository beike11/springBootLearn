package com.stevenw.chat.controller;

import com.stevenw.chat.netty.client.NettyClient;
import com.stevenw.chat.protocol.protobuf.MessageBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
public class ConsumerController {

    @Autowired
    private NettyClient nettyClient;

    @GetMapping("/send")
    @ResponseBody
    public String send(){
        MessageBase.Message message = MessageBase.Message.newBuilder()
                .setCmd(MessageBase.Message.CommandType.NORMAL)
                .setContent("hello")
                .setRequestId(UUID.randomUUID().toString()).build();
        nettyClient.sendMsg(message);
        return "send ok";
    }
}
