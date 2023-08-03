package com.workflow.pro.common.secure.captcha;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Captcha 验证码实体
 *
 * Author: SOME
 * CreateTime: 2022/04/01
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecureCaptcha {

    /**
     * 标识
     * */
    private String key;

    /**
     * 验证码
     * */
    private String code;

    /**
     * 验证图片
     * */
    private String image;

}
