package com.workflow.pro.modules.apitable.mapper;

import com.workflow.pro.modules.apitable.domain.dto.DataSheetRecordDTO;
import com.workflow.pro.modules.apitable.domain.vo.DatasheetRecordVo;
import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.workflow.pro.modules.apitable.param.ApitableDatasheetRecordRequest;
import com.workflow.pro.modules.apitable.domain.ApitableDatasheetRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;



/**
 * 数据单记录Mapper接口
 *
 * @author some
 * @date 2023-05-22
 */
@Mapper
public interface ApitableDatasheetRecordMapper extends BaseMapper<ApitableDatasheetRecord> {
    /**
     * 查询数据单记录
     *
     * @param id 数据单记录ID
     * @return 数据单记录
     */
    ApitableDatasheetRecord selectApitableDatasheetRecordById(String id);

    /**
     * 查询数据单记录列表
     *

     * @return 数据单记录集合
     */
    List<ApitableDatasheetRecord> list(@Param("request") ApitableDatasheetRecordRequest request);

    /**
     * 查询数据单记录分頁列表
     *

     * @return 数据单记录集合
     */
    List<ApitableDatasheetRecord> selectApitableDatasheetRecordList(@Param("request") ApitableDatasheetRecordRequest request);


    /**
     * 新增数据单记录
     *
     * @param apitableDatasheetRecord 数据单记录
     * @return 结果
     */
    int insertApitableDatasheetRecord(ApitableDatasheetRecord apitableDatasheetRecord);

    /**
     * 修改数据单记录
     *
     * @param apitableDatasheetRecord 数据单记录
     * @return 结果
     */
    int updateApitableDatasheetRecord(ApitableDatasheetRecord apitableDatasheetRecord);

    /**
     * 删除数据单记录
     *
     * @param id 数据单记录ID
     * @return 结果
     */
    int deleteApitableDatasheetRecordById(String id);

    /**
     * 批量删除数据单记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteApitableDatasheetRecordByIds(String[] ids);

    List<DataSheetRecordDTO> selectDtoByDstId(@Param("dstId") String dstId);




}
