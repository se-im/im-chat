package com.im.chat.controller;


import com.im.chat.annotation.CurrentUser;
import com.im.chat.entity.po.Inbox;
import com.im.chat.service.IInboxService;
import com.im.user.entity.po.User;
import com.mr.response.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "收件箱相关的api")
@RestController("/chat/inbox/")
public class InboxController
{

    @Autowired
    private IInboxService iInboxService;

    //TODO 查询当前用户指定会话视图的收件箱
    @ApiOperation(value = "查询当前用户指定会话视图的收件箱")
    @GetMapping("/query")
    public ServerResponse<Inbox> queryInbox(@CurrentUser @ApiIgnore User user,Long cvsId)
    {
        Inbox inbox = iInboxService.queryInboxByUserIdCvsId(user.getId(), cvsId);
        return ServerResponse.success(inbox);
    }


}
