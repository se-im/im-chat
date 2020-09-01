package com.im.chat.netty;

import com.google.common.collect.Maps;
import com.im.sync.netty.ConnectorManager;
import com.im.user.entity.po.User;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.utils.ConcurrentHashSet;
import org.assertj.core.util.Sets;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


@Component("connectorManager")
@Slf4j
public class WsServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> implements ConnectorManager
{

    /**
     * 用于记录和管理所有客户端的channel
     */
    private ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private ConcurrentHashMap<ChannelHandlerContext, User> contextUserMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Long, ChannelHandlerContext> userIdContextMap = new ConcurrentHashMap<>();
    private Set<ChannelHandlerContext>  handlerContextSet = new ConcurrentHashSet<>();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        // 获取客户端传输来的文本消息
        String text = msg.text();
        // 这个是自定义的日志工具类，可见其它文章
        log.info("收到的文本消息：[{}]", text);


        // 写回客户端，这里是广播
        //clients.writeAndFlush(new TextWebSocketFrame("服务器收到消息: " + text));
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

}
