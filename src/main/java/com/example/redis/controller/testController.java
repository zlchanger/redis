package com.example.redis.controller;

import com.example.redis.produceAndconsume.CThread;
import com.example.redis.produceAndconsume.PThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: changzhaoliang
 * @Date: 2018/8/23 18:29
 * @Description:
 */
@Controller
public class testController {
    @Autowired
    private CThread cThread;
    @Autowired
    private PThread pThread;

    @ResponseBody
    @RequestMapping("test")
    void testInjection(){
        // 通过注入的Bean启动线程
        pThread.execute();
        cThread.execute();
    }

}
