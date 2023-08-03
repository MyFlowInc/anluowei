package com.workflow.pro.modules.apitable.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;
import com.workflow.pro.common.web.base.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.TableName;
import org.apache.ibatis.type.Alias;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 【请填写功能名称】对象 sys_captcha
 *
 * @author some
 * @date 2023-05-30
 */


@Alias("SysCaptcha")
@TableName("sys_captcha")
@EqualsAndHashCode(callSuper = true)
@Data
public class SysCaptcha extends BaseDomain {
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */

    /**  @TableId(value = "id", type = IdType.AUTO)*/
    @TableField(value = "id")
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

    private int expired;

    /** 1:Delete，0:Not Deleted */


    /** 排序 */

    private Long sort;

    /** 租户编号 */

    private String tenantId;

}
