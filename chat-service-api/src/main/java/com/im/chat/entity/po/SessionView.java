package com.im.chat.entity.po;

import lombok.Data;

import java.util.Date;

/**
 * Table: im_session_view
 */
@Data
public class SessionView {
    /**
     * Column:    id
     * Nullable:  false
     */
    private Long id;

    /**
     * 会话名
     *
     * Column:    cvs_name
     * Nullable:  false
     */
    private String cvsName;

    /**
     * 谁的会话（会话的拥有者）
     *
     * Column:    owner_id
     * Nullable:  false
     */
    private Long ownerId;

    /**
     * 会话类型；0-单聊；1-群聊
     *
     * Column:    cvs_type
     * Nullable:  false
     */
    private Byte cvsType;

    /**
     * 会话关联对象的id，群聊为群id，单聊为用户id
     *
     * Column:    relation_entity_id
     * Nullable:  false
     */
    private Long relationEntityId;

    /**
     * 会话头像url地址
     *
     * Column:    avatar_url
     * Nullable:  false
     */
    private String avatarUrl;

    /**
     * 会话的最近一条消息
     *
     * Column:    last_message
     * Nullable:  false
     */
    private String lastMessage;

    /**
     * 最后一条消息发送者
     *
     * Column:    sender_name
     * Nullable:  false
     */
    private String senderName;

    /**
     * 会话的未读消息数
     *
     * Column:    unread_message_num
     * Nullable:  false
     */
    private Integer unreadMessageNum;

    /**
     * 会话最近一条消息发送时间
     *
     * Column:    last_message_time
     * Nullable:  false
     */
    private Date lastMessageTime;

    /**
     * 会话创建时间
     *
     * Column:    create_time
     * Nullable:  false
     */
    private Date createTime;

    /**
     * 会话更新时间
     *
     * Column:    update_time
     * Nullable:  false
     */
    private Date updateTime;

    /**
     * 消息免打扰；0-关闭；1-开启；默认关闭
     *
     * Column:    not_disturb
     * Nullable:  false
     */
    private Byte notDisturb;

    /**
     *  会话置顶；0-false；1-true；默认false
     *
     * Column:    stick
     * Nullable:  false
     */
    private Byte stick;

    /**
     * 扩展字段
     *
     * Column:    ext
     * Nullable:  false
     */
    private String ext;
}