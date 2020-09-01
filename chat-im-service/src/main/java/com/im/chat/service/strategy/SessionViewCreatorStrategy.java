package com.im.chat.service.strategy;


import com.im.user.entity.po.User;
import com.mr.response.error.BusinessException;
import org.springframework.stereotype.Component;

@Component
public interface SessionViewCreatorStrategy
{
    Long createSessionView(User curUser, Long entityId) throws BusinessException;
}
