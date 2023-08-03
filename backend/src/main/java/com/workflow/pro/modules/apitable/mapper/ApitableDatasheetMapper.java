package com.workflow.pro.modules.apitable.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.workflow.pro.modules.apitable.param.ApitableDatasheetRequest;
import com.workflow.pro.modules.apitable.domain.ApitableDatasheet;
import org.apache.ibatis.annotations.Param;

import java.util.List;



/**
 * 数据格Mapper接口
 *
 * @author some
 * @date 2023-05-22
 */
@Mapper
public interface ApitableDatasheetMapper extends BaseMapper<ApitableDatasheet> {
    /**
     * 查询数据格
     *
     * @param id 数据格ID
     * @return 数据格
     */
        ApitableDatasheet selectApitableDatasheetById(String id);

    /**
     * 查询数据格列表
     *

     * @return 数据格集合
     */
    List<ApitableDatasheet> list(@Param("request") ApitableDatasheetRequest request);

    /**
     * 查询数据格分頁列表
     *

     * @return 数据格集合
     */
    List<ApitableDatasheet> selectApitableDatasheetList(@Param("request") ApitableDatasheetRequest request);


    /**
     * 新增数据格
     *
     * @param apitableDatasheet 数据格
     * @return 结果
     */
    int insertApitableDatasheet(ApitableDatasheet apitableDatasheet);

    /**
     * 修改数据格
     *
     * @param apitableDatasheet 数据格
     * @return 结果
     */
    int updateApitableDatasheet(ApitableDatasheet apitableDatasheet);

    /**
     * 删除数据格
     *
     * @param id 数据格ID
     * @return 结果
     */
    int deleteApitableDatasheetById(String id);

    /**
     * 批量删除数据格
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteApitableDatasheetByIds(String[] ids);

}
