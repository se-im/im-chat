package com.mr;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhaomanzhou
 */


@SpringBootApplication
@EnableDubbo
@MapperScan("com.mr.mapper")
public class  RoomServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(RoomServiceApp.class, args);
    }
}