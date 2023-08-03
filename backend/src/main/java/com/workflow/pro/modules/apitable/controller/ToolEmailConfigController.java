package com.workflow.pro.modules.apitable.controller;


import com.workflow.pro.common.constant.ControllerConstant;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import com.workflow.pro.modules.apitable.service.IToolEmailConfigService;

import com.workflow.pro.modules.apitable.param.ToolEmailConfigRequest;
import com.workflow.pro.modules.apitable.domain.ToolEmailConfig;

import javax.annotation.Resource;
import java.util.List;

import io.swagger.annotations.ApiOperation;
import com.workflow.pro.common.web.base.BaseController;
import com.workflow.pro.common.aop.annotation.Log;
import com.workflow.pro.common.web.domain.Result;

/**
 * 邮箱配置Controller
 *
 * @author some
 * @date 2023-05-24
 */
@Api(tags = {"邮箱配置"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "toolEmailConfig")
public class ToolEmailConfigController extends BaseController {

    @Resource
    private IToolEmailConfigService toolEmailConfigService;

    /**
     * 查询邮箱配置列表
     * @param request 查询参数
     */
    @GetMapping("/list")
    @Log(title = "邮箱配置")
    @ApiOperation(value = "邮箱配置")
    public Result list(ToolEmailConfigRequest request) {
     return success(toolEmailConfigService.list(request));
    }

    /**
     * 查询邮箱配置分页列表
     * @param request 查询参数
     */
    @GetMapping("/page")
    @Log(title = "邮箱配置列表")
    @ApiOperation(value = "邮箱配置列表")
    public Result page(ToolEmailConfigRequest request) {
     return success(toolEmailConfigService.page(request));
    }


    /**
     * 新增邮箱配置类型
     *
     * @param toolEmailConfig toolEmailConfig 实体
     */
    @PostMapping("save")
    @Log(title = "新增邮箱配置")
    @ApiOperation(value = "邮箱配置")
    public Result save(@RequestBody ToolEmailConfig toolEmailConfig) {
        return auto(toolEmailConfigService.save(toolEmailConfig));
    }

    /**
     * 修改邮箱配置
     * @param toolEmailConfig toolEmailConfig 实体
     */
    @PutMapping("edit")
    @Log(title = "修改邮箱配置")
    @ApiOperation(value = "修改邮箱配置")
    public Result edit(@RequestBody ToolEmailConfig toolEmailConfig) {
        return auto(toolEmailConfigService.updateById(toolEmailConfig));
    }

    /**
     * 删除邮箱配置类型
     *
     * @param id toolEmailConfig编号
     */
    @DeleteMapping("remove")
    @Log(title = "删除邮箱配置")
    @ApiOperation(value = "删除邮箱配置")
    public Result remove(@RequestParam String id) {
        return auto(toolEmailConfigService.removeById(id));
    }

    /**
     * 删除邮箱配置类型
     *
     * @param ids toolEmailConfig 实体
     */
    @DeleteMapping("removeBatch")
    @Log(title = "批量删除")
    @ApiOperation(value = "批量删除")
    public Result removeBatch(@RequestParam List<String> ids) {
        return auto(toolEmailConfigService.removeByIds(ids));
    }
}
