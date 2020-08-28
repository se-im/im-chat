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

    SessionView selectByUserIdEntityIdCvsType(@Param("userId") Long userId,@Param("entityId") Long entityId, @Param("cvsType") Integer cvsType);


}