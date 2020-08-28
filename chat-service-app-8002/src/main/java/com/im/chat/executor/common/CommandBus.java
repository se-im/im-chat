package com.im.chat.executor.common;

public interface CommandBus
{
    void send(Command command);

    Object sendWithResult(Command cmd);
}
