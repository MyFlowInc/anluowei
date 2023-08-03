package com.workflow.pro.modules.apitable.controller;


import com.workflow.pro.common.constant.ControllerConstant;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import com.workflow.pro.modules.apitable.service.IApitableNodeShareSettingService;

import com.workflow.pro.modules.apitable.param.ApitableNodeShareSettingRequest;
import com.workflow.pro.modules.apitable.domain.ApitableNodeShareSetting;

import javax.annotation.Resource;
import java.util.List;

import io.swagger.annotations.ApiOperation;
import com.workflow.pro.common.web.base.BaseController;
import com.workflow.pro.common.aop.annotation.Log;
import com.workflow.pro.common.web.domain.Result;

/**
 * 格基础设置Controller
 *
 * @author some
 * @date 2023-06-14
 */
@Api(tags = {"格基础设置"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "apitableNodeShareSetting")
public class ApitableNodeShareSettingController extends BaseController {

    @Resource
    private IApitableNodeShareSettingService apitableNodeShareSettingService;

    /**
     * 查询格基础设置列表
     * @param request 查询参数
     */
    @GetMapping("/list")
    @Log(title = "格基础设置")
    @ApiOperation(value = "格基础设置")
    public Result list(ApitableNodeShareSettingRequest request) {
     return success(apitableNodeShareSettingService.list(request));
    }

    /**
     * 查询格基础设置分页列表
     * @param request 查询参数
     */
    @GetMapping("/page")
    @Log(title = "格基础设置列表")
    @ApiOperation(value = "格基础设置列表")
    public Result page(ApitableNodeShareSettingRequest request) {
     return success(apitableNodeShareSettingService.page(request));
    }


    /**
     * 新增格基础设置类型
     *
     * @param apitableNodeShareSetting apitableNodeShareSetting 实体
     */
    @PostMapping("save")
    @Log(title = "新增格基础设置")
    @ApiOperation(value = "格基础设置")
    public Result save(@RequestBody ApitableNodeShareSetting apitableNodeShareSetting) {

        return auto(apitableNodeShareSettingService.save(apitableNodeShareSetting));
    }

    /**
     * 修改格基础设置
     * @param apitableNodeShareSetting apitableNodeShareSetting 实体
     */
    @PutMapping("edit")
    @Log(title = "修改格基础设置")
    @ApiOperation(value = "修改格基础设置")
    public Result edit(@RequestBody ApitableNodeShareSetting apitableNodeShareSetting) {
        return auto(apitableNodeShareSettingService.updateById(apitableNodeShareSetting));
    }

    /**
     * 删除格基础设置类型
     *
     * @param id apitableNodeShareSetting编号
     */
    @DeleteMapping("remove")
    @Log(title = "删除格基础设置")
    @ApiOperation(value = "删除格基础设置")
    public Result remove(@RequestParam String id) {
        return auto(apitableNodeShareSettingService.removeById(id));
    }

    /**
     * 删除格基础设置类型
     *
     * @param ids apitableNodeShareSetting 实体
     */
    @DeleteMapping("removeBatch")
    @Log(title = "批量删除")
    @ApiOperation(value = "批量删除")
    public Result removeBatch(@RequestParam List<String> ids) {
        return auto(apitableNodeShareSettingService.removeByIds(ids));
    }
}
