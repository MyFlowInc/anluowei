package com.workflow.pro.modules.sms.rest;

import com.workflow.pro.common.web.base.BaseController;
import com.workflow.pro.common.web.domain.Result;
import com.workflow.pro.modules.sms.domain.SysSms;
import com.workflow.pro.modules.sms.service.SysSmsService;
import com.workflow.pro.common.constant.ControllerConstant;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 短信控制器
 *
 * Author: SOME
 * CreateTime: 2022/03/28
 * */
@Api(tags = {"短信"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "sms")
public class SysSmsController extends BaseController {

    @Resource
    private SysSmsService sysSmsService;

    /**
     * 发送短信
     *
     * @param sysSms 短信实体
     *
     * @return {@link Boolean}
     * */
    @PostMapping("send")
    public Result send(@RequestBody SysSms sysSms) {
        return auto(sysSmsService.send(sysSms.getPhone(), sysSms.getMessage()));
    }
}
