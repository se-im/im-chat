package com.im.chat.controller;


import com.im.chat.annotation.CurrentUser;
import com.im.chat.entity.domain.InboxDo;
import com.im.chat.entity.vo.InboxVo;
import com.im.chat.enums.MsgContentTypeEnum;
import com.im.chat.enums.MsgReadedEnum;
import com.im.chat.service.IInboxService;
import com.im.user.entity.po.User;
import com.mr.response.ServerResponse;
import com.mr.response.error.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "收件箱相关的api")
@RestController
@RequestMapping("/chat/inbox/")
@CrossOrigin
public class InboxController {

    @Autowired
    private IInboxService iInboxService;


    //TODO 查询当前用户的收件箱 和 message联合表

    @ApiOperation(value = "查询当前用户某个会话视图的收件箱")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cvsId", value = "会话Id", required = true,dataType = "Long")
    })
    @PostMapping("/query/all")

    public ServerResponse<List<InboxVo>> queryInboxByUserIdCvsIdSyncId(@CurrentUser @ApiIgnore User user, Long cvsId) throws BusinessException {
        List<InboxDo> inboxDos = iInboxService.queryInboxByUserIdCvsId(user.getId(), cvsId);
        if(inboxDos == null){
                return ServerResponse.success(null);
        }
            List<InboxVo> inboxVos = new ArrayList<InboxVo>();
        for(InboxDo inboxDo : inboxDos){
            InboxVo inboxVo = new InboxVo();
            BeanUtils.copyProperties(inboxDo,inboxVo);
            inboxVo.setReaded(MsgReadedEnum.codeOf(inboxDo.getReaded()).getValue());
            inboxVo.setMsgContentType(MsgContentTypeEnum.codeOf(inboxDo.getMsgContentType()).getName());
            if(inboxDo.getSenderId().equals(user.getId())){
                inboxVo.setSelfSend(true);
            }else{
                inboxVo.setSelfSend(false);
            }
            inboxVo.setCreateTime(inboxDo.getCreateTime().getTime());
            inboxVos.add(inboxVo);
        }

        return ServerResponse.success(inboxVos);
    }
    //TODO 分页查询接口 pageHelper
    //TODO 查询当前用户某个会话视图的收件箱 （带SyncId）

    @ApiOperation(value = "查询当前用户某个会话视图的收件箱的最后10条记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cvsId", value = "会话Id", required = true,dataType = "Long")
    })
    @PostMapping("/query/syncid/lastten")
    public ServerResponse<List<InboxVo>> queryLastTenInboxByUserIdCvsIdSyncId(@CurrentUser @ApiIgnore User user, Long cvsId) throws BusinessException {
        List<InboxDo> inboxDos = iInboxService.queryInboxLastTenByUserIdCvsId(user.getId(), cvsId);
        if(inboxDos == null){
            return ServerResponse.success(null);
        }
        List<InboxVo> inboxVos = new ArrayList<InboxVo>();
        for(InboxDo inboxDo : inboxDos){
            InboxVo inboxVo = new InboxVo();
            BeanUtils.copyProperties(inboxDo,inboxVo);
            inboxVo.setReaded(MsgReadedEnum.codeOf(inboxDo.getReaded()).getValue());
            inboxVo.setMsgContentType(MsgReadedEnum.codeOf(inboxDo.getMsgContentType()).name());
            if(inboxDo.getSenderId().equals(user.getId())){
                inboxVo.setSelfSend(true);
            }else{
                inboxVo.setSelfSend(false);
            }
            inboxVo.setCreateTime(inboxDo.getCreateTime().getTime());
            inboxVos.add(inboxVo);
        }

        return ServerResponse.success(inboxVos);
    }

    @ApiOperation(value = "根据syncId查询当前用户某个会话视图的收件箱")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cvsId", value = "会话Id", required = true,dataType = "Long")
    })
    @PostMapping("/query/syncid")
    public ServerResponse<List<InboxVo>> queryInboxBySyncId(@CurrentUser @ApiIgnore User user, Long cvsId, Long syncId) throws BusinessException {
        List<InboxDo> inboxDos = iInboxService.queryInboxBySyncId(user.getId(), cvsId, syncId);
        if(inboxDos == null){
            return ServerResponse.success(null);
        }
        List<InboxVo> inboxVos = new ArrayList<InboxVo>();
        for(InboxDo inboxDo : inboxDos){
            InboxVo inboxVo = new InboxVo();
            BeanUtils.copyProperties(inboxDo,inboxVo);
            inboxVo.setReaded(MsgReadedEnum.codeOf(inboxDo.getReaded()).getValue());
            inboxVo.setMsgContentType(MsgReadedEnum.codeOf(inboxDo.getMsgContentType()).name());
            if(inboxDo.getSenderId().equals(user.getId())){
                inboxVo.setSelfSend(true);
            }else{
                inboxVo.setSelfSend(false);
            }
            inboxVo.setCreateTime(inboxDo.getCreateTime().getTime());
            inboxVos.add(inboxVo);
        }

        return ServerResponse.success(inboxVos);
    }


}
