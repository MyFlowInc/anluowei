package com.workflow.pro.modules.workflow.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.workflow.pro.common.web.base.domain.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

/**
 * 工作流主表实体
 *
 * @author some
 * @date 2022-11-16
 */
@Data
@Alias("WorkFlow")
@TableName("work_flow")
@EqualsAndHashCode(callSuper = true)
public class WorkFlow extends BaseDomain {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    @TableField(value = "id")
    private int id;

    /** 名称 */
    private String name;

    private Integer statusId;

    /** user_id */
    private String userId;

    /** 排序 */
    private Long sort;

    /** 租户编号 */
    private String tenantId;


}
