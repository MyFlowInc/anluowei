package com.workflow.pro.modules.apitable.service;

import java.util.List;


import com.baomidou.mybatisplus.extension.service.IService;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;


import com.workflow.pro.modules.apitable.param.OmsOrderOperateHistoryRequest;
import com.workflow.pro.modules.apitable.domain.OmsOrderOperateHistory;

/**
 * 订单操作历史记录IService接口
 *
 * @author some
 * @date 2023-06-09
 */
public interface IOmsOrderOperateHistoryService  extends IService <OmsOrderOperateHistory>{
    /**
     * 查询订单操作历史记录
     *
     * @param id 订单操作历史记录ID
     * @return 订单操作历史记录
     */
        OmsOrderOperateHistory selectOmsOrderOperateHistoryById(String id);


    /**
     * 查询订单操作历史记录
     * @param  request pageSize pageNum
     * @return 订单操作历史记录 分页集合
     * */
    PageResponse<OmsOrderOperateHistory> page(OmsOrderOperateHistoryRequest request);

    /**
     * 查询订单操作历史记录列表
     * @return 订单操作历史记录集合
     */
    List<OmsOrderOperateHistory> list(OmsOrderOperateHistoryRequest request);

    /**
     * 新增订单操作历史记录
     *
     * @param omsOrderOperateHistory 订单操作历史记录
     * @return 结果
     */
    int insertOmsOrderOperateHistory(OmsOrderOperateHistory omsOrderOperateHistory);

    /**
     * 修改订单操作历史记录
     *
     * @param omsOrderOperateHistory 订单操作历史记录
     * @return 结果
     */
    int updateOmsOrderOperateHistory(OmsOrderOperateHistory omsOrderOperateHistory);

    /**
     * 批量删除订单操作历史记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteOmsOrderOperateHistoryByIds(String[] ids);

    /**
     * 删除订单操作历史记录信息
     *
     * @param id 订单操作历史记录ID
     * @return 结果
     */
    int deleteOmsOrderOperateHistoryById(String id);

}
