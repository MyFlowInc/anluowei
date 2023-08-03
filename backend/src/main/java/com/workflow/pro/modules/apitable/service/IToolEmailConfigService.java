package com.workflow.pro.modules.apitable.service;

import java.util.List;


import com.baomidou.mybatisplus.extension.service.IService;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;


import com.workflow.pro.modules.apitable.param.ToolEmailConfigRequest;
import com.workflow.pro.modules.apitable.domain.ToolEmailConfig;

/**
 * 邮箱配置IService接口
 *
 * @author some
 * @date 2023-05-24
 */
public interface IToolEmailConfigService  extends IService <ToolEmailConfig>{
    /**
     * 查询邮箱配置
     *
     * @param configId 邮箱配置ID
     * @return 邮箱配置
     */
        ToolEmailConfig selectToolEmailConfigById(Long configId);


    /**
     * 查询邮箱配置
     * @param  request pageSize pageNum
     * @return 邮箱配置 分页集合
     * */
    PageResponse<ToolEmailConfig> page(ToolEmailConfigRequest request);

    /**
     * 查询邮箱配置列表
     * @return 邮箱配置集合
     */
    List<ToolEmailConfig> list(ToolEmailConfigRequest request);

    /**
     * 新增邮箱配置
     *
     * @param toolEmailConfig 邮箱配置
     * @return 结果
     */
    int insertToolEmailConfig(ToolEmailConfig toolEmailConfig);

    /**
     * 修改邮箱配置
     *
     * @param toolEmailConfig 邮箱配置
     * @return 结果
     */
    int updateToolEmailConfig(ToolEmailConfig toolEmailConfig);

    /**
     * 批量删除邮箱配置
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteToolEmailConfigByIds(String[] ids);

    /**
     * 删除邮箱配置信息
     *
     * @param configId 邮箱配置ID
     * @return 结果
     */
    int deleteToolEmailConfigById(Long configId);

}
