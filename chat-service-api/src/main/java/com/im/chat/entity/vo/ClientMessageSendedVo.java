package com.im.chat.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.lang.NonNull;

@ApiModel("客户端发送的消息模型")
@Data
public class ClientMessageSendedVo
{
    @ApiModelProperty("消息内容")
    @NonNull
    private String msg;

    @ApiModelProperty("消息类型；文本-text；图片-image；文件-file ")
    @NonNull
    private String msgType;

    @ApiModelProperty("会话id")
    @NonNull
    private Long cvsId;
}
