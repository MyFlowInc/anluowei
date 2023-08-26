package com.workflow.pro.modules.apitable.param;

import com.workflow.pro.common.web.base.page.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 数据单记录Request参数请求接口
 *
 * @author some
 * @date 2023-05-22
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ApitableDatasheetRecordRequest extends PageRequest {

    /** Primary key */
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
    private Integer deleted;

    /** 排序 */
    private Long sort;

    /** 租户编号 */
    private String tenantId;

}