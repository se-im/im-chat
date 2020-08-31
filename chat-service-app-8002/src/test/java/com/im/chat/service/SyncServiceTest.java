package com.im.chat.service;

import com.im.sync.service.SyncIdService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SyncServiceTest
{


    @Autowired
    private SyncIdService syncService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Test
    public void testRedis()
    {
        System.out.println(redisTemplate.hasKey("22"));
    }

    @Test
    public void test001()
    {

        System.out.println(syncService.getNextSyncId(1L, 1L));
    }
}