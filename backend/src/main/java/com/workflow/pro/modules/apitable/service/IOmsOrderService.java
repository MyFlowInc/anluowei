package com.workflow.pro.modules.apitable.service;

import java.util.List;


import com.baomidou.mybatisplus.extension.service.IService;
import com.workflow.pro.common.web.base.page.PageResponse;


import com.workflow.pro.modules.apitable.domain.ToolAlipayConfig;
import com.workflow.pro.modules.apitable.param.OmsOrderRequest;
import com.workflow.pro.modules.apitable.domain.OmsOrder;
import com.workflow.pro.modules.apitable.param.OrderPayParam;
import com.workflow.pro.modules.apitable.param.OrderVo;

import org.springframework.transaction.annotation.Transactional;

/**
 * 订单IService接口
 *
 * @author some
 * @date 2023-06-09
 */
public interface IOmsOrderService  extends IService <OmsOrder>{
    /**
     * 查询订单
     *
     * @param id 订单ID
     * @return 订单
     */
        OmsOrder selectOmsOrderById(String id);


    /**
     * 查询订单
     * @param  request pageSize pageNum
     * @return 订单 分页集合
     * */
    PageResponse<OmsOrder> page(OmsOrderRequest request);

    /**
     * 查询订单列表
     * @return 订单集合
     */
    List<OmsOrder> list(OmsOrderRequest request);

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
     * 修改订单
     *
     * @param omsOrder 订单
     * @return 结果
     */
    /**
     * 批量删除订单
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteOmsOrderByIds(String[] ids);

    /**
     * 删除订单信息
     *
     * @param id 订单ID
     * @return 结果
     */
    int deleteOmsOrderById(String id);




    /**
     * 处理来自PC的交易请求
     * @return String
     * @throws Exception 异常
     */
    String toPayAsPc(ToolAlipayConfig alipay,OrderPayParam orderPayParam) throws Exception;


    OmsOrder generateOrder(OrderVo orderVo) throws Exception;

    /**
     * 支付成功后的回调
     */
    @Transactional
    Integer paySuccess(String orderId, Integer payType);
}
