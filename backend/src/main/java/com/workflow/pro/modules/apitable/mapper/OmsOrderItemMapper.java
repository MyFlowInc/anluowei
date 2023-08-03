package com.workflow.pro.modules.apitable.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.workflow.pro.modules.apitable.param.OmsOrderItemRequest;
import com.workflow.pro.modules.apitable.domain.OmsOrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;



/**
 * 订单中所包含的商品Mapper接口
 *
 * @author some
 * @date 2023-06-13
 */
@Mapper
public interface OmsOrderItemMapper extends BaseMapper<OmsOrderItem> {
    /**
     * 查询订单中所包含的商品
     *
     * @param id 订单中所包含的商品ID
     * @return 订单中所包含的商品
     */
        OmsOrderItem selectOmsOrderItemById(String id);

    /**
     * 查询订单中所包含的商品列表
     *

     * @return 订单中所包含的商品集合
     */
    List<OmsOrderItem> list(@Param("request") OmsOrderItemRequest request);

    /**
     * 查询订单中所包含的商品分頁列表
     *

     * @return 订单中所包含的商品集合
     */
    List<OmsOrderItem> selectOmsOrderItemList(@Param("request") OmsOrderItemRequest request);


    /**
     * 新增订单中所包含的商品
     *
     * @param omsOrderItem 订单中所包含的商品
     * @return 结果
     */
    int insertOmsOrderItem(OmsOrderItem omsOrderItem);

    /**
     * 修改订单中所包含的商品
     *
     * @param omsOrderItem 订单中所包含的商品
     * @return 结果
     */
    int updateOmsOrderItem(OmsOrderItem omsOrderItem);

    /**
     * 删除订单中所包含的商品
     *
     * @param id 订单中所包含的商品ID
     * @return 结果
     */
    int deleteOmsOrderItemById(String id);

    /**
     * 批量删除订单中所包含的商品
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteOmsOrderItemByIds(String[] ids);

}
