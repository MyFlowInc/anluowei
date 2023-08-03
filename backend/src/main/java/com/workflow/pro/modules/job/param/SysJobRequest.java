package com.workflow.pro.modules.job.param;

import com.workflow.pro.common.web.base.page.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SysJobRequest extends PageRequest {

    /**
     * 任务名称
     * */
    private String name;

    /**
     * 运行目标
     * */
    private String beanName;
}
