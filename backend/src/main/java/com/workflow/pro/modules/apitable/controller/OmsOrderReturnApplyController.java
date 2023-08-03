package com.workflow.pro.modules.apitable.controller;


import com.workflow.pro.common.constant.ControllerConstant;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import com.workflow.pro.modules.apitable.service.IOmsOrderReturnApplyService;

import com.workflow.pro.modules.apitable.param.OmsOrderReturnApplyRequest;
import com.workflow.pro.modules.apitable.domain.OmsOrderReturnApply;

import javax.annotation.Resource;
import java.util.List;

import io.swagger.annotations.ApiOperation;
import com.workflow.pro.common.web.base.BaseController;
import com.workflow.pro.common.aop.annotation.Log;
import com.workflow.pro.common.web.domain.Result;

/**
 * 订单退货申请Controller
 *
 * @author some
 * @date 2023-06-09
 */
@Api(tags = {"订单退货申请"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "omsOrderReturnApply")
public class OmsOrderReturnApplyController extends BaseController {






    @Resource
    private IOmsOrderReturnApplyService omsOrderReturnApplyService;

    /**
     * 查询订单退货申请列表
     * @param request 查询参数
     */
    @GetMapping("/list")
    @Log(title = "订单退货申请")
    @ApiOperation(value = "订单退货申请")
    public Result list(OmsOrderReturnApplyRequest request) {
     return success(omsOrderReturnApplyService.list(request));
    }

    /**
     * 查询订单退货申请分页列表
     * @param request 查询参数
     */
    @GetMapping("/page")
    @Log(title = "订单退货申请列表")
    @ApiOperation(value = "订单退货申请列表")
    public Result page(OmsOrderReturnApplyRequest request) {
     return success(omsOrderReturnApplyService.page(request));
    }


    /**
     * 新增订单退货申请类型
     *
     * @param omsOrderReturnApply omsOrderReturnApply 实体
     */
    @PostMapping("save")
    @Log(title = "新增订单退货申请")
    @ApiOperation(value = "订单退货申请")
    public Result save(@RequestBody OmsOrderReturnApply omsOrderReturnApply) {
        return auto(omsOrderReturnApplyService.save(omsOrderReturnApply));
    }

    /**
     * 修改订单退货申请
     * @param omsOrderReturnApply omsOrderReturnApply 实体
     */
    @PutMapping("edit")
    @Log(title = "修改订单退货申请")
    @ApiOperation(value = "修改订单退货申请")
    public Result edit(@RequestBody OmsOrderReturnApply omsOrderReturnApply) {
        return auto(omsOrderReturnApplyService.updateById(omsOrderReturnApply));
    }

    /**
     * 删除订单退货申请类型
     *
     * @param id omsOrderReturnApply编号
     */
    @DeleteMapping("remove")
    @Log(title = "删除订单退货申请")
    @ApiOperation(value = "删除订单退货申请")
    public Result remove(@RequestParam String id) {
        return auto(omsOrderReturnApplyService.removeById(id));
    }

    /**
     * 删除订单退货申请类型
     *
     * @param ids omsOrderReturnApply 实体
     */
    @DeleteMapping("removeBatch")
    @Log(title = "批量删除")
    @ApiOperation(value = "批量删除")
    public Result removeBatch(@RequestParam List<String> ids) {
        return auto(omsOrderReturnApplyService.removeByIds(ids));
    }
}
