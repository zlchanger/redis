package com.example.redis.counter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Auther: changzhaoliang
 * @Date: 2018/8/27 20:17
 * @Description:
 *
 * Redis的散列可以让用户将多个键值对存储到一个Redis键里面。
 */
@Component
public class RedisUtilForHash {

    @Autowired
    private RedisTemplate redisTemplate;

    // 删除给定的哈希hashKeys
    // Long delete(H key, Object... hashKeys);

    // 确定哈希hashKey是否存在
    // Boolean hasKey(H key, Object hashKey);

    // 从键中的哈希获取给定hashKey的值
    // HV get(H key, Object hashKey);

    // 从哈希中获取给定hashKey的值
    // List<HV> multiGet(H key, Collection<HK> hashKeys);

    // 通过给定的delta增加散列hashKey的值（整型/浮点）
     Long increment(String key, String hashKey, long delta){
         return redisTemplate.opsForHash().increment(key,hashKey,delta);
     }

    // 获取key所对应的散列表的key
    // Set<HK> keys(H key);

    // 获取key所对应的散列表的大小个数
    // Long size(H key);

    // 使用m中提供的多个散列字段设置到key对应的散列表中
    // void putAll(H key, Map<? extends HK, ? extends HV> m);

    // 设置散列hashKey的值
    // void put(H key, HK hashKey, HV value);

    // 仅当hashKey不存在时才设置散列hashKey的值。
    // Boolean putIfAbsent(H key, HK hashKey, HV value);

    // 获取整个哈希存储的值根据密钥([tom, 26, 6])
    // List<HV> values(H key);

    // 获取整个哈希存储根据密钥({age=26, class=6, name=tom})
    // Map<HK, HV> entries(H key);

    // 使用Cursor在key的hash中迭代，相当于迭代器。
    // Cursor<Map.Entry<HK, HV>> scan(H key, ScanOptions options);

//    Cursor<Map.Entry<Object, Object>> curosr = template.opsForHash().scan("redisHash", ScanOptions.ScanOptions.NONE);
//        while(curosr.hasNext()){
//        Map.Entry<Object, Object> entry = curosr.next();
//        System.out.println(entry.getKey()+":"+entry.getValue());
//    }
}
