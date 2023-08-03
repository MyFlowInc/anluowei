package com.workflow.pro.modules.sys.param;

import com.workflow.pro.common.web.base.page.PageRequest;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * 租户列表 -- 参数实体
 *
 * Author: SOME
 * CreateTime: 2022/04/01
 */
@Data
@Alias("SysTenantRequest")
public class SysTenantRequest extends PageRequest {

    /**
     * 名称
     * */
    private String name;

}
