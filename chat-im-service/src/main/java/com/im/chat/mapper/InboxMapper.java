package com.im.chat.mapper;

import com.im.chat.entity.domain.InboxDo;
import com.im.chat.entity.po.Inbox;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InboxMapper
{
    int deleteByPrimaryKey(Long id);

    int insert(Inbox record);

    int insertSelective(Inbox record);

    Inbox selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Inbox record);

    int updateByPrimaryKey(Inbox record);

    Inbox selectByUserIdCvsId(@Param("userId") Long userId, @Param("cvsId") Long cvsId);


    Long getLargestSyncId(@Param("userId") Long userId, @Param("cvsId") Long cvsId);

    List<InboxDo> selectInboxDoByUserIdCvsId(@Param("userId") Long userId, @Param("cvsId") Long cvsId);


    List<InboxDo> selectInboxDoBySyncId(@Param("userId") Long userId, @Param("cvsId") Long cvsId, @Param("syncId") Long syncId);

    List<InboxDo> selectLastTenInboxDoByUserIdCvsId(@Param("userId") Long userId, @Param("cvsId") Long cvsId);


}