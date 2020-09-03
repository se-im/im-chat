package com.im.chat.mapper;

import com.im.chat.entity.po.SessionView;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SessionViewMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SessionView record);

    int insertSelective(SessionView record);

    SessionView selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SessionView record);

    int updateByPrimaryKey(SessionView record);

    List<SessionView> selectByUserId(Long userId);

    SessionView getSessionViewForEntity(@Param("userId") Long userId, @Param("entityId") Long entityId, @Param("cvsType") Byte cvsType);

    int updateRedundantByuserId(@Param("relationEntityId") Long relationEntityId,@Param("cvsName") String cvsName,@Param("avatarUrl") String avatarUrl);

    int updateUnreadNum(@Param("cvsId") Long cvsId, @Param("unreadNumAdded") Integer unreadNumAdded);

    int updateSessionViewWithUnreadMessageNum(SessionView record);

    int updateUnReaded(Long cvsId);
}