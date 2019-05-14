package com.jk.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitController2 {

    //创建两个消息队列

    @Bean
    public Queue AMessage() {
        return new Queue("user.news");
    }

    @Bean
    public Queue BMessage() {
        return new Queue("user.weather");
    }

    @Bean
    public Queue CMessage() {
        return new Queue("jk.weather");
    }

    //创建交换机
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("topic_abc");//fanout_abc 交换机名字
    }


    @Bean
    Binding bindingExchangeMessage(Queue AMessage, TopicExchange exchange) {
        return BindingBuilder.bind(AMessage).to(exchange).with("user.#");
    }

    @Bean
    Binding bindingExchangeMessages1(Queue BMessage, TopicExchange exchange) {
        return BindingBuilder.bind(BMessage).to(exchange).with("#.news");
    }

    @Bean
    Binding bindingExchangeMessages2(Queue CMessage, TopicExchange exchange) {
        return BindingBuilder.bind(CMessage).to(exchange).with("#.weather");
    }



}
