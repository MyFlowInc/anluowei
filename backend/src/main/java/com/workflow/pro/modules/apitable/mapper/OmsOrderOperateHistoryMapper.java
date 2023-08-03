package com.workflow.pro.modules.apitable.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.workflow.pro.modules.apitable.param.OmsOrderOperateHistoryRequest;
import com.workflow.pro.modules.apitable.domain.OmsOrderOperateHistory;
import org.apache.ibatis.annotations.Param;

import java.util.List;



/**
 * 订单操作历史记录Mapper接口
 *
 * @author some
 * @date 2023-06-09
 */
@Mapper
public interface OmsOrderOperateHistoryMapper extends BaseMapper<OmsOrderOperateHistory> {
    /**
     * 查询订单操作历史记录
     *
     * @param id 订单操作历史记录ID
     * @return 订单操作历史记录
     */
        OmsOrderOperateHistory selectOmsOrderOperateHistoryById(String id);

    /**
     * 查询订单操作历史记录列表
     *

     * @return 订单操作历史记录集合
     */
    List<OmsOrderOperateHistory> list(@Param("request") OmsOrderOperateHistoryRequest request);

    /**
     * 查询订单操作历史记录分頁列表
     *

     * @return 订单操作历史记录集合
     */
    List<OmsOrderOperateHistory> selectOmsOrderOperateHistoryList(@Param("request") OmsOrderOperateHistoryRequest request);


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
     * 删除订单操作历史记录
     *
     * @param id 订单操作历史记录ID
     * @return 结果
     */
    int deleteOmsOrderOperateHistoryById(String id);

    /**
     * 批量删除订单操作历史记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteOmsOrderOperateHistoryByIds(String[] ids);

}
