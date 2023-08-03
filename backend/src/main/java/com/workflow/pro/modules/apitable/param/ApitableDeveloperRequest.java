package com.workflow.pro.modules.apitable.param;

import com.workflow.pro.common.web.base.page.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 格协作者Request参数请求接口
 *
 * @author some
 * @date 2023-06-21
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ApitableDeveloperRequest extends PageRequest {

    /** Primary Key */
    private String id;

    /** 表格id */
    private String dstId;

    /** 用户id */
    private String userId;

    /** 管理 */
    private Integer allowManage;

    /** 查看 */
    private Integer allowWatch;

    /** 编辑 */
    private Integer allowEdit;

    /** 保存记录 */
    private Integer allowSave;

    /** 开发者平台的唯一令牌 */
    private String apiKey;

    /** 是否开启 */
    private Integer enable;

    /** 排序 */
    private Long sort;

    /** 租户编号 */
    private String tenantId;

}