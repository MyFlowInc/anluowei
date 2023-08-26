package com.workflow.pro.modules.apitable.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import com.workflow.pro.modules.sys.domain.SysUser;
import lombok.Data;
import com.workflow.pro.common.web.base.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.TableName;
import org.apache.ibatis.type.Alias;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 格协作者对象 apitable_developer
 *
 * @author some
 * @date 2023-06-21
 */


@Alias("ApitableDeveloper")
@TableName("apitable_developer")
@EqualsAndHashCode(callSuper = true)
@Data
public class ApitableDeveloper extends BaseDomain {
    private static final long serialVersionUID = 1L;

    /** Primary Key */

    /**  @TableId(value = "id", type = IdType.AUTO)*/
    @TableField(value = "id")
    private String id;

    /** 表格id */

    private String dstId;

    /** 用户id */

    private String userId;

    /** 管理 */

    private Integer allowManage;

    /** 查看 */

    private Integer allowWatch;

    /** 编辑 */

    private Integer allowEdit;

    /** 保存记录 */

    private Integer allowSave;

    /** 开发者平台的唯一令牌 */

    private String apiKey;

    /** 是否开启 */

    private Integer enable;

    /** 排序 */

    private Long sort;

    /** 租户编号 */

    private String tenantId;

    /**
     * 子级集合
     */
    @TableField(exist = false)
    private SysUser userInfo;
    /**
     * 子级集合
     */
    @TableField(exist = false)
    private String interviewUserName;

}
