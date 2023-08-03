package com.workflow.pro.modules.oss.service.config;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "oss.qiniu")
public class QiNiuConfig {


    private String bucketName;

    private String accessKey;

    private String secretKey;

    private String endpoint;

    private String srv;

    private String upload_host;

}
