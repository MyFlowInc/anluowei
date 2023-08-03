package com.workflow.pro.common.secure.process;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.workflow.pro.common.constant.TokenConstant;
import com.workflow.pro.common.context.BaseContext;
import com.workflow.pro.common.secure.uutoken.SecureUserTokenService;
import com.workflow.pro.common.tools.core.ServletUtil;
import com.workflow.pro.common.web.domain.Result;
import com.workflow.pro.common.web.domain.ResultCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.io.IOException;

/**
 * Security 注销成功处理类
 *
 * Author: SOME
 * CreateTime: 2019/10/23
 * */
@Component
public class SecureLogoutSuccessHandler implements LogoutSuccessHandler {

    @Resource
    private BaseContext context;

    @Resource
    private SecureUserTokenService customUserDetailsTokenService;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        String token = httpServletRequest.getHeader(TokenConstant.TOKEN_HEADER).replaceFirst(TokenConstant.TOKEN_PREFIX, "");
        customUserDetailsTokenService.destroyToken(token);
        ServletUtil.writeJson(Result.success(ResultCode.LOGOUT_SUCCESS));
    }
}
