package com.workflow.pro.modules.apitable.param;

import com.workflow.pro.common.web.base.page.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 数据格Request参数请求接口
 *
 * @author some
 * @date 2023-05-22
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ApitableDatasheetRequest extends PageRequest {

    /** Primary key */
    private String id;

    private List<String> ids;

    /** Custom ID */
    private String dstId;

    /** 节点id */
    private String nodeId;

    /** 表格名 */
    private String dstName;

    /** 工作空间 */
    private String spaceId;

    /** 版本号 */
    private Long revision;

    /** 删除表示(0:No,1:Yes) */
    private Integer deleted;

    /** 排序 */
    private Long sort;

    /** 租户编号 */
    private String tenantId;

    /** 租户编号 */
    private String createBy;

    private Integer isDeveloper;
}