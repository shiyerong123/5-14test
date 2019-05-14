package com.jk.controller;

import com.jk.model.Book;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendController {
    @Autowired
    private AmqpTemplate rabbitTemplate;
    @RequestMapping("send001")
    public String send001(String routingKey){
        Book book = new Book();
        try {
            //rabbitTemplate.convertAndSend("jk001", msg);
             //rabbitTemplate.convertAndSend("fanout_abc", "", msg);
            //rabbitTemplate.convertAndSend("fanout_abc", "", book);
            rabbitTemplate.convertAndSend("topic_abc", routingKey, "hello world");
        }catch (AmqpException e){
           e.printStackTrace();
           return "error";
        }
        return "success";
    }
}
