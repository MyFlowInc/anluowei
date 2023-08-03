package com.workflow.pro.modules.workflow.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.modules.workflow.domain.WorkFlowItemField;
import com.workflow.pro.modules.workflow.domain.WorkFlowStatus;
import com.workflow.pro.modules.workflow.param.FlowStatusRequest;

import java.util.List;

/**
 * 2Service接口
 *
 * @author Some
 * @date 2022-11-16
 */
public interface IWorkFlowStatusService extends IService<WorkFlowStatus> {

    /**
     * 查询2
     * @param workFlowStatus 2
     * @param pageDomain
     * @return 2 分页集合
     * */

    PageResponse<WorkFlowStatus> selectWorkFlowStatusPage(FlowStatusRequest request);


    List<WorkFlowStatus> list(FlowStatusRequest request);
    WorkFlowStatus SaveWorkFlowStatus(WorkFlowStatus workFlowStatus);
}
