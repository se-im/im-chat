package com.im.chat.enums;

public enum CvsNotDisturbEnum {
    CLOSE(Byte.valueOf("0")),
    OPEN(Byte.valueOf("1"));

    CvsNotDisturbEnum(Byte code)
    {
        this.code = code;
    }

    private Byte code;

    public Byte getCode()
    {
        return this.code;
    }
}
