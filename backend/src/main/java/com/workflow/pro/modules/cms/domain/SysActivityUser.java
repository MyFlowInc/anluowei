package com.workflow.pro.modules.cms.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.workflow.pro.common.web.base.domain.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

/**
 * activty实体
 *
 * @author Some
 * @date 2022-09-20
 */
@Data
@Alias("SysActivityUser")
@TableName("sys_activity_user")
@EqualsAndHashCode(callSuper = true)
public class SysActivityUser extends BaseDomain {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    @ExcelProperty("id")
    private int id;

    /**
     * 用户id
     */
    @TableField("user_id")
    @ExcelProperty("用户id")
    private String userId;

    /**
     * 用户名
     */
    @TableField("user_name")
    @ExcelProperty("用户名")
    private String userName;
    /**
     * 用户头像
     */
    @TableField("user_image")
    @ExcelProperty("用户头像")
    private String userImage;

    /**
     * 活动id
     */
    @TableField("activity_id")
    @ExcelProperty("活动id")
    private String activityId;

    /**
     * null
     */
    @TableField("activity_name")
    @ExcelIgnore
    private String activityName;
    /**
     * 活动名
     * */
    @TableField(exist = false)
    @ExcelProperty("活动名称")
    private String activityTitle;


    /**
     * 活动名
     * */
    @TableField(exist = false)
    @ExcelProperty("部门名称")
    private String activityUserDeptName;

}