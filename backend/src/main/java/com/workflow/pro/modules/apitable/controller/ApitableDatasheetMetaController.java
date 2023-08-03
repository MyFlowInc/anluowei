package com.workflow.pro.modules.apitable.controller;


import com.workflow.pro.common.constant.ControllerConstant;
import com.workflow.pro.modules.apitable.exception.BusinessExceptionNew;
import com.workflow.pro.modules.apitable.param.AddFieldRequest;
import io.swagger.annotations.Api;

import org.springframework.web.bind.annotation.*;

import com.workflow.pro.modules.apitable.service.IApitableDatasheetMetaService;

import com.workflow.pro.modules.apitable.param.ApitableDatasheetMetaRequest;
import com.workflow.pro.modules.apitable.domain.ApitableDatasheetMeta;

import javax.annotation.Resource;

import java.util.List;
import java.util.Properties;

import io.swagger.annotations.ApiOperation;
import com.workflow.pro.common.web.base.BaseController;
import com.workflow.pro.common.aop.annotation.Log;
import com.workflow.pro.common.web.domain.Result;

import com.apitable.client.api.exception.ApiException;
import com.apitable.client.api.model.CreateFieldRequest;
import com.apitable.client.api.model.CreateFieldResponse;
import com.apitable.client.api.model.HttpResult;
import com.apitable.client.api.model.builder.CreateFieldRequestBuilder;
import com.apitable.client.api.model.field.FieldTypeEnum;
import com.apitable.client.api.model.field.property.BaseFieldProperty;
import com.apitable.client.api.model.field.property.EmptyProperty;
import com.apitable.core.http.GenericTypeReference;
import com.apitable.core.http.HttpHeader;
import com.apitable.core.utils.StringUtil;

/**
 * 数据元数据Controller
 *
 * @author some
 * @date 2023-05-22
 */
@Api(tags = { "数据元数据" })
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "apitableDatasheetMeta")
public class ApitableDatasheetMetaController extends BaseController {

    @Resource
    private IApitableDatasheetMetaService apitableDatasheetMetaService;


    /**
     * 查询数据元数据列表
     * @param request 查询参数
     */
    @GetMapping("/list")
    @Log(title = "数据元数据")
    @ApiOperation(value = "数据元数据")
    public Result list(ApitableDatasheetMetaRequest request) {
        return success(apitableDatasheetMetaService.list(request));
    }

    /**
     * 查询数据元数据分页列表
     * @param request 查询参数
     */
    @GetMapping("/page")
    @Log(title = "数据元数据列表")
    @ApiOperation(value = "数据元数据列表")
    public Result page(ApitableDatasheetMetaRequest request) {
        return success(apitableDatasheetMetaService.page(request));
    }


    /**
     * 新增数据元数据类型
     *
     * @param apitableDatasheetMeta apitableDatasheetMeta 实体
     */
    @PostMapping("save")
    @Log(title = "新增数据元数据")
    @ApiOperation(value = "数据元数据")
    public Result save(@RequestBody ApitableDatasheetMeta apitableDatasheetMeta) {
        return auto(apitableDatasheetMetaService.save(apitableDatasheetMeta));
    }

    /**
     * 修改数据元数据
     * @param apitableDatasheetMeta apitableDatasheetMeta 实体
     */
    @PutMapping("edit")
    @Log(title = "修改数据元数据")
    @ApiOperation(value = "修改数据元数据")
    public Result edit(@RequestBody ApitableDatasheetMeta apitableDatasheetMeta) {
        return auto(apitableDatasheetMetaService.updateById(apitableDatasheetMeta));
    }

    /**
     * 删除数据元数据类型
     *
     * @param id apitableDatasheetMeta编号
     */
    @DeleteMapping("remove")
    @Log(title = "删除数据元数据")
    @ApiOperation(value = "删除数据元数据")
    public Result remove(@RequestParam String id) {
        return auto(apitableDatasheetMetaService.removeById(id));
    }

    /**
     * 删除数据元数据类型
     *
     * @param ids apitableDatasheetMeta 实体
     */
    @DeleteMapping("removeBatch")
    @Log(title = "批量删除")
    @ApiOperation(value = "批量删除")
    public Result removeBatch(@RequestParam List<String> ids) {
        return auto(apitableDatasheetMetaService.removeByIds(ids));
    }

    //编辑表结构
    //增加单个field 结构
    //   "id":"fldcvLnUdpo3A",
    //           "name":"日期类型",
    //           "type":5,
    //           "property":{
    //     "autoFill":false,
    //             "dateFormat":0,
    //             "timeFormat":1,
    //             "includeTime":false
    // }

    /**
     * 增加数据元数据类型
     *
     */
    @PostMapping("addField")
    @Log(title = "添加field")
    @ApiOperation(value = "addField")
    public Result addField(@RequestParam(value = "spaceId") String spaceId, @RequestParam(value = "dstId") String datasheetId, @RequestBody AddFieldRequest field) throws ApiException {
        //checkPostFieldPathArgs(spaceId, datasheetId);
        ApitableDatasheetMeta datasheetMeta = new ApitableDatasheetMeta();
        try {
            datasheetMeta = apitableDatasheetMetaService.addField(spaceId, datasheetId, field);
        }
        catch (BusinessExceptionNew e) {
            return Result.failure(e.getCode(), e.getMessage());
        }
        return success(datasheetMeta);
    }

    /**
     * 删除数据元数据类型
     *
     */
    @PostMapping("delField")
    @Log(title = "delField")
    @ApiOperation(value = "delField")
    public Result delField(@RequestParam(value = "dstId") String datasheetId, @RequestBody List<String> delFieldIds) throws ApiException {
        //checkPostFieldPathArgs(spaceId, datasheetId);
        apitableDatasheetMetaService.delField(datasheetId, delFieldIds, false);
        return success();
    }


    private void checkPostFieldPathArgs(String spaceId, String datasheetId) {

        if (!StringUtil.hasText(spaceId)) {
            throw new ApiException("space id must be not null");
        }

        if (!StringUtil.hasText(datasheetId)) {
            throw new ApiException("datasheet id must be not null");
        }

    }

}
