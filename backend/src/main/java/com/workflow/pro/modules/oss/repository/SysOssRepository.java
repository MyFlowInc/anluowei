package com.workflow.pro.modules.oss.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.workflow.pro.modules.oss.domain.SysOss;
import com.workflow.pro.modules.oss.param.SysOssRequest;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface SysOssRepository extends BaseMapper<SysOss> {

    /**
     * 获取文件列表
     *
     * @param request 查询参数
     *
     * @return {@link SysOss}
     * */
    List<SysOss> selectFile(SysOssRequest request);
    List<SysOss> selectUniqueFile(SysOssRequest request);


}
