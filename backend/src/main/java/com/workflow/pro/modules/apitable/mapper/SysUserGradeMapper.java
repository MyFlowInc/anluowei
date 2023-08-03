package com.workflow.pro.modules.apitable.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.workflow.pro.modules.apitable.param.SysUserGradeRequest;
import com.workflow.pro.modules.apitable.domain.SysUserGrade;
import org.apache.ibatis.annotations.Param;

import java.util.List;



/**
 * 会员等级Mapper接口
 *
 * @author some
 * @date 2023-06-02
 */
@Mapper
public interface SysUserGradeMapper extends BaseMapper<SysUserGrade> {
    /**
     * 查询会员等级
     *
     * @param id 会员等级ID
     * @return 会员等级
     */
        SysUserGrade selectSysUserGradeById(String id);

    /**
     * 查询会员等级列表
     *

     * @return 会员等级集合
     */
    List<SysUserGrade> list(@Param("request") SysUserGradeRequest request);

    /**
     * 查询会员等级分頁列表
     *

     * @return 会员等级集合
     */
    List<SysUserGrade> selectSysUserGradeList(@Param("request") SysUserGradeRequest request);


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
     * 删除会员等级
     *
     * @param id 会员等级ID
     * @return 结果
     */
    int deleteSysUserGradeById(String id);

    /**
     * 批量删除会员等级
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteSysUserGradeByIds(String[] ids);

}
