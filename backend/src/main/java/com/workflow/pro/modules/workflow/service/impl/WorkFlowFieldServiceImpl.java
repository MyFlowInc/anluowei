package com.workflow.pro.modules.workflow.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;
import com.workflow.pro.modules.workflow.domain.WorkFlowField;
import com.workflow.pro.modules.workflow.mapper.WorkFlowFieldMapper;
import com.workflow.pro.modules.workflow.param.FlowFieldRequest;
import com.workflow.pro.modules.workflow.service.IWorkFlowFieldService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 1Service业务层处理
 *
 * @author Some
 * @date 2022-11-16
 */
@Service
public class WorkFlowFieldServiceImpl extends ServiceImpl<WorkFlowFieldMapper, WorkFlowField> implements IWorkFlowFieldService {

    @Override
    public PageResponse<WorkFlowField> selectWorkFlowFieldPage(FlowFieldRequest request) {
        return Pageable.of(request, (() -> baseMapper.selectWorkFlowFieldList(request)));
    }

    @Override
    public List<WorkFlowField> list(FlowFieldRequest request) {
        return baseMapper.selectWorkFlowFieldList(request);
    }

    @Override
    public WorkFlowField SaveWorkFlowField(WorkFlowField workFlowField) {
        if (workFlowField.getId() != 0) {
            saveOrUpdate(workFlowField);
        } else {
            baseMapper.insert(workFlowField);
        }
        return workFlowField;
    }

}
