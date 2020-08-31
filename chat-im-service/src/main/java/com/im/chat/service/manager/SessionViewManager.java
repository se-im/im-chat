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

    public void updateSessionView(Message message, User senderUser, Long ownerId, boolean online) throws BusinessException
    {
        SessionView sessionView = sessionViewMapper.selectByPrimaryKey(ownerId);
        if(sessionView == null){
            throw new BusinessException("不存在的会话视图");
        }
        sessionView.setLastMessage(message.getMsg());
        sessionView.setLastMessageTime(message.getCreateTime());
        sessionView.setSenderName(senderUser.getUsername());
        if(!online)
        {
            sessionView.setUnreadMessageNum(sessionView.getUnreadMessageNum() + 1);
        }

        sessionViewMapper.updateByPrimaryKeySelective(sessionView);

    }

}
