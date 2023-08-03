/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.workflow.pro.modules.apitable.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.workflow.pro.modules.apitable.domain.ToolAlipayConfig;
import com.workflow.pro.modules.apitable.param.ToolAlipayConfigRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * @author Some
 * @date 2018-12-31
 */
@Mapper
public interface AliPayRepositoryMapper extends BaseMapper<ToolAlipayConfig> {
    /**
     * 查询支付宝配置类
     *
     * @param configId 支付宝配置类ID
     * @return 支付宝配置类
     */
    ToolAlipayConfig selectToolAlipayConfigById(Long configId);

    /**
     * 查询支付宝配置类列表
     *
     * @return 支付宝配置类集合
     */
    List<ToolAlipayConfig> list(@Param("request") ToolAlipayConfigRequest request);

    /**
     * 查询支付宝配置类分頁列表
     *
     * @return 支付宝配置类集合
     */
    List<ToolAlipayConfig> selectToolAlipayConfigList(@Param("request") ToolAlipayConfigRequest request);


    /**
     * 新增支付宝配置类
     *
     * @param toolAlipayConfig 支付宝配置类
     * @return 结果
     */
    int insertToolAlipayConfig(ToolAlipayConfig toolAlipayConfig);

    /**
     * 修改支付宝配置类
     *
     * @param toolAlipayConfig 支付宝配置类
     * @return 结果
     */
    int updateToolAlipayConfig(ToolAlipayConfig toolAlipayConfig);

    /**
     * 删除支付宝配置类
     *
     * @param configId 支付宝配置类ID
     * @return 结果
     */
    int deleteToolAlipayConfigById(Long configId);

    /**
     * 批量删除支付宝配置类
     *
     * @param configIds 需要删除的数据ID
     * @return 结果
     */
    int deleteToolAlipayConfigByIds(String[] configIds);

}
