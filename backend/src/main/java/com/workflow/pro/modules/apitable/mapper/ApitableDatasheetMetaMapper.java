package com.workflow.pro.modules.apitable.mapper;

import com.workflow.pro.modules.apitable.domain.dto.DatasheetSnapshot;
import com.workflow.pro.modules.apitable.domain.ro.SimpleDatasheetMetaDTO;
import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.workflow.pro.modules.apitable.param.ApitableDatasheetMetaRequest;
import com.workflow.pro.modules.apitable.domain.ApitableDatasheetMeta;
import org.apache.ibatis.annotations.Param;

import java.util.List;



/**
 * 数据元数据Mapper接口
 *
 * @author some
 * @date 2023-05-22
 */
@Mapper
public interface ApitableDatasheetMetaMapper extends BaseMapper<ApitableDatasheetMeta> {
    /**
     * 查询数据元数据
     *
     * @param id 数据元数据ID
     * @return 数据元数据
     */
        ApitableDatasheetMeta selectApitableDatasheetMetaById(String id);

    /**
     * 查询数据元数据列表
     *

     * @return 数据元数据集合
     */
    List<ApitableDatasheetMeta> list(@Param("request") ApitableDatasheetMetaRequest request);

    /**
     * 查询数据元数据分頁列表
     *

     * @return 数据元数据集合
     */
    List<ApitableDatasheetMeta> selectApitableDatasheetMetaList(@Param("request") ApitableDatasheetMetaRequest request);


    /**
     * 新增数据元数据
     *
     * @param apitableDatasheetMeta 数据元数据
     * @return 结果
     */
    int insertApitableDatasheetMeta(ApitableDatasheetMeta apitableDatasheetMeta);

    /**
     * 修改数据元数据
     *
     * @param apitableDatasheetMeta 数据元数据
     * @return 结果
     */
    int updateApitableDatasheetMeta(ApitableDatasheetMeta apitableDatasheetMeta);

    /**
     * 删除数据元数据
     *
     * @param id 数据元数据ID
     * @return 结果
     */
    int deleteApitableDatasheetMetaById(String id);

    /**
     * 批量删除数据元数据
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteApitableDatasheetMetaByIds(String[] ids);

    /**
     * @param dstId datasheet id
     * @return DatasheetMetaVo
     */
    SimpleDatasheetMetaDTO selectByNodeId(@Param("dstId") String dstId);

    DatasheetSnapshot selectByDstId(@Param("dstId") String dstId);

    int updateByDstId(@Param("userId") Long userId, @Param("meta") String meta, @Param("dstId") String dstId);

}
