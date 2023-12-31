package com.workflow.pro.modules.sys.rest;

import com.workflow.pro.common.aop.annotation.Log;
import com.workflow.pro.modules.sys.domain.SysDict;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import com.workflow.pro.modules.sys.param.SysDictRequest;
import com.workflow.pro.modules.sys.service.SysDictService;
import com.workflow.pro.common.constant.ControllerConstant;
import com.workflow.pro.common.web.base.BaseController;
import com.workflow.pro.common.web.domain.Result;
import javax.annotation.Resource;
import java.util.List;

/**
 * 数据字典类型控制器
 *
 * Author: SOME
 * CreateTime: 2022/04/28
 * */
@Api(tags = {"字典"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "dict")
public class SysDictController extends BaseController {

    @Resource
    private SysDictService sysDictService;

    /**
     * 查询字典类型
     *
     * @param request 查询参数
     */
    @GetMapping("page")
    @Log(title = "字典列表")
    @ApiOperation(value = "字典列表")
    public Result page(SysDictRequest request){
       return success(sysDictService.page(request));
    }

    /**
     * 新增字典类型
     *
     * @param sysDict 字典实体
     */
    @PostMapping("save")
    @Log(title = "新增字典")
    @ApiOperation(value = "新增字典")
    public Result save(@RequestBody SysDict sysDict) {
        return auto(sysDictService.save(sysDict));
    }

    /**
     * 修改字典类型
     *
     * @param sysDict 字典实体
     */
    @PutMapping("edit")
    @Log(title = "修改字典")
    @ApiOperation(value = "修改字典")
    public Result edit(@RequestBody SysDict sysDict) {
        return auto(sysDictService.updateById(sysDict));
    }

    /**
     * 删除字典类型
     *
     * @param id 字典编号
     */
    @DeleteMapping("remove")
    @Log(title = "删除字典")
    @ApiOperation(value = "删除字典")
    public Result remove(@RequestParam String id) {
        return auto(sysDictService.removeById(id));
    }

    /**
     * 删除字典类型
     *
     * @param ids 字典实体
     */
    @DeleteMapping("removeBatch")
    @Log(title = "批量删除")
    @ApiOperation(value = "批量删除")
    public Result removeBatch(@RequestParam List<String> ids) {
        return auto(sysDictService.removeByIds(ids));
    }
}