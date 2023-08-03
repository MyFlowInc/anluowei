package com.workflow.pro.modules.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;
import com.workflow.pro.modules.cms.domain.SysActivity;
import com.workflow.pro.modules.cms.domain.SysActivityUser;
import com.workflow.pro.modules.cms.mapper.SysActivityMapper;
import com.workflow.pro.modules.cms.param.SysActivityRequest;
import com.workflow.pro.modules.cms.service.ISysActivityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysActivityServiceImpl extends ServiceImpl<SysActivityMapper, SysActivity> implements ISysActivityService {
    @Resource
    SysActivityMapper sysActivityMapper;

    @Resource
    SysActivityUserServiceImpl sysActivityUserService;
    @Override
    public List<SysActivity> list(SysActivityRequest request) {
        return null;
    }

    @Override
    public PageResponse<SysActivity> page(SysActivityRequest request) {
        List<SysActivity> activityList = sysActivityMapper.selectSysActivityList(request);
        if (activityList.size() > 0) {
            for (SysActivity activity : activityList) {
                Long count = sysActivityUserService.count(new QueryWrapper<SysActivityUser>().eq("activity_id", activity.getId()));
                activity.setUsersNu(count);
                activity.setUrl("https://h5主页地址"+"/#/act/"+activity.getId());
            }
        }

//        activityList = activityList .stream().map(object -> {
//            SysActivity activity = new SysActivity();
//            BeanUtils.copyProperties(object, activity);
//            activity.setUrl("Value");
//            return activity;
//
//        }).collect(Collectors.toList());
        return Pageable.of(request, activityList);
    }


}