package com.workflow.pro.modules.apitable.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;


import com.workflow.pro.modules.apitable.mapper.ApitableDatasheetChangesetMapper;
import com.workflow.pro.modules.apitable.domain.ApitableDatasheetChangeset;
import com.workflow.pro.modules.apitable.param.ApitableDatasheetChangesetRequest;
import com.workflow.pro.modules.apitable.service.IApitableDatasheetChangesetService;


import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;


/**
 * 变更集收集ServiceImpl业务层处理
 *
 * @author some
 * @date 2023-05-22
 */
@Service
public class ApitableDatasheetChangesetServiceImpl extends ServiceImpl<ApitableDatasheetChangesetMapper, ApitableDatasheetChangeset> implements IApitableDatasheetChangesetService {

    @Resource
    ApitableDatasheetChangesetMapper apitableDatasheetChangesetMapper;

    /**
     * 查询变更集收集
     *
     * @param id 变更集收集ID
     * @return 变更集收集
     */
    @Override
    public ApitableDatasheetChangeset selectApitableDatasheetChangesetById(String id) {
        return apitableDatasheetChangesetMapper.selectApitableDatasheetChangesetById(id);
    }

    /**
     * 查询变更集收集列表
     * @return 变更集收集
     */
    @Override
    public List<ApitableDatasheetChangeset> list (ApitableDatasheetChangesetRequest request){
        return apitableDatasheetChangesetMapper.selectApitableDatasheetChangesetList(request);
    }

    /**
     * 查询变更集收集
     * @return 变更集收集 分页集合
     * */
    @Override
    public PageResponse<ApitableDatasheetChangeset> page(ApitableDatasheetChangesetRequest request) {
        return Pageable.of(request, (() -> apitableDatasheetChangesetMapper.selectApitableDatasheetChangesetList(request)));
    }

    /**
     * 新增变更集收集
     *
     * @param apitableDatasheetChangeset 变更集收集
     * @return 结果
     */

    @Override
    public int insertApitableDatasheetChangeset(ApitableDatasheetChangeset apitableDatasheetChangeset) {
        return apitableDatasheetChangesetMapper.insertApitableDatasheetChangeset(apitableDatasheetChangeset);
    }

    /**
     * 修改变更集收集
     *
     * @param apitableDatasheetChangeset 变更集收集
     * @return 结果
     */
    @Override
    public int updateApitableDatasheetChangeset(ApitableDatasheetChangeset apitableDatasheetChangeset) {
        return apitableDatasheetChangesetMapper.updateApitableDatasheetChangeset(apitableDatasheetChangeset);
    }

    /**
     * 删除变更集收集对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteApitableDatasheetChangesetByIds(String[] ids) {
        return apitableDatasheetChangesetMapper.deleteApitableDatasheetChangesetByIds(ids);
    }

    /**
     * 删除变更集收集信息
     *
     * @param id 变更集收集ID
     * @return 结果
     */
    @Override
    public int deleteApitableDatasheetChangesetById(String id) {
        return apitableDatasheetChangesetMapper.deleteApitableDatasheetChangesetById(id);
    }
}
