package com.workflow.pro.modules.workflow.controller;

import com.workflow.pro.common.web.base.BaseController;
import com.workflow.pro.common.web.domain.Result;
import com.workflow.pro.modules.workflow.domain.WorkFlowItem;
import com.workflow.pro.modules.workflow.domain.WorkFlowItemField;
import com.workflow.pro.modules.workflow.param.FlowItemFieldRequest;
import com.workflow.pro.modules.workflow.service.IWorkFlowItemFieldService;
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
@RequestMapping("/sys/flowItemField")
public class WorkFlowItemFieldController extends BaseController {

    private String prefix = "sys/flowItemField";

    @Autowired
    private IWorkFlowItemFieldService workFlowItemFieldService;

    /**
     * 查询工作流主表列表
     */
    @ResponseBody
    @GetMapping("list")
    public Result list(FlowItemFieldRequest request) {
        return success(workFlowItemFieldService.list(request));
    }
    /**
     * 查询工作流主表列表
     */
    @ResponseBody
    @GetMapping("page")
    public Result page(FlowItemFieldRequest request) {
        return success(workFlowItemFieldService.selectWorkFlowItemFieldPage(request));
    }

    /**
     * 新增工作流主表
     */
    @ResponseBody
    @PostMapping("/save")
    public Result save(@RequestBody WorkFlowItemField workFlowItemField) {
        return success(workFlowItemFieldService.SaveWorkFlowItemField(workFlowItemField));
    }
    /**
     * 新增工作流主表批量
     */
    @ResponseBody
    @PostMapping("/saveBatch")
    public Result saveBatch(@RequestBody WorkFlowItemField[] workFlowItemField) {
        return auto(workFlowItemFieldService.saveOrUpdateBatch(Arrays.asList(workFlowItemField)));
    }
    /**
     * 修改工作流主表
     */
    @PostMapping("/edit")
    public Result edit(@RequestBody WorkFlowItemField workFlowItemField) {
        return auto(workFlowItemFieldService.updateById(workFlowItemField));
    }


    /**
     * 删除工作流主表
     */
    @ResponseBody
    @DeleteMapping("/batchRemove")
    public Result batchRemove(String ids) {
        return auto(workFlowItemFieldService.removeByIds(Arrays.asList(ids.split(","))));
    }

    /**
     * 删除工作流主表
     */
    @ResponseBody
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable("id") Integer id) {
        return auto(workFlowItemFieldService.removeById(id));
    }
}
