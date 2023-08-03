package com.workflow.pro.common.web.base.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

public class BaseDomainJob {
    /**
     * 创建人
     */
    @ExcelIgnore
    @TableField(value = "create_by")
    @ApiModelProperty("创建人")
    private String createBy;

    /**
     * 创建时间
     */
    @ExcelIgnore
    @TableField(value = "create_time")
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    /**
     * 修改人
     */
    @ExcelIgnore
    @TableField(value = "update_by")
    @ApiModelProperty("修改人")
    private String updateBy;

    /**
     * 修改时间
     */
    @ExcelIgnore
    @TableField(value = "update_time")
    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

    /**
     * 删除
     */
    @ExcelIgnore
    @TableField(value = "deleted")
    @ApiModelProperty("逻辑删除")
    private boolean deleted;

    /**
     * 备注
     */
    @ExcelIgnore
    @TableField("remark")
    @ApiModelProperty("备注")
    private String remark;

}
