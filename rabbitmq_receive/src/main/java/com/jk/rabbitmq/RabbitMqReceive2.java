package com.jk.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component  //标识spring组件 把此类交给spring容器来进行管理
@RabbitListener(queues = "user.weather")//表明要监控哪个队列
public class RabbitMqReceive2 {
    @RabbitHandler
    public void process(String hello) {
        System.out.println("user.weather 接收到的消息 : " + hello);
    }



}
