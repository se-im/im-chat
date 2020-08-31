package com.im.dispatcher.command;


import com.im.chat.entity.po.Message;
import com.im.dispatcher.common.Command;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class GroupChatCommand implements Command
{
    private Message message;
}
