package com.im.chat.service.asyncupdate;

import com.im.chat.mapper.SessionViewMapper;
import com.mr.response.error.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class SessionViewRedundantUpdatation {
    @Resource
    private SessionViewMapper sessionViewMapper;

    public void sessionViewUserRedundantUpdatate(Long userId,String userName,String avatarUrl) throws BusinessException {
        int res = sessionViewMapper.updateRedundantByuserId(userId, userName, avatarUrl);
        if(res < 1){
            throw new BusinessException("会话用户冗余信息更改失败！");
        }
    }
}
