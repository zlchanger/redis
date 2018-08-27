package com.example.redis.performanceRank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @Auther: changzhaoliang
 * @Date: 2018/8/27 19:15
 * @Description:
 *
 * Redis 有序集合和无序集合一样也是string类型元素的集合,且不允许重复的成员。
 * 不同的是每个元素都会关联一个double类型的分数。redis正是通过分数来为集合中的成员进行从小到大的排序。
 * 有序集合的成员是唯一的,但分数(score)却可以重复。
 */
@Component
public class RedisUtilForZset<T> {

    @Autowired
    private RedisTemplate redisTemplate;

    //新增一个有序集合，存在的话为false，不存在的话为true
    public Boolean add(String key, T value, double score){
        return redisTemplate.opsForZSet().add(key,value,score);
    }

    //新增一个有序集合
    // ZSetOperations.TypedTuple<Object> objectTypedTuple2 = new DefaultTypedTuple<Object>("zset-6",9.9);
    // Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<ZSetOperations.TypedTuple<Object>>();
    // tuples.add(objectTypedTuple1);
    public Long add(String key, Set<ZSetOperations.TypedTuple<T>> tuples){
        return redisTemplate.opsForZSet().add(key,tuples);
    }

    //...


    //  从有序集合中移除一个或者多个元素
    //  Long remove(K key, Object... values);

    //  增加元素的score值，并返回增加后的值
    //  Double incrementScore(K key, V value, double delta);

    //  返回有序集中指定成员的排名，其中有序集成员按分数值递增(从小到大)顺序排列
    //  Long rank(K key, Object o);

    //  返回有序集中指定成员的排名，其中有序集成员按分数值递减(从大到小)顺序排列
    //  Long reverseRank(K key, Object o);

    //  通过索引区间返回有序集合成指定区间内的成员，其中有序集成员按分数值递增(从小到大)顺序排列
    //  Set<V> range(K key, long start, long end);

    //  通过索引区间返回有序集合成指定区间内的成员对象，其中有序集成员按分数值递增(从小到大)顺序排列
    //  Set<TypedTuple<V>> rangeWithScores(K key, long start, long end);

    //  通过分数返回有序集合指定区间内的成员，其中有序集成员按分数值递增(从小到大)顺序排列
    //  Set<V> rangeByScore(K key, double min, double max);

    //  通过分数返回有序集合指定区间内的成员对象，其中有序集成员按分数值递增(从小到大)顺序排列
    //  Set<TypedTuple<V>> rangeByScoreWithScores(K key, double min, double max);

    //  通过分数返回有序集合指定区间内的成员，并在索引范围内，其中有序集成员按分数值递增(从小到大)顺序排列
    //  Set<V> rangeByScore(K key, double min, double max, long offset, long count);

    //  通过分数返回有序集合指定区间内的成员对象，并在索引范围内，其中有序集成员按分数值递增(从小到大)顺序排列
    //  Set<TypedTuple<V>> rangeByScoreWithScores(K key, double min, double max, long offset, long count);

    //  通过索引区间返回有序集合成指定区间内的成员，其中有序集成员按分数值递减(从大到小)顺序排列
    //  Set<V> reverseRange(K key, long start, long end);

    //  通过索引区间返回有序集合成指定区间内的成员对象，其中有序集成员按分数值递减(从大到小)顺序排列
      Set<ZSetOperations.TypedTuple<T>> reverseRangeWithScores(String key, long start, long end){
        return reverseRangeWithScores(key,start,end);
      }

    //  Set<V> reverseRangeByScore(K key, double min, double max);

    //  Set<TypedTuple<V>> reverseRangeByScoreWithScores(K key, double min, double max);

    //  Set<V> reverseRangeByScore(K key, double min, double max, long offset, long count);

    //  Set<TypedTuple<V>> reverseRangeByScoreWithScores(K key, double min, double max, long offset, long count);

    //  通过分数返回有序集合指定区间内的成员个数
    //  Long count(K key, double min, double max);

    //  获取有序集合的成员数，内部调用的就是zCard方法
    //  Long size(K key);

    //  获取有序集合的成员数
    //  Long zCard(K key);

    //  获取指定成员的score值
    //  Double score(K key, Object o);

    //  移除指定索引位置的成员，其中有序集成员按分数值递增(从小到大)顺序排列
    //  Long removeRange(K key, long start, long end);

    //  根据指定的score值得范围来移除成员
    //  Long removeRangeByScore(K key, double min, double max);

    //  计算给定的一个有序集的并集，并存储在新的 destKey中，key相同的话会把score值相加
    //  Long unionAndStore(K key, K otherKey, K destKey);

    //  计算给定的多个有序集的并集，并存储在新的 destKey中
    //  Long unionAndStore(K key, Collection<K> otherKeys, K destKey);

    //  计算给定的一个或多个有序集的交集并将结果集存储在新的有序集合 key 中
    //  Long intersectAndStore(K key, K otherKey, K destKey);

    //  计算给定的一个或多个有序集的交集并将结果集存储在新的有序集合 key 中
    //  Long intersectAndStore(K key, Collection<K> otherKeys, K destKey);

    //  遍历zset
    //  Cursor<TypedTuple<V>> scan(K key, ScanOptions options);

//    Cursor<ZSetOperations.TypedTuple<Object>> cursor = template.opsForZSet().scan("zzset1", ScanOptions.NONE);
//        while (cursor.hasNext()){
//        ZSetOperations.TypedTuple<Object> item = cursor.next();
//        System.out.println(item.getValue() + ":" + item.getScore());
//    }
}
