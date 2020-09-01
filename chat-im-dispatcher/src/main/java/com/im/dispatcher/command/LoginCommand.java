package com.im.dispatcher.command;

import com.im.dispatcher.common.Command;
import lombok.Data;

@Data
public class LoginCommand implements Command
{
    private String token;
}
