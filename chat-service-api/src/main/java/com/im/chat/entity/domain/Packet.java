package com.im.chat.entity.domain;

import lombok.Data;

@Data
public class Packet
{
    private Header header;
    private Body body;
}

