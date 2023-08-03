package com.workflow.pro.modules.apitable.service;

import java.util.List;


import com.baomidou.mybatisplus.extension.service.IService;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;


import com.workflow.pro.modules.apitable.param.OmsOrderItemRequest;
import com.workflow.pro.modules.apitable.domain.OmsOrderItem;

/**
 * 订单中所包含的商品IService接口
 *
 * @author some
 * @date 2023-06-13
 */
public interface IOmsOrderItemService  extends IService <OmsOrderItem>{
    /**
     * 查询订单中所包含的商品
     *
     * @param id 订单中所包含的商品ID
     * @return 订单中所包含的商品
     */
        OmsOrderItem selectOmsOrderItemById(String id);


    /**
     * 查询订单中所包含的商品
     * @param  request pageSize pageNum
     * @return 订单中所包含的商品 分页集合
     * */
    PageResponse<OmsOrderItem> page(OmsOrderItemRequest request);

    /**
     * 查询订单中所包含的商品列表
     * @return 订单中所包含的商品集合
     */
    List<OmsOrderItem> list(OmsOrderItemRequest request);

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
     * 批量删除订单中所包含的商品
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteOmsOrderItemByIds(String[] ids);

    /**
     * 删除订单中所包含的商品信息
     *
     * @param id 订单中所包含的商品ID
     * @return 结果
     */
    int deleteOmsOrderItemById(String id);

}
