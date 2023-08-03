package com.workflow.pro.modules.cms.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.workflow.pro.modules.cms.param.AppRequest;
import com.workflow.pro.modules.qywx.entity.AppInfo;
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
public interface SysAppInfoMapper extends BaseMapper<AppInfo> {
    /**
     * 查询activity列表
     *
     * @param request activity集合
     * @return activity集合
     */
    List<AppInfo> selectAppInfoList(@Param("request") AppRequest request);

}