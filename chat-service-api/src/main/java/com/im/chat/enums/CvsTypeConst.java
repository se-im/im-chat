package com.im.chat.enums;

public enum CvsTypeConst {
    U(Byte.valueOf("0")),
    G(Byte.valueOf("1"));

    CvsTypeConst(Byte code)
    {
        this.code = code;
    }

    private Byte code;

    public Byte getCode()
    {
        return this.code;
    }
}
