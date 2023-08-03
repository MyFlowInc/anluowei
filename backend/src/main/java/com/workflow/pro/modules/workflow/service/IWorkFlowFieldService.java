package com.workflow.pro.modules.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.modules.workflow.domain.WorkFlow;
import com.workflow.pro.modules.workflow.domain.WorkFlowField;
import com.workflow.pro.modules.workflow.param.FlowFieldRequest;

import java.util.List;

/**
 * 1Service接口
 *
 * @author Some
 * @date 2022-11-16
 */
public interface IWorkFlowFieldService extends IService<WorkFlowField> {

    /**
     * 查询1
     * @return 1 分页集合
     * */

    PageResponse<WorkFlowField> selectWorkFlowFieldPage(FlowFieldRequest request);


    List<WorkFlowField> list(FlowFieldRequest request);

    WorkFlowField SaveWorkFlowField(WorkFlowField workFlowField);
}
