package com.im.sync.service;

import com.im.chat.mapper.InboxMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class SyncIdService
{

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private InboxMapper inboxMapper;


    private ReentrantLock lock = new ReentrantLock();

    public Long getNextSyncId(Long userId, Long cvsId)
    {
        String key = genRedisKey(userId, cvsId);

        while (true)
        {
            if(redisTemplate.hasKey(key))
            {
                return redisTemplate.opsForValue().increment(key);
            }else
            {

                try
                {
                    lock.lock();
                    if(redisTemplate.hasKey(key))
                    {
                        continue;
                    }


                    Long syncId = inboxMapper.getLargestSyncId(userId, cvsId);
                    if(syncId == null)
                    {
                        return 1L;
                    }else
                    {
                        redisTemplate.opsForValue().set(key, syncId + 1);
                        return syncId+1;
                    }


                }finally
                {
                    lock.unlock();
                }

            }
        }

    }


    private String genRedisKey(Long userId, Long cvsId)
    {
        return userId + "-" + cvsId;
    }
}
