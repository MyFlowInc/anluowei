package com.workflow.pro.modules.apitable.controller;


import com.workflow.pro.common.constant.ControllerConstant;
import com.workflow.pro.common.context.UserContext;
import com.workflow.pro.modules.sys.param.SysUserRequest;
import com.workflow.pro.modules.sys.service.SysUserService;
import io.swagger.annotations.Api;

import org.springframework.web.bind.annotation.*;

import com.workflow.pro.modules.apitable.service.IApitableInviteRecordService;

import com.workflow.pro.modules.apitable.param.ApitableInviteRecordRequest;
import com.workflow.pro.modules.apitable.domain.ApitableInviteRecord;

import javax.annotation.Resource;

import java.util.List;
import java.util.Objects;

import io.swagger.annotations.ApiOperation;
import com.workflow.pro.common.web.base.BaseController;
import com.workflow.pro.common.aop.annotation.Log;
import com.workflow.pro.common.web.domain.Result;

/**
 * 邀请同意记录Controller
 *
 * @author some
 * @date 2023-06-30
 */
@Api(tags = { "邀请同意记录" })
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "apitableInviteRecord")
public class ApitableInviteRecordController extends BaseController {

    @Resource
    private IApitableInviteRecordService apitableInviteRecordService;

    /**
     * 用 户 服 务
     */
    @Resource
    private UserContext userContext;



    /**
     * 查询邀请同意记录列表
     * @param request 查询参数
     */
    @GetMapping("/list")
    @Log(title = "邀请同意记录")
    @ApiOperation(value = "邀请同意记录")
    public Result list(ApitableInviteRecordRequest request) {
        return success(apitableInviteRecordService.list(request));
    }

    /**
     * 查询邀请同意记录分页列表
     * @param request 查询参数
     */
    @GetMapping("/page")
    @Log(title = "邀请同意记录列表")
    @ApiOperation(value = "邀请同意记录列表")
    public Result page(ApitableInviteRecordRequest request) {
        if (Objects.equals(userContext.getFromAgent(), "web")) {
            request.setAccepter(userContext.getUserId());
        }
        return success(apitableInviteRecordService.page(request));
    }


    /**
     * 新增邀请同意记录类型
     *
     * @param apitableInviteRecord apitableInviteRecord 实体
     */
    @PostMapping("save")
    @Log(title = "新增邀请同意记录")
    @ApiOperation(value = "邀请同意记录")
    public Result save(@RequestBody ApitableInviteRecord apitableInviteRecord) {
        return auto(apitableInviteRecordService.save(apitableInviteRecord));
    }

    /**
     * 修改邀请同意记录
     * @param apitableInviteRecord apitableInviteRecord 实体
     */
    @PutMapping("edit")
    @Log(title = "修改邀请同意记录")
    @ApiOperation(value = "修改邀请同意记录")
    public Result edit(@RequestBody ApitableInviteRecord apitableInviteRecord) {
        return auto(apitableInviteRecordService.updateById(apitableInviteRecord));
    }

    /**
     * 删除邀请同意记录类型
     *
     * @param id apitableInviteRecord编号
     */
    @DeleteMapping("remove")
    @Log(title = "删除邀请同意记录")
    @ApiOperation(value = "删除邀请同意记录")
    public Result remove(@RequestParam String id) {
        return auto(apitableInviteRecordService.removeById(id));
    }

    /**
     * 删除邀请同意记录类型
     *
     * @param ids apitableInviteRecord 实体
     */
    @DeleteMapping("removeBatch")
    @Log(title = "批量删除")
    @ApiOperation(value = "批量删除")
    public Result removeBatch(@RequestParam List<String> ids) {
        return auto(apitableInviteRecordService.removeByIds(ids));
    }
}
