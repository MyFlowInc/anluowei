package com.workflow.pro.modules.apitable.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;


import com.workflow.pro.modules.apitable.domain.ApitableDatasheet;
import com.workflow.pro.modules.apitable.domain.ApitableDatasheetMeta;
import com.workflow.pro.modules.apitable.domain.dto.DataSheetRecordDTO;
import com.workflow.pro.modules.apitable.domain.ro.MetaMapRo;
import com.workflow.pro.modules.apitable.domain.ro.RecordMapRo;

import com.workflow.pro.modules.apitable.domain.ro.SimpleDatasheetMetaDTO;
import com.workflow.pro.modules.apitable.domain.ro.ViewMapRo;
import com.workflow.pro.modules.apitable.domain.vo.DatasheetRecordMapVo;
import com.workflow.pro.modules.apitable.domain.vo.DatasheetRecordVo;
import com.workflow.pro.modules.apitable.exception.BusinessExceptionNew;
import com.workflow.pro.modules.apitable.mapper.ApitableDatasheetRecordMapper;
import com.workflow.pro.modules.apitable.domain.ApitableDatasheetRecord;
import com.workflow.pro.modules.apitable.param.ApitableDatasheetRecordRequest;
import com.workflow.pro.modules.apitable.service.IApitableDatasheetRecordService;
import com.workflow.pro.modules.apitable.utils.IdUtil;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.apitable.client.api.model.CreateRecordRequest;
import com.apitable.client.api.model.RecordMap;


/**
 * 数据单记录ServiceImpl业务层处理
 *
 * @author some
 * @date 2023-05-22
 */
@Service
public class ApitableDatasheetRecordServiceImpl extends ServiceImpl<ApitableDatasheetRecordMapper, ApitableDatasheetRecord> implements IApitableDatasheetRecordService {

    @Resource
    ApitableDatasheetRecordMapper apitableDatasheetRecordMapper;

    @Resource
    ApitableDatasheetServiceImpl datasheet;

    /**
     * 查询数据单记录
     *
     * @param id 数据单记录ID
     * @return 数据单记录
     */
    @Override
    public ApitableDatasheetRecord selectApitableDatasheetRecordById(String id) {
        return apitableDatasheetRecordMapper.selectApitableDatasheetRecordById(id);
    }

    /**
     * 查询数据单记录列表
     * @return 数据单记录
     */
    @Override
    public List<ApitableDatasheetRecord> list(ApitableDatasheetRecordRequest request) {
        return apitableDatasheetRecordMapper.selectApitableDatasheetRecordList(request);
    }

    /**
     * 查询数据单记录
     * @return 数据单记录 分页集合
     * */
    @Override
    public PageResponse<ApitableDatasheetRecord> page(ApitableDatasheetRecordRequest request) {
        return Pageable.of(request, (() -> apitableDatasheetRecordMapper.selectApitableDatasheetRecordList(request)));
    }

    /**
     * 新增数据单记录
     *
     * @param apitableDatasheetRecord 数据单记录
     * @return 结果
     */

    @Override
    public int insertApitableDatasheetRecord(ApitableDatasheetRecord apitableDatasheetRecord) {
        return apitableDatasheetRecordMapper.insertApitableDatasheetRecord(apitableDatasheetRecord);
    }

    /**
     * 修改数据单记录
     *
     * @param apitableDatasheetRecord 数据单记录
     * @return 结果
     */
    @Override
    public int updateApitableDatasheetRecord(ApitableDatasheetRecord apitableDatasheetRecord) {
        return apitableDatasheetRecordMapper.updateApitableDatasheetRecord(apitableDatasheetRecord);
    }

    /**
     * 删除数据单记录对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteApitableDatasheetRecordByIds(String[] ids) {
        return apitableDatasheetRecordMapper.deleteApitableDatasheetRecordByIds(ids);
    }

    /**
     * 删除数据单记录信息
     *
     * @param id 数据单记录ID
     * @return 结果
     */
    @Override
    public int deleteApitableDatasheetRecordById(String id) {
        return apitableDatasheetRecordMapper.deleteApitableDatasheetRecordById(id);
    }

    /**
     * gets the initialized recordmeta
     */
    private JSONObject getInitRecordMeta(String userId) {
        //  String uuid = userMapper.selectUuidById(userId);
        long createdAt = Instant.now(Clock.system(ZoneId.of("+8"))).toEpochMilli();
        JSONObject recordMeta = JSONUtil.createObj();
        recordMeta.set("createdAt", createdAt);
        recordMeta.set("createdBy", "userid");
        return recordMeta;
    }

    @Override
    public List<ApitableDatasheetRecord> saveBatchRecord(String userId, JSONObject recordMap, String dstId) {
        List<ApitableDatasheetRecord> recordList = new ArrayList<>();
        JSONObject recordMeta = this.getInitRecordMeta(userId);
        for (Map.Entry<String, Object> entry : recordMap.entrySet()) {
            RecordMapRo recordMapRo = BeanUtil.toBean(entry.getValue(), RecordMapRo.class);
            JSONObject fieldUpdatedMap = recordMapRo.getFieldUpdatedMap();
            if (fieldUpdatedMap != null && !fieldUpdatedMap.isEmpty()) {
                recordMeta.set("fieldUpdatedMap", fieldUpdatedMap);
            }
            String recordId = Optional.ofNullable(recordMapRo.getId()).orElse(IdUtil.createRecordId());
            ApitableDatasheetRecord recordId1 = getOne(new QueryWrapper<ApitableDatasheetRecord>().eq("record_id", recordId));
            String id = String.valueOf(IdWorker.getId());
            if (recordId1 != null && recordId1.getId() != "") {
                id = recordId1.getId();
            }
            ApitableDatasheetRecord recordEntity = ApitableDatasheetRecord.builder()
                    .id(id)
                    .recordId(recordId)
                    .data(StrUtil.isEmptyIfStr(recordMapRo.getData()) ? null : StrUtil.toString(recordMapRo.getData()))
                    .dstId(dstId)
                    .fieldUpdatedInfo(recordMeta.toString())
                    .build();

            recordList.add(recordEntity);
        }

        List<List<ApitableDatasheetRecord>> split = CollUtil.split(recordList, 1000);

        for (List<ApitableDatasheetRecord> entities : split) {
            saveOrUpdateBatch(entities);
        }

        return recordList;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DatasheetRecordMapVo delFieldData(String dstId, List<String> delFieldIds, boolean saveDb) {
        if (CollUtil.isNotEmpty(delFieldIds)) {
            List<DataSheetRecordDTO> voList = apitableDatasheetRecordMapper.selectDtoByDstId(dstId);
            List<ApitableDatasheetRecord> list = new ArrayList<>();
            Map<String, DatasheetRecordVo> map = MapUtil.newHashMap();
            voList.forEach(vo -> {
                JSONObject data = vo.getData();
                delFieldIds.forEach(data::remove);
                ApitableDatasheetRecord entity = ApitableDatasheetRecord.builder().id(vo.getId()).data(StrUtil.toString(data)).build();
                list.add(entity);
                map.put(vo.getRecordId(), DatasheetRecordVo.builder().id(vo.getRecordId()).data(data).build());
            });
            if (saveDb) {
                this.updateBatchById(list, list.size());
            }
            return DatasheetRecordMapVo.builder().recordMap(JSONUtil.parseObj(map)).build();
        }
        return null;
    }

    @Override
    public int addRecords(String dstId, CreateRecordRequest record) throws BusinessExceptionNew {

        return 0;
    }
}
