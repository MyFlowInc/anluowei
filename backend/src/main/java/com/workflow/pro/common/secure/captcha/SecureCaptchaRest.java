package com.workflow.pro.common.secure.captcha;

import com.workflow.pro.common.constant.ControllerConstant;
import com.workflow.pro.common.web.domain.Result;
import com.workflow.pro.common.web.domain.ResultController;
import com.workflow.pro.common.web.interceptor.annotation.RateLimit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * Captcha Rest 服务
 *
 * Author: SOME
 * CreateTime: 2022/04/01
 * */
@Api(tags = {"验证"})
@RestController
@RequestMapping(ControllerConstant.PREFIX + "captcha")
public class SecureCaptchaRest extends ResultController {

    @Resource
    private SecureCaptchaService customCaptchaService;

    /**
     * Captcha 生成
     * */
    @RateLimit
    @GetMapping("create")
    @ApiOperation(value = "创建验证码")
    public Result createCaptcha(){
        return success(customCaptchaService.createCaptcha());
    }

}
