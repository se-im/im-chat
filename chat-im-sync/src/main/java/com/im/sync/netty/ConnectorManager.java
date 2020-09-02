package com.im.sync.netty;


public interface ConnectorManager
{
    public Boolean isOnline(Long userId);

    public void pushToClient(String msg, Long userId);

}
