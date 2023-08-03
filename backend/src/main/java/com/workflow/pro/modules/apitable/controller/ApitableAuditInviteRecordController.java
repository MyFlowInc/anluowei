package com.workflow.pro.modules.apitable.controller;


import com.workflow.pro.common.constant.ControllerConstant;
import com.workflow.pro.common.context.UserContext;
import io.swagger.annotations.Api;

import org.springframework.web.bind.annotation.*;

import com.workflow.pro.modules.apitable.service.IApitableAuditInviteRecordService;

import com.workflow.pro.modules.apitable.param.ApitableAuditInviteRecordRequest;
import com.workflow.pro.modules.apitable.domain.ApitableAuditInviteRecord;

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
 * @date 2023-06-21
 */
@Api(tags = { "邀请同意记录" })
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "apitableAuditInviteRecord")
public class ApitableAuditInviteRecordController extends BaseController {

    @Resource
    private IApitableAuditInviteRecordService apitableAuditInviteRecordService;

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
    public Result list(ApitableAuditInviteRecordRequest request) {
        return success(apitableAuditInviteRecordService.list(request));
    }

    /**
     * 查询邀请同意记录分页列表
     * @param request 查询参数
     */
    @GetMapping("/page")
    @Log(title = "邀请同意记录列表")
    @ApiOperation(value = "邀请同意记录列表")
    public Result page(ApitableAuditInviteRecordRequest request) {
        if (request.getIgnoreMsg() == null) {
            request.setIgnoreMsg(1);
        }
        if (Objects.equals(userContext.getFromAgent(), "web")) {
            request.setAccepter(userContext.getUserId());
        }
        return success(apitableAuditInviteRecordService.page(request));
    }


    /**
     * 新增邀请同意记录类型
     *
     * @param apitableAuditInviteRecord apitableAuditInviteRecord 实体
     */
    @PostMapping("save")
    @Log(title = "新增邀请同意记录")
    @ApiOperation(value = "邀请同意记录")
    public Result save(@RequestBody ApitableAuditInviteRecord apitableAuditInviteRecord) {
        return auto(apitableAuditInviteRecordService.save(apitableAuditInviteRecord));
    }

    /**
     * 修改邀请同意记录
     * @param apitableAuditInviteRecord apitableAuditInviteRecord 实体
     */
    @PutMapping("edit")
    @Log(title = "修改邀请同意记录")
    @ApiOperation(value = "修改邀请同意记录")
    public Result edit(@RequestBody ApitableAuditInviteRecord apitableAuditInviteRecord) {

        return auto(apitableAuditInviteRecordService.updateById(apitableAuditInviteRecord));
    }

    /**
     * 删除邀请同意记录类型
     *
     * @param id apitableAuditInviteRecord编号
     */
    @DeleteMapping("remove")
    @Log(title = "删除邀请同意记录")
    @ApiOperation(value = "删除邀请同意记录")
    public Result remove(@RequestParam String id) {
        return auto(apitableAuditInviteRecordService.removeById(id));
    }

    /**
     * 删除邀请同意记录类型
     *
     * @param ids apitableAuditInviteRecord 实体
     */
    @DeleteMapping("removeBatch")
    @Log(title = "批量删除")
    @ApiOperation(value = "批量删除")
    public Result removeBatch(@RequestParam List<String> ids) {
        return auto(apitableAuditInviteRecordService.removeByIds(ids));
    }
}
