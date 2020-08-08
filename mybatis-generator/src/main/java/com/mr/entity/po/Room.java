package com.mr.entity.po;

import java.util.Date;
import lombok.Data;

/**
 * Table: mr_room
 */
@Data
public class Room {
    /**
     * 用户表id
     *
     * Column:    id
     * Nullable:  false
     */
    private Long id;

    /**
     * 楼号
     *
     * Column:    building_no
     * Nullable:  false
     */
    private String buildingNo;

    /**
     * 楼层号
     *
     * Column:    floor_no
     * Nullable:  false
     */
    private String floorNo;

    /**
     * 房间号
     *
     * Column:    room_no
     * Nullable:  true
     */
    private String roomNo;

    /**
     * 1小，2中，3大
     *
     * Column:    room_size
     * Nullable:  true
     */
    private Integer roomSize;

    /**
     * 图片
     *
     * Column:    pic
     * Nullable:  false
     */
    private String pic;

    /**
     * 创建时间
     *
     * Column:    create_time
     * Nullable:  false
     */
    private Date createTime;

    /**
     * 最后一次更新时间
     *
     * Column:    update_time
     * Nullable:  false
     */
    private Date updateTime;

    /**
     * 是否删除，0-没有, 1-已删除
     *
     * Column:    deleted
     * Nullable:  false
     */
    private Byte deleted;
}