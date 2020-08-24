package com.im.chat.service;

import com.im.user.service.IUserService;
import com.mr.response.error.BusinessException;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SessionViewServiceImplTest {
    @Reference
    private IUserService iUserService;

    @Test
    public void test01() throws BusinessException {
        System.out.println(iUserService.login("tom", "1"));
    }

}