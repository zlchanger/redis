package com.example.redis.controller;

import com.example.redis.queue.RedisMessagePublisher;
import com.example.redis.queue.RedisMessageSubscriber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

/**
 * @Auther: changzhaoliang
 * @Date: 2018/8/24 14:53
 * @Description:
 */
@Slf4j
@Controller
public class testController {

    @Autowired
    private RedisMessagePublisher redisMessagePublisher;

    //redis 发布者/订阅者模式 测试
    @ResponseBody
    @RequestMapping("/testPS")
    public void testPS(){
        String message = "Message " + UUID.randomUUID();
        redisMessagePublisher.publish(message);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(RedisMessageSubscriber.messageList.get(0).contains(message)){
            log.info("true");
        }else {
            log.info("false");
        }
    }
}
