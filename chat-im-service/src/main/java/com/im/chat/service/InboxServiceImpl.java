package com.im.chat.service;

import com.im.chat.entity.domain.InboxDo;
import com.im.chat.entity.po.SessionView;
import com.im.chat.mapper.InboxMapper;
import com.im.chat.mapper.SessionViewMapper;
import com.mr.response.error.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
@Service
@Slf4j
public class InboxServiceImpl implements IInboxService{
    @Resource
    private InboxMapper inboxMapper;

    @Resource
    private SessionViewMapper sessionViewMapper;

    @Override
    public List<InboxDo> queryInboxByUserIdCvsId(Long userId, Long cvsId) throws BusinessException {
        SessionView sessionView = sessionViewMapper.selectByPrimaryKey(cvsId);
        if(sessionView == null){
            throw new BusinessException("会话视图不存在！");
        }
        List<InboxDo> inboxDos = inboxMapper.selectInboxDoByUserIdCvsId(userId, cvsId);
        return inboxDos;
    }
}
