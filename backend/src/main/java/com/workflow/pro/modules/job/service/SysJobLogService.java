package com.workflow.pro.modules.job.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.modules.job.domain.SysJobLog;
import com.workflow.pro.modules.job.param.SysJobLogRequest;
import java.util.List;

/**
 * 任务日志服务接口
 *
 * Author: SOME
 * CreateTime: 2022/04/15
 * */
public interface SysJobLogService extends IService<SysJobLog> {

    /**
     * 获取任务日志列表
     *
     * @param request 查询参数
     * */
    List<SysJobLog> list(SysJobLogRequest request);

    /**
     * 获取任务日志列表 (分页)
     *
     * @param request 查询参数
     * */
    PageResponse<SysJobLog> page(SysJobLogRequest request);

}
