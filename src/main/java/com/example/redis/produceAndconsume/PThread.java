package com.example.redis.produceAndconsume;

import com.example.redis.Utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Auther: changzhaoliang
 * @Date: 2018/8/23 18:09
 * @Description:
 */
@Component
@Slf4j
public class PThread {

    @Autowired
    RedisUtil redisUtil;

    public void execute(){
        new TaskProducer().start();
    }

    private class TaskProducer extends Thread{

        @Override
        public void run() {
            try{
                for(int i=0;i<5;i++){
                    PThread.this.redisUtil.leftPush("task-queue", "value_" + i);
                    log.info("插入一个新任务:value_ ->{}",i);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
