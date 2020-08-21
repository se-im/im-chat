package com.im.chat.entity.po;

import lombok.Data;

import java.util.Date;

/**
 * Table: im_message
 */
@Data
public class Message {
    /**
     * Column:    id
     * Nullable:  false
     */
    private Long id;

    /**
     * 发送者的id
     *
     * Column:    sender_id
     * Nullable:  false
     */
    private Long senderId;

    /**
     * 发送者姓名
     *
     * Column:    sender_name
     * Nullable:  false
     */
    private String senderName;

    /**
     * 消息内容
     *
     * Column:    msg
     * Nullable:  false
     */
    private String msg;

    /**
     * 消息类型：0-文本消息；1-图片消息
     *
     * Column:    msg_type
     * Nullable:  false
     */
    private Byte msgType;

    /**
     * 接受者实体id
     *
     * Column:    receiver_entity_id
     * Nullable:  false
     */
    private Long receiverEntityId;

    /**
     * 接收实体类型；0-普通用户；1-群
     *
     * Column:    receiver_entity_type
     * Nullable:  false
     */
    private Byte receiverEntityType;

    /**
     * 消息创建时间
     *
     * Column:    create_time
     * Nullable:  false
     */
    private Date createTime;

    /**
     * 扩展字段
     *
     * Column:    ext
     * Nullable:  false
     */
    private String ext;
}