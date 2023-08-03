package com.workflow.pro.modules.not.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;
import com.workflow.pro.modules.not.domain.SysAnnounceUser;
import com.workflow.pro.modules.not.repository.SysAnnounceUserRepository;
import com.workflow.pro.modules.not.service.SysAnnounceUserService;
import com.workflow.pro.modules.not.param.SysAnnounceUserRequest;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class SysAnnounceUserServiceImpl extends ServiceImpl<SysAnnounceUserRepository, SysAnnounceUser>
implements SysAnnounceUserService {

    @Resource
    private SysAnnounceUserRepository sysAnnounceUserRepository;

    @Override
    public List<SysAnnounceUser> list(SysAnnounceUserRequest request) {
        return sysAnnounceUserRepository.selectAnnounceUser(request);
    }

    @Override
    public PageResponse<SysAnnounceUser> page(SysAnnounceUserRequest request) {
        return Pageable.of(request, (()-> sysAnnounceUserRepository.selectAnnounceUser(request)));
    }
}
