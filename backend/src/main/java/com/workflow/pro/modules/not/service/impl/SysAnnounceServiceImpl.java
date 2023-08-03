package com.workflow.pro.modules.not.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;
import com.workflow.pro.modules.not.repository.SysAnnounceRepository;
import com.workflow.pro.modules.not.domain.SysAnnounce;
import com.workflow.pro.modules.not.param.SysAnnounceRequest;
import com.workflow.pro.modules.not.service.SysAnnounceService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class SysAnnounceServiceImpl extends ServiceImpl<SysAnnounceRepository, SysAnnounce> implements SysAnnounceService
{
    @Resource
    private SysAnnounceRepository sysAnnounceRepository;

    @Override
    public List<SysAnnounce> list(SysAnnounceRequest request) {
        return sysAnnounceRepository.selectAnnounce(request);
    }

    @Override
    public PageResponse<SysAnnounce> page(SysAnnounceRequest request) { return Pageable.of(request, (()-> sysAnnounceRepository.selectAnnounce(request))); }
}
