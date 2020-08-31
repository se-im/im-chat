package com.im.chat.mapper;

import com.im.chat.entity.po.Message;

public interface MessageMapper
{
    int deleteByPrimaryKey(Long id);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);
}