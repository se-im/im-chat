package com.im.chat.executor.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("commandBus")
public class DefaultCommandBus implements CommandBus
{


    @Autowired
    private CommandExecutorFactory commandExecutorFactory;

    @Override
    public void send(Command cmd) {
        commandExecutorFactory.getCommandExecutor(cmd).executor(cmd);
    }

    @Override
    public Object sendWithResult(Command cmd) {
        return commandExecutorFactory.getCommandExecutor(cmd).executeWithResult(cmd);
    }
}
