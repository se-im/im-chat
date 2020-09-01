package com.im.chat.netty;

import com.im.sync.netty.ConnectorManager;

public class OnlineConnectorManager implements ConnectorManager
{
    @Override
    public Boolean isOnline(Long userId)
    {
        return null;
    }

    @Override
    public void pushToClient(String msg, Long userId)
    {

    }
}
