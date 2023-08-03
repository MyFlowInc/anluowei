package com.workflow.pro.modules.job.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;
import com.workflow.pro.modules.job.repository.SysJobLogRepository;
import com.workflow.pro.modules.job.domain.SysJobLog;
import com.workflow.pro.modules.job.param.SysJobLogRequest;
import com.workflow.pro.modules.job.service.SysJobLogService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * 任务日志服务实现
 *
 * Author: SOME
 * CreateTime: 2022/04/15
 * */
@Service
public class SysJobLogServiceImpl extends ServiceImpl<SysJobLogRepository, SysJobLog> implements SysJobLogService {

    @Resource
    private SysJobLogRepository sysJobLogRepository;

    @Override
    public List<SysJobLog> list(SysJobLogRequest request) {
        return sysJobLogRepository.selectJobLog(request);
    }

    @Override
    public PageResponse<SysJobLog> page(SysJobLogRequest request) {
        return Pageable.of(request,(()-> sysJobLogRepository.selectJobLog(request)));
    }
}
