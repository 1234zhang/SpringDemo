/*
package com.example.demo;

import com.example.demo.config.RedisConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RedisConfig.class})

public class RedisTest {
    @Autowired
    public RedisTemplate<String,String> redisTemplate;

    @Test
    public void test(){
        Map<String,String> testMap = new HashMap<>();
        testMap.put("123","hello");
        testMap.put("abc","test");

        redisTemplate.opsForHash().putAll("hash",testMap);
        Map<Object,Object> ans = redisTemplate.opsForHash().entries("hash");
        System.out.println("ans" + ans);
    }

}
*/
