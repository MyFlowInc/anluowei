package com.workflow.pro.modules.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.modules.workflow.domain.WorkFlow;
import com.workflow.pro.modules.workflow.domain.WorkFlowItem;
import com.workflow.pro.modules.workflow.param.FlowItemRequest;

import java.util.List;

/**
 * 2Service接口
 *
 * @author Some
 * @date 2022-11-16
 */
public interface IWorkFlowItemService extends IService<WorkFlowItem> {

    /**
     * 查询工作流主表
     * @param workFlow 工作流主表
     * @param pageDomain
     * @return 工作流主表 分页集合
     * */
    PageResponse<WorkFlowItem> selectWorkFlowItemPage(FlowItemRequest request);


    List<WorkFlowItem> list(FlowItemRequest request);

    WorkFlowItem SaveFlowItem(WorkFlowItem workFlow);
}
