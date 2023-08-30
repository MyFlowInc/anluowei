package com.workflow.pro.modules.apitable.service;

import java.util.List;


import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.workflow.pro.common.web.base.page.PageResponse;


import com.workflow.pro.modules.apitable.domain.vo.DatasheetRecordMapVo;
import com.workflow.pro.modules.apitable.exception.BusinessExceptionNew;
import com.workflow.pro.modules.apitable.param.ApitableDatasheetRecordRequest;
import com.workflow.pro.modules.apitable.domain.ApitableDatasheetRecord;

import com.apitable.client.api.model.CreateRecordRequest;

/**
 * 数据单记录IService接口
 *
 * @author some
 * @date 2023-05-22
 */
public interface IApitableDatasheetRecordService extends IService<ApitableDatasheetRecord> {
    /**
     * 查询数据单记录
     *
     * @param id 数据单记录ID
     * @return 数据单记录
     */
    ApitableDatasheetRecord selectApitableDatasheetRecordById(String id);


    /**
     * 查询数据单记录
     * @param  request pageSize pageNum
     * @return 数据单记录 分页集合
     * */
    PageResponse<ApitableDatasheetRecord> page(ApitableDatasheetRecordRequest request);

    /**
     * 查询数据单记录列表
     * @return 数据单记录集合
     */
    List<ApitableDatasheetRecord> list(ApitableDatasheetRecordRequest request);

    /**
     * 新增数据单记录
     *
     * @param apitableDatasheetRecord 数据单记录
     * @return 结果
     */
    int insertApitableDatasheetRecord(ApitableDatasheetRecord apitableDatasheetRecord);

    /**
     * 修改数据单记录
     *
     * @param apitableDatasheetRecord 数据单记录
     * @return 结果
     */
    int updateApitableDatasheetRecord(ApitableDatasheetRecord apitableDatasheetRecord);

    /**
     * 批量删除数据单记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteApitableDatasheetRecordByIds(String[] ids);

    /**
     * 删除数据单记录信息
     *
     * @param id 数据单记录ID
     * @return 结果
     */
    int deleteApitableDatasheetRecordById(String id);

    List<ApitableDatasheetRecord> saveBatchRecord(String userId, JSONObject recordMap, String dstId);

    /**
     * Deletes the data of the specified field in the number table record.
     *
     * @param dstId datasheet id
     * @param delFieldIds deleted field id list
     * @param saveDb      whether save to database
     * @return map
     */
    DatasheetRecordMapVo delFieldData(String dstId, List<String> delFieldIds, boolean saveDb);

    int addRecords(String dstId, CreateRecordRequest record) throws BusinessExceptionNew;

    /**
     * 修订类型重置值
     *
     * @param datasheetId
     * @param fieldId
     */
    void resetFieldType(String datasheetId, String fieldId);
}

