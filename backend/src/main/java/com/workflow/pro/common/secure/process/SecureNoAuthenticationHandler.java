package com.workflow.pro.common.secure.process;

import com.workflow.pro.common.tools.core.ServletUtil;
import com.workflow.pro.common.web.domain.Result;
import com.workflow.pro.common.web.domain.ResultCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Security 用户未登陆处理类
 *
 * Author: SOME
 * CreateTime: 2019/10/23
 * */
@Component
public class SecureNoAuthenticationHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        ServletUtil.writeJson(Result.failure(ResultCode.NOT_LOGIN));
    }
}
