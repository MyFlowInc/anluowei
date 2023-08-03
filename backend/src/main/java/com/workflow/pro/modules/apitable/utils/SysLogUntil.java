package com.workflow.pro.modules.apitable.utils;

import javax.annotation.Resource;

import com.workflow.pro.modules.sys.domain.SysLog;
import com.workflow.pro.modules.sys.service.SysLogService;

public class SysLogUntil {
    /**
     * 日 志 服 务
     * */
    @Resource
    private SysLogService sysLogService;

    public void LogToSys(String Title, String Desc,String result) {

        SysLog sysLog = new SysLog();
        sysLog.setResult(result);
        sysLog.setTitle(Title);
        sysLog.setDescribe(Desc);
        sysLogService.save(sysLog);
    }
}
