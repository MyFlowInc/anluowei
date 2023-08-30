package com.workflow.pro.modules.apitable.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qiniu.util.Json;
import com.qiniu.util.StringMap;
import com.workflow.pro.common.constant.ControllerConstant;
import com.workflow.pro.modules.apitable.controller.api.RecordApi;
import com.workflow.pro.modules.apitable.domain.ApitableDatasheet;
import com.workflow.pro.modules.apitable.domain.ApitableDatasheetMeta;
import com.workflow.pro.modules.apitable.exception.BusinessExceptionNew;
import com.workflow.pro.modules.apitable.param.AddFieldRecordUpdate;
import com.workflow.pro.modules.apitable.service.IApitableDatasheetMetaService;
import io.swagger.annotations.Api;

import org.springframework.web.bind.annotation.*;

import com.workflow.pro.modules.apitable.service.IApitableDatasheetRecordService;

import com.workflow.pro.modules.apitable.param.ApitableDatasheetRecordRequest;
import com.workflow.pro.modules.apitable.domain.ApitableDatasheetRecord;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import io.swagger.annotations.ApiOperation;
import com.workflow.pro.common.web.base.BaseController;
import com.workflow.pro.common.aop.annotation.Log;
import com.workflow.pro.common.web.domain.Result;

import com.apitable.client.api.ApitableApiClient;
import com.apitable.client.api.exception.ApiException;
import com.apitable.client.api.http.ApiCredential;
import com.apitable.client.api.http.ApiHttpClient;
import com.apitable.client.api.http.ApiHttpClient.ApiVersion;
import com.apitable.client.api.model.ApiQueryParam;
import com.apitable.client.api.model.CreateRecordRequest;
import com.apitable.client.api.model.HttpResult;
import com.apitable.client.api.model.Pager;
import com.apitable.client.api.model.PagerInfo;
import com.apitable.client.api.model.Record;
import com.apitable.client.api.model.Records;
import com.apitable.client.api.model.UpdateRecordRequest;
import com.apitable.core.http.GenericTypeReference;
import com.apitable.core.http.HttpHeader;
import com.apitable.core.utils.MapUtil;
import com.apitable.core.utils.StringUtil;

/**
 * 数据单记录Controller
 *
 * @author some
 * @date 2023-05-22
 */
@Api(tags = { "数据单记录" })
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "apitableDatasheetRecord")
public class ApitableDatasheetRecordController extends BaseController {

    @Resource
    private IApitableDatasheetRecordService apitableDatasheetRecordService;


    @Resource
    private IApitableDatasheetMetaService apitableDatasheetMetaService;

    /**
     * 查询数据单记录列表
     * @param request 查询参数
     */
    @GetMapping("/list")
    @Log(title = "数据单记录")
    @ApiOperation(value = "数据单记录")
    public Result list(ApitableDatasheetRecordRequest request) {
        return success(apitableDatasheetRecordService.list(request));
    }

    /**
     * 查询数据单记录分页列表
     * @param request 查询参数
     */
    @GetMapping("/page")
    @Log(title = "数据单记录列表")
    @ApiOperation(value = "数据单记录列表")
    public Result page(ApitableDatasheetRecordRequest request) {
        return success(apitableDatasheetRecordService.page(request));
    }


    /**
     * 新增数据单记录类型
     *
     * @param apitableDatasheetRecord apitableDatasheetRecord 实体
     */
    @PostMapping("save")
    @Log(title = "新增数据单记录")
    @ApiOperation(value = "数据单记录")
    public Result save(@RequestBody ApitableDatasheetRecord apitableDatasheetRecord) {
        return auto(apitableDatasheetRecordService.save(apitableDatasheetRecord));
    }

    /**
     * 修改数据单记录
     * @param apitableDatasheetRecord apitableDatasheetRecord 实体
     */
    @PutMapping("edit")
    @Log(title = "修改数据单记录")
    @ApiOperation(value = "修改数据单记录")
    public Result edit(@RequestBody ApitableDatasheetRecord apitableDatasheetRecord) {
        return auto(apitableDatasheetRecordService.updateById(apitableDatasheetRecord));
    }

    /**
     * 删除数据单记录类型
     *
     * @param id apitableDatasheetRecord编号
     */
    @DeleteMapping("remove")
    @Log(title = "删除数据单记录")
    @ApiOperation(value = "删除数据单记录")
    public Result remove(@RequestParam String id) {
        return auto(apitableDatasheetRecordService.removeById(id));
    }

    /**
     * 删除数据单记录类型
     *
     * @param ids apitableDatasheetRecord 实体
     */
    @DeleteMapping("removeBatch")
    @Log(title = "批量删除")
    @ApiOperation(value = "批量删除")
    public Result removeBatch(@RequestParam List<String> ids) {
        return auto(apitableDatasheetRecordService.removeByIds(ids));
    }


    @PostMapping("addFieldRecord")
    @ApiOperation(value = "添加数据")
    public Result addFieldRecord(@RequestParam(value = "dstId") String datasheetId, @RequestBody CreateRecordRequest record) throws BusinessExceptionNew {

        if (!StringUtil.hasText(datasheetId)) {
            throw new ApiException("datasheet id must be not null");
        }
        if (record.getRecords().size() > 10) {
            throw new ApiException("record only can add 10 every request");
        }
        List<ApitableDatasheetRecord> apitableDatasheetRecords = apitableDatasheetMetaService.addRecords(datasheetId, record);
        return success(apitableDatasheetRecords);
    }

    @PostMapping("updateRecords")
    @ApiOperation(value = "updateRecords")
    public Result updateRecords(@RequestParam(value = "dstId") String datasheetId, @RequestBody UpdateRecordRequest record) {
        List<ApitableDatasheetRecord> apitableDatasheetRecords = apitableDatasheetMetaService.updateRecords(datasheetId, record);
        return success(apitableDatasheetRecords);
    }

    @PostMapping("delFieldData")
    @ApiOperation(value = "delFieldData")
    public Result delFieldData(@RequestParam(value = "dstId") String dstId, @RequestBody List<String> recordIds) {
        apitableDatasheetMetaService.deleteRecords(dstId, recordIds);
        return success();
    }

    //分享邀请功能
    @GetMapping("getRecord")
    @ApiOperation(value = "getRecord")
    public Result getRecords(@RequestParam(value = "record_id") String recordId) {

        ApitableDatasheetRecord record_id = apitableDatasheetRecordService.getOne(new QueryWrapper<ApitableDatasheetRecord>().eq("record_id", recordId), false);
        if (record_id == null) {
            throw new ApiException("record_id not exist");
        }

        return success(record_id);
    }
    //设置邀请状态
    @PostMapping("setRecordValue")
    public Result setRecordValue(@RequestParam(value = "record_id") String recordId, @RequestBody AddFieldRecordUpdate record) {
        ApitableDatasheetRecord record_id = apitableDatasheetRecordService.getOne(new QueryWrapper<ApitableDatasheetRecord>().eq("record_id", recordId), false);
        if (record_id == null) {
            throw new ApiException("record_id not exist");
        }
        if (!Objects.equals(record_id.getData(), "") || record_id.getData() != null) {
            StringMap decode = Json.decode(record_id.getData());
            decode.put(record.getFiledKey(), record.getFiledValue());
            String encode = Json.encode(decode);
            record_id.setData(encode);
            apitableDatasheetRecordService.updateApitableDatasheetRecord(record_id);

        }
        return success(record_id);
    }


    // public List<Record> getRecords(String datasheetId, int page, int itemsPerPage) throws ApiException {
    //     if (page < 0 || itemsPerPage < 0) {
    //         throw new ApiException("page or itemsPerPage don't set right");
    //     }
    //     ApiQueryParam queryParam = new ApiQueryParam(page, itemsPerPage);
    //     Map<String, String> uriVariables = queryParam.toMap();
    //     GenericTypeReference<HttpResult<PagerInfo<Record>>> reference = new GenericTypeReference<HttpResult<PagerInfo<Record>>>() {};
    //     String uri = String.format(PATH, datasheetId) + MapUtil.extractKeyToVariables(uriVariables);
    //     HttpResult<PagerInfo<Record>> result = getDefaultHttpClient().get(uri, new HttpHeader(), reference, uriVariables);
    //     return result.getData().getRecords();
    // }
}
