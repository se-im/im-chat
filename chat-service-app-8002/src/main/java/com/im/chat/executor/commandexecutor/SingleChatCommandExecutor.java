package com.im.chat.executor.commandexecutor;

import com.im.chat.entity.po.Message;
import com.im.chat.executor.command.SingleChatCommand;
import com.im.chat.executor.common.CommandExecutor;
import org.springframework.stereotype.Component;


@Component
public class SingleChatCommandExecutor implements CommandExecutor<SingleChatCommand>
{
    @Override
    public void executor(SingleChatCommand cmd)
    {
        Message message = cmd.getMessage();

    }

    @Override
    public Object executeWithResult(SingleChatCommand cmd)
    {
        return null;
    }
}
