package com.workflow.pro.modules.apitable.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.workflow.pro.modules.apitable.param.SysCaptchaRequest;
import com.workflow.pro.modules.apitable.domain.SysCaptcha;
import org.apache.ibatis.annotations.Param;

import java.util.List;



/**
 * 【请填写功能名称】Mapper接口
 *
 * @author some
 * @date 2023-05-30
 */
@Mapper
public interface SysCaptchaMapper extends BaseMapper<SysCaptcha> {
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
        SysCaptcha selectSysCaptchaById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *

     * @return 【请填写功能名称】集合
     */
    List<SysCaptcha> list(@Param("request") SysCaptchaRequest request);

    /**
     * 查询【请填写功能名称】分頁列表
     *

     * @return 【请填写功能名称】集合
     */
    List<SysCaptcha> selectSysCaptchaList(@Param("request") SysCaptchaRequest request);


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
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    int deleteSysCaptchaById(Long id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteSysCaptchaByIds(String[] ids);

}
