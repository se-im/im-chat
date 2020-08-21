package com.im.chat.enums;

public enum CvsStickEnum {
    CLOSE(Byte.valueOf("0")),
    OPEN(Byte.valueOf("1"));

    CvsStickEnum(Byte code)
    {
        this.code = code;
    }

    private Byte code;

    public Byte getCode()
    {
        return this.code;
    }
}
