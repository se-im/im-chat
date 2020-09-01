package com.im.dispatcher.common;

import com.mr.response.error.BusinessException;

public interface CommandBus
{
    void send(Command command) throws BusinessException;

    Object sendWithResult(Command cmd) throws BusinessException;
}
