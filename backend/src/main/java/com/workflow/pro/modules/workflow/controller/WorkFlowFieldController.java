package com.workflow.pro.modules.workflow.controller;

import com.workflow.pro.common.web.base.BaseController;
import com.workflow.pro.common.web.domain.Result;
import com.workflow.pro.modules.workflow.domain.WorkFlowField;
import com.workflow.pro.modules.workflow.domain.WorkFlowItem;
import com.workflow.pro.modules.workflow.param.FlowFieldRequest;
import com.workflow.pro.modules.workflow.service.IWorkFlowFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 1Controller
 *
 * @author Some
 * @date 2022-11-16
 */
@RestController
@RequestMapping("/sys/flowField")
public class WorkFlowFieldController extends BaseController {

    private String prefix = "sys/flowField";

    @Autowired
    private IWorkFlowFieldService workFlowFieldService;


    /**
     * 查询工作流主表列表
     */
    @ResponseBody
    @GetMapping("list")
    public Result list(FlowFieldRequest request) {
        return success(workFlowFieldService.list(request));
    }
    /**
     * 查询工作流主表列表
     */
    @ResponseBody
    @GetMapping("page")
    public Result page(FlowFieldRequest request) {
        return success(workFlowFieldService.selectWorkFlowFieldPage(request));
    }

    /**
     * 新增工作流主表
     */
    @ResponseBody
    @PostMapping("/save")
    public Result save(@RequestBody WorkFlowField workFlowField) {
        return success(workFlowFieldService.SaveWorkFlowField(workFlowField));
    }
    /**
     * 新增工作流主表批量
     */
    @ResponseBody
    @PostMapping("/saveBatch")
    public Result saveBatch(@RequestBody WorkFlowField[] workFlowField) {
        return auto(workFlowFieldService.saveOrUpdateBatch(Arrays.asList(workFlowField)));
    }
    /**
     * 修改工作流主表
     */
    @PostMapping("/edit")
    public Result edit(@RequestBody WorkFlowField workFlowField) {
        return auto(workFlowFieldService.updateById(workFlowField));
    }


    /**
     * 删除工作流主表
     */
    @ResponseBody
    @DeleteMapping("/batchRemove")
    public Result batchRemove(String ids) {
        return auto(workFlowFieldService.removeByIds(Arrays.asList(ids.split(","))));
    }

    /**
     * 删除工作流主表
     */
    @ResponseBody
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable("id") Integer id) {
        return auto(workFlowFieldService.removeById(id));
    }
}
