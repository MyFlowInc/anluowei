package com.workflow.pro.modules.cms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
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
@Alias("CmsCategoryPower")
@TableName("cms_category_power")
public class CmsCategoryPower {

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    @TableField(value = "id")
    private String id;

    /**
     * 角色编号
     */
    @TableField("category_id")
    private String categoryId;

    /**
     * 部门编号
     */
    @TableField("dept_id")
    private String deptId;

}
