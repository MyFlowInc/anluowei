package com.workflow.pro.modules.apitable.param;

import com.amazonaws.services.dynamodbv2.xspec.S;
import com.sun.jna.platform.unix.solaris.LibKstat.KstatNamed.UNION.STR;
import com.workflow.pro.common.web.base.page.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 【请填写功能名称】Request参数请求接口
 *
 * @author some
 * @date 2023-05-30
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class SysCaptchaRequest extends PageRequest {

    /** $column.columnComment */
    private String id;

    /** 邮箱 */
    private String email;

    /** 手机号 */
    private String phone;

    /** 验证码 */
    private String captcha;

    /** 验证类型 0:验证码验证 1:sign验证 */
    private Integer type;

    /** 验证结果 0:未验证 1:验证成功 2:验证失败 */
    private Integer validated;

    /** 过期时长 */
    private Long expired;

    /** 1:Delete，0:Not Deleted */
    private Integer deleted;

    /** 排序 */
    private Long sort;

    /** 租户编号 */
    private String tenantId;

}