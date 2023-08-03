package com.workflow.pro.modules.apitable.controller;


import com.workflow.pro.common.constant.ControllerConstant;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import com.workflow.pro.modules.apitable.service.ISysUserGradeService;

import com.workflow.pro.modules.apitable.param.SysUserGradeRequest;
import com.workflow.pro.modules.apitable.domain.SysUserGrade;

import javax.annotation.Resource;
import java.util.List;

import io.swagger.annotations.ApiOperation;
import com.workflow.pro.common.web.base.BaseController;
import com.workflow.pro.common.aop.annotation.Log;
import com.workflow.pro.common.web.domain.Result;

/**
 * 会员等级Controller
 *
 * @author some
 * @date 2023-06-02
 */
@Api(tags = {"会员等级"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "sysUserGrade")
public class SysUserGradeController extends BaseController {

    @Resource
    private ISysUserGradeService sysUserGradeService;

    /**
     * 查询会员等级列表
     * @param request 查询参数
     */
    @GetMapping("/list")
    @Log(title = "会员等级")
    @ApiOperation(value = "会员等级")
    public Result list(SysUserGradeRequest request) {
     return success(sysUserGradeService.list(request));
    }

    /**
     * 查询会员等级分页列表
     * @param request 查询参数
     */
    @GetMapping("/page")
    @Log(title = "会员等级列表")
    @ApiOperation(value = "会员等级列表")
    public Result page(SysUserGradeRequest request) {
     return success(sysUserGradeService.page(request));
    }


    /**
     * 新增会员等级类型
     *
     * @param sysUserGrade sysUserGrade 实体
     */
    @PostMapping("save")
    @Log(title = "新增会员等级")
    @ApiOperation(value = "会员等级")
    public Result save(@RequestBody SysUserGrade sysUserGrade) {
        return auto(sysUserGradeService.save(sysUserGrade));
    }

    /**
     * 修改会员等级
     * @param sysUserGrade sysUserGrade 实体
     */
    @PutMapping("edit")
    @Log(title = "修改会员等级")
    @ApiOperation(value = "修改会员等级")
    public Result edit(@RequestBody SysUserGrade sysUserGrade) {
        return auto(sysUserGradeService.updateById(sysUserGrade));
    }

    /**
     * 删除会员等级类型
     *
     * @param id sysUserGrade编号
     */
    @DeleteMapping("remove")
    @Log(title = "删除会员等级")
    @ApiOperation(value = "删除会员等级")
    public Result remove(@RequestParam String id) {
        return auto(sysUserGradeService.removeById(id));
    }

    /**
     * 删除会员等级类型
     *
     * @param ids sysUserGrade 实体
     */
    @DeleteMapping("removeBatch")
    @Log(title = "批量删除")
    @ApiOperation(value = "批量删除")
    public Result removeBatch(@RequestParam List<String> ids) {
        return auto(sysUserGradeService.removeByIds(ids));
    }
}
