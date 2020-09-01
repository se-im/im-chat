package com.im.chat.netty;

import com.alibaba.fastjson.JSON;
import com.im.chat.entity.domain.Packet;
import com.im.chat.enums.PacketTypeEnum;
import com.im.dispatcher.command.LoginCommand;
import com.im.dispatcher.common.CommandBus;
import com.im.sync.netty.ConnectorManager;
import com.im.user.entity.po.User;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.utils.ConcurrentHashSet;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


@Component("connectorManager")
@Slf4j
@ChannelHandler.Sharable
public class WsServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> implements ConnectorManager
{


    /**
     * 用于记录和管理所有客户端的channel
     */
    private ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private ConcurrentHashMap<ChannelHandlerContext, User> contextUserMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Long, ChannelHandlerContext> userIdContextMap = new ConcurrentHashMap<>();
    private Set<ChannelHandlerContext>  handlerContextSet = new ConcurrentHashSet<>();


    @Autowired
    private CommandBus commandBus;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        // 获取客户端传输来的文本消息

        String text = msg.text();
        log.info("收到的文本消息：[{}]", text);

        Packet packet = JSON.parseObject(text, Packet.class);
        PacketTypeEnum packetType = PacketTypeEnum.codeOf(packet.getBody().getPacketType());
        switch (packetType){
            case LOGIN: {
                LoginCommand command = new LoginCommand();
                command.setToken(packet.getBody().getContent().toString());
                Object o = commandBus.sendWithResult(command);
                if(o == null){
                    ctx.close();
                }
                User user = ((User) o);
                log.info("用户{}已登录", user);
                contextUserMap.put(ctx, user);
                userIdContextMap.put(user.getId(),ctx);
                break;
            }
        }

    }


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        clients.add(ctx.channel());
        handlerContextSet.add(ctx);
        log.info("客户端建立连接，channel的短ID：[{}]", ctx.channel().id().asShortText());
    }

    /**
     * 当触发当前方法时，ChannelGroup会自动移除对应客户端的channel
     * @param ctx ChannelHandlerContext
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        log.info("客户端断开连接，channel的短ID：[{}]", ctx.channel().id().asShortText());
        User user = contextUserMap.get(ctx);
        if(user == null){
            log.info("用户连接丢失, channel的短ID: {}", ctx.channel().id().asLongText() );
            return;
        }

        contextUserMap.remove(ctx);
        userIdContextMap.remove(user.getId());
        handlerContextSet.remove(ctx);
    }

    @Override
    public Boolean isOnline(Long userId)
    {
        return userIdContextMap.containsKey(userId);
    }


    @Override
    public void pushToClient(String msg, Long userId)
    {
        ChannelHandlerContext channelHandlerContext = userIdContextMap.get(userId);
        channelHandlerContext.writeAndFlush(msg);
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
