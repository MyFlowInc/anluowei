package com.workflow.pro.modules.sys.param;

import com.workflow.pro.common.web.base.page.PageRequest;
import lombok.Data;

/**
 * 配置列表 -- 参数实体
 *
 * Author: SOME
 * CreateTime: 2022/04/01
 */
@Data
public class SysConfigRequest extends PageRequest {

    /** 名称 */
    private String name;

    /** 键 */
    private String key;

}
