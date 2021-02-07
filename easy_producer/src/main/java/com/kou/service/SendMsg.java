package com.kou.service;

import com.kou.Utils.MqConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author JIAJUN KOU
 *
 * 我的rabbit账号为yeb 密码admin123
 *
 * 实现功能发送消息到消息队列
 */
public class SendMsg {

    public static void main(String[] args) throws IOException, TimeoutException {
        String msg="hello mq2";
        //获取一个连接
        Connection connection = MqConnectionUtil.getConnection();
        //创建频道，相当于jdbc的statement
        Channel channel = connection.createChannel();
        //定义队列（就是用java代码创建队列）
        //参数一：定义队列的名称
        //参数二：队列中的数据是否持久化
        //参数三：是否排外（当前队列是否为当前连接私有）
        //参数四：自动删除（当前队列的连接数为0时，此队列会销毁）
        //参数五：设置队列的属性
        //一般不会这样定义
        //channel.queueDeclare("queue7",false,false,false,null);
        //发布消息(参数一：交换机名称，如果直接发送消息到队列，则交换机为"")
        //参数二：目标队列名称
        //参数三：设置当前消息的属性（设置过期时间等.....）
        //参数四：消息的内容
        channel.basicPublish("","TestQueues",null,msg.getBytes());
        System.out.println("send"+msg);
        channel.close();
        connection.close();
    }

}
