package com.workflow.pro.modules.apitable.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;


import com.workflow.pro.modules.apitable.mapper.ToolAlipayConfigMapper;
import com.workflow.pro.modules.apitable.domain.ToolAlipayConfig;
import com.workflow.pro.modules.apitable.param.ToolAlipayConfigRequest;
import com.workflow.pro.modules.apitable.service.IToolAlipayConfigService;


import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;


/**
 * 支付宝配置类ServiceImpl业务层处理
 *
 * @author some
 * @date 2023-05-24
 */
@Service
public class ToolAlipayConfigServiceImpl extends ServiceImpl<ToolAlipayConfigMapper, ToolAlipayConfig> implements IToolAlipayConfigService {

    @Resource
    ToolAlipayConfigMapper toolAlipayConfigMapper;

    /**
     * 查询支付宝配置类
     *
     * @param configId 支付宝配置类ID
     * @return 支付宝配置类
     */
    @Override
    public ToolAlipayConfig selectToolAlipayConfigById(Long configId) {
        return toolAlipayConfigMapper.selectToolAlipayConfigById(configId);
    }

    /**
     * 查询支付宝配置类列表
     * @return 支付宝配置类
     */
    @Override
    public List<ToolAlipayConfig> list (ToolAlipayConfigRequest request){
        return toolAlipayConfigMapper.list(request);
    }

    /**
     * 查询支付宝配置类
     * @return 支付宝配置类 分页集合
     * */
    @Override
    public PageResponse<ToolAlipayConfig> page(ToolAlipayConfigRequest request) {
        return Pageable.of(request, (() -> toolAlipayConfigMapper.selectToolAlipayConfigList(request)));
    }

    /**
     * 新增支付宝配置类
     *
     * @param toolAlipayConfig 支付宝配置类
     * @return 结果
     */

    @Override
    public int insertToolAlipayConfig(ToolAlipayConfig toolAlipayConfig) {
        return toolAlipayConfigMapper.insertToolAlipayConfig(toolAlipayConfig);
    }

    /**
     * 修改支付宝配置类
     *
     * @param toolAlipayConfig 支付宝配置类
     * @return 结果
     */
    @Override
    public int updateToolAlipayConfig(ToolAlipayConfig toolAlipayConfig) {
        return toolAlipayConfigMapper.updateToolAlipayConfig(toolAlipayConfig);
    }

    /**
     * 删除支付宝配置类对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteToolAlipayConfigByIds(String[] ids) {
        return toolAlipayConfigMapper.deleteToolAlipayConfigByIds(ids);
    }

    /**
     * 删除支付宝配置类信息
     *
     * @param configId 支付宝配置类ID
     * @return 结果
     */
    @Override
    public int deleteToolAlipayConfigById(Long configId) {
        return toolAlipayConfigMapper.deleteToolAlipayConfigById(configId);
    }
}
