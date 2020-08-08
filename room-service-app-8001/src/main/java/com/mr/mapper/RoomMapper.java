package com.mr.mapper;

import com.mr.entity.po.Room;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RoomMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Room record);

    int insertSelective(Room record);

    Room selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Room record);

    int updateByPrimaryKey(Room record);

    List<Room> selectByBuildingNo(String BuildingNo);

    List<Room> selectByBuildingNoFloorNo(String buildingNo,String floorNo);

}