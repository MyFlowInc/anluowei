package com.workflow.pro.modules.apitable.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;


import com.workflow.pro.modules.apitable.mapper.OmsOrderReturnApplyMapper;
import com.workflow.pro.modules.apitable.domain.OmsOrderReturnApply;
import com.workflow.pro.modules.apitable.param.OmsOrderReturnApplyRequest;
import com.workflow.pro.modules.apitable.service.IOmsOrderReturnApplyService;


import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;


/**
 * 订单退货申请ServiceImpl业务层处理
 *
 * @author some
 * @date 2023-06-09
 */
@Service
public class OmsOrderReturnApplyServiceImpl extends ServiceImpl<OmsOrderReturnApplyMapper, OmsOrderReturnApply> implements IOmsOrderReturnApplyService {

    @Resource
    OmsOrderReturnApplyMapper omsOrderReturnApplyMapper;

    /**
     * 查询订单退货申请
     *
     * @param id 订单退货申请ID
     * @return 订单退货申请
     */
    @Override
    public OmsOrderReturnApply selectOmsOrderReturnApplyById(String id) {
        return omsOrderReturnApplyMapper.selectOmsOrderReturnApplyById(id);
    }

    /**
     * 查询订单退货申请列表
     * @return 订单退货申请
     */
    @Override
    public List<OmsOrderReturnApply> list (OmsOrderReturnApplyRequest request){
        return omsOrderReturnApplyMapper.list(request);
    }

    /**
     * 查询订单退货申请
     * @return 订单退货申请 分页集合
     * */
    @Override
    public PageResponse<OmsOrderReturnApply> page(OmsOrderReturnApplyRequest request) {
        return Pageable.of(request, (() -> omsOrderReturnApplyMapper.selectOmsOrderReturnApplyList(request)));
    }

    /**
     * 新增订单退货申请
     *
     * @param omsOrderReturnApply 订单退货申请
     * @return 结果
     */

    @Override
    public int insertOmsOrderReturnApply(OmsOrderReturnApply omsOrderReturnApply) {
        return omsOrderReturnApplyMapper.insertOmsOrderReturnApply(omsOrderReturnApply);
    }

    /**
     * 修改订单退货申请
     *
     * @param omsOrderReturnApply 订单退货申请
     * @return 结果
     */
    @Override
    public int updateOmsOrderReturnApply(OmsOrderReturnApply omsOrderReturnApply) {
        return omsOrderReturnApplyMapper.updateOmsOrderReturnApply(omsOrderReturnApply);
    }

    /**
     * 删除订单退货申请对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOmsOrderReturnApplyByIds(String[] ids) {
        return omsOrderReturnApplyMapper.deleteOmsOrderReturnApplyByIds(ids);
    }

    /**
     * 删除订单退货申请信息
     *
     * @param id 订单退货申请ID
     * @return 结果
     */
    @Override
    public int deleteOmsOrderReturnApplyById(String id) {
        return omsOrderReturnApplyMapper.deleteOmsOrderReturnApplyById(id);
    }
}
