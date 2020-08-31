package com.im.chat.service.strategy;


import com.mr.response.error.BusinessException;
import org.springframework.stereotype.Component;

@Component
public interface SessionViewCreatorStrategy
{
    void createSessionView(Long userId, Long entityId) throws BusinessException;
}
