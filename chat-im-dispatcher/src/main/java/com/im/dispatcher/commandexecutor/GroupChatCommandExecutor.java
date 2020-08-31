package com.im.dispatcher.commandexecutor;

import com.im.chat.entity.po.Message;
import com.im.chat.entity.po.SessionView;
import com.im.chat.enums.CvsTypeEnum;
import com.im.chat.mapper.MessageMapper;
import com.im.chat.mapper.SessionViewMapper;
import com.im.chat.service.ISessionViewService;
import com.im.dispatcher.command.GroupChatCommand;
import com.im.dispatcher.command.SingleChatCommand;
import com.im.dispatcher.common.CommandExecutor;
import com.im.sync.service.SyncService;
import com.im.user.entity.po.GroupPo;
import com.im.user.entity.vo.GroupUserBriefVo;
import com.im.user.service.IGroupService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.*;

@Component
@Slf4j
public class GroupChatCommandExecutor implements CommandExecutor<GroupChatCommand>
{

    @Autowired
    private ISessionViewService sessionViewService;


    @Autowired
    private SyncService syncService;

    @Resource
    private MessageMapper messageMapper;


    @Reference
    private IGroupService groupService;





    private ExecutorService executorService = new ThreadPoolExecutor(20, 50, 5, TimeUnit.MINUTES
    ,new LinkedBlockingQueue<>(1000));

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void executor(GroupChatCommand cmd)
    {
        log.info("接收 {} 发送的消息消息内容 {},开始转发", cmd.getSenderUser().getUsername(), cmd.getMessage().getMsg());
        messageMapper.insertSelective(cmd.getMessage());

        log.info("进行消息持久化，消息id为 {}", cmd.getMessage().getId());
        //消息推给对方
        dispatcherReceiverMessage(cmd);
        //更新自己，对方会话视图，收件箱
        //dispatcherSenderMessage(cmd);
    }

    private void dispatcherSenderMessage(GroupChatCommand cmd)
    {
        Message message = cmd.getMessage();
        SessionView sendSessionView = cmd.getSendSessionView();
        log.info("消息转发给自己：{}", sendSessionView.getOwnerId());
        syncService.SyncMessage(message, sendSessionView);
    }


    private void dispatcherReceiverMessage(GroupChatCommand cmd)
    {
        Message message = cmd.getMessage();
        Long groupId = message.getReceiverEntityId();
        GroupPo groupPo = groupService.queryGroupById(groupId);
        List<GroupUserBriefVo> groupUsers = groupService.queryGroupUsers(groupId);

        log.info("发送到群 {}, 群id {}", groupPo.getName(), groupPo.getId());

        for (int i = 0; i < groupUsers.size(); i++)
        {
            Long userId = groupUsers.get(i).getUserid();
            log.info("消息转发给用户 {}", userId);
            executorService.submit(()->{
                SessionView sessionViewForEntity = sessionViewService.getSessionViewForEntity(userId, groupId, CvsTypeEnum.G);
                if(sessionViewForEntity == null)
                {
                    SessionView sessionView = new SessionView();
                    sessionView.setCvsName(groupPo.getName());
                    sessionView.setOwnerId(userId);
                    sessionView.setCvsType(CvsTypeEnum.G.getCode());
                    sessionView.setRelationEntityId(groupId);
                    sessionView.setAvatarUrl(groupPo.getAvatarUrl());
                    sessionViewService.insertSelective(sessionView);
                    sessionViewForEntity = sessionView;
                }
                syncService.SyncMessage(message, sessionViewForEntity);
            });
        }


    }

    @Override
    public Object executeWithResult(GroupChatCommand cmd)
    {
        executor(cmd);
        return null;
    }
}
