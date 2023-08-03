package com.workflow.pro.modules.apitable.controller;


import com.workflow.pro.common.constant.ControllerConstant;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import com.workflow.pro.modules.apitable.service.IOmsOrderOperateHistoryService;

import com.workflow.pro.modules.apitable.param.OmsOrderOperateHistoryRequest;
import com.workflow.pro.modules.apitable.domain.OmsOrderOperateHistory;

import javax.annotation.Resource;
import java.util.List;

import io.swagger.annotations.ApiOperation;
import com.workflow.pro.common.web.base.BaseController;
import com.workflow.pro.common.aop.annotation.Log;
import com.workflow.pro.common.web.domain.Result;

/**
 * 订单操作历史记录Controller
 *
 * @author some
 * @date 2023-06-09
 */
@Api(tags = {"订单操作历史记录"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "omsOrderOperateHistory")
public class OmsOrderOperateHistoryController extends BaseController {

    @Resource
    private IOmsOrderOperateHistoryService omsOrderOperateHistoryService;

    /**
     * 查询订单操作历史记录列表
     * @param request 查询参数
     */
    @GetMapping("/list")
    @Log(title = "订单操作历史记录")
    @ApiOperation(value = "订单操作历史记录")
    public Result list(OmsOrderOperateHistoryRequest request) {
     return success(omsOrderOperateHistoryService.list(request));
    }

    /**
     * 查询订单操作历史记录分页列表
     * @param request 查询参数
     */
    @GetMapping("/page")
    @Log(title = "订单操作历史记录列表")
    @ApiOperation(value = "订单操作历史记录列表")
    public Result page(OmsOrderOperateHistoryRequest request) {
     return success(omsOrderOperateHistoryService.page(request));
    }


    /**
     * 新增订单操作历史记录类型
     *
     * @param omsOrderOperateHistory omsOrderOperateHistory 实体
     */
    @PostMapping("save")
    @Log(title = "新增订单操作历史记录")
    @ApiOperation(value = "订单操作历史记录")
    public Result save(@RequestBody OmsOrderOperateHistory omsOrderOperateHistory) {
        return auto(omsOrderOperateHistoryService.save(omsOrderOperateHistory));
    }

    /**
     * 修改订单操作历史记录
     * @param omsOrderOperateHistory omsOrderOperateHistory 实体
     */
    @PutMapping("edit")
    @Log(title = "修改订单操作历史记录")
    @ApiOperation(value = "修改订单操作历史记录")
    public Result edit(@RequestBody OmsOrderOperateHistory omsOrderOperateHistory) {
        return auto(omsOrderOperateHistoryService.updateById(omsOrderOperateHistory));
    }

    /**
     * 删除订单操作历史记录类型
     *
     * @param id omsOrderOperateHistory编号
     */
    @DeleteMapping("remove")
    @Log(title = "删除订单操作历史记录")
    @ApiOperation(value = "删除订单操作历史记录")
    public Result remove(@RequestParam String id) {
        return auto(omsOrderOperateHistoryService.removeById(id));
    }

    /**
     * 删除订单操作历史记录类型
     *
     * @param ids omsOrderOperateHistory 实体
     */
    @DeleteMapping("removeBatch")
    @Log(title = "批量删除")
    @ApiOperation(value = "批量删除")
    public Result removeBatch(@RequestParam List<String> ids) {
        return auto(omsOrderOperateHistoryService.removeByIds(ids));
    }
}
