package com.im.chat.service;


import com.im.chat.enums.CvsTypeConst;
import com.im.chat.mapper.SessionViewMapper;
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
    public void addSessionView(Long userId, CvsTypeConst cvsType, Long entityId) {

    }
}
