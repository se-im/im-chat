package com.im.chat.entity.domain;

import lombok.Data;

@Data
public class Body
{
    private Integer packetType;
    private Object content;
}
