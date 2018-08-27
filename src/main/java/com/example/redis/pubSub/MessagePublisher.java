package com.example.redis.pubSub;

/**
 * @Auther: changzhaoliang
 * @Date: 2018/8/24 11:57
 * @Description:
 */
public interface MessagePublisher {

    void publish(final String message);
}
