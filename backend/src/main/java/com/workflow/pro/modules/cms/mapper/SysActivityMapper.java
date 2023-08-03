package com.workflow.pro.modules.cms.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.workflow.pro.modules.cms.domain.SysActivity;
import com.workflow.pro.modules.cms.param.SysActivityRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * activityMapper接口
 *
 * @author Some
 * @date 2022-09-20
 */
@Mapper
public interface SysActivityMapper extends BaseMapper<SysActivity> {
    /**
     * 查询activity列表
     *
     * @param request activity集合
     * @return activity集合
     */
    List<SysActivity> selectSysActivityList(@Param("request") SysActivityRequest request);

}