package com.im.chat.entity.domain;

import lombok.Data;

@Data
public class Packet
{
    private Header header;
    private Body body;
}

@Data
class Header{
    private String version;
}

@Data
class Body{
    private Integer packetType;
    private String content;
}