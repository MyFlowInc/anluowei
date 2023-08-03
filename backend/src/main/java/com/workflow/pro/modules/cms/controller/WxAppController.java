package com.workflow.pro.modules.cms.controller;

import com.workflow.pro.common.aop.annotation.Log;
import com.workflow.pro.common.web.base.BaseController;
import com.workflow.pro.common.web.domain.Result;
import com.workflow.pro.modules.cms.param.AppRequest;
import com.workflow.pro.modules.cms.service.IAppInfoService;
import com.workflow.pro.modules.qywx.entity.AppInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = {"app"})
@RequestMapping("/api/wx/app")
public class WxAppController extends BaseController {

    @Autowired
    private IAppInfoService appInfoService;
    /**
     * 查询app列表
     *
     * @param request 查询参数
     * @return {@link Result}
     */
    @GetMapping("page")
    @Log(title = "app列表")
    @ApiOperation(value = "app列表")
    public Result page(AppRequest request) {
        return success(appInfoService.page(request));
    }

    /**
     * @param appInfo 查询参数
     * 新增app
     */
    @PostMapping("save")
    @Log(title = "app新增")
    @ApiOperation(value = "app新增")
    public Result save(@RequestBody AppInfo appInfo) {

        return success(appInfoService.save(appInfo));
    }

    /**
     * 修改app
     * @param  appInfo app实体
     */
    @PutMapping("edit")
    @Log(title = "app修改")
    @ApiOperation(value = "app修改")
    public Result edit(@RequestBody AppInfo appInfo) {
        return auto(appInfoService.updateById(appInfo));
    }


    /**
     * 删除app
     *
     * @param id app编号
     */
    @DeleteMapping("remove")
    @Log(title = "app删除")
    @ApiOperation(value = "app删除")
    public Result remove(@RequestParam String id) {
        return auto(appInfoService.removeById(id));
    }

}
