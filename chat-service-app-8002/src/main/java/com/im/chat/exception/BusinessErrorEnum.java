package com.im.chat.exception;


import com.mr.response.error.CommonError;

public enum BusinessErrorEnum implements CommonError
{

    UNKNOWN_ERROR(5000, "未知错误"),

    PARAMETER_VALIDATION_ERROR(1000, "参数不合法"),

    BUILDINGNO_EMPTY_ERROR(2002,"楼号不能为空"),

    FLOOR_EMPTY_ERROR(2003,"楼层不能为空"),

    ROOM_EMPTY_ERROR(2004,"房间号不能为空"),

    ROOMSIZE_EMPTY_ERROR(2005,"房间大小不能为空"),

    ROOM_NOT_EXIST(2006,"房间不存在"),

    ROOM_EXIST(2007,"房间已存在"),

    INSERT_FAILED(2008, "插入失败"),

    DELETE_FAILED(2008, "删除失败"),

    UPDATE_FAILED(2009,"更新失败")
    ;

    BusinessErrorEnum(int errorCode, String errorMessage)
    {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    private int errorCode;
    private String errorMessage;

    @Override
    public int getErrorCode()
    {
        return errorCode;
    }

    @Override
    public String getErrorMessage()
    {
        return errorMessage;
    }

    @Override
    public CommonError setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
        return this;
    }
}
