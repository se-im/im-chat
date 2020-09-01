package com.im.chat.enums;

public enum  PacketTypeEnum
{
    PUSH_MESSAGE_TO_CLIENT(1, "push_message_to_client"),
    LOGIN(2, "login")
    ;
    private Integer code;
    private String value;

    PacketTypeEnum(Integer code, String value)
    {
        this.code = code;
        this.value = value;
    }


    public Integer getCode(){
        return this.code;
    }

    public static PacketTypeEnum codeOf(Integer code){
        switch (code){
            case 1: return PUSH_MESSAGE_TO_CLIENT;
            case 2: return LOGIN;
        }
        return null;
    }
}
