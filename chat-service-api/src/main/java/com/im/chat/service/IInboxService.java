package com.im.chat.service;

import com.im.chat.entity.po.Inbox;
import com.im.chat.entity.po.SessionView;
import com.mr.response.error.BusinessException;

import java.util.List;

public interface IInboxService {
    public Inbox queryInboxByUserIdCvsId(Long userId,Long cvsId) throws BusinessException;
}
