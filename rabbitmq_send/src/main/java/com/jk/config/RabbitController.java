package com.jk.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitController {
   /* @Bean
    public Queue queue(){
        //新建一个队列 并把他交给spring容器来进行管理
        return new Queue("jk001");
    }*/
    //创建三个消息队列

    @Bean
    public Queue AMessage() {
        return new Queue("A");
    }

    @Bean
    public Queue BMessage() {
        return new Queue("B");
    }

    @Bean
    public Queue CMessage() {
        return new Queue("C");
    }

    //创建交换机
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanout_abc");//fanout_abc 交换机名字
    }

    //绑定消息队列到交换机上
    @Bean
    Binding bindingExchangeA(Queue AMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(AMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(Queue BMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(BMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeC(Queue CMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(CMessage).to(fanoutExchange);
    }

}
