package com.workflow.pro.modules.workflow.controller;

import com.workflow.pro.common.context.BeanContext;
import com.workflow.pro.common.context.UserContext;
import com.workflow.pro.common.web.base.BaseController;
import com.workflow.pro.common.web.domain.Result;
import com.workflow.pro.modules.workflow.domain.WorkFlow;
import com.workflow.pro.modules.workflow.param.FlowRequest;
import com.workflow.pro.modules.workflow.service.IWorkFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 工作流主表Controller
 *
 * @author some
 * @date 2022-11-16
 */
@RestController
@RequestMapping("/sys/flow")
public class WorkFlowController extends BaseController {

    private String prefix = "sys/flow";

    @Autowired
    private IWorkFlowService workFlowService;


    /**
     * 查询工作流主表列表
     */
    @ResponseBody
    @GetMapping("list")
    public Result list(FlowRequest request) {

        return success(workFlowService.list(request));
    }

    /**
     * 查询工作流主表列表
     */
    @ResponseBody
    @GetMapping("page")
    public Result page(FlowRequest request) {
        //筛选当前用户的列表1·
        return success(workFlowService.selectWorkFlowStatusPage(request));
    }

    /**
     * 新增工作流主表
     */
    @ResponseBody
    @PostMapping("/save")
    public Result save(@RequestBody WorkFlow workFlow) {
        UserContext userContext = BeanContext.getBean(UserContext.class);
        String userId = userContext.getUserId();
        workFlow.setUserId(userId);
        return success(workFlowService.SaveFlow(workFlow));
    }

    /**
     * 新增工作流主表批量
     */
    @ResponseBody
    @PostMapping("/saveBatch")
    public Result saveBatch(@RequestBody WorkFlow[] workFlows) {
        UserContext userContext = BeanContext.getBean(UserContext.class);
        Arrays.asList(workFlows).forEach(object -> object.setUserId(userContext.getUserId()));

        return auto(workFlowService.saveOrUpdateBatch(Arrays.asList(workFlows)));
    }

    /**
     * 修改工作流主表
     */
    @PostMapping("/edit")
    public Result edit(@RequestBody WorkFlow workFlow) {
        return auto(workFlowService.updateById(workFlow));
    }


    /**
     * 删除工作流主表
     */
    @ResponseBody
    @DeleteMapping("/batchRemove")
    public Result batchRemove(String ids) {
        return auto(workFlowService.removeByIds(Arrays.asList(ids.split(","))));
    }

    /**
     * 删除工作流主表
     */
    @ResponseBody
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable("id") Integer id) {
        return auto(workFlowService.removeById(id));
    }
}
