package com.example.redis.counter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: changzhaoliang
 * @Date: 2018/8/27 19:50
 * @Description:计数器
 */
@Service
public class counterService {

    @Autowired
    private RedisUtilForValue redisUtilForValue;
    @Autowired
    private RedisUtilForHash redisUtilForHash;

    //1.注册用户
    //系统初始化是计算当前时间到次日的时间，将REGISTERED_COUNT_TODAY的有效期设为该值
    public void register(){
        //TODO 注册

        //每天注册成功人数加一
        redisUtilForValue.increment("REGISTERED_COUNT_TODAY",1);
    }

    //2.微博点赞数
    //微博发表时初始化微博的点赞数
    public void greatForWeibo(){
        String id = "1";//微博ID
        redisUtilForHash.increment("key:"+id, "like_number",1);
    }

}
