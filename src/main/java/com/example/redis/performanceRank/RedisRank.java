package com.example.redis.performanceRank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @Auther: changzhaoliang
 * @Date: 2018/8/27 19:13
 * @Description:redis排行榜 样例 sorted sort（zset）
 */
@Service
public class RedisRank {

    @Autowired
    private RedisUtilForZset redisUtilForZset;

    public void addPerformanceRank(Long studentId, double score){
        redisUtilForZset.add("performance:student",studentId,score);
    }

    public Set<ZSetOperations.TypedTuple<Long>> pageForStudentByScoreDesc(int pageNumber, int pageSize){
        int start = (pageNumber-1)*pageSize;
        int end = pageNumber*pageSize;
        Set<ZSetOperations.TypedTuple<Long>> set = redisUtilForZset.reverseRangeWithScores("performance:student",start,end);
        return set;
    }


}
