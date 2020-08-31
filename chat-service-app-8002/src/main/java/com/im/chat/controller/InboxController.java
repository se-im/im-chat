package com.im.chat.controller;


import com.im.chat.annotation.CurrentUser;
import com.im.chat.entity.po.Inbox;
import com.im.chat.service.IInboxService;
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

@Api(tags = "收件箱相关的api")
@RestController
@RequestMapping("/chat/inbox/")
@CrossOrigin
public class InboxController {

    @Autowired
    private IInboxService iInboxService;

    @ApiOperation(value = "查询当前用户某个会话视图的收件箱")
//    @ApiImplicitParam(name = "cvsId", value = "会话Id", required = true,dataType = "Long")

    @PostMapping("/query")
    public ServerResponse<Inbox> queryInboxByUserIdCvsId(@CurrentUser @ApiIgnore User user,Long cvsId) throws BusinessException {
        Inbox inbox = iInboxService.queryInboxByUserIdCvsId(user.getId(), cvsId);
        return ServerResponse.success(inbox);
    }


}
