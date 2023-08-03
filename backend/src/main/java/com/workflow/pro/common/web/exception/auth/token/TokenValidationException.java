package com.workflow.pro.common.web.exception.auth.token;

/**
 * TOKEN 验 证 异 常
 *
 * Author: SOME
 * CreateTime: 2022/04/23
 * */
public class TokenValidationException extends TokenException{

    public TokenValidationException(String message){
        super(message);
    }
}
