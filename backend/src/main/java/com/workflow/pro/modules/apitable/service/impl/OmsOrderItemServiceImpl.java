package com.workflow.pro.modules.apitable.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;


import com.workflow.pro.modules.apitable.mapper.OmsOrderItemMapper;
import com.workflow.pro.modules.apitable.domain.OmsOrderItem;
import com.workflow.pro.modules.apitable.param.OmsOrderItemRequest;
import com.workflow.pro.modules.apitable.service.IOmsOrderItemService;


import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;


/**
 * 订单中所包含的商品ServiceImpl业务层处理
 *
 * @author some
 * @date 2023-06-13
 */
@Service
public class OmsOrderItemServiceImpl extends ServiceImpl<OmsOrderItemMapper, OmsOrderItem> implements IOmsOrderItemService {

    @Resource
    OmsOrderItemMapper omsOrderItemMapper;

    /**
     * 查询订单中所包含的商品
     *
     * @param id 订单中所包含的商品ID
     * @return 订单中所包含的商品
     */
    @Override
    public OmsOrderItem selectOmsOrderItemById(String id) {
        return omsOrderItemMapper.selectOmsOrderItemById(id);
    }

    /**
     * 查询订单中所包含的商品列表
     * @return 订单中所包含的商品
     */
    @Override
    public List<OmsOrderItem> list (OmsOrderItemRequest request){
        return omsOrderItemMapper.list(request);
    }

    /**
     * 查询订单中所包含的商品
     * @return 订单中所包含的商品 分页集合
     * */
    @Override
    public PageResponse<OmsOrderItem> page(OmsOrderItemRequest request) {
        return Pageable.of(request, (() -> omsOrderItemMapper.selectOmsOrderItemList(request)));
    }

    /**
     * 新增订单中所包含的商品
     *
     * @param omsOrderItem 订单中所包含的商品
     * @return 结果
     */

    @Override
    public int insertOmsOrderItem(OmsOrderItem omsOrderItem) {
        return omsOrderItemMapper.insertOmsOrderItem(omsOrderItem);
    }

    /**
     * 修改订单中所包含的商品
     *
     * @param omsOrderItem 订单中所包含的商品
     * @return 结果
     */
    @Override
    public int updateOmsOrderItem(OmsOrderItem omsOrderItem) {
        return omsOrderItemMapper.updateOmsOrderItem(omsOrderItem);
    }

    /**
     * 删除订单中所包含的商品对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOmsOrderItemByIds(String[] ids) {
        return omsOrderItemMapper.deleteOmsOrderItemByIds(ids);
    }

    /**
     * 删除订单中所包含的商品信息
     *
     * @param id 订单中所包含的商品ID
     * @return 结果
     */
    @Override
    public int deleteOmsOrderItemById(String id) {
        return omsOrderItemMapper.deleteOmsOrderItemById(id);
    }
}
