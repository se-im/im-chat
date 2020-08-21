package com.im.chat;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhaomanzhou
 */


@SpringBootApplication
@EnableDubbo
@MapperScan("com.im.chat.mapper")
public class ChatServiceApp
{
    public static void main(String[] args) {
        SpringApplication.run(ChatServiceApp.class, args);
    }
}