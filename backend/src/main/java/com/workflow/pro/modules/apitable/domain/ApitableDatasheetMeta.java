package com.workflow.pro.modules.apitable.domain;


import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;
import com.workflow.pro.common.web.base.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.TableName;
import org.apache.ibatis.type.Alias;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 数据元数据对象 apitable_datasheet_meta
 *
 * @author some
 * @date 2023-05-22
 */


@Alias("ApitableDatasheetMeta")
@TableName("apitable_datasheet_meta")
@EqualsAndHashCode(callSuper = true)
@Data
public class ApitableDatasheetMeta extends BaseDomain {
    private static final long serialVersionUID = 1L;

    /** Primary key */

    /**  @TableId(value = "id", type = IdType.AUTO)*/
    @TableField(value = "id")
    private String id;

    /** 表格id */

    private String dstId;

    /** 元数据 */

    private String metaData;

    /** 版本号 */

    private Long revision;

    /** 删除标识 */


    /** 排序 */

    private Long sort;

    /** 租户编号 */

    private String tenantId;

}
