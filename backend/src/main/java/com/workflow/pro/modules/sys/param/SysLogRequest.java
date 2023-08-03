package com.workflow.pro.modules.sys.param;

import com.workflow.pro.common.web.base.page.PageRequest;
import lombok.Data;

/**
 * 日志列表 -- 参数实体
 *
 * Author: SOME
 * CreateTime: 2022/04/01
 */
@Data
public class SysLogRequest extends PageRequest {

    /**
     * 标题
     * */
    private String title;

    /**
     * 类型
     * */
    private Boolean isAuth;

    /**
     * 状态
     * */
    private Boolean state;
}
