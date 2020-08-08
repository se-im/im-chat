package com.mr.service;

import com.mr.entity.po.Room;
import com.mr.entity.vo.RoomBriefVo;
import com.mr.response.error.BusinessException;

import java.util.List;

public interface IRoomService {
    //增删改查
    void insert(Room room) throws BusinessException;

    void delete(Room room) throws BusinessException;

    boolean update(Room room) throws BusinessException;

    //查询当前楼，楼楼层，返回就是房间对象list
    List<RoomBriefVo> getRoomByBuildNo(String buildingNo) throws BusinessException;

    List<RoomBriefVo>  getRoomByBuildNoFloorNo(String buildingNo, String floorNo) throws BusinessException;
}
