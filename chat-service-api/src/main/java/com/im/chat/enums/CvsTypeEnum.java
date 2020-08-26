package com.im.chat.enums;

public enum CvsTypeEnum {
    U(0),
    G(1);

    CvsTypeEnum(Integer code)
    {
        this.code = code;
    }

    CvsTypeEnum(Integer code,String name)
    {
        this.code = code;
        this.name = name;
    }

    private Integer code;
    private String name;

    public Integer getCode()
    {
        return this.code;
    }
    public String getName(){
        return this.name;
    }
    public static CvsTypeEnum codeOf(Byte code) {
        switch (code)
        {
            case 0: return U;
            case 1: return G;
            default: return null;
        }
    }
    public static CvsTypeEnum nameOf(String name){
        if(name == null){
            return null;
        }
        if(name.equals("U")){
            return U;
        }else if(name.equals("G")){
            return G;
        }else{
            return null;
        }
    }


}
