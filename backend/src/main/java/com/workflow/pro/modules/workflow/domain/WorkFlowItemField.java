package com.workflow.pro.modules.workflow.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.workflow.pro.common.web.base.domain.BaseDomain;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

/**
 * 2实体
 *
 * @author Some
 * @date 2022-11-16
 */
@Data
    @TableName("work_flow_item_field")
    @Alias("WorkFlowItemField")
    @EqualsAndHashCode(callSuper = true)
public class WorkFlowItemField extends BaseDomain{

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    @TableField(value = "id")
    private int id;

    /** 流程id */
    private Integer flowItemId;

    /** 流程id */
    private Integer fieldId;

    /** 内容 */
    private String flowValue;

    /** 名称 */
    private String name;

    /** 排序 */
    private Long sort;


    /** 租户编号 */
    private String tenantId;


}
