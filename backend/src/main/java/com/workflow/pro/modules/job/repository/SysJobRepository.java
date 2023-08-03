package com.workflow.pro.modules.job.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.workflow.pro.modules.job.domain.SysJob;
import com.workflow.pro.modules.job.param.SysJobRequest;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 任务模型
 *
 * Author: SOME
 * CreateTime: 2022/04/21
 * */
@Mapper
public interface SysJobRepository extends BaseMapper<SysJob> {

    /**
     * 获取任务列表
     *
     * @return {@link SysJob}
     * */
    List<SysJob> selectJob(SysJobRequest request);
}
