package com.im.chat.service;

import com.im.chat.entity.po.Inbox;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

@Component
@Service
@Slf4j
public class InboxServiceImpl implements IInboxService{

    @Override
    public Inbox queryInboxByUserIdCvsId(Long userId,Long cvsId) {

        return null;
    }
}
