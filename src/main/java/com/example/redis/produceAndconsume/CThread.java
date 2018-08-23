package com.example.redis.produceAndconsume;

import com.example.redis.Utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: changzhaoliang
 * @Date: 2018/8/23 18:12
 * @Description:
 */
@Component
@Slf4j
public class CThread {

    @Autowired
    RedisUtil redisUtil;

    public void execute(){
        new CThread.TaskConsumer().start();
    }

    private class TaskConsumer extends Thread{
        @Override
        public void run() {
            while (true) {
                try {
                    String taskid = (String) CThread.this.redisUtil.rightPopAndLeftPush("task-queue", "tmp-queue");//取出消息放到临时队列
                    // Thread.sleep(1000);
                    // RedisUtil.rightPop("tmp-queue");//非阻塞

                    // 阻塞式brpop，List中无数据时阻塞，参数0表示一直阻塞下去，直到List出现数据
                    String str = CThread.this.redisUtil.rightPop("tmp-queue", 0, TimeUnit.MINUTES);//阻塞，取出临时队列
                    log.info("线程取数据：{}", str);
                    log.info(str + "处理成功，被清除");

                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
         /*
         * if(random.nextInt(13) % 7 == 0){
         * RedisUtil.rightPopAndLeftPush("tmp-queue","task-queue");//弹回任务队列
         * logger.info(taskid+"处理失败，被弹回任务队列"); }else{
         * RedisUtil.rightPop("tmp-queue"); logger.info(taskid+"处理成功，被清除"); }
         */
        }
    }
}
