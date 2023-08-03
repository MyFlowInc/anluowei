package com.workflow.pro.modules.apitable.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;


import com.workflow.pro.modules.apitable.mapper.SysUserGradeMapper;
import com.workflow.pro.modules.apitable.domain.SysUserGrade;
import com.workflow.pro.modules.apitable.param.SysUserGradeRequest;
import com.workflow.pro.modules.apitable.service.ISysUserGradeService;


import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;


/**
 * 会员等级ServiceImpl业务层处理
 *
 * @author some
 * @date 2023-06-02
 */
@Service
public class SysUserGradeServiceImpl extends ServiceImpl<SysUserGradeMapper, SysUserGrade> implements ISysUserGradeService {

    @Resource
    SysUserGradeMapper sysUserGradeMapper;

    /**
     * 查询会员等级
     *
     * @param id 会员等级ID
     * @return 会员等级
     */
    @Override
    public SysUserGrade selectSysUserGradeById(String id) {
        return sysUserGradeMapper.selectSysUserGradeById(id);
    }

    /**
     * 查询会员等级列表
     * @return 会员等级
     */
    @Override
    public List<SysUserGrade> list (SysUserGradeRequest request){
        return sysUserGradeMapper.selectSysUserGradeList(request);
    }

    /**
     * 查询会员等级
     * @return 会员等级 分页集合
     * */
    @Override
    public PageResponse<SysUserGrade> page(SysUserGradeRequest request) {
        return Pageable.of(request, (() -> sysUserGradeMapper.selectSysUserGradeList(request)));
    }

    /**
     * 新增会员等级
     *
     * @param sysUserGrade 会员等级
     * @return 结果
     */

    @Override
    public int insertSysUserGrade(SysUserGrade sysUserGrade) {
        return sysUserGradeMapper.insertSysUserGrade(sysUserGrade);
    }

    /**
     * 修改会员等级
     *
     * @param sysUserGrade 会员等级
     * @return 结果
     */
    @Override
    public int updateSysUserGrade(SysUserGrade sysUserGrade) {
        return sysUserGradeMapper.updateSysUserGrade(sysUserGrade);
    }

    /**
     * 删除会员等级对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysUserGradeByIds(String[] ids) {
        return sysUserGradeMapper.deleteSysUserGradeByIds(ids);
    }

    /**
     * 删除会员等级信息
     *
     * @param id 会员等级ID
     * @return 结果
     */
    @Override
    public int deleteSysUserGradeById(String id) {
        return sysUserGradeMapper.deleteSysUserGradeById(id);
    }
}
