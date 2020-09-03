package com.im.chat.enums;

public enum MsgContentTypeEnum
{
    /**
     * TEXT 文本消息
     * IMAGE 图片消息
     */
    TEXT(Byte.valueOf("0"),"text"),
    IMAGE(Byte.valueOf("1"),"image"),
    FILE(Byte.valueOf("2"),"file");

    MsgContentTypeEnum(Byte code)
    {
        this.code = code;
    }

    MsgContentTypeEnum(Byte code, String name)
    {
        this.code = code;
        this.name = name;
    }

    private final Byte code;
    private String name;

    public Byte getCode()
    {
        return this.code;
    }
    public String getName(){
        return this.name;
    }
    public static MsgContentTypeEnum codeOf(Byte code) {
        switch (code)
        {
            case 0: return TEXT;
            case 1: return IMAGE;
            case 2: return FILE;
            default: return null;
        }
    }
    public static MsgContentTypeEnum nameOf(String name){
        if(name == null){
            return null;
        }
        if(name.equalsIgnoreCase("image")){
            return IMAGE;
        }else if(name.equalsIgnoreCase("text")){
            return TEXT;
        }else if(name.equalsIgnoreCase("file")){
            return FILE;
        }else{
            return null;
        }
    }


}
