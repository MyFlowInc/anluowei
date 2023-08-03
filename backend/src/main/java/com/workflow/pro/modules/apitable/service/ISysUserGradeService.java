package com.workflow.pro.modules.apitable.service;

import java.util.List;


import com.baomidou.mybatisplus.extension.service.IService;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;


import com.workflow.pro.modules.apitable.param.SysUserGradeRequest;
import com.workflow.pro.modules.apitable.domain.SysUserGrade;

/**
 * 会员等级IService接口
 *
 * @author some
 * @date 2023-06-02
 */
public interface ISysUserGradeService  extends IService <SysUserGrade>{
    /**
     * 查询会员等级
     *
     * @param id 会员等级ID
     * @return 会员等级
     */
        SysUserGrade selectSysUserGradeById(String id);


    /**
     * 查询会员等级
     * @param  request pageSize pageNum
     * @return 会员等级 分页集合
     * */
    PageResponse<SysUserGrade> page(SysUserGradeRequest request);

    /**
     * 查询会员等级列表
     * @return 会员等级集合
     */
    List<SysUserGrade> list(SysUserGradeRequest request);

    /**
     * 新增会员等级
     *
     * @param sysUserGrade 会员等级
     * @return 结果
     */
    int insertSysUserGrade(SysUserGrade sysUserGrade);

    /**
     * 修改会员等级
     *
     * @param sysUserGrade 会员等级
     * @return 结果
     */
    int updateSysUserGrade(SysUserGrade sysUserGrade);

    /**
     * 批量删除会员等级
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteSysUserGradeByIds(String[] ids);

    /**
     * 删除会员等级信息
     *
     * @param id 会员等级ID
     * @return 结果
     */
    int deleteSysUserGradeById(String id);

}
