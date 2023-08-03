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
 * 1实体
 *
 * @author Some
 * @date 2022-11-16
 */
@Data
@Alias("WorkFlowField")
@TableName("work_flow_field")
@EqualsAndHashCode(callSuper = true)
public class WorkFlowField extends BaseDomain {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    @TableField(value = "id")
    private int id;

    /** 名称 */
    private String name;

    /** 流程id */
    private int flowId;

    /** 值类型 */
    private String type;

    /** 排序 */
    private Long sort;

    /** 租户编号 */
    private String tenantId;


}
