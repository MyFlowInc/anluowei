package com.workflow.pro.modules.sys.param;

import com.workflow.pro.common.web.base.page.PageRequest;
import lombok.Data;

/**
 * 字典列表 -- 参数实体
 *
 * Author: SOME
 * CreateTime: 2022/04/01
 */
@Data
public class SysDictRequest extends PageRequest {

    /** 字典名称 */
    private String name;
}
