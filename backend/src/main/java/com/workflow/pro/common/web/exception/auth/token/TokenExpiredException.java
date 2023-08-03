package com.workflow.pro.common.web.exception.auth.token;

/**
 * TOKEN 过 期 异 常
 *
 * Author: SOME
 * CreateTime: 2022/04/23
 * */
public class TokenExpiredException extends TokenException{

    public TokenExpiredException(String message){ super(message); }
}
