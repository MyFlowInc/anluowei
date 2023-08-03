package com.workflow.pro.common.constant;

/**
 * Json Web Token 静态配置
 *
 * Author: SOME
 * CreateTime: 2019/10/23
 */
public class TokenConstant {

    /**
     * Access Token 密钥
     * */
    public static final String SECRET = "CMS-ADMIN-SECRET";

    /**
     * Access Token 前缀
     * */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * Access Token 发布者
     * */
    public static final String ISSUER = "Cms-Admin";

    /**
     * Http Header Token 请求头
     * */
    public static final String TOKEN_HEADER =  "Authorization";

    /**
     * Http Header Token Key
     * */
    public static final String TOKEN_HEADER_KEY = "Authorization-Key";

}
