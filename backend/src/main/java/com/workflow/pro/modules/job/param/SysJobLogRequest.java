package com.workflow.pro.modules.job.param;

import com.workflow.pro.common.web.base.page.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SysJobLogRequest extends PageRequest {

    /**
     * 任务名称
     * */
    private String jobName;

    /**
     * 执行状态
     * */
    private Boolean state;

}
