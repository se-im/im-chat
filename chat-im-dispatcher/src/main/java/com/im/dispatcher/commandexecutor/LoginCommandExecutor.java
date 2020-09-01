package com.im.dispatcher.commandexecutor;

import com.im.chat.constant.TokenHashConst;
import com.im.dispatcher.command.GroupChatCommand;
import com.im.dispatcher.command.LoginCommand;
import com.im.dispatcher.common.CommandExecutor;
import com.im.user.entity.po.User;
import com.mr.common.RedisPrefixConst;
import com.mr.response.error.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class LoginCommandExecutor implements CommandExecutor<LoginCommand>
{
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${token.expiration}")
    private int expiration;

    @Override
    public void executor(LoginCommand cmd) throws BusinessException
    {
        executeWithResult(cmd);
    }

    @Override
    public Object executeWithResult(LoginCommand cmd) throws BusinessException
    {
        String token = cmd.getToken();
        User user = (User)redisTemplate.opsForHash().get(RedisPrefixConst.TOKEN_PREFIX + token, TokenHashConst.USER);
        if(user == null)
        {
            return null;
        }else
        {
            redisTemplate.expire(RedisPrefixConst.TOKEN_PREFIX+token, expiration, TimeUnit.MINUTES);
        }
        return user;
    }
}
