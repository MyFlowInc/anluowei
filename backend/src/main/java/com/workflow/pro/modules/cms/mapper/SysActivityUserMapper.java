package com.workflow.pro.modules.cms.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.workflow.pro.modules.cms.domain.SysActivityUser;
import com.workflow.pro.modules.cms.param.SysActivityUserRequest;
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
public interface SysActivityUserMapper extends BaseMapper<SysActivityUser> {
    /**
     * 查询activity列表
     *
     * @param request activity集合
     * @return activity集合
     */
    List<SysActivityUser> selectSysActivityUserList(@Param("request") SysActivityUserRequest request);

}