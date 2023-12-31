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
package com.workflow.pro.modules.apitable.service.impl;


import java.util.Optional;

import javax.annotation.Resource;

import cn.hutool.extra.mail.Mail;
import cn.hutool.extra.mail.MailAccount;
import com.workflow.pro.modules.apitable.domain.ToolEmailConfig;
import com.workflow.pro.modules.apitable.domain.vo.EmailVo;
import com.workflow.pro.modules.apitable.mapper.AliPayRepositoryMapper;
import com.workflow.pro.modules.apitable.mapper.EmailConfigMapper;
import com.workflow.pro.modules.apitable.service.IEmailService;
import com.workflow.pro.modules.apitable.utils.EncryptUtils;
import lombok.RequiredArgsConstructor;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Some
 * @date 2018-12-26
 */
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "email")
public class EmailServiceImpl implements IEmailService {

    @Resource
    EmailConfigMapper emailRepository;

    @Override
    @CachePut(key = "'config'")
    @Transactional(rollbackFor = Exception.class)
    public ToolEmailConfig config(ToolEmailConfig emailConfig, ToolEmailConfig old) throws Exception {
        emailConfig.setConfigId(1L);
        if (!emailConfig.getPass().equals(old.getPass())) {
            // 对称加密
            emailConfig.setPass(EncryptUtils.desEncrypt(emailConfig.getPass()));
        }
        ToolEmailConfig t = emailRepository.selectToolEmailConfigById(emailConfig.getConfigId());
        if (t.getConfigId() != null) {
            emailRepository.updateToolEmailConfig(emailConfig);
        }
        else {
            emailRepository.insertToolEmailConfig(emailConfig);
        }
        return emailConfig;
    }

    @Override
    @Cacheable(key = "'config'")
    public ToolEmailConfig find() {
        ToolEmailConfig emailConfig = emailRepository.selectToolEmailConfigById(1L);
        return emailConfig;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void send(EmailVo emailVo, ToolEmailConfig emailConfig) throws Exception {
        if (emailConfig.getConfigId() == null) {
            throw new Exception("请先配置，再操作");
        }
        // 封装
        MailAccount account = new MailAccount();
        // 设置用户
        String user = emailConfig.getFromUser().split("@")[0];
        account.setUser(user);
        account.setHost(emailConfig.getHost());
        account.setPort(Integer.parseInt(emailConfig.getPort()));
        account.setAuth(true);
        try {
            // 对称解密
            account.setPass(EncryptUtils.desDecrypt(emailConfig.getPass()));
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        account.setFrom(emailConfig.getUser() + "<" + emailConfig.getFromUser() + ">");
        // ssl方式发送
        account.setSslEnable(true);
        account.setDebug(true);
        // 使用STARTTLS安全连接
        // account.setStarttlsEnable(true);
        String content = emailVo.getContent();
        // 发送
        try {
            int size = emailVo.getTos().size();
            Mail.create(account)
                    .setTos(emailVo.getTos().toArray(new String[size]))
                    .setTitle(emailVo.getSubject())
                    .setContent(content)
                    .setHtml(true)
                    //关闭session
                    .setUseGlobalSession(false)
                    .send();
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
