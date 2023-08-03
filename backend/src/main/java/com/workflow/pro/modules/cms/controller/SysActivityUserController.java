package com.workflow.pro.modules.cms.controller;


import com.workflow.pro.common.aop.annotation.Excel;
import com.workflow.pro.common.aop.annotation.Log;
import com.workflow.pro.common.aop.enums.Model;
import com.workflow.pro.common.web.base.BaseController;
import com.workflow.pro.common.web.domain.Result;
import com.workflow.pro.modules.cms.domain.SysActivityUser;
import com.workflow.pro.modules.cms.param.SysActivityUserRequest;
import com.workflow.pro.modules.cms.service.ISysActivityUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * activityController
 *
 * @author Some
 * @date 2022-09-20
 */
@RestController
@Api(tags = {"活动人员参与"})
@RequestMapping("/sys/activityUser")

public class SysActivityUserController extends BaseController {
    @Autowired
    private ISysActivityUserService sysActivityUserService;

    /**
     * 查询活动参与人员列表
     *
     * @param request 查询参数
     * @return {@link com.workflow.pro.common.web.domain.Result}
     */
    @GetMapping("page")
    @Log(title = "活动参与人员列表")
    @ApiOperation(value = "活动参与人员列表")
    public Result page(SysActivityUserRequest request) {

        return success(sysActivityUserService.page(request));
    }

    /**
     * 删除活动参与人员
     *
     * @param id 活动参与人员编号
     */
    @DeleteMapping("remove")
    @Log(title = "活动参与人员删除")
    @ApiOperation(value = "活动参与人员删除")
    public Result remove(@RequestParam String id) {
        return auto(sysActivityUserService.removeById(id));
    }

    /**
     * 活动参与人员详情
     *
     * @param userId 活动参与人员编号
     */
    @GetMapping("info")
    @Log(title = "活动参与人员详情")
    @ApiOperation(value = "活动参与人员详情")
    public Result info(String userId) {
        return success(sysActivityUserService.getById(userId));
    }


    /**
     * 新增活动参与人员
     *
     * @param sysActivityUser 活动参与人员实体
     */
    @PostMapping("save")
    @Log(title = "活动参与人员新增")
    @ApiOperation(value = "活动参与人员新增")
    public Result save(@RequestBody SysActivityUser sysActivityUser) {

        return success(sysActivityUserService.add(sysActivityUser));
    }

    /**
     * 修改活动参与人员
     *
     * @param sysActivityUser 活动参与人员实体
     */
    @PutMapping("edit")
    @Log(title = "活动参与人员修改")
    @ApiOperation(value = "活动参与人员修改")
    public Result edit(@RequestBody SysActivityUser sysActivityUser) {
        return auto(sysActivityUserService.updateById(sysActivityUser));
    }

    /**
     * 修改活动参与人员
     *
     * @param request 活动参与人员实体
     */
    @GetMapping("list")
    @Log(title = "list")
    @ApiOperation(value = "list")
    public Result list( SysActivityUserRequest request) {
        return success(sysActivityUserService.list(request));
    }


    @GetMapping("/export")
    @Log(title = "导出人员")
    @ApiOperation(value = "导出人员")
    @Excel(clazz = SysActivityUser.class, model = Model.WRITE)
    public List<SysActivityUser>  export (SysActivityUserRequest request) {
        return  sysActivityUserService.list(request);
    }


//    @Resource
//    private SysLogService sysLogService;
//    @GetMapping("/export")
//    @Log(title = "导出日志")
//    @ApiOperation(value = "导出日志")
//    @Excel(clazz = SysLog.class, model = Model.WRITE)
//    public List<SysLog> export() {
//        return sysLogService.list(new SysLogRequest());
//    }
}