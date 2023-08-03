package com.workflow.pro.modules.workflow.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;
import com.workflow.pro.modules.workflow.domain.WorkFlowStatus;
import com.workflow.pro.modules.workflow.mapper.WorkFlowStatusMapper;
import com.workflow.pro.modules.workflow.param.FlowStatusRequest;
import com.workflow.pro.modules.workflow.service.IWorkFlowStatusService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 2Service业务层处理
 *
 * @author Some
 * @date 2022-11-16
 */
@Service
public class WorkFlowStatusServiceImpl extends ServiceImpl<WorkFlowStatusMapper, WorkFlowStatus> implements IWorkFlowStatusService {

    /**
     * 查询2
     *
     * @return 2 分页集合
     */

    @Override
    public PageResponse<WorkFlowStatus> selectWorkFlowStatusPage(FlowStatusRequest request) {
        return Pageable.of(request, (() -> baseMapper.selectWorkFlowStatusList(request)));
    }

    @Override
    public List<WorkFlowStatus> list(FlowStatusRequest request) {
        return baseMapper.selectWorkFlowStatusList(request);
    }

    @Override
    public WorkFlowStatus SaveWorkFlowStatus(WorkFlowStatus workFlowStatus) {
        if (workFlowStatus.getId() != 0) {
            saveOrUpdate(workFlowStatus);
        } else {
            baseMapper.insert(workFlowStatus);
        }
        return workFlowStatus;
    }

}
