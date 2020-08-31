package com.im.dispatcher.common;

public interface CommandBus
{
    void send(Command command);

    Object sendWithResult(Command cmd);
}
