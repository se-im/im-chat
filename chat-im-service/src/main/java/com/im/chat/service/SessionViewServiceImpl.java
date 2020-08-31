package com.im.chat.service;


import com.im.chat.entity.po.SessionView;
import com.im.chat.enums.CvsTypeEnum;
import com.im.chat.mapper.SessionViewMapper;
import com.im.chat.service.strategy.SessionViewCreatorContext;
import com.im.chat.service.strategy.SessionViewCreatorStrategy;
import com.im.user.entity.po.User;
import com.im.user.service.IUserService;
import com.mr.response.error.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    @Autowired
    private SessionViewCreatorContext sessionViewCreatorContext;

    @Override
    public void createSessionView(User curUser, CvsTypeEnum cvsType, Long entityId) throws BusinessException {

        SessionViewCreatorStrategy strategy = sessionViewCreatorContext.getStrategy(cvsType);
        strategy.createSessionView(curUser, entityId);
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
