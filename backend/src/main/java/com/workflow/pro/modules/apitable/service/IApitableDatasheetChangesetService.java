package com.workflow.pro.modules.apitable.service;

import java.util.List;


import com.baomidou.mybatisplus.extension.service.IService;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;


import com.workflow.pro.modules.apitable.param.ApitableDatasheetChangesetRequest;
import com.workflow.pro.modules.apitable.domain.ApitableDatasheetChangeset;

/**
 * 变更集收集IService接口
 *
 * @author some
 * @date 2023-05-22
 */
public interface IApitableDatasheetChangesetService  extends IService <ApitableDatasheetChangeset>{
    /**
     * 查询变更集收集
     *
     * @param id 变更集收集ID
     * @return 变更集收集
     */
        ApitableDatasheetChangeset selectApitableDatasheetChangesetById(String id);


    /**
     * 查询变更集收集
     * @param  request pageSize pageNum
     * @return 变更集收集 分页集合
     * */
    PageResponse<ApitableDatasheetChangeset> page(ApitableDatasheetChangesetRequest request);

    /**
     * 查询变更集收集列表
     * @return 变更集收集集合
     */
    List<ApitableDatasheetChangeset> list(ApitableDatasheetChangesetRequest request);

    /**
     * 新增变更集收集
     *
     * @param apitableDatasheetChangeset 变更集收集
     * @return 结果
     */
    int insertApitableDatasheetChangeset(ApitableDatasheetChangeset apitableDatasheetChangeset);

    /**
     * 修改变更集收集
     *
     * @param apitableDatasheetChangeset 变更集收集
     * @return 结果
     */
    int updateApitableDatasheetChangeset(ApitableDatasheetChangeset apitableDatasheetChangeset);

    /**
     * 批量删除变更集收集
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteApitableDatasheetChangesetByIds(String[] ids);

    /**
     * 删除变更集收集信息
     *
     * @param id 变更集收集ID
     * @return 结果
     */
    int deleteApitableDatasheetChangesetById(String id);

}
