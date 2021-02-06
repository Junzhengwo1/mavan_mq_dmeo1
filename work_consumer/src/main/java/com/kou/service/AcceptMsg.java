package com.kou.service;

import com.kou.Utils.MqConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author JIAJUN KOU
 */
public class AcceptMsg {

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = MqConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //消费者消费消息
        Consumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //body就是从队列中获取得数据
                String msg=new String(body);
                System.out.println("接受1："+msg);
            }
        };
        channel.basicConsume("queue2",true,consumer);
    }
}
