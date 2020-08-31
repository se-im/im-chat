package com.im.sync.netty;

import org.springframework.stereotype.Component;

@Component("connectorManager")
public class FakeConnectorManager implements ConnectorManager
{
    @Override
    public Boolean isOnline(Long userId)
    {
        return false;
    }
}
