package com.stevenw.rabbitmq.web;


import com.stevenw.rabbitmq.RabbitmqApplicationTests;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


/**
 * @author stevenw
 * @date 2019/10/25
 */

@AutoConfigureMockMvc
public class TestMQ extends RabbitmqApplicationTests {

    @Autowired
    public MockMvc mockMvc;

    @Test
    public void sendMq() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/mq/sendMsg"));
        mockMvc.perform(MockMvcRequestBuilders.get("/mq/getMsg"));
    }
}
