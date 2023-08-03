package com.workflow.pro.common.web.exception.auth.captcha;

/**
 * 验 证 码 失 效 异 常
 *
 * Author: SOME
 * CreateTime: 2022/04/23
 * */
public class CaptchaValidationException extends CaptchaException{

    public CaptchaValidationException(String message){
        super(message);
    }
}
