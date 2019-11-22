package com.stevenw.rabbitmq.config;



import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author stevenw
 * @date 2019/10/23
 */
@Configuration
public class RabbitMQconfig {
    public static final String QUEUE = "queue";
    public static final String EXCHANGE1 = "exchange1";
    @Bean
    public Queue queue(){
        return new Queue(QUEUE,true);
    }

}
