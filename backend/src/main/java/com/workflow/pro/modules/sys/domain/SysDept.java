package com.workflow.pro.modules.sys.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;
import com.baomidou.mybatisplus.annotation.TableField;
import com.workflow.pro.common.web.base.domain.TreeDomain;

/**
 * 部门模型
 *
 * Author: SOME
 * CreateTime: 2019/10/23
 * */
@Data
@Alias("SysDept")
@TableName("sys_dept")
@EqualsAndHashCode(callSuper = true)
public class SysDept extends TreeDomain<SysDept> {

    /**
     * 部门 ID
     * */
    private String id;

    /**
     * 部门名称
     * */
    @TableField("name")
    private String name;

    /**
     * 显示顺序
     * */
    @TableField("sort")
    private Integer sort;

    /**
     * 是否启用
     * */
    @TableField("enable")
    private boolean enable;

    /**
     * 地址
     * */
    @TableField("address")
    private String address;

    /**
     * 租户
     * */
    @TableField("tenant_id")
    private String tenantId;

}
