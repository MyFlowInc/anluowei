package com.workflow.pro.modules.apitable.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import com.workflow.pro.common.web.base.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 数据单记录对象 apitable_datasheet_record
 *
 * @author some
 * @date 2023-05-22
 */

@Builder(toBuilder = true)
@Alias("ApitableDatasheetRecord")
@TableName("apitable_datasheet_record")
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApitableDatasheetRecord extends BaseDomain {
    private static final long serialVersionUID = 1L;

    /** Primary key */

    /**  @TableId(value = "id", type = IdType.AUTO)*/
    @TableField(value = "id")
    private String id;

    /** 操作id */

    private String recordId;

    /** 表格id */

    private String dstId;

    /** 一行记录数据(对应于每个字段) */

    private String data;

    /** 版本历史 */

    private String revisionHistory;

    /** 版本号 */

    private Long revision;

    /** 字段更新信息 */

    private String fieldUpdatedInfo;

    /** 删除标识 */


    /** 排序 */

    private Long sort;

    /** 租户编号 */

    private String tenantId;

}
