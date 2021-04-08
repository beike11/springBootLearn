package com.stevenw.rabbitmq.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stevenw.rabbitmq.send.MQsender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author stevenw
 * @date 2019/10/28
 */
@Controller
public class IndexController {
    @GetMapping("/getIndex")
    public String indexPage(){
        System.err.println(123);
        return "index";
    }
    public static void main(String[] args) throws JsonProcessingException {
        MQsender mQsender = new MQsender();
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(mQsender));
    }
}
