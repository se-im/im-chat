package com.im.chat.netty;

import com.alibaba.fastjson.JSON;
import com.im.chat.entity.domain.Body;
import com.im.chat.entity.domain.Header;
import com.im.chat.entity.domain.Packet;
import com.im.chat.enums.PacketTypeEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class WsServerHandlerTest
{
    @Test
    public void testPacket(){
        Packet packet = new Packet();
        Header header = new Header();
        header.setVersion("1");
        Body body = new Body();
        body.setPacketType(PacketTypeEnum.LOGIN.getCode());
        body.setContent("dfff");
        packet.setHeader(header);
        packet.setBody(body);
        System.out.println(JSON.toJSONString(packet));
    }

}