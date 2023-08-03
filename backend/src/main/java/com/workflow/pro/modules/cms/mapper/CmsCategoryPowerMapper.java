package com.workflow.pro.modules.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.workflow.pro.modules.cms.domain.CmsCategoryPower;
import com.workflow.pro.modules.cms.param.CmsCategoryPowerRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CmsCategoryPowerMapper extends BaseMapper<CmsCategoryPower> {

    List<CmsCategoryPower> selectCmsCategoryPowerList(@Param("request") CmsCategoryPowerRequest request);
}
