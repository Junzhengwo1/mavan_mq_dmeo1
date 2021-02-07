package com.kou.service;

import com.kou.Utils.MqConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * @author JIAJUN KOU
 *
 * 实现功能发送消息到消息队列
 */
public class SendMsg {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        System.out.println("请输入消息：");
        Scanner scanner=new Scanner(System.in);
        String msg=scanner.nextLine();
        //获取一个连接
        Connection connection = MqConnectionUtil.getConnection();
        //创建频道，相当于jdbc的statement
        Channel channel = connection.createChannel();

        //在消息发送之前开启消息确认
        channel.confirmSelect();

        channel.basicPublish("ex1","",null,msg.getBytes());
        System.out.println("send"+msg);
        //消息发送之后，确认是否成功
        boolean b = channel.waitForConfirms();
        System.out.println("发送"+(b?"成功":"失败"));

        channel.close();
        connection.close();
    }

}
