package com.mr.service.impl;

import com.mr.common.Const;
import com.mr.common.JwtToken;
import com.mr.common.RedisPrefixConst;
import com.mr.constant.TokenHashConst;
import com.mr.entity.po.Room;
import com.mr.entity.vo.RoomBriefVo;
import com.mr.exception.BusinessErrorEnum;
import com.mr.mapper.RoomMapper;
import com.mr.response.error.BusinessException;
import com.mr.service.IRoomService;
import com.mr.util.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;




@Component
@Service
@Slf4j
public class RoomServiceImpl implements IRoomService {

    @Resource
    private RoomMapper roomMapper;

    @Override
    public void insert(Room room) throws BusinessException {

        roomMapper.insertSelective(room);

    }

    @Override
    public void delete(Room room) throws BusinessException {

    }

    @Override
    public boolean update(Room room) throws BusinessException {
        return false;
    }

    @Override
    public List<RoomBriefVo> getRoomByBuildNo(String buildingNo) throws BusinessException {
        return null;
    }

    @Override
    public List<RoomBriefVo> getRoomByBuildNoFloorNo(String buildingNo, String floorNo) throws BusinessException {
        return null;
    }

    private RoomBriefVo assembleUserVo(Room room){
        RoomBriefVo roomBriefVo = new RoomBriefVo();
        roomBriefVo.setBuildingNo(room.getBuildingNo());
        roomBriefVo.setFloorNo(room.getFloorNo());
        roomBriefVo.setRoomNo(room.getRoomNo());
        roomBriefVo.setRoomSize(room.getRoomSize());
        roomBriefVo.setPic(room.getPic());

        return roomBriefVo;
    }

}

