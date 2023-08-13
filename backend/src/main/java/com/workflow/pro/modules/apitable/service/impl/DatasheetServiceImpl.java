/*
 * APITable <https://github.com/apitable/apitable>
 * Copyright (C) 2022 APITable Ltd. <https://apitable.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.workflow.pro.modules.apitable.service.impl;

import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.workflow.pro.modules.apitable.domain.ApitableDatasheet;
import com.workflow.pro.modules.apitable.domain.ApitableDatasheetMeta;
import com.workflow.pro.modules.apitable.domain.ApitableNodeShareSetting;
import com.workflow.pro.modules.apitable.domain.ro.MetaMapRo;
import com.workflow.pro.modules.apitable.domain.ro.SimpleDatasheetMetaDTO;
import com.workflow.pro.modules.apitable.domain.ro.SnapshotMapRo;
import com.workflow.pro.modules.apitable.domain.ro.ViewMapRo;
import com.workflow.pro.modules.apitable.service.IApitableDatasheetMetaService;
import com.workflow.pro.modules.apitable.service.IApitableDatasheetRecordService;
import com.workflow.pro.modules.apitable.service.IApitableDatasheetService;
import com.workflow.pro.modules.apitable.service.IApitableDeveloperService;
import com.workflow.pro.modules.apitable.service.IApitableNodeShareSettingService;
import com.workflow.pro.modules.apitable.service.IDatasheetServiceImpl;
import com.workflow.pro.modules.apitable.sysconfig.autoconfig.BeetlTemplate;
import com.workflow.pro.modules.apitable.utils.IdUtil;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class DatasheetServiceImpl implements IDatasheetServiceImpl {

    @Resource
    private BeetlTemplate beetlTemplate;

    @Resource
    private IApitableDatasheetService datasheetService;

    @Resource
    private IApitableDeveloperService developerService;

    @Resource
    private IApitableDatasheetRecordService datasheetRecordService;

    @Resource
    private IApitableNodeShareSettingService nodeShareSettingService;

    // FieldCreateRo
    @Resource
    private IApitableDatasheetMetaService datasheetMetaService;

    @Transactional(rollbackFor = Exception.class)
    public void create(
            final String creator,
            final String spaceId,
            final String dstId,
            final String dstName,
            final String viewName
    ) {
        log.info("Create datasheet");
        ApitableDatasheet datasheet = ApitableDatasheet.builder()
                .spaceId(spaceId)
                .nodeId(dstId)
                .dstId(dstId)
                .dstName(dstName)
                .build();
        datasheet.setCreateBy(creator);
        datasheetService.save(datasheet);
        // ExceptionUtil.isTrue(flag, DataSheetException.CREATE_FAIL);
        // Initialize datasheet information
        SnapshotMapRo snapshot = initialize(viewName);
        // Save Meta information
        String meta = JSONUtil.parseObj(snapshot.getMeta()).toString();

        ApitableDatasheetMeta datasheetMeta = new ApitableDatasheetMeta();
        datasheetMeta.setMetaData(meta);
        datasheetMeta.setCreateBy(String.valueOf(creator));
        datasheetMeta.setDstId(dstId);

        ApitableNodeShareSetting shareSetting = new ApitableNodeShareSetting();
        shareSetting.setCreateBy(creator);
        shareSetting.setDstId(dstId);
        shareSetting.setIsEnabled(1);
        shareSetting.setShareId(IdUtil.createShareId());
        //插入详细基础设置表
        nodeShareSettingService.save(shareSetting);
        datasheetMetaService.save(datasheetMeta);
        // Save record information

        datasheetRecordService.saveBatchRecord(creator, snapshot.getRecordMap(), dstId);
    }


    private SnapshotMapRo initialize(final String viewName) {
        // get language
        Locale currentLang = LocaleContextHolder.getLocale();
        // call the template to get the snapshot
        Map<String, Object> metaMap = MapUtil.newHashMap();

        int initLength = 4;
        for (int i = 0; i < initLength; i++) {
            String recordId = IdUtil.createRecordId();
            metaMap.put("recordId" + i, recordId);
            String optionId = IdUtil.createOptionId();
            metaMap.put("optionId" + i, optionId);
        }

        for(int i =0 ; i < 9; i++) {
            String fieldId = IdUtil.createFieldId();
            // template binding dynamic parameters
            metaMap.put("fieldId" + i, fieldId);
        }

        String viewId = IdUtil.createViewId();
        metaMap.put("viewId", viewId);
        String defaultViewName = StrUtil.isNotBlank(viewName) ? viewName : "视图";
        metaMap.put("defaultView", defaultViewName);
        metaMap.put("HRResumeTemplateName", "姓名");
        metaMap.put("HRResumeTemplateOptions", "状态");
        metaMap.put("HRResumeTemplateGender", "性别");
        metaMap.put("HRResumeTemplateExperience", "从业时间");
        metaMap.put("HRResumeTemplateEducation", "最高学历");
        metaMap.put("HRResumeTemplatePhone", "电话");
        metaMap.put("HRResumeTemplateComment", "评论");
        metaMap.put("HRResumeTemplateResume", "简历");

        // internationalization elements
        String snapshotJson = beetlTemplate.render("datasheet/datasheet-meta-hr-resume.btl", metaMap);
        return new JSONObject(snapshotJson).toBean(SnapshotMapRo.class);
    }
}