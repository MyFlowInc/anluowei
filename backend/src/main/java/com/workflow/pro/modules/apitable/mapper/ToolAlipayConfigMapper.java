package com.workflow.pro.modules.apitable.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.workflow.pro.modules.apitable.param.ToolAlipayConfigRequest;
import com.workflow.pro.modules.apitable.domain.ToolAlipayConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;



/**
 * 支付宝配置类Mapper接口
 *
 * @author some
 * @date 2023-05-24
 */
@Mapper
public interface ToolAlipayConfigMapper extends BaseMapper<ToolAlipayConfig> {
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
