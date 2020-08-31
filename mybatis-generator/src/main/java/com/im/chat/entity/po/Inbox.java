package com.im.chat.entity.po;

import java.util.Date;
import lombok.Data;

/**
 * Table: im_inbox
 */
@Data
public class Inbox {
    /**
     * Column:    id
     * Nullable:  false
     */
    private Long id;

    /**
     * 谁的收件箱
     *
     * Column:    owner_id
     * Nullable:  false
     */
    private Long ownerId;

    /**
     * 对应的消息id
     *
     * Column:    message_id
     * Nullable:  false
     */
    private Long messageId;

    /**
     * 对应的会话id
     *
     * Column:    cvs_id
     * Nullable:  false
     */
    private Long cvsId;

    /**
     * 消息是否已读：0-没有；1-已读
     *
     * Column:    readed
     * Nullable:  false
     */
    private Byte readed;

    /**
     * 同步id，当个会话递增
     *
     * Column:    sync_id
     * Nullable:  false
     */
    private Long syncId;

    /**
     * 消息创建时间，跟消息体时间保持一致
     *
     * Column:    create_time
     * Nullable:  false
     */
    private Date createTime;

    /**
     * 消息更新时间
     *
     * Column:    update_time
     * Nullable:  false
     */
    private Date updateTime;
}