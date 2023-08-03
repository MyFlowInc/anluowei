package com.workflow.pro.modules.workflow.controller;

import com.workflow.pro.common.web.base.BaseController;
import com.workflow.pro.common.web.domain.Result;
import com.workflow.pro.modules.workflow.domain.WorkFlowStatus;
import com.workflow.pro.modules.workflow.param.FlowStatusRequest;
import com.workflow.pro.modules.workflow.service.IWorkFlowStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 2Controller
 *
 * @author Some
 * @date 2022-11-16
 */
@RestController
@RequestMapping("/sys/flowStatus")
public class WorkFlowStatusController extends BaseController {

    private String prefix = "sys/flowStatus";

    @Autowired
    private IWorkFlowStatusService workFlowStatusService;

    /**
     * 查询工作流主表列表
     */
    @ResponseBody
    @GetMapping("list")
    public Result list(FlowStatusRequest request) {
        return success(workFlowStatusService.selectWorkFlowStatusPage(request));
    }
    /**
     * 查询工作流主表列表
     */
    @ResponseBody
    @GetMapping("page")
    public Result page(FlowStatusRequest request) {
        return success(workFlowStatusService.selectWorkFlowStatusPage(request));
    }

    /**
     * 新增工作流主表
     */
    @ResponseBody
    @PostMapping("/save")
    public Result save(@RequestBody WorkFlowStatus workFlowStatus) {
        return success(workFlowStatusService.SaveWorkFlowStatus(workFlowStatus));
    }

    /**
     * 新增工作流主表
     */
    @ResponseBody
    @PostMapping("/saveBatch")
    public Result saveBatch(@RequestBody WorkFlowStatus[] workFlowStatus) {
        return auto(workFlowStatusService.saveOrUpdateBatch(Arrays.asList(workFlowStatus)));
    }
    /**
     * 修改工作流主表
     */
    @PostMapping("/edit")
    public Result edit(@RequestBody WorkFlowStatus workFlowStatus) {
        return auto(workFlowStatusService.updateById(workFlowStatus));
    }


    /**
     * 删除工作流主表
     */
    @ResponseBody
    @DeleteMapping("/batchRemove")
    public Result batchRemove(String ids) {
        return auto(workFlowStatusService.removeByIds(Arrays.asList(ids.split(","))));
    }


    /**
     * 删除工作流主表
     */
    @ResponseBody
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable("id") Integer id) {
        return auto(workFlowStatusService.removeById(id));
    }

}
