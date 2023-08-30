package com.workflow.pro.common.constant;

/**
 * Security 静态配置
 * <p>
 * Author: SOME
 * CreateTime: 2022/03/28
 */
public class SecurityConstant {

    /**
     * 不需要认证的接口资源
     */
    public static final String HTTP_ACT_MATCHERS = "/webjars/springfox-swagger-ui/fonts/**,/swagger-resources,/swagger-resources/configuration/**,/webjars**,/swagger-ui.html,/swagger-ui/**,/webjars/springfox-swagger-ui/**,/v2/api-docs,/api/login,/api/captcha/create," +
            "/sys/login_by_weixin,/api/sys/user/register,"
            + "/api/sys/user/login_email,"
            + "/api/sys/sysCaptcha/send,"
            + "/api/sys/omsOrder/notify,"

            + "/api/sys/apitableDatasheetRecord/getRecord,"
            + "/api/sys/apitableDatasheetRecord/setRecordValue,"
            + "/api/sys/user/password/reset/email,"
            + "/file/**/**/**/*,"
            + "/file/*,"



            + "/api/sys/user/register_email,";

    /**
     * 不需要认证的静态资源
     */
    public static final String WEB_ACT_MATCHERS = "/favicon.ico"
            + "/file/**/**/**/*,"
            + "/file/*,";

    /**
     * 默认的登录接口
     */
    public static final String LOGIN_URL = "/api/login";

    /**
     * 登录接口方式
     */
    public static final String LOGIN_METHOD = "POST";

    /**
     * 默认的注销接口
     */
    public static final String LOGOUT_URL = "/api/logout";

    public static final String Wx_LOGOUT_URL = "/login_by_weixin";

    /**
     * 是否开启验证码
     */
    public static final Boolean IS_CAPTCHA_VERIFICATION = true;

    /**
     * Captcha 过期时间
     */
    public static final long CAPTCHA_EXPIRATION = 1000 * 60 * 5;

    /**
     * Token 过期时间
     */
    public static final long TOKEN_EXPIRATION = 1000 * 60 * 30 * 365;

    public static final long CACHE_CODE = 1000 * 60 * 10;
}
