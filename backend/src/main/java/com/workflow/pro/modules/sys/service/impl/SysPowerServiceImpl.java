package com.workflow.pro.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workflow.pro.common.context.UserContext;
import com.workflow.pro.modules.sys.domain.SysPower;
import com.workflow.pro.modules.sys.repository.SysPowerRepository;
import com.workflow.pro.modules.sys.service.SysPowerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SysPowerServiceImpl extends ServiceImpl<SysPowerRepository, SysPower> implements SysPowerService {

    @Resource
    private SysPowerRepository sysPowerRepository;

    @Resource
    private UserContext userContext;

    @Override
    public List<SysPower> tree() {
        // List to Tree
        return toTree(sysPowerRepository.selectPower(),"0");
    }

    @Override
    public List<SysPower> treeByTenantId() {
        String tenantId = userContext.getTenantId();
        return toTree(sysPowerRepository.selectPowerByTenantId(tenantId),"0");
    }

    public List<SysPower> toTree(List<SysPower> sysMenus, String parent) {
        List<SysPower> list = new ArrayList<>();
        for (SysPower menu : sysMenus) {
            if (parent.equals(menu.getParent())) {
                menu.setChildren(toTree(sysMenus, menu.getId()));
                list.add(menu);
            }
        }
        return list;
    }
}
