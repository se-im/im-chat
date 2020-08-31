package com.im.chat.service.manager;

import com.im.chat.entity.po.Inbox;
import com.im.chat.entity.po.Message;
import com.im.chat.mapper.InboxMapper;
import com.im.user.entity.po.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class InboxManager
{
    @Resource
    private InboxMapper inboxMapper;

    public void insertSingleInbox(Message message, User senderUser, Long ownerId){
        Inbox inbox = Inbox.builder()
                .ownerId(ownerId)
                .messageId(message.getId())
                .msg(message.getMsg())
                .cvsId(message.getCvsId())
                .senderId(senderUser.getId())
                .senderName(senderUser.getUsername())
                .senderAvatarUrl(senderUser.getAvatarUrl())
                .readed((byte) 0)
                .build();
        inboxMapper.insertSelective(inbox);
    }


    public Long getLargestCvsId(Long userId, Long cvsId)
    {
        return 0l;
    }



}
