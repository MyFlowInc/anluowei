package com.workflow.pro.modules.job.demo;

import com.workflow.pro.common.context.UserContext;
import com.workflow.pro.common.quartz.base.BaseQuartz;
import com.workflow.pro.common.secure.uutoken.SecureUserToken;
import com.workflow.pro.common.secure.uutoken.SecureUserTokenService;
import com.workflow.pro.common.tools.core.TokenUtil;
import com.workflow.pro.modules.sys.domain.SysUser;
import com.workflow.pro.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("SyncUserInfo")
public class SyncUserInfo implements BaseQuartz {

    @Resource
    private SysUserService sysUserService;
    @Resource
    private UserContext userContext;
    @Resource
    private SecureUserTokenService customUserDetailsTokenService;
    @Autowired
    private SysUserService sysUserWxService;

    @Override
    public void run(String param) throws Exception {
        System.out.println("执行 同步 微信 任务");
        SysUser secureUser = sysUserService.getById("4");

        SecureUserToken userToken = new SecureUserToken();
        userToken.setToken(TokenUtil.create(secureUser.getId(), secureUser.getUsername()));
        userToken.setSecureUser(secureUser);
        userContext = new UserContext();
        sysUserWxService.syncWxUserInfo();
    }
}
