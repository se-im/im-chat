package com.im.chat.service.asyncupdate;

import com.im.chat.mapper.SessionViewMapper;
import com.im.chat.service.SessionViewRedundantUpdation;
import com.im.user.entity.po.User;
import com.im.user.entity.vo.FriendUserBriefVo;
import com.im.user.service.IFriendService;
import com.mr.response.error.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
@Slf4j
public class SessionViewRedundantUpdatation  implements SessionViewRedundantUpdation {
    @Resource
    private SessionViewMapper sessionViewMapper;


    //修改个人信息时异步更新
    @Override
    public void sessionViewUserRedundantUpdatate(Long userId, String userName, String avatarUrl) throws BusinessException {

            int res = sessionViewMapper.updateRedundantByuserId(userId, userName, avatarUrl);

            if(res < 1){
                throw new BusinessException("会话用户冗余信息更改失败！");
            }
        }
    }


