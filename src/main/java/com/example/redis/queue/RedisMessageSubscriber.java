package com.example.redis.queue;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: changzhaoliang
 * @Date: 2018/8/24 11:53
 * @Description: 订阅者
 */
@Service
public class RedisMessageSubscriber implements MessageListener{
    public static List<String> messageList = new ArrayList<String>();

    /**
     * @param message 消息
     * @param pattern 匹配通道的模式（如果已指定）
     */
    public void onMessage(final Message message, final byte[] pattern) {
        messageList.add(message.toString());
        System.out.println("Message received: " + new String(message.getBody()));
    }
}
