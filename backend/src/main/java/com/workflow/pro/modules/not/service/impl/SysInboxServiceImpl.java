package com.workflow.pro.modules.not.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;
import com.workflow.pro.modules.not.repository.SysInboxRepository;
import com.workflow.pro.modules.not.domain.SysInbox;
import com.workflow.pro.modules.not.param.SysInboxRequest;
import com.workflow.pro.modules.not.service.SysInboxService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysInboxServiceImpl extends ServiceImpl<SysInboxRepository, SysInbox> implements SysInboxService {

    @Resource
    private SysInboxRepository sysInboxRepository;

    @Override
    public List<SysInbox> list(SysInboxRequest request) {
        return sysInboxRepository.selectInbox(request);
    }

    @Override
    public PageResponse<SysInbox> page(SysInboxRequest request) {
        return Pageable.of(request, (()-> sysInboxRepository.selectInbox(request)));
    }
}
