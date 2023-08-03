package com.workflow.pro.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.workflow.pro.modules.sys.domain.SysPower;

import java.util.List;

public interface SysPowerService extends IService<SysPower> {

    /**
     * 获取权限树
     * */
    List<SysPower> tree();

    /**
     * 根据 tenantId 获取权限树
     * */
    List<SysPower> treeByTenantId();
}
