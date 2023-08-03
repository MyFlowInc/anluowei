package com.workflow.pro.modules.apitable.controller;


import com.workflow.pro.common.constant.ControllerConstant;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import com.workflow.pro.modules.apitable.service.IOmsOrderItemService;

import com.workflow.pro.modules.apitable.param.OmsOrderItemRequest;
import com.workflow.pro.modules.apitable.domain.OmsOrderItem;

import javax.annotation.Resource;
import java.util.List;

import io.swagger.annotations.ApiOperation;
import com.workflow.pro.common.web.base.BaseController;
import com.workflow.pro.common.aop.annotation.Log;
import com.workflow.pro.common.web.domain.Result;

/**
 * 订单中所包含的商品Controller
 *
 * @author some
 * @date 2023-06-13
 */
@Api(tags = {"订单中所包含的商品"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "omsOrderItem")
public class OmsOrderItemController extends BaseController {

    @Resource
    private IOmsOrderItemService omsOrderItemService;

    /**
     * 查询订单中所包含的商品列表
     * @param request 查询参数
     */
    @GetMapping("/list")
    @Log(title = "订单中所包含的商品")
    @ApiOperation(value = "订单中所包含的商品")
    public Result list(OmsOrderItemRequest request) {
     return success(omsOrderItemService.list(request));
    }

    /**
     * 查询订单中所包含的商品分页列表
     * @param request 查询参数
     */
    @GetMapping("/page")
    @Log(title = "订单中所包含的商品列表")
    @ApiOperation(value = "订单中所包含的商品列表")
    public Result page(OmsOrderItemRequest request) {
     return success(omsOrderItemService.page(request));
    }


    /**
     * 新增订单中所包含的商品类型
     *
     * @param omsOrderItem omsOrderItem 实体
     */
    @PostMapping("save")
    @Log(title = "新增订单中所包含的商品")
    @ApiOperation(value = "订单中所包含的商品")
    public Result save(@RequestBody OmsOrderItem omsOrderItem) {
        return auto(omsOrderItemService.save(omsOrderItem));
    }

    /**
     * 修改订单中所包含的商品
     * @param omsOrderItem omsOrderItem 实体
     */
    @PutMapping("edit")
    @Log(title = "修改订单中所包含的商品")
    @ApiOperation(value = "修改订单中所包含的商品")
    public Result edit(@RequestBody OmsOrderItem omsOrderItem) {
        return auto(omsOrderItemService.updateById(omsOrderItem));
    }

    /**
     * 删除订单中所包含的商品类型
     *
     * @param id omsOrderItem编号
     */
    @DeleteMapping("remove")
    @Log(title = "删除订单中所包含的商品")
    @ApiOperation(value = "删除订单中所包含的商品")
    public Result remove(@RequestParam String id) {
        return auto(omsOrderItemService.removeById(id));
    }

    /**
     * 删除订单中所包含的商品类型
     *
     * @param ids omsOrderItem 实体
     */
    @DeleteMapping("removeBatch")
    @Log(title = "批量删除")
    @ApiOperation(value = "批量删除")
    public Result removeBatch(@RequestParam List<String> ids) {
        return auto(omsOrderItemService.removeByIds(ids));
    }
}
