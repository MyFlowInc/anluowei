package com.workflow.pro.modules.workflow.controller;

import com.workflow.pro.common.web.base.BaseController;
import com.workflow.pro.common.web.domain.Result;
import com.workflow.pro.modules.workflow.domain.WorkFlowItem;
import com.workflow.pro.modules.workflow.domain.WorkFlowStatus;
import com.workflow.pro.modules.workflow.param.FlowItemRequest;
import com.workflow.pro.modules.workflow.service.IWorkFlowItemService;
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
@RequestMapping("/sys/flowItem")
public class WorkFlowItemController extends BaseController {

    private String prefix = "sys/flowItem";

    @Autowired
    private IWorkFlowItemService workFlowItemService;

    /**
     * 查询工作流主表列表
     */
    @ResponseBody
    @GetMapping("list")
    public Result list(FlowItemRequest request) {
        return success(workFlowItemService.list(request));
    }
    /**
     * 查询工作流主表列表
     */
    @ResponseBody
    @GetMapping("page")
    public Result page(FlowItemRequest request) {
        return success(workFlowItemService.selectWorkFlowItemPage(request));
    }

    /**
     * 新增工作流主表
     */
    @ResponseBody
    @PostMapping("/save")
    public Result save(@RequestBody WorkFlowItem workFlowItem) {
        return success(workFlowItemService.SaveFlowItem(workFlowItem));
    }


    /**
     * 新增工作流主表批量
     */
    @ResponseBody
    @PostMapping("/saveBatch")
    public Result saveBatch(@RequestBody WorkFlowItem[] workFlowItem) {
        return auto(workFlowItemService.saveOrUpdateBatch(Arrays.asList(workFlowItem)));
    }
    /**
     * 修改工作流主表
     */
    @PostMapping("/edit")
    public Result edit(@RequestBody WorkFlowItem workFlowItem) {
        return auto(workFlowItemService.updateById(workFlowItem));
    }


    /**
     * 删除工作流主表
     */
    @ResponseBody
    @DeleteMapping("/batchRemove")
    public Result batchRemove(String ids) {
        return auto(workFlowItemService.removeByIds(Arrays.asList(ids.split(","))));
    }

    /**
     * 删除工作流主表
     */
    @ResponseBody
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable("id") Integer id) {
        return auto(workFlowItemService.removeById(id));
    }
}
