package com.workflow.pro.modules.sys.param;

import lombok.Data;

import java.util.List;

/**
 * 租户授权列表 -- 参数实体
 *
 * Author: SOME
 * CreateTime: 2022/04/01
 */
@Data
public class SysTenantGiveRequest {

    /**
     * 租户编号
     * */
    private String tenantId;

    /**
     * 权限列表
     * */
    private List<String> powerIds;

}
