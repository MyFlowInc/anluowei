package com.workflow.pro.modules.sms.service.impl;

import com.workflow.pro.common.constant.SystemConstant;
import com.workflow.pro.modules.sms.cloud.SendConfig;
import com.workflow.pro.modules.sms.cloud.impl.CloudSendService;
import com.workflow.pro.modules.sms.service.SysSmsService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class SysSmsServiceImpl implements SysSmsService {

    @Resource
    private SendConfig sendConfig;

    @Resource
    private CloudSendService cloudSendService;

    @Override
    public boolean send(String phone, String message) {
        if(sendConfig.getLocation().equals(SystemConstant.ALIYUN)) {
           return cloudSendService.sendSample(phone, message);
        }
        return false;
    }
}
