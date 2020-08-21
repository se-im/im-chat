package com.im.chat.mapper;

import com.im.chat.entity.po.Inbox;

public interface InboxMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Inbox record);

    int insertSelective(Inbox record);

    Inbox selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Inbox record);

    int updateByPrimaryKey(Inbox record);
}