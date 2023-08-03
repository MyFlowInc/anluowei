package com.workflow.pro.modules.apitable.service;

import java.util.List;


import com.baomidou.mybatisplus.extension.service.IService;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;


import com.workflow.pro.modules.apitable.param.OmsOrderReturnApplyRequest;
import com.workflow.pro.modules.apitable.domain.OmsOrderReturnApply;

/**
 * 订单退货申请IService接口
 *
 * @author some
 * @date 2023-06-09
 */
public interface IOmsOrderReturnApplyService  extends IService <OmsOrderReturnApply>{
    /**
     * 查询订单退货申请
     *
     * @param id 订单退货申请ID
     * @return 订单退货申请
     */
        OmsOrderReturnApply selectOmsOrderReturnApplyById(String id);


    /**
     * 查询订单退货申请
     * @param  request pageSize pageNum
     * @return 订单退货申请 分页集合
     * */
    PageResponse<OmsOrderReturnApply> page(OmsOrderReturnApplyRequest request);

    /**
     * 查询订单退货申请列表
     * @return 订单退货申请集合
     */
    List<OmsOrderReturnApply> list(OmsOrderReturnApplyRequest request);

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
     * 批量删除订单退货申请
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteOmsOrderReturnApplyByIds(String[] ids);

    /**
     * 删除订单退货申请信息
     *
     * @param id 订单退货申请ID
     * @return 结果
     */
    int deleteOmsOrderReturnApplyById(String id);

}
