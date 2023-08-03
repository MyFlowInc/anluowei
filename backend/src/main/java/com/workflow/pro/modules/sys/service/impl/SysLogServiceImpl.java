package com.workflow.pro.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;
import com.workflow.pro.common.web.interceptor.annotation.DataScope;
import com.workflow.pro.common.web.interceptor.enums.Scope;
import com.workflow.pro.modules.sys.domain.SysLog;
import com.workflow.pro.modules.sys.repository.SysLogRepository;
import com.workflow.pro.modules.sys.param.SysLogRequest;
import com.workflow.pro.modules.sys.service.SysLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogRepository, SysLog> implements SysLogService {

    @Resource
    private SysLogRepository sysLogRepository;

    @Override
    public List<SysLog> list(SysLogRequest request) {
        return sysLogRepository.selectLog(request);
    }

    @Override
    @DataScope(scope = Scope.SELF)
    public PageResponse<SysLog> page(SysLogRequest request) {
        return Pageable.of(request,(()-> sysLogRepository.selectLog(request)));
    }
}
