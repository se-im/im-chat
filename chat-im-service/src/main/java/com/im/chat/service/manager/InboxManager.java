package com.im.chat.service.manager;

import com.im.chat.entity.po.Inbox;
import com.im.chat.entity.po.Message;
import com.im.chat.entity.po.SessionView;
import com.im.chat.enums.MsgReadedEnum;
import com.im.chat.mapper.InboxMapper;
import com.im.user.entity.po.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class InboxManager
{
    @Resource
    private InboxMapper inboxMapper;

    public void insertSingleInbox(Message message, SessionView sessionView, Long syncId,  boolean online){
        Inbox inbox = new Inbox();
        inbox.setOwnerId(sessionView.getOwnerId());
        inbox.setMessageId(message.getId());
        inbox.setCvsId(sessionView.getId());
        inbox.setSyncId(syncId);
        if(online)
        {
            inbox.setReaded(MsgReadedEnum.TRUE.getCode());
        }else
        {
            inbox.setReaded(MsgReadedEnum.FALSE.getCode());
        }

        inboxMapper.insertSelective(inbox);
    }


    public Long getLargestCvsId(Long userId, Long cvsId)
    {
        return 0l;
    }



}
