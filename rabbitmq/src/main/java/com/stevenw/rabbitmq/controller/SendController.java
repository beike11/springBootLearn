package com.stevenw.rabbitmq.controller;

import com.stevenw.rabbitmq.config.RabbitMQconfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

/**
 * @author stevenw
 * @date 2019/10/23
 */
@Controller
public class SendController {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @RequestMapping("/mq/sendMsg")
    @ResponseBody
    public void sendMsg(String msg){
        if(!Objects.isNull(msg)){
            rabbitTemplate.convertAndSend(RabbitMQconfig.QUEUE, msg);
            System.err.println(22222);
        }
    }
    @RequestMapping("/mq/getMsg")
    @ResponseBody
    public String getMsg(){
      return  rabbitTemplate.receiveAndConvert(RabbitMQconfig.QUEUE).toString();
    }
}
