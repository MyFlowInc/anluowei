package com.workflow.pro.modules.apitable.service;

import java.util.List;


import com.baomidou.mybatisplus.extension.service.IService;
import com.workflow.pro.common.web.base.page.PageResponse;


import com.workflow.pro.modules.apitable.param.ToolAlipayConfigRequest;
import com.workflow.pro.modules.apitable.domain.ToolAlipayConfig;

/**
 * 支付宝配置类IService接口
 *
 * @author some
 * @date 2023-05-24
 */
public interface IToolAlipayConfigService extends IService<ToolAlipayConfig> {
    /**
     * 查询支付宝配置类
     *
     * @param configId 支付宝配置类ID
     * @return 支付宝配置类
     */
    ToolAlipayConfig selectToolAlipayConfigById(Long configId);


    /**
     * 查询支付宝配置类
     * @param  request pageSize pageNum
     * @return 支付宝配置类 分页集合
     * */
    PageResponse<ToolAlipayConfig> page(ToolAlipayConfigRequest request);

    /**
     * 查询支付宝配置类列表
     * @return 支付宝配置类集合
     */
    List<ToolAlipayConfig> list(ToolAlipayConfigRequest request);

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
     * 批量删除支付宝配置类
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteToolAlipayConfigByIds(String[] ids);

    /**
     * 删除支付宝配置类信息
     *
     * @param configId 支付宝配置类ID
     * @return 结果
     */
    int deleteToolAlipayConfigById(Long configId);

}
