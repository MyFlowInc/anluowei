package com.workflow.pro.modules.apitable.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.workflow.pro.modules.apitable.param.ApitableDatasheetChangesetRequest;
import com.workflow.pro.modules.apitable.domain.ApitableDatasheetChangeset;
import org.apache.ibatis.annotations.Param;

import java.util.List;



/**
 * 变更集收集Mapper接口
 *
 * @author some
 * @date 2023-05-22
 */
@Mapper
public interface ApitableDatasheetChangesetMapper extends BaseMapper<ApitableDatasheetChangeset> {
    /**
     * 查询变更集收集
     *
     * @param id 变更集收集ID
     * @return 变更集收集
     */
        ApitableDatasheetChangeset selectApitableDatasheetChangesetById(String id);

    /**
     * 查询变更集收集列表
     *

     * @return 变更集收集集合
     */
    List<ApitableDatasheetChangeset> list(@Param("request") ApitableDatasheetChangesetRequest request);

    /**
     * 查询变更集收集分頁列表
     *

     * @return 变更集收集集合
     */
    List<ApitableDatasheetChangeset> selectApitableDatasheetChangesetList(@Param("request") ApitableDatasheetChangesetRequest request);


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
     * 删除变更集收集
     *
     * @param id 变更集收集ID
     * @return 结果
     */
    int deleteApitableDatasheetChangesetById(String id);

    /**
     * 批量删除变更集收集
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteApitableDatasheetChangesetByIds(String[] ids);

}
