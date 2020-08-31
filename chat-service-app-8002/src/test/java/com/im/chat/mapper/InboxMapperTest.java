package com.im.chat.mapper;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class InboxMapperTest
{

    @Resource
    private InboxMapper inboxMapper;

    @Test
    public void getLargestSyncId()
    {
        System.out.println(inboxMapper.getLargestSyncId(4l,7l));
    }
}