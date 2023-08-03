package com.workflow.pro.modules.job.demo;

import com.workflow.pro.common.quartz.base.BaseQuartz;
import com.workflow.pro.modules.sys.service.SysDeptService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpDepart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("SysDep")
public class SysDep implements BaseQuartz {
    @Autowired
    private WxCpService wxCpService;
    @Autowired
    SysDeptService sysDeptService;


    @Override
    public void run(String param) throws Exception {
        List<WxCpDepart> Departs = wxCpService.getDepartmentService().list(Long.valueOf("0"));
        System.out.println("执行 同步  部门信息 任务");
       sysDeptService.syncDepartInfo(Departs);
    }
}
