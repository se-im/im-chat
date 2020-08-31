package com.im.sync.service;


import com.im.chat.entity.po.Inbox;
import com.im.chat.entity.po.Message;
import com.im.chat.entity.po.SessionView;
import com.im.chat.enums.MsgReadedEnum;
import com.im.chat.mapper.InboxMapper;
import com.im.chat.mapper.MessageMapper;
import com.im.chat.mapper.SessionViewMapper;
import com.im.chat.service.manager.InboxManager;
import com.im.chat.service.manager.SessionViewManager;
import com.im.sync.netty.ConnectorManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component
public class SyncService
{
    @Resource
    private MessageMapper messageMapper;

    @Autowired
    private SessionViewManager sessionViewManager;

    @Resource
    private ConnectorManager connectorManager;

    @Resource
    private SyncIdService syncIdService;

    @Resource
    private InboxManager inboxManager;

    public void SyncMessage(Message message, SessionView sessionView)
    {
        boolean online = connectorManager.isOnline(sessionView.getOwnerId());
        //更新会话视图
        updateCvsInBoxTransactional(message, sessionView, online);
    }


    @Transactional(rollbackFor = Exception.class)
    public void updateCvsInBoxTransactional(Message message, SessionView sessionView, boolean online)
    {
        //插入消息

        //更新会话视图
        sessionViewManager.updateSessionView(message, sessionView,online);

        //插入收件箱
        Long syncId = syncIdService.getNextSyncId(sessionView.getOwnerId(), sessionView.getId());

        inboxManager.insertSingleInbox(message, sessionView, syncId, online);

        //TODO
    }
}
