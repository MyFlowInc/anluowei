package com.workflow.pro.modules.sys.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * 角色部门模型
 *
 * Author: SOME
 * CreateTime: 2022/03/23
 * */

@Data
@Alias("SysRoleDept")
@TableName("sys_role_dept")
public class SysRoleDept{

    /**
     * 编号
     */
    @TableId("id")
    private String id;

    /**
     * 角色编号
     */
    @TableField("role_id")
    private String roleId;

    /**
     * 部门编号
     */
    @TableField("dept_id")
    private String deptId;

    /**
     * 租户编号
     * */
    @TableField("tenant_id")
    private String tenantId;

}
