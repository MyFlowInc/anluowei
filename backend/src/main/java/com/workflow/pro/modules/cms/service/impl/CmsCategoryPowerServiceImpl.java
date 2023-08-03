package com.workflow.pro.modules.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;
import com.workflow.pro.modules.cms.mapper.CmsCategoryPowerMapper;
import com.workflow.pro.modules.cms.param.CmsCategoryPowerRequest;
import com.workflow.pro.modules.cms.service.ICmsCategoryPowerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import com.workflow.pro.modules.cms.domain.CmsCategoryPower;

@Service
public class CmsCategoryPowerServiceImpl extends ServiceImpl<CmsCategoryPowerMapper, CmsCategoryPower> implements ICmsCategoryPowerService {


    @Resource
    CmsCategoryPowerMapper cmsCategoryPowerMapper;

    @Override
    public List<CmsCategoryPower> list(CmsCategoryPowerRequest request) {

        return   cmsCategoryPowerMapper.selectCmsCategoryPowerList(request);
    }

    @Override
    public PageResponse<CmsCategoryPower> page(CmsCategoryPowerRequest request) {
        return Pageable.of(request, (() -> cmsCategoryPowerMapper.selectCmsCategoryPowerList(request)));
    }

    @Override
    public Boolean give(String deptId, List<String> categoryIds) {
        this.lambdaUpdate().eq(CmsCategoryPower::getDeptId,deptId).remove();
        List<CmsCategoryPower> CmsCategoryPowers = new ArrayList<>();
        categoryIds.forEach(categoryId -> {
            CmsCategoryPower deptPower=new CmsCategoryPower();
            deptPower.setDeptId(deptId);
            deptPower.setCategoryId(categoryId);
            CmsCategoryPowers.add(deptPower);
        });
        return this.saveBatch(CmsCategoryPowers);
    }
}
