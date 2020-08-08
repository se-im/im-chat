package com.mr.entity.vo;

import lombok.Data;

@Data
public class RoomBriefVo {
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
}
