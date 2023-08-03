package com.workflow.pro.modules.workflow.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.modules.workflow.domain.WorkFlow;
import com.workflow.pro.modules.workflow.param.FlowRequest;

import java.util.List;

/**
 * 工作流主表Service接口
 *
 * @author some
 * @date 2022-11-16
 */
public interface IWorkFlowService extends IService<WorkFlow> {

    /**
     * 查询工作流主表
     * @param workFlow 工作流主表
     * @param pageDomain
     * @return 工作流主表 分页集合
     * */
    PageResponse<WorkFlow> selectWorkFlowStatusPage(FlowRequest request);


    List<WorkFlow> list(FlowRequest request);

    WorkFlow SaveFlow(WorkFlow workFlow);

}
