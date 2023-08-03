package com.workflow.pro.modules.sys.rest;

import com.workflow.pro.common.aop.annotation.Log;
import com.workflow.pro.common.constant.ControllerConstant;
import com.workflow.pro.common.web.base.BaseController;
import com.workflow.pro.common.web.domain.Result;
import com.workflow.pro.modules.sys.domain.SysMail;
import com.workflow.pro.modules.sys.service.SysMailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * 邮箱控制器
 *
 * Author: SOME
 * CreateTime: 2022/03/28
 * */
@Api(tags = {"邮箱"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "mail")
public class SysMailController extends BaseController {

    @Resource
    private SysMailService sysMailService;

    /**
     * 发送普通邮件
     *
     * @param sysMail 邮件实体
     */
    @PostMapping("send")
    @Log(title = "邮件发送")
    @ApiOperation(value = "邮件发送")
    public Result send(@RequestBody SysMail sysMail){
        sysMailService.sendSimpleMail(sysMail.getTo(), sysMail.getSubject(), sysMail.getContent());
        return success();
    }
}
