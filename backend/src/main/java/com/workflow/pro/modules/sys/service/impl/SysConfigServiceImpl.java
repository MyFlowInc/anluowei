package com.workflow.pro.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;
import com.workflow.pro.modules.sys.domain.SysConfig;
import com.workflow.pro.modules.sys.repository.SysConfigRepository;
import com.workflow.pro.modules.sys.param.SysConfigRequest;
import com.workflow.pro.modules.sys.service.SysConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigRepository, SysConfig> implements SysConfigService {

    @Resource
    private SysConfigRepository sysConfigRepository;

    @Override
    public List<SysConfig> list(SysConfigRequest request) {
        return sysConfigRepository.selectConfig(request);
    }

    @Override
    public PageResponse<SysConfig> page(SysConfigRequest request) {
        return Pageable.of(request,(()-> sysConfigRepository.selectConfig(request)));
    }
}
