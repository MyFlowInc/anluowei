package com.workflow.pro.modules.apitable.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;


import com.workflow.pro.modules.apitable.mapper.OmsOrderOperateHistoryMapper;
import com.workflow.pro.modules.apitable.domain.OmsOrderOperateHistory;
import com.workflow.pro.modules.apitable.param.OmsOrderOperateHistoryRequest;
import com.workflow.pro.modules.apitable.service.IOmsOrderOperateHistoryService;


import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;


/**
 * 订单操作历史记录ServiceImpl业务层处理
 *
 * @author some
 * @date 2023-06-09
 */
@Service
public class OmsOrderOperateHistoryServiceImpl extends ServiceImpl<OmsOrderOperateHistoryMapper, OmsOrderOperateHistory> implements IOmsOrderOperateHistoryService {

    @Resource
    OmsOrderOperateHistoryMapper omsOrderOperateHistoryMapper;

    /**
     * 查询订单操作历史记录
     *
     * @param id 订单操作历史记录ID
     * @return 订单操作历史记录
     */
    @Override
    public OmsOrderOperateHistory selectOmsOrderOperateHistoryById(String id) {
        return omsOrderOperateHistoryMapper.selectOmsOrderOperateHistoryById(id);
    }

    /**
     * 查询订单操作历史记录列表
     * @return 订单操作历史记录
     */
    @Override
    public List<OmsOrderOperateHistory> list (OmsOrderOperateHistoryRequest request){
        return omsOrderOperateHistoryMapper.list(request);
    }

    /**
     * 查询订单操作历史记录
     * @return 订单操作历史记录 分页集合
     * */
    @Override
    public PageResponse<OmsOrderOperateHistory> page(OmsOrderOperateHistoryRequest request) {
        return Pageable.of(request, (() -> omsOrderOperateHistoryMapper.selectOmsOrderOperateHistoryList(request)));
    }

    /**
     * 新增订单操作历史记录
     *
     * @param omsOrderOperateHistory 订单操作历史记录
     * @return 结果
     */

    @Override
    public int insertOmsOrderOperateHistory(OmsOrderOperateHistory omsOrderOperateHistory) {
        return omsOrderOperateHistoryMapper.insertOmsOrderOperateHistory(omsOrderOperateHistory);
    }

    /**
     * 修改订单操作历史记录
     *
     * @param omsOrderOperateHistory 订单操作历史记录
     * @return 结果
     */
    @Override
    public int updateOmsOrderOperateHistory(OmsOrderOperateHistory omsOrderOperateHistory) {
        return omsOrderOperateHistoryMapper.updateOmsOrderOperateHistory(omsOrderOperateHistory);
    }

    /**
     * 删除订单操作历史记录对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOmsOrderOperateHistoryByIds(String[] ids) {
        return omsOrderOperateHistoryMapper.deleteOmsOrderOperateHistoryByIds(ids);
    }

    /**
     * 删除订单操作历史记录信息
     *
     * @param id 订单操作历史记录ID
     * @return 结果
     */
    @Override
    public int deleteOmsOrderOperateHistoryById(String id) {
        return omsOrderOperateHistoryMapper.deleteOmsOrderOperateHistoryById(id);
    }
}
