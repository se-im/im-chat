package com.im.dispatcher.commandexecutor;

import com.im.chat.entity.po.Message;
import com.im.chat.entity.po.SessionView;
import com.im.chat.enums.CvsTypeEnum;
import com.im.dispatcher.command.SingleChatCommand;
import com.im.dispatcher.common.CommandExecutor;
import com.im.chat.service.IInboxService;
import com.im.chat.service.ISessionViewService;
import com.im.sync.service.SyncIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class SingleChatCommandExecutor implements CommandExecutor<SingleChatCommand>
{

    @Autowired
    private ISessionViewService sessionViewService;

    @Autowired
    private IInboxService iInboxService;

    @Autowired
    private SyncIdService syncService;


    @Override
    public void executor(SingleChatCommand cmd)
    {

        //消息推给对方
        //更新自己，对方会话视图，收件箱

    }

    public void dispatcherSenderMessage(SingleChatCommand command)
    {

    }


    private void dispatcherReceiverMessage(SingleChatCommand cmd)
    {
        Message message = cmd.getMessage();
        //找到对方会话视图
        SessionView receiverSessionView = sessionViewService.
                getSessionViewForEntity(message.getReceiverEntityId(), message.getSenderId(), CvsTypeEnum.U);
        //获取对方会话视图的syncId
        syncService.getNextSyncId(receiverSessionView.getOwnerId(), receiverSessionView.getId() );
    }

    @Override
    public Object executeWithResult(SingleChatCommand cmd)
    {
        executor(cmd);
        return null;
    }
}
