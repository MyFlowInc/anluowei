package com.workflow.pro.common.secure.process;

import com.workflow.pro.common.aop.enums.Action;
import com.workflow.pro.common.context.BaseContext;
import com.workflow.pro.common.secure.services.SecureUser;
import com.workflow.pro.common.secure.uutoken.SecureUserTokenService;
import com.workflow.pro.common.tools.core.ServletUtil;
import com.workflow.pro.common.web.domain.Result;
import com.workflow.pro.common.web.domain.ResultCode;
import com.workflow.pro.common.secure.uutoken.SecureUserToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;
import java.io.IOException;

/**
 * Describe: Security 登陆成功处理类
 * Author: SOME
 * CreateTime: 2019/10/23
 * */
@Component
public class SecureLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private BaseContext context;

    @Resource
    private SecureUserTokenService customUserDetailsTokenService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
            SecureUser secureUser = (SecureUser) authentication.getPrincipal();

            SecureUserToken userToken = customUserDetailsTokenService.createToken(secureUser);
            String tokenKey = customUserDetailsTokenService.saveToken(userToken);
            String tokenValue = userToken.getToken();

            // 登 录 日 志
            context.record("登录","登录成功", Action.AUTH, true, "","");

            ServletUtil.writeJson(Result.success(ResultCode.LOGIN_SUCCESS,tokenKey,tokenValue));
    }
}
