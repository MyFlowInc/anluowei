package com.workflow.pro.modules.qywx.config;

import com.workflow.pro.modules.qywx.service.AppInfoMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
@Slf4j
@Data
@Configuration
@ConfigurationProperties(prefix = "wecom")
public class WxComProperties implements InitializingBean , ServletContextAware {

    @Autowired
    private AppInfoMapper appInfoMapper;

    private String corpId;

    private String secret;

    private Integer agentId;

    private String redirectUrl;

    public static String CROP_ID;

    public static String SECRET;

    public static String REDIRECT_URL;

    @Override
    public void afterPropertiesSet() throws Exception {
        WxComProperties.CROP_ID = getCorpId();
        WxComProperties.SECRET = getSecret();
        WxComProperties.REDIRECT_URL = getRedirectUrl();
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        try {
            /*AppInfo appInfo = appInfoMapper.selectById(1);
            agentId = appInfo.getAgentId();
            log.info("数据库agentId为：" + agentId);*/
        }catch (Exception e) {
            throw e;
        }
    }
}
