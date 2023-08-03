package com.workflow.pro.modules.qywx.config;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class WeComConfig {

    @Autowired
    private WxComProperties properties;

    @Bean(name = "wxMpService")
    public WxCpService wxMpService() {
        //log.info("cropId：" + JSON.toJSONString(properties.getCorpId()) + "企业微信服务初始化配置");
        WxCpDefaultConfigImpl wxConfigProvider = new WxCpDefaultConfigImpl();
        wxConfigProvider.setCorpId(properties.getCorpId());
        wxConfigProvider.setCorpSecret(properties.getSecret());
        //wxConfigProvider.setBaseApiUrl("https://nw-api");

        WxCpService wxCpService = new WxCpServiceImpl();
        wxCpService.setWxCpConfigStorage(wxConfigProvider);
      //  log.info("cropId：" + JSON.toJSONString(properties.getCorpId()) + "企业微信服务配置完成");
        return wxCpService;
    }

}
