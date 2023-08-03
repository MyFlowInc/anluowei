package com.workflow.pro.modules.apitable.param;

import com.workflow.pro.common.web.base.page.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 邀请同意记录Request参数请求接口
 *
 * @author some
 * @date 2023-06-30
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ApitableInviteRecordRequest extends PageRequest {

    /** Primary Key */
    private String id;

    /** 空间id */
    private String spaceId;

    /** 表格id */
    private String dstId;

    /** 邀请id */
    private String inviter;

    /** 成员id */
    private String accepter;

    /** 0: 邀请; 1: 文件导出; 2: 邀请链接 */
    private Integer type;

    /** 内容 */
    private String content;

    /** 排序 */
    private Long sort;

    /** 忽略消息 */
    private Integer ignoreMsg = 0;

    /** $column.columnComment */
    private Integer deleted;

    /** 租户编号 */
    private String tenantId;

}