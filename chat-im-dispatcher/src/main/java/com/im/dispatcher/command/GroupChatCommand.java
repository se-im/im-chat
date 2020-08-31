package com.im.dispatcher.command;


import com.im.chat.entity.po.Message;
import com.im.chat.entity.po.SessionView;
import com.im.dispatcher.common.Command;
import com.im.user.entity.po.User;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class GroupChatCommand implements Command
{
    private SessionView sendSessionView;
    private Message message;
    private User senderUser;
}
