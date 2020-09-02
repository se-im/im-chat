package com.im.chat.service;

import com.im.chat.entity.po.SessionView;
import com.im.chat.entity.vo.SessionViewVo;
import com.im.chat.enums.CvsTypeEnum;
import com.im.user.entity.po.User;
import com.mr.response.error.BusinessException;

import java.util.List;

public interface ISessionViewService {

    public void createSingleSessionView(Long userId, String cvsType, Long entityId) throws BusinessException;

    public Long createSessionView(User curUser, CvsTypeEnum cvsType, Long entityId) throws BusinessException;

    public List<SessionView> queryMySessionViewList(Long userId);

    public void deleteSessionView(Long cvsId) throws BusinessException;

    /**
     * 查询当前用户和指定对象的会话
     */
    public SessionView getSessionViewForEntity(Long userId, Long relationEntityId, CvsTypeEnum cvsTypeEnum);

    public SessionView selectById(Long cvsId);


    public void insertSelective(SessionView sessionView);

    public void clearUnReaded(Long cvsId) throws BusinessException;
}
