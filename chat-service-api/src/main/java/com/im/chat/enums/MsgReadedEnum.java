package com.im.chat.enums;

public enum MsgReadedEnum
{
    /**
     * U 单聊
     * G 群聊
     */
    FALSE(Byte.valueOf("0"),false),
    TRUE(Byte.valueOf("1"),true);

    MsgReadedEnum(Byte code)
    {
        this.code = code;
    }

    MsgReadedEnum(Byte code, boolean value)
    {
        this.code = code;
        this.value = value;
    }

    private final Byte code;
    private boolean value;

    public Byte getCode()
    {
        return this.code;
    }
    public boolean getValue(){
        return this.value;
    }
    public static MsgReadedEnum codeOf(Byte code) {
        switch (code)
        {
            case 0: return FALSE;
            case 1: return TRUE;
            default: return null;
        }
    }
    public static MsgReadedEnum nameOf(boolean value){

        if (value)
        {
            return TRUE;
        }else
        {
            return FALSE;
        }
    }


}
