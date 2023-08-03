package com.workflow.pro.modules.apitable.controller;


import com.workflow.pro.common.constant.ControllerConstant;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import com.workflow.pro.modules.apitable.service.IApitableDatasheetChangesetService;

import com.workflow.pro.modules.apitable.param.ApitableDatasheetChangesetRequest;
import com.workflow.pro.modules.apitable.domain.ApitableDatasheetChangeset;

import javax.annotation.Resource;
import java.util.List;

import io.swagger.annotations.ApiOperation;
import com.workflow.pro.common.web.base.BaseController;
import com.workflow.pro.common.aop.annotation.Log;
import com.workflow.pro.common.web.domain.Result;

/**
 * 变更集收集Controller
 *
 * @author some
 * @date 2023-05-22
 */
@Api(tags = {"变更集收集"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "apitableDatasheetChangeset")
public class ApitableDatasheetChangesetController extends BaseController {

    @Resource
    private IApitableDatasheetChangesetService apitableDatasheetChangesetService;

    /**
     * 查询变更集收集列表
     * @param request 查询参数
     */
    @GetMapping("/list")
    @Log(title = "变更集收集")
    @ApiOperation(value = "变更集收集")
    public Result list(ApitableDatasheetChangesetRequest request) {
     return success(apitableDatasheetChangesetService.list(request));
    }

    /**
     * 查询变更集收集分页列表
     * @param request 查询参数
     */
    @GetMapping("/page")
    @Log(title = "变更集收集列表")
    @ApiOperation(value = "变更集收集列表")
    public Result page(ApitableDatasheetChangesetRequest request) {
     return success(apitableDatasheetChangesetService.page(request));
    }


    /**
     * 新增变更集收集类型
     *
     * @param apitableDatasheetChangeset apitableDatasheetChangeset 实体
     */
    @PostMapping("save")
    @Log(title = "新增变更集收集")
    @ApiOperation(value = "变更集收集")
    public Result save(@RequestBody ApitableDatasheetChangeset apitableDatasheetChangeset) {
        return auto(apitableDatasheetChangesetService.save(apitableDatasheetChangeset));
    }

    /**
     * 修改变更集收集
     * @param apitableDatasheetChangeset apitableDatasheetChangeset 实体
     */
    @PutMapping("edit")
    @Log(title = "修改变更集收集")
    @ApiOperation(value = "修改变更集收集")
    public Result edit(@RequestBody ApitableDatasheetChangeset apitableDatasheetChangeset) {
        return auto(apitableDatasheetChangesetService.updateById(apitableDatasheetChangeset));
    }

    /**
     * 删除变更集收集类型
     *
     * @param id apitableDatasheetChangeset编号
     */
    @DeleteMapping("remove")
    @Log(title = "删除变更集收集")
    @ApiOperation(value = "删除变更集收集")
    public Result remove(@RequestParam String id) {
        return auto(apitableDatasheetChangesetService.removeById(id));
    }

    /**
     * 删除变更集收集类型
     *
     * @param ids apitableDatasheetChangeset 实体
     */
    @DeleteMapping("removeBatch")
    @Log(title = "批量删除")
    @ApiOperation(value = "批量删除")
    public Result removeBatch(@RequestParam List<String> ids) {
        return auto(apitableDatasheetChangesetService.removeByIds(ids));
    }
}
