package com.im.chat.netty;

import com.im.sync.netty.ConnectorManager;
import com.im.user.entity.po.User;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.Data;
import org.apache.dubbo.common.utils.ConcurrentHashSet;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component("connectorManager")
@Data
public class OnlineConnectorManager implements ConnectorManager
{


    private ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private ConcurrentHashMap<ChannelHandlerContext, User> contextUserMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Long, ChannelHandlerContext> userIdContextMap = new ConcurrentHashMap<>();
    private Set<ChannelHandlerContext> handlerContextSet = new ConcurrentHashSet<>();

    @Override
    public Boolean isOnline(Long userId)
    {
        return userIdContextMap.containsKey(userId);
    }



    @Override
    public void pushToClient(String msg, Long userId)
    {
        ChannelHandlerContext channelHandlerContext = userIdContextMap.get(userId);
        channelHandlerContext.channel().writeAndFlush(new TextWebSocketFrame(msg));
    }


    public List<User> getAllOnlineUser()
    {
        List<User> collect = contextUserMap.values().stream().collect(Collectors.toList());
        return collect;
    }

    public List<String> getAllActiveConnect()
    {
        List<String> collect = handlerContextSet.stream().map(e -> e.channel().id().asShortText()).collect(Collectors.toList());
        return collect;
    }
}
