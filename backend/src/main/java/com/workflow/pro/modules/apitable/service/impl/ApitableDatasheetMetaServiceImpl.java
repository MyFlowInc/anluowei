package com.workflow.pro.modules.apitable.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;


import com.workflow.pro.modules.apitable.domain.ApitableDatasheet;
import com.workflow.pro.modules.apitable.domain.ApitableDatasheetRecord;
import com.workflow.pro.modules.apitable.domain.dto.DataSheetRecordDTO;
import com.workflow.pro.modules.apitable.domain.dto.DatasheetSnapshot;
import com.workflow.pro.modules.apitable.domain.ro.FieldMapRo;
import com.workflow.pro.modules.apitable.domain.ro.MetaMapRo;
import com.workflow.pro.modules.apitable.domain.ro.MetaOpRo;
import com.workflow.pro.modules.apitable.domain.ro.RecordDataRo;
import com.workflow.pro.modules.apitable.domain.ro.RecordMapRo;
import com.workflow.pro.modules.apitable.domain.ro.SimpleDatasheetMetaDTO;
import com.workflow.pro.modules.apitable.domain.ro.SnapshotMapRo;
import com.workflow.pro.modules.apitable.domain.ro.ViewMapRo;
import com.workflow.pro.modules.apitable.domain.vo.DatasheetRecordMapVo;
import com.workflow.pro.modules.apitable.enums.FieldType;
import com.workflow.pro.modules.apitable.enums.FieldTypeEn;
import com.workflow.pro.modules.apitable.exception.BusinessExceptionNew;
import com.workflow.pro.modules.apitable.mapper.ApitableDatasheetMetaMapper;
import com.workflow.pro.modules.apitable.domain.ApitableDatasheetMeta;
import com.workflow.pro.modules.apitable.mapper.ApitableDatasheetRecordMapper;
import com.workflow.pro.modules.apitable.param.AddFieldRequest;
import com.workflow.pro.modules.apitable.param.ApitableDatasheetMetaRequest;
import com.workflow.pro.modules.apitable.service.IApitableDatasheetMetaService;
import com.workflow.pro.modules.apitable.service.IApitableDatasheetRecordService;
import com.workflow.pro.modules.apitable.utils.IdUtil;


import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.apitable.client.api.model.CreateRecordRequest;
import com.apitable.client.api.model.RecordMap;
import com.apitable.client.api.model.UpdateRecord;
import com.apitable.client.api.model.UpdateRecordRequest;


/**
 * 数据元数据ServiceImpl业务层处理
 *
 * @author some
 * @date 2023-05-22
 */
@Service
public class ApitableDatasheetMetaServiceImpl extends ServiceImpl<ApitableDatasheetMetaMapper, ApitableDatasheetMeta> implements IApitableDatasheetMetaService {

    @Resource
    ApitableDatasheetMetaMapper apitableDatasheetMetaMapper;


    @Resource
    ApitableDatasheetRecordMapper datasheetRecordMapper;

    @Resource
    private IApitableDatasheetRecordService datasheetRecordService;

    /**
     * 查询数据元数据
     *
     * @param id 数据元数据ID
     * @return 数据元数据
     */
    @Override
    public ApitableDatasheetMeta selectApitableDatasheetMetaById(String id) {
        return apitableDatasheetMetaMapper.selectApitableDatasheetMetaById(id);
    }

    /**
     * 查询数据元数据列表
     * @return 数据元数据
     */
    @Override
    public List<ApitableDatasheetMeta> list(ApitableDatasheetMetaRequest request) {
        return apitableDatasheetMetaMapper.selectApitableDatasheetMetaList(request);
    }

    /**
     * 查询数据元数据
     * @return 数据元数据 分页集合
     * */
    @Override
    public PageResponse<ApitableDatasheetMeta> page(ApitableDatasheetMetaRequest request) {
        return Pageable.of(request, (() -> apitableDatasheetMetaMapper.selectApitableDatasheetMetaList(request)));
    }

    /**
     * 新增数据元数据
     *
     * @param apitableDatasheetMeta 数据元数据
     * @return 结果
     */

    @Override
    public int insertApitableDatasheetMeta(ApitableDatasheetMeta apitableDatasheetMeta) {
        return apitableDatasheetMetaMapper.insertApitableDatasheetMeta(apitableDatasheetMeta);
    }

    /**
     * 修改数据元数据
     *
     * @param apitableDatasheetMeta 数据元数据
     * @return 结果
     */
    @Override
    public int updateApitableDatasheetMeta(ApitableDatasheetMeta apitableDatasheetMeta) {
        return apitableDatasheetMetaMapper.updateApitableDatasheetMeta(apitableDatasheetMeta);
    }

    /**
     * 删除数据元数据对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteApitableDatasheetMetaByIds(String[] ids) {
        return apitableDatasheetMetaMapper.deleteApitableDatasheetMetaByIds(ids);
    }

    /**
     * 删除数据元数据信息
     *
     * @param id 数据元数据ID
     * @return 结果
     */
    @Override
    public int deleteApitableDatasheetMetaById(String id) {
        return apitableDatasheetMetaMapper.deleteApitableDatasheetMetaById(id);
    }

    //向record表增加一个record 返回 record id
    //1.build rows 的filed id
    //2.build filedMap中 字段
    //3.然后 build view 中columns 中fieldId
    @Override
    public ApitableDatasheetMeta addField(String spaceId, String datasheetId, AddFieldRequest fieldRequest) throws BusinessExceptionNew {


        Long userId = 1L;
        Map<String, Object> metaMap = MapUtil.newHashMap();
        String fieldId = IdUtil.createFieldId();
        String recordId = IdUtil.createRecordId();

        ApitableDatasheetMeta exist = getOne(new QueryWrapper<ApitableDatasheetMeta>().eq("dst_id", datasheetId));
        if (exist == null || exist.getId() == null) {
            throw new BusinessExceptionNew(3011, "当前dst_id不存在");
        }
        SimpleDatasheetMetaDTO meta = findByDstId(datasheetId);
        MetaMapRo metaMapRo = meta.getMeta().toBean(MetaMapRo.class);

        if (fieldRequest.getFieldId() != null) {
            //标识更新
            Object o = metaMapRo.getFieldMap().get(fieldRequest.getFieldId());
            Object property = JSONUtil.parseObj(o).get("property");
            Object type = JSONUtil.parseObj(o).get("type");


            if (o == null) {
                throw new BusinessExceptionNew(3012, "当前fieldId不存在");
            }

            FieldMapRo build = FieldMapRo.builder()
                    .id(fieldRequest.getFieldId())
                    .name(fieldRequest.getName())
                    .type(fieldRequest.getType() == null ? (Integer) type : FieldTypeEn.getPatternStr(fieldRequest.getType()))
                    .property(fieldRequest.getProperty() == null ? (JSONObject) property : fieldRequest.getProperty())
                    .build();
            metaMapRo.getFieldMap().set(fieldRequest.getFieldId(), build);
        }
        else {
            JSONArray views = new JSONArray();
            JSONObject columns = JSONUtil.createObj();
            columns.set("fieldId", fieldId);
            metaMapRo.getViews().jsonIter().forEach(json -> {
                ViewMapRo viewMapRo = JSONUtil.toBean(json, ViewMapRo.class);
                viewMapRo.getColumns().set(columns);
                views.add(viewMapRo);
            });

            FieldMapRo build = FieldMapRo.builder()
                    .id(fieldId)
                    .name(fieldRequest.getName())
                    .type(FieldTypeEn.getPatternStr(fieldRequest.getType()))
                    .property(fieldRequest.getProperty())
                    .build();


            metaMapRo.getFieldMap().set(fieldId, build);
            metaMapRo.setViews(views);

        }

        JSONObject metaData = JSONUtil.parseObj(metaMapRo);

        exist.setMetaData(metaData.toString());
        updateById(exist);
        return exist;
    }

    @Override
    public int delField(String dstId, List<String> delFieldIds, boolean saveD) throws BusinessExceptionNew {
        ApitableDatasheetMeta exist = getOne(new QueryWrapper<ApitableDatasheetMeta>().eq("dst_id", dstId));
        if (exist == null || exist.getId() == null) {
            throw new BusinessExceptionNew(3011, "当前dst_id不存在");
        }

        SimpleDatasheetMetaDTO meta = findByDstId(dstId);
        MetaMapRo metaMapRo = meta.getMeta().toBean(MetaMapRo.class);
        // fieldMap processing
        delFieldIds.forEach(fieldId -> metaMapRo.getFieldMap().remove(fieldId));
        // view attribute processing
        this.delViewFieldId(metaMapRo, delFieldIds, delFieldIds);

        DatasheetRecordMapVo recordMapVo = datasheetRecordService.delFieldData(dstId, delFieldIds, false);

        SnapshotMapRo snapshotMapRo = SnapshotMapRo.builder().meta(JSONUtil.parseObj(metaMapRo)).recordMap(recordMapVo.getRecordMap()).build();

        exist.setMetaData(snapshotMapRo.getMeta().toString());
        updateById(exist);
        return 0;
    }


    private void delViewFieldId(MetaMapRo metaMapRo, List<String> delFieldIds, List<String> delFieldIdsInFilter) {
        if (CollUtil.isNotEmpty(delFieldIds) || CollUtil.isNotEmpty(delFieldIdsInFilter)) {
            JSONArray views = JSONUtil.createArray();
            metaMapRo.getViews().jsonIter().forEach(view -> {
                ViewMapRo viewMapRo = JSONUtil.toBean(view, ViewMapRo.class);
                // columns processing
                JSONArray columns = delInfoIfExistFieldId(delFieldIds, viewMapRo.getColumns());
                viewMapRo.setColumns(columns);
                views.add(viewMapRo);
            });
            metaMapRo.setViews(views);
        }
    }

    /**
     * Deletes the object containing the specified field id from the object array
     */
    private JSONArray delInfoIfExistFieldId(List<String> delFieldIds, JSONArray filterInfo) {
        if (!JSONUtil.isNull(filterInfo)) {
            JSONArray array = JSONUtil.createArray();
            filterInfo.jsonIter().forEach(info -> {
                Object fieldId = info.get("fieldId");
                if (fieldId != null && !delFieldIds.contains(fieldId.toString())) {
                    array.add(info);
                }
            });
            return array;
        }
        return filterInfo;
    }


    @Override
    public SimpleDatasheetMetaDTO findByDstId(String dstId) {
        return apitableDatasheetMetaMapper.selectByNodeId(dstId);
    }


    public DatasheetSnapshot getMetaByDstId(String dstId) {
        return apitableDatasheetMetaMapper.selectByDstId(dstId);
    }

    public void edit(Long userId, String dstId, MetaOpRo meta) {
        boolean flag = SqlHelper.retBool(apitableDatasheetMetaMapper.updateByDstId(userId, StrUtil.toString(meta.getMeta()), dstId));

    }


    @Override
    public List<ApitableDatasheetRecord>  addRecords(String dstId, CreateRecordRequest record) throws BusinessExceptionNew {
        ApitableDatasheetMeta exist = getOne(new QueryWrapper<ApitableDatasheetMeta>().eq("dst_id", dstId));
        if (exist == null || exist.getId() == null) {
            throw new BusinessExceptionNew(3011, "当前dst_id不存在");
        }
        JSONObject recordMap = JSONUtil.createObj();
        JSONArray rows = JSONUtil.createArray();
        List<RecordMap> records = record.getRecords();

        //todo缺少对比view层的column 变成了直接插入
        for (RecordMap list1 : records) {
            Map<String, Object> fields = list1.getFields();
            JSONObject jsonObject = JSONUtil.parseObj(fields);
            String recordId = IdUtil.createRecordId();
            RecordMapRo recordMapRo = RecordMapRo.builder().id(recordId).data(jsonObject).build();
            recordMap.set(recordId, recordMapRo);
            //row加入新的记录再统计后面的查询出来的记录
            JSONObject recordJson = JSONUtil.createObj();
            recordJson.set("recordId", recordId);
            rows.add(recordJson);
        }
        List<DataSheetRecordDTO> voList = datasheetRecordMapper.selectDtoByDstId(dstId);
        voList.forEach(vo -> {
            String recordId = vo.getRecordId();
            JSONObject recordJson = JSONUtil.createObj();
            recordJson.set("recordId", recordId);
            rows.add(recordJson);
        });
        updateViewRows(dstId, rows);
        List<ApitableDatasheetRecord> apitableDatasheetRecords = datasheetRecordService.saveBatchRecord("1", recordMap, dstId);
        //增加record表 增加 meta表的record记录
        // processing record
        return apitableDatasheetRecords;
    }

    @Override
    public List<ApitableDatasheetRecord> updateRecords(String dstId, UpdateRecordRequest record) throws BusinessExceptionNew {
        ApitableDatasheetMeta exist = getOne(new QueryWrapper<ApitableDatasheetMeta>().eq("dst_id", dstId));
        if (exist == null || exist.getId() == null) {
            throw new BusinessExceptionNew(3011, "当前dst_id不存在");
        }
        JSONObject recordMap = JSONUtil.createObj();
        JSONArray rows = JSONUtil.createArray();
        List<UpdateRecord> records = record.getRecords();

        for (UpdateRecord list1 : records) {
            if (list1.getRecordId() != null || !Objects.equals(list1.getRecordId(), "")) {
                Map<String, Object> fields = list1.getFields();
                JSONObject jsonObject = JSONUtil.parseObj(fields);
                String recordId = list1.getRecordId();
                RecordMapRo recordMapRo = RecordMapRo.builder().id(recordId).data(jsonObject).build();
                recordMap.set(recordId, recordMapRo);
                JSONObject recordJson = JSONUtil.createObj();
                recordJson.set("recordId", recordId);
                rows.add(recordJson);
            }
        }
        updateViewRows(dstId, rows);

        List<ApitableDatasheetRecord> apitableDatasheetRecords = datasheetRecordService.saveBatchRecord("1", recordMap, dstId);
        return apitableDatasheetRecords;
    }

    @Override
    public int deleteRecords(String dstId, List<String> recordIds) {
        for (String recId : recordIds) {
            datasheetRecordMapper.delete(new QueryWrapper<ApitableDatasheetRecord>().eq("record_id", recId));
        }
        JSONArray rows = JSONUtil.createArray();
        List<DataSheetRecordDTO> voList = datasheetRecordMapper.selectDtoByDstId(dstId);
        voList.forEach(vo -> {
            String recordId = vo.getRecordId();
            JSONObject recordJson = JSONUtil.createObj();
            recordJson.set("recordId", recordId);
            rows.add(recordJson);
        });
        updateViewRows(dstId, rows);
        return 0;
    }

    private void updateViewRows(String dstId, JSONArray rows) {
        List<DataSheetRecordDTO> voList = datasheetRecordMapper.selectDtoByDstId(dstId);
        voList.forEach(vo -> {
            String recordId = vo.getRecordId();
            JSONObject recordJson = JSONUtil.createObj();
            recordJson.set("recordId", recordId);
            rows.add(recordJson);
        });
        JSONArray views = new JSONArray();

        SimpleDatasheetMetaDTO meta = this.findByDstId(dstId);

        MetaMapRo metaMapRo = meta.getMeta().toBean(MetaMapRo.class);
        metaMapRo.getViews().jsonIter().forEach(json -> {
            ViewMapRo viewMapRo = JSONUtil.toBean(json, ViewMapRo.class);
            viewMapRo.setRows(rows);
            views.set(viewMapRo);
        });
        metaMapRo.setViews(views);

        ApitableDatasheetMeta metaNew = this.getOne(new QueryWrapper<ApitableDatasheetMeta>().eq("dst_id", dstId));

        JSONObject metaData = JSONUtil.parseObj(metaMapRo);

        metaNew.setMetaData(metaData.toString());
        this.updateById(metaNew);
    }
}
