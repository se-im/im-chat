package com.im.chat.service;


import com.im.chat.entity.po.SessionView;
import com.im.chat.enums.CvsTypeEnum;
import com.im.chat.mapper.SessionViewMapper;
import com.im.chat.service.strategy.SessionViewCreatorContext;
import com.im.chat.service.strategy.SessionViewCreatorStrategy;
import com.im.user.entity.po.GroupPo;
import com.im.user.entity.po.User;
import com.im.user.entity.vo.GroupUserBriefVo;
import com.im.user.service.IGroupService;
import com.im.user.service.IUserService;
import com.mr.response.ServerResponse;
import com.mr.response.error.BusinessException;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import java.util.List;

@Component
@Service
@Slf4j
public class SessionViewServiceImpl implements ISessionViewService {

    @Resource
    private SessionViewMapper sessionViewMapper;

    @Reference
    private IUserService iUserService;

    @Reference
    private IGroupService iGroupService;

    @Autowired
    private SessionViewCreatorContext sessionViewCreatorContext;

    @Override
    public Long createSessionView(User curUser, CvsTypeEnum cvsType, Long entityId) throws BusinessException {

        SessionViewCreatorStrategy strategy = sessionViewCreatorContext.getStrategy(cvsType);
        return strategy.createSessionView(curUser, entityId);
    }

    @Override
    public List<SessionView> queryMySessionViewList(Long userId) {
        List<SessionView> sessionViews = sessionViewMapper.selectByUserId(userId);
        return sessionViews;
    }
    @Override
    public SessionView getSessionViewForEntity(Long userId, Long relationEntityId, CvsTypeEnum cvsTypeEnum) {
        SessionView sessionView = sessionViewMapper.getSessionViewForEntity(userId, relationEntityId, cvsTypeEnum.getCode());
        return sessionView;
    }
    @Override
    public void clearUnReaded(Long cvsId) throws BusinessException {
        int res = sessionViewMapper.updateUnReaded(cvsId);
        if(res < 1){
            throw new BusinessException("未读消息置零更新失败！");
        }
    }

    @Override
    public void createSingleSessionView(Long userId, String cvsType, Long entityId) throws BusinessException {
        Byte cvsTypeCode = CvsTypeEnum.nameOf(cvsType).getCode();
        SessionView sessionView1 = sessionViewMapper.getSessionViewForEntity(userId, entityId, cvsTypeCode);
        if(sessionView1 != null){
            throw new BusinessException("会话视图已存在！");
        }
        SessionView sessionView = new SessionView();
        if( cvsTypeCode == 0){

            User destUser = iUserService.getUserById(entityId);
            sessionView.setCvsName(destUser.getUsername());
            sessionView.setAvatarUrl(destUser.getAvatarUrl());

            sessionView.setOwnerId(userId);
            sessionView.setCvsType(cvsTypeCode);
            sessionView.setRelationEntityId(destUser.getId());
        }else if(cvsTypeCode == 1){
            GroupPo groupPo = iGroupService.queryGroupById(entityId);

            sessionView.setCvsName(groupPo.getName());
            sessionView.setAvatarUrl(groupPo.getAvatarUrl());
            sessionView.setOwnerId(userId);
            sessionView.setCvsType(CvsTypeEnum.G.getCode());
            sessionView.setRelationEntityId(entityId);
        }

        int res = sessionViewMapper.insertSelective(sessionView);
        if(res < 1){
            throw new BusinessException("创建会话视图失败");
        }
    }
    @Override
    public void deleteSessionView(Long cvsId) throws BusinessException {
        SessionView sessionView = sessionViewMapper.selectByPrimaryKey(cvsId);
        if(sessionView == null){
            throw new BusinessException("会话视图不存在！");
        }
        int res = sessionViewMapper.deleteByPrimaryKey(cvsId);
        if(res < 1){
            throw new BusinessException("删除会话视图失败！");
        }
    }

    @Override
    public void insertSelective(SessionView sessionView)
    {
        sessionViewMapper.insertSelective(sessionView);
    }

    @Override
    public SessionView selectById(Long cvsId)
    {
        return sessionViewMapper.selectByPrimaryKey(cvsId);
    }
}
