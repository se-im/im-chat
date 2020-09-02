package com.im.chat.netty.base;

import com.im.chat.netty.OnlineConnectorManager;
import com.im.chat.netty.WsServerHandler;
import com.im.dispatcher.common.CommandBus;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WsChannelInitializer extends ChannelInitializer
{
    @Autowired
    private OnlineConnectorManager onlineConnectorManager;

    @Autowired
    private CommandBus commandBus;
    @Override
    protected void initChannel(Channel ch) {
        ChannelPipeline pipeline = ch.pipeline();
        // websocket是基于http协议的，所以需要使用http编解码器
        pipeline.addLast(new HttpServerCodec());
        // 对写大数据流的支持
        pipeline.addLast(new ChunkedWriteHandler());
        // 对http消息的聚合，聚合成FullHttpRequest或FullHttpResponse
        pipeline.addLast(new HttpObjectAggregator(1024 * 64));
        // 以上三个处理器是对http协议的支持
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        // 自定义的处理器
        pipeline.addLast(new WsServerHandler(commandBus, onlineConnectorManager));
    }
}
