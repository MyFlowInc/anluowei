package com.workflow.pro.modules.sys.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.workflow.pro.modules.sys.domain.SysDataSource;
import com.workflow.pro.modules.sys.param.SysDataSourceRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface SysDataSourceRepository extends BaseMapper<SysDataSource> {

    /**
     * 获取多库列表
     *
     * @return {@link SysDataSource}
     * */
    List<SysDataSource> selectDataSource(@Param("request") SysDataSourceRequest request);
}
