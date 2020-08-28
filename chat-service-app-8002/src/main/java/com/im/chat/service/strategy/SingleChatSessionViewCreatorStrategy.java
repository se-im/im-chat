package com.im.chat.service.strategy;


import com.im.chat.annotation.SessionViewStrategyAnnotation;
import com.im.chat.entity.po.SessionView;
import com.im.chat.enums.CvsNotDisturbEnum;
import com.im.chat.enums.CvsStickEnum;
import com.im.chat.enums.CvsTypeEnum;
import com.im.chat.mapper.SessionViewMapper;
import com.im.user.entity.vo.UserVo;
import com.im.user.service.IUserService;
import com.mr.response.error.BusinessException;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@SessionViewStrategyAnnotation(cvsType = CvsTypeEnum.U)
public class SingleChatSessionViewCreatorStrategy implements SessionViewCreatorStrategy
{
    @Reference
    private IUserService iUserService;

    @Resource
    private SessionViewMapper sessionViewMapper;

    @Override
    public void createSessionView(Long userId, Long entityId) throws BusinessException
    {
        SessionView sessionView1 = sessionViewMapper.selectByUserIdEntityIdCvsType(userId, entityId, CvsTypeEnum.U.getCode());
        if(sessionView1 != null){
            throw new BusinessException("会话视图已存在！");
        }
        UserVo user = iUserService.getUserById(entityId);
        SessionView sessionView = new SessionView();
        sessionView.setCvsName(user.getUsername());
        sessionView.setAvatarUrl(user.getAvatarUrl());

        sessionView.setOwnerId(userId);
        sessionView.setCvsType(CvsTypeEnum.U.getCode());
        sessionView.setRelationEntityId(entityId);

        int res = sessionViewMapper.insertSelective(sessionView);
        if(res < 1){
            throw new BusinessException("创建会话识图失败");
        }
    }
}
