package com.workflow.pro.modules.apitable.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;


import com.workflow.pro.modules.apitable.mapper.ToolEmailConfigMapper;
import com.workflow.pro.modules.apitable.domain.ToolEmailConfig;
import com.workflow.pro.modules.apitable.param.ToolEmailConfigRequest;
import com.workflow.pro.modules.apitable.service.IToolEmailConfigService;


import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;


/**
 * 邮箱配置ServiceImpl业务层处理
 *
 * @author some
 * @date 2023-05-24
 */
@Service
public class ToolEmailConfigServiceImpl extends ServiceImpl<ToolEmailConfigMapper, ToolEmailConfig> implements IToolEmailConfigService {

    @Resource
    ToolEmailConfigMapper toolEmailConfigMapper;

    /**
     * 查询邮箱配置
     *
     * @param configId 邮箱配置ID
     * @return 邮箱配置
     */
    @Override
    public ToolEmailConfig selectToolEmailConfigById(Long configId) {
        return toolEmailConfigMapper.selectToolEmailConfigById(configId);
    }

    /**
     * 查询邮箱配置列表
     * @return 邮箱配置
     */
    @Override
    public List<ToolEmailConfig> list (ToolEmailConfigRequest request){
        return toolEmailConfigMapper.list(request);
    }

    /**
     * 查询邮箱配置
     * @return 邮箱配置 分页集合
     * */
    @Override
    public PageResponse<ToolEmailConfig> page(ToolEmailConfigRequest request) {
        return Pageable.of(request, (() -> toolEmailConfigMapper.selectToolEmailConfigList(request)));
    }

    /**
     * 新增邮箱配置
     *
     * @param toolEmailConfig 邮箱配置
     * @return 结果
     */

    @Override
    public int insertToolEmailConfig(ToolEmailConfig toolEmailConfig) {
        return toolEmailConfigMapper.insertToolEmailConfig(toolEmailConfig);
    }

    /**
     * 修改邮箱配置
     *
     * @param toolEmailConfig 邮箱配置
     * @return 结果
     */
    @Override
    public int updateToolEmailConfig(ToolEmailConfig toolEmailConfig) {
        return toolEmailConfigMapper.updateToolEmailConfig(toolEmailConfig);
    }

    /**
     * 删除邮箱配置对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteToolEmailConfigByIds(String[] ids) {
        return toolEmailConfigMapper.deleteToolEmailConfigByIds(ids);
    }

    /**
     * 删除邮箱配置信息
     *
     * @param configId 邮箱配置ID
     * @return 结果
     */
    @Override
    public int deleteToolEmailConfigById(Long configId) {
        return toolEmailConfigMapper.deleteToolEmailConfigById(configId);
    }
}
