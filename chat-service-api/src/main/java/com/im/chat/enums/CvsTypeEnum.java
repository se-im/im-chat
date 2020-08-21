package com.im.chat.enums;

public enum CvsTypeEnum {
    U(0),
    G(1);

    CvsTypeEnum(Integer code)
    {
        this.code = code;
    }

    private Integer code;

    public Integer getCode()
    {
        return this.code;
    }
}
