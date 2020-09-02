package com.im.chat.service;

import com.im.user.entity.po.User;
import com.im.user.entity.vo.FriendUserBriefVo;
import com.mr.response.error.BusinessException;

import java.util.List;

public interface SessionViewRedundantUpdation {
    public void sessionViewUserRedundantUpdatate(Long userId, String userName, String avatarUrl) throws BusinessException ;

}
