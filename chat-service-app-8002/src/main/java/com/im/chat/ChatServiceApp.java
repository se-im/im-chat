package com.im.chat;

import com.im.chat.netty.base.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zhaomanzhou
 */


@SpringBootApplication
@EnableDubbo
@MapperScan("com.im.chat.mapper")
@ComponentScan({"com.im.chat", "com.im.dispatcher","com.im.sync"})
@Slf4j
public class ChatServiceApp implements CommandLineRunner
{

    @Autowired
    private WebSocketServer webSocketServer;

    public static void main(String[] args) {
        SpringApplication.run(ChatServiceApp.class, args);
    }


    @Override
    public void run(String... args) throws Exception
    {
        new Thread(()->{
            log.info("打开websocket");
            webSocketServer.start();
            Runtime.getRuntime().addShutdownHook(new Thread(){
                @Override
                public synchronized void start()
                {
                    log.info("关闭websocket");
                    try
                    {
                        webSocketServer.shutDown();
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            });
        }).start();

    }

}