package com.im.chat.service;

import com.im.chat.entity.po.Inbox;
import com.im.chat.entity.po.SessionView;
import com.im.chat.mapper.InboxMapper;
import com.im.chat.mapper.SessionViewMapper;
import com.mr.response.error.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Service
@Slf4j
public class InboxServiceImpl implements IInboxService{
    @Resource
    private InboxMapper inboxMapper;

    @Resource
    private SessionViewMapper sessionViewMapper;
    @Override
    public Inbox queryInboxByUserIdCvsId(Long userId,Long cvsId) throws BusinessException {
        SessionView sessionView = sessionViewMapper.selectByPrimaryKey(cvsId);
        if(sessionView == null){
            throw new BusinessException("会话视图不存在！");
        }
        Inbox inbox = inboxMapper.selectByUserIdCvsId(userId, cvsId);
        return inbox;
    }
}
