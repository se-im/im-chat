package com.im.chat.controller;


import com.mr.response.ServerResponse;
import com.mr.response.error.BusinessException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UnloginController {
    @RequestMapping(value = "/unlogin")
    public ServerResponse<String> unlogin() throws BusinessException
    {
        throw new BusinessException("没有登录的用户，请先登录");

    }
}
