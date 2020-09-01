package com.im.chat.controller;

import com.im.chat.netty.WsServerHandler;
import com.im.user.entity.po.User;
import com.mr.response.ServerResponse;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "收件箱相关的api")
@RestController
@RequestMapping("/chat1/online/")
@CrossOrigin
public class OnlineController
{

    @Autowired
    private WsServerHandler wsServerHandler;


    @GetMapping("/user/all")
    public ServerResponse<List<User>> getAllOnlineUser(){
        List<User> allOnlineUser = wsServerHandler.getAllOnlineUser();
        return ServerResponse.success(allOnlineUser);
    }
    @GetMapping("/handler/all")
    public ServerResponse<List<String>> getAllActiveConnect(){
        List<String> allActiveConnect = wsServerHandler.getAllActiveConnect();
        return ServerResponse.success(allActiveConnect);
    }
}
