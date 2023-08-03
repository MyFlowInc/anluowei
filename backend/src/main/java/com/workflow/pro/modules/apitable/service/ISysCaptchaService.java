package com.workflow.pro.modules.apitable.service;

import java.util.List;


import com.baomidou.mybatisplus.extension.service.IService;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;


import com.workflow.pro.modules.apitable.exception.BusinessExceptionNew;
import com.workflow.pro.modules.apitable.param.SysCaptchaRequest;
import com.workflow.pro.modules.apitable.domain.SysCaptcha;

/**
 * 【请填写功能名称】IService接口
 *
 * @author some
 * @date 2023-05-30
 */
public interface ISysCaptchaService extends IService<SysCaptcha> {
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    SysCaptcha selectSysCaptchaById(Long id);


    /**
     * 查询【请填写功能名称】
     * @param  request pageSize pageNum
     * @return 【请填写功能名称】 分页集合
     * */
    PageResponse<SysCaptcha> page(SysCaptchaRequest request);

    /**
     * 查询【请填写功能名称】列表
     * @return 【请填写功能名称】集合
     */
    List<SysCaptcha> list(SysCaptchaRequest request);

    /**
     * 新增【请填写功能名称】
     *
     * @param sysCaptcha 【请填写功能名称】
     * @return 结果
     */
    int insertSysCaptcha(SysCaptcha sysCaptcha);

    /**
     * 修改【请填写功能名称】
     *
     * @param sysCaptcha 【请填写功能名称】
     * @return 结果
     */
    int updateSysCaptcha(SysCaptcha sysCaptcha);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteSysCaptchaByIds(String[] ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    int deleteSysCaptchaById(Long id);

    SysCaptcha send(SysCaptcha sysCaptcha) throws Exception;

    Boolean Check(String email, String code) throws BusinessExceptionNew;

}
