package com.workflow.pro.modules.apitable.service;

import java.util.List;


import com.baomidou.mybatisplus.extension.service.IService;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;


import com.workflow.pro.modules.apitable.param.ApitableNodeShareSettingRequest;
import com.workflow.pro.modules.apitable.domain.ApitableNodeShareSetting;

/**
 * 格基础设置IService接口
 *
 * @author some
 * @date 2023-06-14
 */
public interface IApitableNodeShareSettingService extends IService<ApitableNodeShareSetting> {
    /**
     * 查询格基础设置
     *
     * @param id 格基础设置ID
     * @return 格基础设置
     */
    ApitableNodeShareSetting selectApitableNodeShareSettingById(String id);


    /**
     * 查询格基础设置
     * @param  request pageSize pageNum
     * @return 格基础设置 分页集合
     * */
    PageResponse<ApitableNodeShareSetting> page(ApitableNodeShareSettingRequest request);

    /**
     * 查询格基础设置列表
     * @return 格基础设置集合
     */
    List<ApitableNodeShareSetting> list(ApitableNodeShareSettingRequest request);

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
     * 批量删除格基础设置
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteApitableNodeShareSettingByIds(String[] ids);

    /**
     * 删除格基础设置信息
     *
     * @param id 格基础设置ID
     * @return 结果
     */
    int deleteApitableNodeShareSettingById(String id);

    String makeShareCode(ApitableNodeShareSetting request);

    String VerityCode(String code);

}
