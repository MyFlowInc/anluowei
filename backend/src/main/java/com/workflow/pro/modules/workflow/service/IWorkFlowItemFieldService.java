package com.workflow.pro.modules.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.modules.workflow.domain.WorkFlowField;
import com.workflow.pro.modules.workflow.domain.WorkFlowItemField;
import com.workflow.pro.modules.workflow.param.FlowItemFieldRequest;

import java.util.List;

/**
 * 2Service接口
 *
 * @author Some
 * @date 2022-11-16
 */
public interface IWorkFlowItemFieldService extends IService<WorkFlowItemField> {

    /**
     * 查询2
     *
     * @param workFlowItemField 2
     * @param pageDomain
     * @return 2 分页集合
     */

    PageResponse<WorkFlowItemField> selectWorkFlowItemFieldPage(FlowItemFieldRequest request);


    List<WorkFlowItemField> list(FlowItemFieldRequest request);

    WorkFlowItemField SaveWorkFlowItemField(WorkFlowItemField workFlowItemField);
}
