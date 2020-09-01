package com.im.chat.executor.common;

import com.im.dispatcher.command.SingleChatCommand;
import com.im.dispatcher.common.CommandBus;
import com.mr.response.error.BusinessException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DefaultCommandBusTest
{
    @Autowired
    private CommandBus commandBus;



    @Test
    public void testSendCommand() throws BusinessException {
        SingleChatCommand chatCommand = new SingleChatCommand();

        commandBus.send(chatCommand);
    }

}