package com.workflow.pro.modules.apitable.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.workflow.pro.modules.apitable.param.OmsOrderRequest;
import com.workflow.pro.modules.apitable.domain.OmsOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;



/**
 * 订单Mapper接口
 *
 * @author some
 * @date 2023-06-09
 */
@Mapper
public interface OmsOrderMapper extends BaseMapper<OmsOrder> {
    /**
     * 查询订单
     *
     * @param id 订单ID
     * @return 订单
     */
        OmsOrder selectOmsOrderById(String id);

    /**
     * 查询订单列表
     *

     * @return 订单集合
     */
    List<OmsOrder> list(@Param("request") OmsOrderRequest request);

    /**
     * 查询订单分頁列表
     *

     * @return 订单集合
     */
    List<OmsOrder> selectOmsOrderList(@Param("request") OmsOrderRequest request);


    /**
     * 新增订单
     *
     * @param omsOrder 订单
     * @return 结果
     */
    int insertOmsOrder(OmsOrder omsOrder);

    /**
     * 修改订单
     *
     * @param omsOrder 订单
     * @return 结果
     */
    int updateOmsOrder(OmsOrder omsOrder);

    /**
     * 删除订单
     *
     * @param id 订单ID
     * @return 结果
     */
    int deleteOmsOrderById(String id);

    /**
     * 批量删除订单
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteOmsOrderByIds(String[] ids);

}
