package com.workflow.pro.modules.apitable.param;

import com.workflow.pro.common.web.base.page.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 格基础设置Request参数请求接口
 *
 * @author some
 * @date 2023-06-14
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ApitableNodeShareSettingRequest extends PageRequest {

    /** Primary Key */
    private String id;

    /** 数据id */
    private String dstId;

    /** 节点id */
    private String nodeId;

    /** 视图id */
    private String viewId;

    /** 分享标识 */
    private String shareId;

    /** 分享状态 (0: off, 1: on) */
    private Integer isEnabled;

    /** 允许保存 (0: No, 1: Yes) */
    private Integer allowSave;

    /** 允许编辑(0: No, 1: Yes) */
    private Integer allowEdit;

    /** 分享属性 */
    private String props;

    /** 排序 */
    private Long sort;

    /** 租户编号 */
    private String tenantId;

}