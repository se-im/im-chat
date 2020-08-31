package com.im.chat.controller;

import com.im.chat.annotation.CurrentUser;
import com.im.chat.entity.po.Message;
import com.im.chat.entity.po.SessionView;
import com.im.chat.entity.vo.ClientMessageSendedVo;
import com.im.chat.enums.CvsTypeEnum;
import com.im.chat.enums.MsgContentTypeEnum;
import com.im.dispatcher.command.GroupChatCommand;
import com.im.dispatcher.command.SingleChatCommand;
import com.im.dispatcher.common.CommandBus;
import com.im.chat.service.ISessionViewService;
import com.im.user.entity.po.User;
import com.mr.response.ServerResponse;
import com.mr.response.error.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.Date;

@Api(tags = "消息发送的api")
@RestController
@RequestMapping("/chat/message/")
@CrossOrigin
public class SendMessageController
{

    @Autowired
    private ISessionViewService sessionViewService;

    @Autowired
    private CommandBus commandBus;

    @PostMapping("/send")
    @ApiOperation("发送消息")
    public ServerResponse<String> sendMessage(@Valid ClientMessageSendedVo clientMessageSendedVo, @CurrentUser @ApiIgnore User user) throws BusinessException
    {

        SessionView sessionView = sessionViewService.selectById(clientMessageSendedVo.getCvsId());
        if(sessionView == null){
            throw new BusinessException("不存的会话视图");
        }

        //TODO 判断该会话视图是否属于当前用户
        Long cvsOwnerId = sessionView.getOwnerId();
        if(!cvsOwnerId.equals(user.getId())){
            throw new BusinessException("当前用户没有该会话视图！会话Id错误！");
        }

        if(sessionView.getCvsType().equals(CvsTypeEnum.U.getCode())){
            Message message = Message.builder()
                    .senderId(user.getId())
                    .senderName(user.getUsername())
                    .cvsId(clientMessageSendedVo.getCvsId())
                    .msg(clientMessageSendedVo.getMsg())
                    .msgContentType(MsgContentTypeEnum.nameOf(clientMessageSendedVo.getMsgType()).getCode())
                    .receiverEntityId(sessionView.getRelationEntityId())
                    .receiverEntityType(CvsTypeEnum.U.getCode())
                    .createTime(new Date())
                    .build();
            SingleChatCommand command = new SingleChatCommand();
            command.setMessage(message);
            command.setSendSessionView(sessionView);
            commandBus.send(command);
        }else if(sessionView.getCvsType().equals(CvsTypeEnum.G.getCode()))
        {
            Message message = Message.builder()
                    .senderId(user.getId())
                    .senderName(user.getUsername())
                    .cvsId(clientMessageSendedVo.getCvsId())
                    .msg(clientMessageSendedVo.getMsg())
                    .msgContentType(MsgContentTypeEnum.nameOf(clientMessageSendedVo.getMsgType()).getCode())
                    .receiverEntityId(sessionView.getRelationEntityId())
                    .receiverEntityType(CvsTypeEnum.G.getCode())
                    .createTime(new Date())
                    .build();
            GroupChatCommand command = new GroupChatCommand();
            command.setMessage(message);
            commandBus.send(command);
        }

        return ServerResponse.success();
    }
}
