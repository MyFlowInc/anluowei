package com.workflow.pro.modules.cms.controller;


import com.workflow.pro.common.aop.annotation.Log;
import com.workflow.pro.common.web.base.BaseController;
import com.workflow.pro.common.web.domain.Result;
import com.workflow.pro.modules.cms.domain.SysActivity;
import com.workflow.pro.modules.cms.param.SysActivityRequest;
import com.workflow.pro.modules.cms.service.ISysActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * activityController
 *
 * @author Some
 * @date 2022-09-20
 */
@RestController
@Api(tags = {"活动"})
@RequestMapping("/sys/activity")

public class SysActivityController extends BaseController {
    @Autowired
    private ISysActivityService sysActivityService;

    /**
     * 查询活动列表
     *
     * @param request 查询参数
     * @return {@link Result}
     */
    @GetMapping("page")
    @Log(title = "活动列表")
    @ApiOperation(value = "活动列表")
    public Result page(SysActivityRequest request) {

        return success(sysActivityService.page(request));
    }

    /**
     * 删除活动
     *
     * @param id 活动编号
     */
    @DeleteMapping("remove")
    @Log(title = "活动删除")
    @ApiOperation(value = "活动删除")
    public Result remove(@RequestParam String id) {
        return auto(sysActivityService.removeById(id));
    }

    /**
     * 活动详情
     *
     * @param Id 活动编号
     */
    @GetMapping("info")
    @Log(title = "活动详情")
    @ApiOperation(value = "活动详情")
    public Result info(String Id) {
        return success(sysActivityService.getById(Id));
    }


    /**
     * 新增活动
     *
     * @param sysActivity 活动实体
     */
    @PostMapping("save")
    @Log(title = "活动新增")
    @ApiOperation(value = "活动新增")
    public Result save(@RequestBody SysActivity sysActivity) {
        System.out.println(sysActivity);


        return auto(sysActivityService.save(sysActivity));
    }

    /**
     * 修改活动
     *
     * @param sysActivity 活动实体
     */
    @PutMapping("edit")
    @Log(title = "活动修改")
    @ApiOperation(value = "活动修改")
    public Result edit(@RequestBody SysActivity sysActivity) {
        return auto(sysActivityService.updateById(sysActivity));
    }

}