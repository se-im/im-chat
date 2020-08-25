package com.im.chat.service;


import com.im.chat.entity.po.SessionView;
import com.im.chat.enums.CvsNotDisturbEnum;
import com.im.chat.enums.CvsStickEnum;
import com.im.chat.enums.CvsTypeEnum;
import com.im.chat.mapper.SessionViewMapper;
import com.im.chat.service.strategy.SessionViewCreatorContext;
import com.im.chat.service.strategy.SessionViewCreatorStrategy;
import com.im.user.entity.vo.UserVo;
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
    public void createSessionView(Long userId, CvsTypeEnum cvsType, Long entityId) throws BusinessException {

        SessionViewCreatorStrategy strategy = sessionViewCreatorContext.getStrategy(cvsType);
        strategy.createSessionView(userId, entityId);
    }

    @Override
    public List<SessionView> queryMySessionViewList(Long userId) {
        List<SessionView> sessionViews = sessionViewMapper.selectByUserId(userId);
        return sessionViews;
    }
}
