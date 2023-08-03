package com.workflow.pro.common.web.base.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Base 实体
 *
 * Author: SOME
 * CreateTime: 2022/03/23
 * */
@Data
public class BaseDomain implements Serializable {

    /**
     * 创建人
     * */
    @ExcelIgnore
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    // @TableField(value = "create_by")
    @ApiModelProperty("创建人")
    private String createBy;


    /**
     * 修改人
     * */
    @ExcelIgnore
    @TableField(value = "update_by", fill = FieldFill.INSERT_UPDATE)
    // @TableField(value = "update_by")
    @ApiModelProperty("修改人")
    private String updateBy;

    /**
     * 创建时间
     * */
    @ExcelIgnore
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;


    /**
     * 修改时间
     * */
    @ExcelIgnore
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

    /**
     * 删除
     * */
    @ExcelIgnore
    @TableField(value = "deleted", fill = FieldFill.INSERT,exist = false)
    @ApiModelProperty("逻辑删除")
    private boolean deleted;

    /**
     * 备注
     * */
    @ExcelIgnore
    @TableField("remark")
    @ApiModelProperty("备注")
    private String remark;

}
