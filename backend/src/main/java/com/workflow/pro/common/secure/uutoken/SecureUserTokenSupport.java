package com.workflow.pro.common.secure.uutoken;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.workflow.pro.common.constant.SecurityConstant;
import com.workflow.pro.common.constant.TokenConstant;
import com.workflow.pro.common.tools.core.PatternUtil;
import com.workflow.pro.common.tools.core.ServletUtil;
import com.workflow.pro.common.web.domain.Result;
import com.workflow.pro.common.web.domain.ResultCode;
import com.workflow.pro.common.web.exception.auth.token.TokenExpiredException;
import com.workflow.pro.common.web.exception.auth.token.TokenValidationException;
import com.workflow.pro.common.context.BeanContext;
import com.workflow.pro.common.secure.services.SecureUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Token Filter 主要增加 Token 的验证
 * <p>
 * Author: SOME
 * CreateTime: 2022/03/27
 */
public class SecureUserTokenSupport extends OncePerRequestFilter {

    @Resource
    private BeanContext beanContext;

    private SecureUserTokenService customUserDetailsTokenService;

    public SecureUserTokenSupport() {
        // Secure Details
        this.customUserDetailsTokenService = beanContext.getBean("secureUserTokenService", SecureUserTokenService.class);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String tokenHeader = request.getHeader(TokenConstant.TOKEN_HEADER);
        String tokenHeaderKey = request.getHeader(TokenConstant.TOKEN_HEADER_KEY);


        // token param verify empty
        if (tokenHeader == null) {
            ServletUtil.writeJson(Result.failure(ResultCode.TOKEN_MISSION));
            return;
        }
        // token verify
        //todo 开发环境使用
        SecureUser secureUser;
        if (tokenHeader.equals("admin")) {
            tokenHeader="eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxIiwic3ViIjoiYWRtaW4iLCJpYXQiOjE2NzI5OTczNzcsImlzcyI6IkNtcy1BZG1pbiJ9.QF9h18e1V88J1TaCXm7eqhAogms-zZfgLHlowJlcnDlmjKyfIeKTk6u0I1OrUzNBn4bighwz8ggvJE52GTFxoA";
            tokenHeaderKey="aeb1ba1a-1b4f-403d-9682-65984cc8e44c";
        }
            try {
                SecureUserToken userToken = customUserDetailsTokenService.verifyToken(tokenHeaderKey, tokenHeader.replaceFirst(TokenConstant.TOKEN_PREFIX, ""));
                secureUser = userToken.getSecureUser();
            } catch (TokenValidationException e) {
                ServletUtil.writeJson(Result.failure(ResultCode.TOKEN_INVALID));
                return;
            } catch (TokenExpiredException e) {
                ServletUtil.writeJson(Result.failure(ResultCode.TOKEN_EXPIRED));
                return;
        }
        // return UsernamePasswordAuthenticationToken
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(secureUser, secureUser.getId(), secureUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }


    // TODO 修正匹配策略
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        List<String> pattern = Arrays.asList(SecurityConstant.HTTP_ACT_MATCHERS.split(","));
        return PatternUtil.matches(pattern, ServletUtil.getRequestURI());
    }
}
