package com.im.sync.service;


import com.alibaba.fastjson.JSON;
import com.im.chat.entity.domain.Body;
import com.im.chat.entity.domain.Header;
import com.im.chat.entity.domain.Packet;
import com.im.chat.entity.po.Inbox;
import com.im.chat.entity.po.Message;
import com.im.chat.entity.po.SessionView;
import com.im.chat.entity.vo.InboxVo;
import com.im.chat.enums.MsgContentTypeEnum;
import com.im.chat.enums.MsgReadedEnum;
import com.im.chat.enums.PacketTypeEnum;
import com.im.chat.mapper.InboxMapper;
import com.im.chat.mapper.MessageMapper;
import com.im.chat.mapper.SessionViewMapper;
import com.im.chat.service.manager.InboxManager;
import com.im.chat.service.manager.SessionViewManager;
import com.im.sync.netty.ConnectorManager;
import com.im.user.entity.po.User;
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

    public void SyncMessage(Message message, SessionView sessionView, User senderUser)
    {
        //生产syncId
        Long syncId = syncIdService.getNextSyncId(sessionView.getOwnerId(), sessionView.getId());
        boolean online = connectorManager.isOnline(sessionView.getOwnerId());

        if(online && !sessionView.getOwnerId().equals(senderUser.getId()))
        {
            pushMessageToOnlineUser(message, sessionView, senderUser, syncId);
        }
        //更新会话视图
        updateCvsInBoxTransactional(message, sessionView, online, syncId);
    }

    private void pushMessageToOnlineUser(Message message, SessionView sessionView,User sendUser, long syncId){
        InboxVo inboxVo = new InboxVo();
        inboxVo.setMessageId(message.getId());
        inboxVo.setMsg(message.getMsg());
        inboxVo.setMsgContentType(MsgContentTypeEnum.codeOf(message.getMsgContentType()).getName());
        inboxVo.setCvsId(sessionView.getId());
        inboxVo.setSenderId(message.getSenderId());
        inboxVo.setSenderName(message.getSenderName());
        inboxVo.setSenderAvatarUrl(sendUser.getAvatarUrl());
        inboxVo.setSyncId(syncId);
        inboxVo.setCreateTime(message.getCreateTime().getTime());
        inboxVo.setSelfSend(false);

        Packet packet = new Packet();
        Header header = new Header();
        header.setVersion("1");
        Body body = new Body();
        body.setPacketType(PacketTypeEnum.PUSH_MESSAGE_TO_CLIENT.getCode());
        body.setContent(inboxVo);

        packet.setHeader(header);
        packet.setBody(body);

        connectorManager.pushToClient(JSON.toJSONString(packet), sessionView.getOwnerId());



    }

    @Transactional(rollbackFor = Exception.class)
    public void updateCvsInBoxTransactional(Message message, SessionView sessionView, boolean online, long syncId)
    {
        //插入消息

        //更新会话视图
        sessionViewManager.updateSessionView(message, sessionView,online);

        //插入收件箱

        inboxManager.insertSingleInbox(message, sessionView, syncId, online);


    }
}
