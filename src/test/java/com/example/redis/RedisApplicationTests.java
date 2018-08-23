package com.example.redis;

import com.example.redis.pojo.User;
import com.example.redis.produceAndconsume.CThread;
import com.example.redis.produceAndconsume.PThread;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisApplicationTests {

	@Autowired
	private RedisTemplate redisTemplate;


	@Test
	public void contextLoads() {
//		ExecutorService executorService = Executors.newFixedThreadPool(1000);
//		IntStream.range(0,1000).forEach(i ->
//			executorService.execute(() -> redisTemplate.opsForValue().increment("kk",1))
//		);
//		redisTemplate.opsForValue().set("k1","v1");
//		final String k1 = (String) redisTemplate.opsForValue().get("k1");
//
//		String key = "battcn:user:1";
//		redisTemplate.opsForValue().set(key, new User(1L, "u1", "pa"));
//
//		final User user = (User) redisTemplate.opsForValue().get(key);
//		cThread.execute();
//		pThread.execute();
	}

}
