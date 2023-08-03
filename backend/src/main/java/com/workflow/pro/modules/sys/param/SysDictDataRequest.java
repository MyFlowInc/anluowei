package com.workflow.pro.modules.sys.param;

import com.workflow.pro.common.web.base.page.PageRequest;
import lombok.Data;

/**
 * 数据字典 -- 参数实体
 *
 * Author: SOME
 * CreateTime: 2022/04/01
 * */
@Data
public class SysDictDataRequest extends PageRequest {

    /** 标签 */
    private String label;

    /** 类型 */
    private String code;
}
