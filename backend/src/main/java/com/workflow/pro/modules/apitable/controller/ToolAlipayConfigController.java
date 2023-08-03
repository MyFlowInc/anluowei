package com.workflow.pro.modules.apitable.controller;


import com.workflow.pro.common.constant.ControllerConstant;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import com.workflow.pro.modules.apitable.service.IToolAlipayConfigService;

import com.workflow.pro.modules.apitable.param.ToolAlipayConfigRequest;
import com.workflow.pro.modules.apitable.domain.ToolAlipayConfig;

import javax.annotation.Resource;
import java.util.List;

import io.swagger.annotations.ApiOperation;
import com.workflow.pro.common.web.base.BaseController;
import com.workflow.pro.common.aop.annotation.Log;
import com.workflow.pro.common.web.domain.Result;

/**
 * 支付宝配置类Controller
 *
 * @author some
 * @date 2023-05-24
 */
@Api(tags = {"支付宝配置类"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "toolAlipayConfig")
public class ToolAlipayConfigController extends BaseController {

    @Resource
    private IToolAlipayConfigService toolAlipayConfigService;

    /**
     * 查询支付宝配置类列表
     * @param request 查询参数
     */
    @GetMapping("/list")
    @Log(title = "支付宝配置类")
    @ApiOperation(value = "支付宝配置类")
    public Result list(ToolAlipayConfigRequest request) {
     return success(toolAlipayConfigService.list(request));
    }

    /**
     * 查询支付宝配置类分页列表
     * @param request 查询参数
     */
    @GetMapping("/page")
    @Log(title = "支付宝配置类列表")
    @ApiOperation(value = "支付宝配置类列表")
    public Result page(ToolAlipayConfigRequest request) {
     return success(toolAlipayConfigService.page(request));
    }


    /**
     * 新增支付宝配置类类型
     *
     * @param toolAlipayConfig toolAlipayConfig 实体
     */
    @PostMapping("save")
    @Log(title = "新增支付宝配置类")
    @ApiOperation(value = "支付宝配置类")
    public Result save(@RequestBody ToolAlipayConfig toolAlipayConfig) {
        return auto(toolAlipayConfigService.save(toolAlipayConfig));
    }

    /**
     * 修改支付宝配置类
     * @param toolAlipayConfig toolAlipayConfig 实体
     */
    @PutMapping("edit")
    @Log(title = "修改支付宝配置类")
    @ApiOperation(value = "修改支付宝配置类")
    public Result edit(@RequestBody ToolAlipayConfig toolAlipayConfig) {
        return auto(toolAlipayConfigService.updateById(toolAlipayConfig));
    }

    /**
     * 删除支付宝配置类类型
     *
     * @param id toolAlipayConfig编号
     */
    @DeleteMapping("remove")
    @Log(title = "删除支付宝配置类")
    @ApiOperation(value = "删除支付宝配置类")
    public Result remove(@RequestParam String id) {
        return auto(toolAlipayConfigService.removeById(id));
    }

    /**
     * 删除支付宝配置类类型
     *
     * @param ids toolAlipayConfig 实体
     */
    @DeleteMapping("removeBatch")
    @Log(title = "批量删除")
    @ApiOperation(value = "批量删除")
    public Result removeBatch(@RequestParam List<String> ids) {
        return auto(toolAlipayConfigService.removeByIds(ids));
    }
}
