package com.im.chat.entity.domin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("收件箱的DO模型")
public class InboxDo {
    @ApiModelProperty("收件箱id")
    private Long id;

    @ApiModelProperty("消息Id")
    private Long messageId;

    @ApiModelProperty("消息内容")

    private String msg;

    @ApiModelProperty("会话Id")
    private Long cvsId;

    @ApiModelProperty("发送消息的用户的Id")
    private Long senderId;

    @ApiModelProperty("发送消息的用户的用户名")
    private String senderName;

    @ApiModelProperty("发送消息的用户的头像")
    private String senderAvatarUrl;

    @ApiModelProperty("消息是否已读：0-没有；1-已读")
    private Byte readed;

    @ApiModelProperty("同步id，当个会话递增")
    private Long syncId;

    @ApiModelProperty("消息创建时间，跟消息体时间保持一致")
    private Date createTime;
}
