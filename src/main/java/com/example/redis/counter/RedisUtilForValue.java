package com.example.redis.counter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Auther: changzhaoliang
 * @Date: 2018/8/27 19:51
 * @Description:推荐直接使用 StringRedisTemplate
 */
@Component
public class RedisUtilForValue {

    @Autowired
    private StringRedisTemplate redisTemplate;

    // void set(K key, V value);

    // void set(K key, V value, long timeout, TimeUnit unit);

    // 该方法是用 value 参数覆写(overwrite)给定 key 所储存的字符串值，从偏移量 offset 开始
    // void set(K key, V value, long offset);

    //之前是否存在
    // Boolean setIfAbsent(K key, V value);

    // 为多个键分别设置它们的值
    // void multiSet(Map<? extends K, ? extends V> m);

    // 为多个键分别设置它们的值，如果存在则返回false，不存在返回true
    // Boolean multiSetIfAbsent(Map<? extends K, ? extends V> m);

     Object get(String key){
         return redisTemplate.opsForValue().get(key);
     }

    // 设置键的字符串值并返回其旧值
    // V getAndSet(K key, V value);

    // 为多个键分别取出它们的值
    // List<V> multiGet(Collection<K> keys);

    // increment支持整型，也支持浮点
    public Long increment(String key, long delta){
        return redisTemplate.opsForValue().increment(key,delta);
    }

    // 如果key已经存在并且是一个字符串，则该命令将该值追加到字符串的末尾。如果键不存在，则它被创建并设置为空字符串，因此APPEND在这种特殊情况下将类似于SET。
    // Integer append(K key, String value);

    // 截取key所对应的value字符串
    // String get(K key, long start, long end);

    // 返回key所对应的value值得长度
    // Long size(K key);

    // 对 key 所储存的字符串值，设置或清除指定偏移量上的位(bit)
    // key键对应的值value对应的ascii码,在offset的位置(从左向右数)变为value
    // Boolean setBit(K key, long offset, boolean value);

    // 获取键对应值的ascii码的在offset处位值
    // Boolean getBit(K key, long offset);

}
