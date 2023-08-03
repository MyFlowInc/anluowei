package com.workflow.pro.modules.generate.repository;

import com.workflow.pro.common.web.interceptor.annotation.TenantIgnore;
import com.workflow.pro.modules.generate.domain.GenTableColumn;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;


/**
 * 业务表字段接口
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/01
 * */
@Mapper
public interface GenTableColumnRepository {
    /**
     * 根据表名称查询列信息
     *
     * @param tableName 表名称
     * @return 列信息
     */
    @TenantIgnore
    List<GenTableColumn> selectDbTableColumnsByName(String tableName);

    /**
     * 查询业务字段列表
     *
     * @param genTableColumn 业务字段信息
     * @return 业务字段集合
     */
    @TenantIgnore
    List<GenTableColumn> selectGenTableColumnListByTableId(GenTableColumn genTableColumn);

    /**
     * 新增业务字段
     *
     * @param genTableColumn 业务字段信息
     * @return 结果
     */
    @TenantIgnore
    int insertGenTableColumn(GenTableColumn genTableColumn);

    /**
     * 修改业务字段
     *
     * @param genTableColumn 业务字段信息
     * @return 结果
     */
    @TenantIgnore
    int updateGenTableColumn(GenTableColumn genTableColumn);

    /**
     * 批量删除业务字段
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @TenantIgnore
    int deleteGenTableColumnByIds(String[] ids);
}