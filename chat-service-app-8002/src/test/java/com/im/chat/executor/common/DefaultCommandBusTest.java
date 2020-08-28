package com.im.chat.executor.common;

import com.im.chat.executor.command.SingleChatCommand;
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
    public void testSendCommand()
    {
        SingleChatCommand chatCommand = new SingleChatCommand();
        chatCommand.setContent("ddddd");

        commandBus.send(chatCommand);
    }

}