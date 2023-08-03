package com.workflow.pro.common.web.exception.auth.captcha;

/**
 * 验 证 码 过 期 异 常
 *
 * Author: SOME
 * CreateTime: 2022/04/23
 * */
public class CaptchaExpiredException extends CaptchaException {

    public CaptchaExpiredException(String message){ super(message); }
}
