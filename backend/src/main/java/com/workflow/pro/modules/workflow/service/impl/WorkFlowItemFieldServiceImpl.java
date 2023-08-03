package com.workflow.pro.modules.workflow.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;
import com.workflow.pro.modules.workflow.domain.WorkFlowItemField;
import com.workflow.pro.modules.workflow.mapper.WorkFlowItemFieldMapper;
import com.workflow.pro.modules.workflow.param.FlowItemFieldRequest;
import com.workflow.pro.modules.workflow.service.IWorkFlowItemFieldService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 2Service业务层处理
 *
 * @author Some
 * @date 2022-11-16
 */
@Service
public class WorkFlowItemFieldServiceImpl extends ServiceImpl<WorkFlowItemFieldMapper, WorkFlowItemField> implements IWorkFlowItemFieldService {


    @Override
    public PageResponse<WorkFlowItemField> selectWorkFlowItemFieldPage(FlowItemFieldRequest request) {
        return Pageable.of(request, (() -> baseMapper.selectWorkFlowItemFieldList(request)));
    }

    @Override
    public List<WorkFlowItemField> list(FlowItemFieldRequest request) {
        return  baseMapper.selectWorkFlowItemFieldList(request);
    }

    @Override
    public WorkFlowItemField SaveWorkFlowItemField(WorkFlowItemField workFlowItemField) {
        if (workFlowItemField.getId() != 0) {
            saveOrUpdate(workFlowItemField);
        } else {
            baseMapper.insert(workFlowItemField);
        }
        return workFlowItemField;
    }

}
