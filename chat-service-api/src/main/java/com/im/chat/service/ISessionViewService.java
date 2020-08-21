package com.im.chat.service;

import com.im.chat.enums.CvsTypeConst;

public interface ISessionViewService {

    public void addSessionView(Long userId, CvsTypeConst cvsType, Long entityId);
}
