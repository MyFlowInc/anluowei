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

package com.workflow.pro.modules.apitable.service;

import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import cn.hutool.core.map.MapUtil;

import cn.hutool.core.util.StrUtil;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import com.workflow.pro.modules.apitable.domain.ApitableDatasheet;
import com.workflow.pro.modules.apitable.domain.ApitableDatasheetMeta;
import com.workflow.pro.modules.apitable.domain.ro.MetaMapRo;
import com.workflow.pro.modules.apitable.domain.ro.SnapshotMapRo;
import com.workflow.pro.modules.apitable.sysconfig.autoconfig.BeetlTemplate;
import com.workflow.pro.modules.apitable.sysconfig.i18n.I18nStringsUtil;
import com.workflow.pro.modules.apitable.utils.IdUtil;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface IDatasheetServiceImpl {
     void create(
            final String creator,
            final String spaceId,
            final String dstId,
            final String dstName,
            final String viewName
    );

}