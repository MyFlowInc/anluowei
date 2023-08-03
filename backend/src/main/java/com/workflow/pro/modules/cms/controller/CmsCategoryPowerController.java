package com.workflow.pro.modules.cms.controller;

import com.workflow.pro.common.aop.annotation.Log;
import com.workflow.pro.common.web.base.BaseController;
import com.workflow.pro.common.web.domain.Result;
import com.workflow.pro.modules.cms.domain.CmsCategoryPower;
import com.workflow.pro.modules.cms.param.CmsCategoryPowerAdd;
import com.workflow.pro.modules.cms.param.CmsCategoryPowerRequest;
import com.workflow.pro.modules.cms.service.ICmsCategoryPowerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 分类Controller
 *
 * @author Some
 * @date 2022-08-05
 */
@Api(tags = {"分类"})
@RestController
@RequestMapping("/api/cms/power")
public class CmsCategoryPowerController extends BaseController {

    @Resource
    private ICmsCategoryPowerService iCmsCategoryPowerService;

    @GetMapping("page")
    @Log(title = "权限列表")
    @ApiOperation(value = "权限列表")
    public Result page(CmsCategoryPowerRequest request) {
        return success(iCmsCategoryPowerService.page(request)
        );
    }

    /**
     * 查询分类
     *
     * @param request 查询参数
     */
    @GetMapping("list")
    @Log(title = "分类列表")
    @ApiOperation(value = "分类列表")
    public Result list(CmsCategoryPowerRequest request) {
        return success(iCmsCategoryPowerService.list(request));
    }

    /**
     * 删除权限
     *
     * @param id 权限编号
     */
    @DeleteMapping("remove")
    @Log(title = "权限删除")
    @ApiOperation(value = "权限删除")
    public Result remove(@RequestParam String id) {
        return auto(iCmsCategoryPowerService.removeById(id));
    }

    /**
     * 权限详情
     *
     * @param userId 权限编号
     */
    @GetMapping("info")
    @Log(title = "权限详情")
    @ApiOperation(value = "权限详情")
    public Result info(String userId) {
        return success(iCmsCategoryPowerService.getById(userId));
    }


    /**
     * 新增权限
     */
    @PostMapping("save")
    @Log(title = "权限新增")
    @ApiOperation(value = "权限新增")
    public Result give(@RequestBody CmsCategoryPowerAdd cRequest) {
        return auto(iCmsCategoryPowerService.give(cRequest.getDeptId(), cRequest.getCategoryId()));
    }

    /**
     * 修改权限
     */
    @PutMapping("edit")
    @Log(title = "权限修改")
    @ApiOperation(value = "权限修改")
    public Result edit(@RequestBody CmsCategoryPower cmsCategoryPower) {
        return auto(iCmsCategoryPowerService.updateById(cmsCategoryPower));
    }

}
