package com.im.chat.service.manager;


import com.im.chat.entity.po.Message;
import com.im.chat.entity.po.SessionView;
import com.im.chat.mapper.SessionViewMapper;
import com.im.user.entity.po.User;
import com.mr.response.error.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class SessionViewManager
{

    @Resource
    private SessionViewMapper sessionViewMapper;

    public void updateSessionView(Message message, SessionView sessionView, boolean online)
    {

        sessionView.setLastMessage(message.getMsg());
        sessionView.setSenderName(message.getSenderName());
        sessionView.setLastMessageTime(message.getCreateTime());
        log.info("更新会话识图时间 {}", sessionView.getLastMessageTime().getTime());
        if(!online)
        {
            sessionViewMapper.updateSessionViewWithUnreadMessageNum(sessionView);
        }else
        {
            sessionViewMapper.updateByPrimaryKeySelective(sessionView);
        }

    }

}
