package com.im.chat.service.manager;


import com.im.chat.entity.po.Message;
import com.im.chat.entity.po.SessionView;
import com.im.chat.mapper.SessionViewMapper;
import com.im.user.entity.po.User;
import com.mr.response.error.BusinessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SessionViewManager
{

    @Resource
    private SessionViewMapper sessionViewMapper;

    public void updateSessionView(Message message, SessionView sessionView, boolean online)
    {

        sessionView.setLastMessage(message.getMsg());
        sessionView.setSenderName(message.getSenderName());
        sessionView.setCreateTime(message.getCreateTime());
        if(!online)
        {
            sessionViewMapper.updateSessionViewWithUnreadMessageNum(sessionView);
        }else
        {
            sessionViewMapper.updateByPrimaryKeySelective(sessionView);
        }

    }

}
