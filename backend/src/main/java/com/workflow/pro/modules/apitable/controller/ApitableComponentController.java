package com.workflow.pro.modules.apitable.controller;


import com.workflow.pro.common.constant.ControllerConstant;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import com.workflow.pro.modules.apitable.service.IApitableComponentService;

import com.workflow.pro.modules.apitable.param.ApitableComponentRequest;
import com.workflow.pro.modules.apitable.domain.ApitableComponent;

import javax.annotation.Resource;
import java.util.List;

import io.swagger.annotations.ApiOperation;
import com.workflow.pro.common.web.base.BaseController;
import com.workflow.pro.common.aop.annotation.Log;
import com.workflow.pro.common.web.domain.Result;

/**
 * 格组件Controller
 *
 * @author some
 * @date 2023-05-22
 */
@Api(tags = {"格组件"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "apitableComponent")
public class ApitableComponentController extends BaseController {

    @Resource
    private IApitableComponentService apitableComponentService;

    /**
     * 查询格组件列表
     * @param request 查询参数
     */
    @GetMapping("/list")
    @Log(title = "格组件")
    @ApiOperation(value = "格组件")
    public Result list(ApitableComponentRequest request) {
     return success(apitableComponentService.list(request));
    }

    /**
     * 查询格组件分页列表
     * @param request 查询参数
     */
    @GetMapping("/page")
    @Log(title = "格组件列表")
    @ApiOperation(value = "格组件列表")
    public Result page(ApitableComponentRequest request) {
     return success(apitableComponentService.page(request));
    }


    /**
     * 新增格组件类型
     *
     * @param apitableComponent apitableComponent 实体
     */
    @PostMapping("save")
    @Log(title = "新增格组件")
    @ApiOperation(value = "格组件")
    public Result save(@RequestBody ApitableComponent apitableComponent) {
        return auto(apitableComponentService.save(apitableComponent));
    }

    /**
     * 修改格组件
     * @param apitableComponent apitableComponent 实体
     */
    @PutMapping("edit")
    @Log(title = "修改格组件")
    @ApiOperation(value = "修改格组件")
    public Result edit(@RequestBody ApitableComponent apitableComponent) {
        return auto(apitableComponentService.updateById(apitableComponent));
    }

    /**
     * 删除格组件类型
     *
     * @param id apitableComponent编号
     */
    @DeleteMapping("remove")
    @Log(title = "删除格组件")
    @ApiOperation(value = "删除格组件")
    public Result remove(@RequestParam String id) {
        return auto(apitableComponentService.removeById(id));
    }

    /**
     * 删除格组件类型
     *
     * @param ids apitableComponent 实体
     */
    @DeleteMapping("removeBatch")
    @Log(title = "批量删除")
    @ApiOperation(value = "批量删除")
    public Result removeBatch(@RequestParam List<String> ids) {
        return auto(apitableComponentService.removeByIds(ids));
    }
}
