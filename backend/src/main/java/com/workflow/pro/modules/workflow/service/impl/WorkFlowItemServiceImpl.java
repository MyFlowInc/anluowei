package com.workflow.pro.modules.workflow.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;
import com.workflow.pro.modules.workflow.domain.WorkFlowItem;
import com.workflow.pro.modules.workflow.mapper.WorkFlowItemMapper;
import com.workflow.pro.modules.workflow.param.FlowItemRequest;
import com.workflow.pro.modules.workflow.service.IWorkFlowItemService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 2Service业务层处理
 *
 * @author Some
 * @date 2022-11-16
 */
@Service
public class WorkFlowItemServiceImpl extends ServiceImpl<WorkFlowItemMapper, WorkFlowItem> implements IWorkFlowItemService {



    @Override
    public PageResponse<WorkFlowItem> selectWorkFlowItemPage(FlowItemRequest request) {
        return Pageable.of(request, (() -> baseMapper.selectWorkFlowItemList(request)));
    }

    @Override
    public List<WorkFlowItem> list(FlowItemRequest request) {
        return baseMapper.selectWorkFlowItemList(request);
    }

    @Override
    public WorkFlowItem SaveFlowItem(WorkFlowItem workFlow) {

        if (workFlow.getId() != 0) {
            saveOrUpdate(workFlow);
        } else {
            baseMapper.insert(workFlow);
        }
        return workFlow;
    }
}

