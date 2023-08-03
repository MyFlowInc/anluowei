package com.workflow.pro.modules.apitable.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.workflow.pro.modules.apitable.param.OmsOrderReturnApplyRequest;
import com.workflow.pro.modules.apitable.domain.OmsOrderReturnApply;
import org.apache.ibatis.annotations.Param;

import java.util.List;



/**
 * 订单退货申请Mapper接口
 *
 * @author some
 * @date 2023-06-09
 */
@Mapper
public interface OmsOrderReturnApplyMapper extends BaseMapper<OmsOrderReturnApply> {
    /**
     * 查询订单退货申请
     *
     * @param id 订单退货申请ID
     * @return 订单退货申请
     */
        OmsOrderReturnApply selectOmsOrderReturnApplyById(String id);

    /**
     * 查询订单退货申请列表
     *

     * @return 订单退货申请集合
     */
    List<OmsOrderReturnApply> list(@Param("request") OmsOrderReturnApplyRequest request);

    /**
     * 查询订单退货申请分頁列表
     *

     * @return 订单退货申请集合
     */
    List<OmsOrderReturnApply> selectOmsOrderReturnApplyList(@Param("request") OmsOrderReturnApplyRequest request);


    /**
     * 新增订单退货申请
     *
     * @param omsOrderReturnApply 订单退货申请
     * @return 结果
     */
    int insertOmsOrderReturnApply(OmsOrderReturnApply omsOrderReturnApply);

    /**
     * 修改订单退货申请
     *
     * @param omsOrderReturnApply 订单退货申请
     * @return 结果
     */
    int updateOmsOrderReturnApply(OmsOrderReturnApply omsOrderReturnApply);

    /**
     * 删除订单退货申请
     *
     * @param id 订单退货申请ID
     * @return 结果
     */
    int deleteOmsOrderReturnApplyById(String id);

    /**
     * 批量删除订单退货申请
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteOmsOrderReturnApplyByIds(String[] ids);

}
