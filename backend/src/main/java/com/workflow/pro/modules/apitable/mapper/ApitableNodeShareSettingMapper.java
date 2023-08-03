package com.workflow.pro.modules.apitable.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.workflow.pro.modules.apitable.param.ApitableNodeShareSettingRequest;
import com.workflow.pro.modules.apitable.domain.ApitableNodeShareSetting;
import org.apache.ibatis.annotations.Param;

import java.util.List;



/**
 * 格基础设置Mapper接口
 *
 * @author some
 * @date 2023-06-14
 */
@Mapper
public interface ApitableNodeShareSettingMapper extends BaseMapper<ApitableNodeShareSetting> {
    /**
     * 查询格基础设置
     *
     * @param id 格基础设置ID
     * @return 格基础设置
     */
        ApitableNodeShareSetting selectApitableNodeShareSettingById(String id);

    /**
     * 查询格基础设置列表
     *

     * @return 格基础设置集合
     */
    List<ApitableNodeShareSetting> list(@Param("request") ApitableNodeShareSettingRequest request);

    /**
     * 查询格基础设置分頁列表
     *

     * @return 格基础设置集合
     */
    List<ApitableNodeShareSetting> selectApitableNodeShareSettingList(@Param("request") ApitableNodeShareSettingRequest request);


    /**
     * 新增格基础设置
     *
     * @param apitableNodeShareSetting 格基础设置
     * @return 结果
     */
    int insertApitableNodeShareSetting(ApitableNodeShareSetting apitableNodeShareSetting);

    /**
     * 修改格基础设置
     *
     * @param apitableNodeShareSetting 格基础设置
     * @return 结果
     */
    int updateApitableNodeShareSetting(ApitableNodeShareSetting apitableNodeShareSetting);

    /**
     * 删除格基础设置
     *
     * @param id 格基础设置ID
     * @return 结果
     */
    int deleteApitableNodeShareSettingById(String id);

    /**
     * 批量删除格基础设置
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteApitableNodeShareSettingByIds(String[] ids);

}
