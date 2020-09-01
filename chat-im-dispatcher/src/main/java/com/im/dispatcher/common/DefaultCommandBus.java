package com.im.dispatcher.common;

import com.mr.response.error.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("commandBus")
public class DefaultCommandBus implements CommandBus
{


    @Autowired
    private CommandExecutorFactory commandExecutorFactory;

    @Override
    public void send(Command cmd) throws BusinessException {
        commandExecutorFactory.getCommandExecutor(cmd).executor(cmd);
    }

    @Override
    public Object sendWithResult(Command cmd) throws BusinessException {
        return commandExecutorFactory.getCommandExecutor(cmd).executeWithResult(cmd);
    }
}
