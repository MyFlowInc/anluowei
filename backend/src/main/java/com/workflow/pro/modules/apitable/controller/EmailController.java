/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.workflow.pro.modules.apitable.controller;

import javax.annotation.Resource;

import com.workflow.pro.common.aop.annotation.Log;
import com.workflow.pro.common.web.base.BaseController;
import com.workflow.pro.common.web.domain.Result;
import com.workflow.pro.modules.apitable.domain.ToolEmailConfig;
import com.workflow.pro.modules.apitable.domain.vo.EmailVo;
import com.workflow.pro.modules.apitable.service.IAliPayService;
import com.workflow.pro.modules.apitable.service.IEmailService;
import com.workflow.pro.modules.apitable.service.IToolAlipayConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 发送邮件
 * @author Some
 * @date 2018/09/28 6:55:53
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/email")
@Api(tags = "工具：邮件管理")
public class EmailController extends BaseController {


    @Resource
    private IEmailService emailService;

    @GetMapping
    public ResponseEntity<Object> queryEmailConfig() {
        return new ResponseEntity<>(emailService.find(), HttpStatus.OK);
    }

    @Log("配置邮件")
    @PutMapping
    @ApiOperation("配置邮件")
    public Result<Object> updateEmailConfig(@Validated @RequestBody ToolEmailConfig emailConfig) throws Exception {
        emailService.config(emailConfig, emailService.find());
        return Result.success(emailConfig);
    }

    @Log("发送邮件")
    @PostMapping
    @ApiOperation("发送邮件")
    public Result<Object> sendEmail(@Validated @RequestBody EmailVo emailVo) throws Exception {
        emailService.send(emailVo, emailService.find());
        return Result.success();
    }
}
