package com.mr.service.impl;

import com.mr.entity.po.Room;
import com.mr.response.error.BusinessException;
import com.mr.service.IRoomService;
import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class RoomServiceImplTest{
    @Autowired
    private IRoomService iRoomService;

    public void testInsert() throws BusinessException {
        Room room = new Room();
        room.setBuildingNo("01");
        room.setFloorNo("05");
        room.setRoomNo("504");
        iRoomService.insert(room);
    }

    public void testDelete() {
    }

    public void testUpdate() {
    }

    public void testGetRoomByBuildNo() {
    }

    public void testGetRoomByBuildNoFloorNo() {
    }
}