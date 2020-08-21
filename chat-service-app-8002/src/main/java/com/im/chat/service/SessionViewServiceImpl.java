package com.im.chat.service;


import com.im.chat.entity.po.SessionView;
import com.im.chat.enums.CvsNotDisturbEnum;
import com.im.chat.enums.CvsStickEnum;
import com.im.chat.enums.CvsTypeEnum;
import com.im.chat.mapper.SessionViewMapper;
import com.mr.entity.vo.UserVo;
import com.mr.response.error.BusinessException;
import com.mr.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Service
@Slf4j
public class SessionViewServiceImpl implements ISessionViewService {

    @Resource
    private SessionViewMapper sessionViewMapper;

    @Reference
    private IUserService iUserService;

    @Override
    public void addSessionView(Long userId, CvsTypeEnum cvsType, Long entityId) throws BusinessException {
        if(cvsType == CvsTypeEnum.U){
            UserVo entity = iUserService.getUserById(entityId);
            SessionView sessionView = new SessionView();
            sessionView.setCvsName(entity.getUsername());
            sessionView.setOwnerId(userId);
            sessionView.setCvsType(cvsType.getCode());
            sessionView.setRelationEntityId(entityId);
            sessionView.setAvatarUrl(entity.getAvatarUrl());
            sessionView.setNotDisturb(CvsNotDisturbEnum.CLOSE.getCode());
            sessionView.setStick(CvsStickEnum.CLOSE.getCode());
            int res = sessionViewMapper.insertSelective(sessionView);
            if(res == 0){

            }
        }else{
            //TODO
        }
    }
}
