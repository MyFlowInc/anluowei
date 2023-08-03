package com.workflow.pro.modules.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.modules.cms.domain.CmsCategoryPower;
import com.workflow.pro.modules.cms.domain.CmsContent;
import com.workflow.pro.modules.cms.param.CmsCategoryPowerRequest;

import java.util.List;

public interface ICmsCategoryPowerService extends IService<CmsCategoryPower>
{

    /**
     * 获取列表
     *
     */
    List<CmsCategoryPower> list(CmsCategoryPowerRequest request);

    /**
     * @param request 查询参数 分页
     * @return {@link CmsContent}
     */
    PageResponse<CmsCategoryPower> page(CmsCategoryPowerRequest request);

    /**
     * 分配用户角色
     *
     * @param deptId 用户编号
     * @param categoryIds 角色编号
     *
     * @return {@link Boolean}
     * */
    Boolean give(String deptId, List<String> categoryIds);

}
