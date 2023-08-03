package com.workflow.pro.modules.apitable.controller;


import com.workflow.pro.common.constant.ControllerConstant;
import com.workflow.pro.modules.apitable.domain.vo.EmailVo;
import io.swagger.annotations.Api;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.workflow.pro.modules.apitable.service.ISysCaptchaService;

import com.workflow.pro.modules.apitable.param.SysCaptchaRequest;
import com.workflow.pro.modules.apitable.domain.SysCaptcha;

import javax.annotation.Resource;

import java.util.List;

import io.swagger.annotations.ApiOperation;
import com.workflow.pro.common.web.base.BaseController;
import com.workflow.pro.common.aop.annotation.Log;
import com.workflow.pro.common.web.domain.Result;

/**
 * 【请填写功能名称】Controller
 *
 * @author some
 * @date 2023-05-30
 */
@Api(tags = { "【请填写功能名称】" })
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "sysCaptcha")
public class SysCaptchaController extends BaseController {

    @Resource
    private ISysCaptchaService sysCaptchaService;

    /**
     * 查询【请填写功能名称】列表
     * @param request 查询参数
     */
    @GetMapping("/list")
    @Log(title = "【请填写功能名称】")
    @ApiOperation(value = "【请填写功能名称】")
    public Result list(SysCaptchaRequest request) {
        return success(sysCaptchaService.list(request));
    }

    /**
     * 查询【请填写功能名称】分页列表
     * @param request 查询参数
     */
    @GetMapping("/page")
    @Log(title = "【请填写功能名称】列表")
    @ApiOperation(value = "【请填写功能名称】列表")
    public Result page(SysCaptchaRequest request) {
        return success(sysCaptchaService.page(request));
    }


    /**
     * 新增【请填写功能名称】类型
     *
     * @param sysCaptcha sysCaptcha 实体
     */
    @PostMapping("save")
    @Log(title = "新增【请填写功能名称】")
    @ApiOperation(value = "【请填写功能名称】")
    public Result save(@RequestBody SysCaptcha sysCaptcha) {
        return auto(sysCaptchaService.save(sysCaptcha));
    }

    /**
     * 修改【请填写功能名称】
     * @param sysCaptcha sysCaptcha 实体
     */
    @PutMapping("edit")
    @Log(title = "修改【请填写功能名称】")
    @ApiOperation(value = "修改【请填写功能名称】")
    public Result edit(@RequestBody SysCaptcha sysCaptcha) {
        return auto(sysCaptchaService.updateById(sysCaptcha));
    }

    /**
     * 删除【请填写功能名称】类型
     *
     * @param id sysCaptcha编号
     */
    @DeleteMapping("remove")
    @Log(title = "删除【请填写功能名称】")
    @ApiOperation(value = "删除【请填写功能名称】")
    public Result remove(@RequestParam String id) {
        return auto(sysCaptchaService.removeById(id));
    }

    /**
     * 删除【请填写功能名称】类型
     *
     * @param ids sysCaptcha 实体
     */
    @DeleteMapping("removeBatch")
    @Log(title = "批量删除")
    @ApiOperation(value = "批量删除")
    public Result removeBatch(@RequestParam List<String> ids) {
        return auto(sysCaptchaService.removeByIds(ids));
    }


    @PostMapping("send")
    @ApiOperation("发送邮件")
    public Result<Object> send(@RequestBody SysCaptcha sysCaptcha) throws Exception {
        SysCaptcha send = new SysCaptcha();
        if (sysCaptcha.getEmail() == null) {
            return Result.failure(2001, "发送邮箱必填");
        }
        try {
            send = sysCaptchaService.send(sysCaptcha);
        }
        catch (Exception e) {
            return Result.failure(3001, e.getMessage());
        }
        return Result.success(send);
    }
}
