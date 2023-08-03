package com.workflow.pro.modules.oss.param;

import com.workflow.pro.common.web.base.page.PageRequest;
import lombok.Data;

/**
 * 文件列表 -- 参数实体
 *
 * Author: SOME
 * CreateTime: 2022/04/01
 */
@Data
public class SysOssRequest extends PageRequest {

    private String name;
}
