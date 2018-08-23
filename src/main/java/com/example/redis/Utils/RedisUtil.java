package com.example.redis.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: changzhaoliang
 * @Date: 2018/8/23 17:02
 * @Description:
 */
@Service
public class RedisUtil {
    @Autowired
    private RedisTemplate redisTemplate;

    public Long addRedisSet(String redisKey, Object value) {
        Long result = redisTemplate.opsForSet().add(redisKey, value);
        return result;
    }

    public void leftPush(String key, String value) {
        redisTemplate.opsForList().leftPush(key, value);
        //getRedisTemplate().opsForList().leftPop(key);
    }
    public String rightPop(String key,long timeout,TimeUnit unit) {
        Object obj =  redisTemplate.opsForList().rightPop(key, timeout, unit);
        String str =  (String) obj;
        return str;
    }
    public Object rightPopAndLeftPush(String sourceKey, String destinationKey) {
        Object value = redisTemplate.opsForList().rightPopAndLeftPush(sourceKey, destinationKey);
        return value;
    }
}
