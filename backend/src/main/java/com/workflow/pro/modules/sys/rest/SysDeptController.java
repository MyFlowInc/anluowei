package com.workflow.pro.modules.sys.rest;

import com.workflow.pro.common.aop.annotation.Log;
import com.workflow.pro.modules.sys.domain.SysDept;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpDepart;
import org.springframework.web.bind.annotation.*;
import com.workflow.pro.modules.sys.service.SysDeptService;
import com.workflow.pro.common.constant.ControllerConstant;
import com.workflow.pro.common.web.base.BaseController;
import com.workflow.pro.common.web.domain.Result;

import javax.annotation.Resource;
import java.util.List;

/**
 * 部门控制器
 * <p>
 * Author: SOME
 * CreateTime: 2022/03/27
 */
@Api(tags = {"部门"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "dept")
public class SysDeptController extends BaseController {

    @Resource
    private SysDeptService sysDeptService;

    /**
     * 查询部门列表
     *
     * @return {@link Result}
     */
    @GetMapping("tree")
    @Log(title = "查询部门")
    @ApiOperation(value = "查询部门")
    public Result tree() {
        return success(sysDeptService.tree());
    }

    /**
     * 新增部门
     *
     * @param sysDept 部门实体
     */
    @PostMapping("save")
    @Log(title = "新增部门")
    @ApiOperation(value = "新增部门")
    public Result save(@RequestBody SysDept sysDept) {
        return auto(sysDeptService.save(sysDept));
    }

    /**
     * 修改部门
     *
     * @param sysDept 部门实体
     */
    @PutMapping("edit")
    @Log(title = "修改部门")
    @ApiOperation(value = "修改部门")
    public Result edit(@RequestBody SysDept sysDept) {
        return auto(sysDeptService.updateById(sysDept));
    }

    /**
     * 删除部门
     *
     * @param id 部门编号
     */
    @DeleteMapping("remove")
    @Log(title = "删除部门")
    @ApiOperation(value = "删除部门")
    public Result remove(String id) {

        /// 检 测 是 否 存 在 子 部 门
        if (sysDeptService.lambdaQuery().eq(SysDept::getParent, id).count() > 0) return failure();

        return auto(sysDeptService.removeById(id));
    }

    /**
     * 删除部门
     *
     * @param ids 部门编号
     */
    @DeleteMapping("removeBatch")
    public Result removeBatch(List<String> ids) {
        return auto(sysDeptService.removeByIds(ids));
    }

    @Resource
    private WxCpService wxCpService;

    /**
     * 新增部门
     */
    @PostMapping("saveSync")
    @Log(title = "新增部门")
    @ApiOperation(value = "新增部门")
    public Result saveSync() throws WxErrorException {
        List<WxCpDepart> Departs = wxCpService.getDepartmentService().list(Long.valueOf("1"));
        System.out.println("执行 同步  部门信息 任务");
        sysDeptService.syncDepartInfo(Departs);
        return success();
    }

}