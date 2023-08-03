package com.workflow.pro.common.web.exception.auth.token;

import org.springframework.security.core.AuthenticationException;

/**
 * TOKEN 异 常 类
 *
 * Author: SOME
 * CreateTime: 2022/04/23
 * */
public class TokenException extends AuthenticationException {

    public TokenException(String message){
        super(message);
    }
}