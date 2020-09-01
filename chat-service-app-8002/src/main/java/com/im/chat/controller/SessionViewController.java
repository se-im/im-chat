package com.im.chat.controller;


import com.im.chat.annotation.CurrentUser;
import com.im.chat.entity.po.SessionView;
import com.im.chat.entity.vo.SessionViewVo;
import com.im.chat.enums.CvsNotDisturbEnum;
import com.im.chat.enums.CvsStickEnum;
import com.im.chat.enums.CvsTypeEnum;
import com.im.chat.netty.OnlineConnectorManager;
import com.im.chat.service.ISessionViewService;
import com.im.user.entity.po.User;
import com.im.user.entity.vo.GroupUserBriefVo;
import com.im.user.service.IGroupService;
import com.mr.response.ServerResponse;
import com.mr.response.error.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Api(tags = "会话相关的api")
@RestController
@RequestMapping("/chat/cvs/")
@CrossOrigin
public class SessionViewController
{
    @Autowired
    private ISessionViewService iSessionViewService;

    @Reference
    private IGroupService iGroupService;

    @Autowired
    private OnlineConnectorManager onlineConnectorManager;

    //cvsType --> U单聊  G群聊
    @ApiOperation(value = "创建会话")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cvsType", value = "会话类型", required = true,dataType = "String", example = "U"),
            @ApiImplicitParam(name = "entityId", value = "会话关联的实体Id", required = true,dataType = "Long", example = "18")
    }
    )
    @PostMapping("/create")
    public ServerResponse<Long> createSessionView(@CurrentUser @ApiIgnore User user, String cvsType, Long entityId) throws BusinessException {

        Long sessionViewId = iSessionViewService.createSessionView(user, CvsTypeEnum.nameOf(cvsType), entityId);
        return ServerResponse.success(sessionViewId);
    }

    @ApiOperation(value = "查找当前用户的所有会话视图")
    @GetMapping("/list")
    //cvsType 时间Long
    public ServerResponse<List<SessionViewVo>> queryMySessionViewList(@CurrentUser @ApiIgnore User user)
    {
        List<SessionView> sessionViews = iSessionViewService.queryMySessionViewList(user.getId());
        List<SessionViewVo> sessionViewVoList = new ArrayList<SessionViewVo>();
        for(SessionView sessionView:sessionViews){
            sessionViewVoList.add(convertSessionView(sessionView));
        }
        return ServerResponse.success(sessionViewVoList);
    }

    @ApiOperation(value = "将当前用户指定会话的未读消息数置零")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cvsId", value = "会话Id", required = true,dataType = "Long"),
    }
    )
    @PostMapping("/clearUnReaded")
    //cvsType 时间Long
    public ServerResponse<String> clearUnReaded(@CurrentUser @ApiIgnore User user,Long cvsId) throws BusinessException {
        SessionView sessionView = iSessionViewService.selectById(cvsId);
        if(sessionView == null){
            throw new BusinessException("不存的会话视图");
        }

        //TODO 判断该会话视图是否属于当前用户
        Long cvsOwnerId = sessionView.getOwnerId();
        if(!cvsOwnerId.equals(user.getId())){
            throw new BusinessException("当前用户没有该会话视图！会话Id错误！");
        }
        iSessionViewService.clearUnReaded(cvsId);
        return ServerResponse.success();
    }


    @ApiOperation(value = "为某个群的所有成员创建一条会话视图")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupId", value = "群Id", required = true,dataType = "Long"),
    }
    )
    @PostMapping("/createCvsForGroupUser")
    public ServerResponse<String> createCvsForGroupUser(Long groupId) throws BusinessException {
        List<GroupUserBriefVo> groupUserBriefVos = iGroupService.queryGroupUsers(groupId);
        for(GroupUserBriefVo groupUserBriefVo:groupUserBriefVos){
            Long userid = groupUserBriefVo.getUserid();
            iSessionViewService.createSingleSessionView(userid,"G",groupId);
        }
        return ServerResponse.success();
    }


    //TODO
    @ApiOperation(value = "删除会话视图")
    @GetMapping("/delete")
    public ServerResponse<String> deleteSessionView(Long userId,String cvsType,Long cvsId) throws BusinessException {
        iSessionViewService.deleteSessionView(cvsId);
        return ServerResponse.success();
    }


    private SessionViewVo convertSessionView(SessionView sessionView)
    {
        SessionViewVo sessionViewVo = new SessionViewVo();
        BeanUtils.copyProperties(sessionView,sessionViewVo);
        sessionViewVo.setCvsType(CvsTypeEnum.codeOf(sessionView.getCvsType()).getName());
        sessionViewVo.setStick(CvsStickEnum.codeOf(sessionView.getStick()).getStatus());
        sessionViewVo.setNotDisturb(CvsNotDisturbEnum.codeOf(sessionView.getNotDisturb()).getStatus());
        sessionViewVo.setLastMessageTime(sessionView.getLastMessageTime().getTime());
        sessionViewVo.setOnline(onlineConnectorManager.isOnline(sessionView.getRelationEntityId()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime lastMessageTime = sessionView.getLastMessageTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime todayBeginning = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 0, 0);
        if(lastMessageTime.isBefore(todayBeginning)){
            formatter = DateTimeFormatter.ofPattern("yy-MM-dd");
        }else {
            formatter = DateTimeFormatter.ofPattern("yy-MM-dd hh:mm");
        }
        sessionViewVo.setLastMessageTimeFormated(formatter.format(lastMessageTime));
        return sessionViewVo;
    }
}
