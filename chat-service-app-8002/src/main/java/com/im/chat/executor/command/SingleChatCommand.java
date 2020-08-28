package com.im.chat.executor.command;


import com.im.chat.entity.po.Message;
import com.im.chat.executor.common.Command;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class SingleChatCommand implements Command
{
    private Message message;
}
