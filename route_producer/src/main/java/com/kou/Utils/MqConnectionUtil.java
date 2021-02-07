package com.kou.Utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

import java.util.concurrent.TimeoutException;

/**
 * @author JIAJUN KOU
 */
public class MqConnectionUtil {

    /**
     * 连接rabbitmq
     * @return
     */
    public static Connection getConnection() throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory factory=new ConnectionFactory();
        //在工厂中设置连接信息（ip,port,virtualHost,username,password）
        factory.setHost("47.108.198.18");
        factory.setPort(5672);
        factory.setVirtualHost("host1");
        factory.setUsername("yeb");
        factory.setPassword("admin123");
        //通过工厂对象获取与MQ的连接
        Connection connection = factory.newConnection();
        return connection;
    }

    public static void main(String[] args) throws IOException, TimeoutException {
        System.out.println(getConnection());
    }
}
