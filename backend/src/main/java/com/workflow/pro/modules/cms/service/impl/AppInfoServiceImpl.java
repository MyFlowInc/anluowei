package com.workflow.pro.modules.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;
import com.workflow.pro.modules.cms.mapper.SysAppInfoMapper;
import com.workflow.pro.modules.cms.param.AppRequest;
import com.workflow.pro.modules.cms.service.IAppInfoService;
import com.workflow.pro.modules.qywx.entity.AppInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AppInfoServiceImpl extends ServiceImpl<SysAppInfoMapper, AppInfo> implements IAppInfoService {


    @Resource
    SysAppInfoMapper sysAppInfoMapper;

    @Override
    public List<AppInfo> list(AppRequest request) {
        return sysAppInfoMapper.selectAppInfoList(request);
    }

    @Override
    public PageResponse<AppInfo> page(AppRequest request) {
        return Pageable.of(request, (() -> sysAppInfoMapper.selectAppInfoList(request)));
    }
}