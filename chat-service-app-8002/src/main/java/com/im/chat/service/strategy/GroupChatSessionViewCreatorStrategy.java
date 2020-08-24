package com.im.chat.service.strategy;


import com.im.chat.annotation.SessionViewStrategyAnnotation;
import com.im.chat.entity.po.SessionView;
import com.im.chat.enums.CvsTypeEnum;
import com.im.chat.mapper.SessionViewMapper;
import com.im.user.entity.po.GroupPo;
import com.im.user.service.IGroupService;
import com.mr.response.error.BusinessException;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@SessionViewStrategyAnnotation(cvsType= CvsTypeEnum.G)
public class GroupChatSessionViewCreatorStrategy implements SessionViewCreatorStrategy
{

    @Reference
    private IGroupService groupService;

    @Resource
    private SessionViewMapper sessionViewMapper;

    @Override
    public void createSessionView(Long userId, Long entityId) throws BusinessException
    {
        GroupPo groupPo = groupService.queryGroupById(entityId);
        SessionView sessionView = new SessionView();
        sessionView.setCvsName(groupPo.getName());
        sessionView.setAvatarUrl(groupPo.getAvatarUrl());

        sessionView.setOwnerId(userId);
        sessionView.setCvsType(CvsTypeEnum.G.getCode());
        sessionView.setRelationEntityId(entityId);
        int res = sessionViewMapper.insertSelective(sessionView);
        if(res < 1){
            throw new BusinessException("创建会话识图失败");
        }
    }


}
