package com.im.chat.enums;

public enum CvsNotDisturbEnum {
    /**
     * CLOSE 关闭
     * OPEN 开启
     */
    CLOSE(Byte.valueOf("0"),false),
    OPEN(Byte.valueOf("1"),true);

    CvsNotDisturbEnum(Byte code)
    {
        this.code = code;
    }
    CvsNotDisturbEnum(Byte code,Boolean status)
    {
        this.code = code;
        this.status = status;
    }

    private Byte code;
    private Boolean status;

    public Byte getCode()
    {
        return this.code;
    }
    public Boolean getStatus(){
        return this.status;
    }
    public static CvsNotDisturbEnum codeOf(Byte code) {
        switch (code)
        {
            case 0: return CLOSE;
            case 1: return OPEN;
            default: return null;
        }
    }
    public static CvsNotDisturbEnum statusOf(Boolean status){
        if(status == null){
            return null;
        }
        if(status == true){
            return OPEN;
        }else if(status == false){
            return CLOSE;
        }else{
            return null;
        }
    }
}
