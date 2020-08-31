package com.im.dispatcher.command;


import com.im.chat.entity.po.Message;
import com.im.chat.entity.po.SessionView;
import com.im.dispatcher.common.Command;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class SingleChatCommand implements Command
{
    private SessionView sendSessionView;
    private Message message;
}
