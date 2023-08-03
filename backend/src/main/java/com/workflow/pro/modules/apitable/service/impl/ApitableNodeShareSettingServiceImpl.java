package com.workflow.pro.modules.apitable.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;


import com.workflow.pro.modules.apitable.domain.ApitableDatasheetMeta;
import com.workflow.pro.modules.apitable.mapper.ApitableNodeShareSettingMapper;
import com.workflow.pro.modules.apitable.domain.ApitableNodeShareSetting;
import com.workflow.pro.modules.apitable.param.ApitableNodeShareSettingRequest;
import com.workflow.pro.modules.apitable.service.IApitableNodeShareSettingService;
import com.workflow.pro.modules.apitable.utils.IdUtil;
import com.workflow.pro.modules.apitable.utils.RandomExtendUtil;


import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;


/**
 * 格基础设置ServiceImpl业务层处理
 *
 * @author some
 * @date 2023-06-14
 */
@Service
public class ApitableNodeShareSettingServiceImpl extends ServiceImpl<ApitableNodeShareSettingMapper, ApitableNodeShareSetting> implements IApitableNodeShareSettingService {

    @Resource
    ApitableNodeShareSettingMapper apitableNodeShareSettingMapper;

    /**
     * 查询格基础设置
     *
     * @param id 格基础设置ID
     * @return 格基础设置
     */
    @Override
    public ApitableNodeShareSetting selectApitableNodeShareSettingById(String id) {
        return apitableNodeShareSettingMapper.selectApitableNodeShareSettingById(id);
    }

    /**
     * 查询格基础设置列表
     * @return 格基础设置
     */
    @Override
    public List<ApitableNodeShareSetting> list(ApitableNodeShareSettingRequest request) {
        return apitableNodeShareSettingMapper.list(request);
    }

    /**
     * 查询格基础设置
     * @return 格基础设置 分页集合
     * */
    @Override
    public PageResponse<ApitableNodeShareSetting> page(ApitableNodeShareSettingRequest request) {
        return Pageable.of(request, (() -> apitableNodeShareSettingMapper.selectApitableNodeShareSettingList(request)));
    }

    /**
     * 新增格基础设置
     *
     * @param apitableNodeShareSetting 格基础设置
     * @return 结果
     */

    @Override
    public int insertApitableNodeShareSetting(ApitableNodeShareSetting apitableNodeShareSetting) {
        return apitableNodeShareSettingMapper.insertApitableNodeShareSetting(apitableNodeShareSetting);
    }

    /**
     * 修改格基础设置
     *
     * @param apitableNodeShareSetting 格基础设置
     * @return 结果
     */
    @Override
    public int updateApitableNodeShareSetting(ApitableNodeShareSetting apitableNodeShareSetting) {
        return apitableNodeShareSettingMapper.updateApitableNodeShareSetting(apitableNodeShareSetting);
    }

    /**
     * 删除格基础设置对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteApitableNodeShareSettingByIds(String[] ids) {
        return apitableNodeShareSettingMapper.deleteApitableNodeShareSettingByIds(ids);
    }

    /**
     * 删除格基础设置信息
     *
     * @param id 格基础设置ID
     * @return 结果
     */
    @Override
    public int deleteApitableNodeShareSettingById(String id) {
        return apitableNodeShareSettingMapper.deleteApitableNodeShareSettingById(id);
    }

    @Override
    public String makeShareCode(ApitableNodeShareSetting request) {
        // 空间id 和userid 默认取


        String shareId = IdUtil.createShareId();
        request.setShareId(shareId);
        save(request);


        RandomExtendUtil.encode("");
        RandomExtendUtil.decode("");
        return shareId;
    }

    @Override
    public String VerityCode(String shareId) {



        return null;
    }
}
