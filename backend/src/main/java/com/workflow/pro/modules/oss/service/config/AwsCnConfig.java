package com.workflow.pro.modules.oss.service.config;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "oss.awscn")
public class AwsCnConfig {
    private Boolean enable;

    private String region;

    private String bucketName;

    private String accessKey;

    private String secretKey;

    private String endpoint;



}
