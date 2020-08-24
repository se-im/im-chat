package com.im.chat.contorller;


import com.im.chat.annotation.CurrentUser;
import com.im.chat.enums.CvsTypeEnum;
import com.im.chat.service.ISessionViewService;
import com.im.user.entity.po.User;
import com.mr.response.error.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/chat/cvs/")
public class SessionViewController
{
    @Autowired
    private ISessionViewService iSessionViewService;

    //cvsType --> U单聊  G群聊

    @PostMapping("/create")
    public void createSessionView(@CurrentUser User user, String cvsType, Long entityId) throws BusinessException {

        if(cvsType.equals("U")){
            iSessionViewService.createSessionView(user.getId(), CvsTypeEnum.U,entityId);
        }else if(cvsType.equals("G")){
            iSessionViewService.createSessionView(user.getId(), CvsTypeEnum.G,entityId);
        }else{
            throw new BusinessException("非法的会话类型");
        }

    }
}
