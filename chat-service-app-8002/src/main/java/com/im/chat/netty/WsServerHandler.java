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
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;




@Slf4j
@ChannelHandler.Sharable
public class WsServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame>
{


    private CommandBus commandBus;

    private OnlineConnectorManager onlineConnectorManager;

    public WsServerHandler(CommandBus commandBus, OnlineConnectorManager onlineConnectorManager)
    {
        this.commandBus = commandBus;
        this.onlineConnectorManager = onlineConnectorManager;
    }

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
                    log.info("非法用户的登录，断开连接");
                    ctx.close();
                    return;
                }
                User user = ((User) o);
                log.info("用户{}已登录", user);
                if(onlineConnectorManager.getUserIdContextMap().containsKey(user.getId())){
                    ctx.close();
                    break;
                }
                onlineConnectorManager.getContextUserMap().put(ctx, user);
                onlineConnectorManager.getUserIdContextMap().put(user.getId(),ctx);
                break;
            }
        }

    }


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        onlineConnectorManager.getHandlerContextSet().add(ctx);
        log.info("客户端建立连接，channel的短ID：[{}]", ctx.channel().id().asShortText());
    }

    /**
     * 当触发当前方法时，ChannelGroup会自动移除对应客户端的channel
     * @param ctx ChannelHandlerContext
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        log.info("客户端断开连接，channel的短ID：[{}]", ctx.channel().id().asShortText());
        User user = onlineConnectorManager.getContextUserMap().get(ctx);
        if(user != null){
            log.info("用户连接丢失, 用户为: {}", user );
            onlineConnectorManager.getContextUserMap().remove(ctx);
            onlineConnectorManager.getUserIdContextMap().remove(user.getId());
        }

        onlineConnectorManager.getHandlerContextSet().remove(ctx);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
    {
        log.info("出错啦");
        cause.printStackTrace();
    }
}
