package com.im.chat.mapper;

import com.im.chat.entity.po.SessionView;

public interface SessionViewMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SessionView record);

    int insertSelective(SessionView record);

    SessionView selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SessionView record);

    int updateByPrimaryKey(SessionView record);
}