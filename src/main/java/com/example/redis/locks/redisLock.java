package com.example.redis.locks;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: changzhaoliang
 * @Date: 2018/8/28 21:37
 * @Description:
 */
@Service
public class redisLock {
    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate redisTemplate;
    /**
     * setNx
     *
     * @param key
     * @param value
     * @return
     */
    public Boolean setNx(String key, String value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }
    /**
     * @param key        锁
     * @param waitTime   等待时间 毫秒
     * @param expireTime 超时时间  毫秒
     * @return
     */
    public Boolean lock(String key, Long waitTime, Long expireTime) {
        String vlaue = UUID.randomUUID().toString().replaceAll("-", "");
        Boolean flag = setNx(key, vlaue);
        //尝试获取锁 成功返回
        if (flag) {
            redisTemplate.expire(key, expireTime, TimeUnit.MILLISECONDS);
            return flag;
        } else {
            //失败
            //现在时间
            long newTime = System.currentTimeMillis();
            //等待过期时间
            long loseTime = newTime + waitTime;
            //不断尝试获取锁成功返回
            while (System.currentTimeMillis() < loseTime) {
                Boolean testFlag = setNx(key, vlaue);
                if (testFlag) {
                    redisTemplate.expire(key, expireTime, TimeUnit.MILLISECONDS);
                    return testFlag;
                }
                //休眠100毫秒
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
    /**
     * @param key
     * @return
     */
    public Boolean lock(String key) {
        return lock(key, 1000L, 60 * 1000L);
    }
    /**
     * @param key
     */
    public void unLock(String key) {
        redisTemplate.delete(key);
    }
}
