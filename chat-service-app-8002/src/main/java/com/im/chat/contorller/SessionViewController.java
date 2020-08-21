package com.im.chat.contorller;


import com.im.chat.annotation.CurrentUser;
import com.mr.entity.po.User;
import org.springframework.web.bind.annotation.RestController;

@RestController("/chat")
public class SessionViewController
{

    //entityType --> U单聊  G群聊
    public void addSessionView(@CurrentUser User user, String entityType, Long entityId)
    {

    }
}
