package com.workflow.pro.common.web.exception.auth.captcha;

import org.springframework.security.core.AuthenticationException;

/**
 * 验 证 码 异 常 基 类
 *
 * Author: Some
 * CreateTime: 2022/04/23
 * */
public class CaptchaException extends AuthenticationException {

    public CaptchaException(String message){
        super(message);
    }

}
