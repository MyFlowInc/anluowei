package com.workflow.pro.modules.apitable.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.workflow.pro.modules.apitable.domain.ToolEmailConfig;
import com.workflow.pro.modules.apitable.param.ToolEmailConfigRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface EmailConfigMapper extends BaseMapper<ToolEmailConfig> {
    /**
     * 查询邮箱配置
     *
     * @param configId 邮箱配置ID
     * @return 邮箱配置
     */
    ToolEmailConfig selectToolEmailConfigById(Long configId);

    /**
     * 查询邮箱配置列表
     *
     * @return 邮箱配置集合
     */
    List<ToolEmailConfig> list(@Param("request") ToolEmailConfigRequest request);

    /**
     * 查询邮箱配置分頁列表
     *
     * @return 邮箱配置集合
     */
    List<ToolEmailConfig> selectToolEmailConfigList(@Param("request") ToolEmailConfigRequest request);


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
     * 删除邮箱配置
     *
     * @param configId 邮箱配置ID
     * @return 结果
     */
    int deleteToolEmailConfigById(Long configId);

    /**
     * 批量删除邮箱配置
     *
     * @param configIds 需要删除的数据ID
     * @return 结果
     */
    int deleteToolEmailConfigByIds(String[] configIds);

}
