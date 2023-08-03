package com.workflow.pro.modules.apitable.service;

import java.util.List;


import com.baomidou.mybatisplus.extension.service.IService;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;


import com.workflow.pro.modules.apitable.domain.ApitableDatasheetRecord;
import com.workflow.pro.modules.apitable.domain.ro.SimpleDatasheetMetaDTO;
import com.workflow.pro.modules.apitable.exception.BusinessExceptionNew;
import com.workflow.pro.modules.apitable.param.AddFieldRequest;
import com.workflow.pro.modules.apitable.param.ApitableDatasheetMetaRequest;
import com.workflow.pro.modules.apitable.domain.ApitableDatasheetMeta;

import com.apitable.client.api.model.CreateFieldRequest;
import com.apitable.client.api.model.CreateRecordRequest;
import com.apitable.client.api.model.UpdateRecordRequest;
import com.apitable.client.api.model.field.property.BaseFieldProperty;

/**
 * 数据元数据IService接口
 *
 * @author some
 * @date 2023-05-22
 */
public interface IApitableDatasheetMetaService extends IService<ApitableDatasheetMeta> {
    /**
     * 查询数据元数据
     *
     * @param id 数据元数据ID
     * @return 数据元数据
     */
    ApitableDatasheetMeta selectApitableDatasheetMetaById(String id);


    /**
     * 查询数据元数据
     * @param  request pageSize pageNum
     * @return 数据元数据 分页集合
     * */
    PageResponse<ApitableDatasheetMeta> page(ApitableDatasheetMetaRequest request);

    /**
     * 查询数据元数据列表
     * @return 数据元数据集合
     */
    List<ApitableDatasheetMeta> list(ApitableDatasheetMetaRequest request);

    /**
     * 新增数据元数据
     *
     * @param apitableDatasheetMeta 数据元数据
     * @return 结果
     */
    int insertApitableDatasheetMeta(ApitableDatasheetMeta apitableDatasheetMeta);

    /**
     * 修改数据元数据
     *
     * @param apitableDatasheetMeta 数据元数据
     * @return 结果
     */
    int updateApitableDatasheetMeta(ApitableDatasheetMeta apitableDatasheetMeta);

    /**
     * 批量删除数据元数据
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteApitableDatasheetMetaByIds(String[] ids);

    /**
     * 删除数据元数据信息
     *
     * @param id 数据元数据ID
     * @return 结果
     */
    int deleteApitableDatasheetMetaById(String id);

    ApitableDatasheetMeta addField(String spaceId, String datasheetId, AddFieldRequest field) throws BusinessExceptionNew;

    int delField(String dstId, List<String> delFieldIds, boolean saveD);

    SimpleDatasheetMetaDTO findByDstId(String dstId);

    List<ApitableDatasheetRecord>  addRecords(String dstId, CreateRecordRequest record) throws BusinessExceptionNew;

    List<ApitableDatasheetRecord> updateRecords(String dstId, UpdateRecordRequest record) throws BusinessExceptionNew;


    int deleteRecords(String dstId, List<String> recordIds);


}
