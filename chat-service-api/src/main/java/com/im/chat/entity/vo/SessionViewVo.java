package com.im.chat.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("会话识图简略信息")
public class SessionViewVo
{
    @ApiModelProperty("会话id")
    private Long id;

    @ApiModelProperty("会话名")
    private String cvsName;

    @ApiModelProperty("会话类型")
    private Integer cvsType;

    @ApiModelProperty("会话关联对象的id，群聊为群id，单聊为用户id")
    private Long relationEntityId;


    @ApiModelProperty("会话头像url地址")
    private String avatarUrl;


    @ApiModelProperty("会话的最近一条消息")
    private String lastMessage;


    @ApiModelProperty("最后一条消息发送者")
    private String senderName;


    @ApiModelProperty("会话的未读消息数")
    private Integer unreadMessageNum;


    @ApiModelProperty("会话最近一条消息发送时间")
    private Date lastMessageTime;

    @ApiModelProperty("会话最近一条消息发送时间,格式化出来过")
    private String lastMessageTimeFormated;


    /**
     * 消息免打扰；0-关闭；1-开启；默认关闭
     */
    @ApiModelProperty("会话是否免打扰")
    private Boolean notDisturb;

    /**
     *  会话置顶；0-false；1-true；默认false
     * Column:    stick
     * Nullable:  false
     */
    @ApiModelProperty("会话是否免置顶")
    private Byte stick;

    /**
     * 扩展字段
     */
    @ApiModelProperty("扩展字段")
    private String ext;
}
