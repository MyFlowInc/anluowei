package com.workflow.pro.modules.workflow.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;
import com.workflow.pro.modules.workflow.domain.WorkFlow;
import com.workflow.pro.modules.workflow.mapper.WorkFlowMapper;
import com.workflow.pro.modules.workflow.param.FlowRequest;
import com.workflow.pro.modules.workflow.service.IWorkFlowService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 工作流主表Service业务层处理
 *
 * @author some
 * @date 2022-11-16
 */
@Service
public class WorkFlowServiceImpl extends ServiceImpl<WorkFlowMapper, WorkFlow> implements IWorkFlowService {


    /**
     * 查询工作流主表
     *
     * @return 工作流主表 分页集合
     */
    @Override
    public PageResponse<WorkFlow> selectWorkFlowStatusPage(FlowRequest request) {
        return Pageable.of(request, (() -> baseMapper.selectWorkFlowList(request)));
    }

    @Override
    public List<WorkFlow> list(FlowRequest request) {
        return baseMapper.selectWorkFlowList(request);
    }

    @Override
    public WorkFlow SaveFlow(WorkFlow workFlow) {
        if (workFlow.getId() != 0) {
            saveOrUpdate(workFlow);
        } else {
            baseMapper.insert(workFlow);
        }
        return workFlow;
    }


}
