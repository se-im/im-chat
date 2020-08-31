package com.im.dispatcher.commandexecutor;

import com.im.chat.entity.po.Message;
import com.im.chat.entity.po.SessionView;
import com.im.chat.enums.CvsTypeEnum;
import com.im.chat.mapper.MessageMapper;
import com.im.dispatcher.command.SingleChatCommand;
import com.im.dispatcher.common.CommandExecutor;
import com.im.chat.service.IInboxService;
import com.im.chat.service.ISessionViewService;
import com.im.sync.service.SyncIdService;
import com.im.sync.service.SyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Component
public class SingleChatCommandExecutor implements CommandExecutor<SingleChatCommand>
{

    @Autowired
    private ISessionViewService sessionViewService;


    @Autowired
    private SyncService syncService;

    @Resource
    private MessageMapper messageMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void executor(SingleChatCommand cmd)
    {
        messageMapper.insertSelective(cmd.getMessage());

        //消息推给对方
        dispatcherReceiverMessage(cmd);
        //更新自己，对方会话视图，收件箱
        dispatcherSenderMessage(cmd);
    }

    private void dispatcherSenderMessage(SingleChatCommand cmd)
    {
        Message message = cmd.getMessage();
        SessionView sendSessionView = cmd.getSendSessionView();
        syncService.SyncMessage(message, sendSessionView);
    }


    private void dispatcherReceiverMessage(SingleChatCommand cmd)
    {
        Message message = cmd.getMessage();
        //找到对方会话视图
        SessionView receiverSessionView = sessionViewService.
                getSessionViewForEntity(message.getReceiverEntityId(), message.getSenderId(), CvsTypeEnum.U);
        //TODO 不存在会话视图则创建（单向）

        //sync对方会话视图
        syncService.SyncMessage(message, receiverSessionView);
    }

    @Override
    public Object executeWithResult(SingleChatCommand cmd)
    {
        executor(cmd);
        return null;
    }
}
