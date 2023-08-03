package com.workflow.pro.modules.oss.service.config;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "oss.aws")
public class AwsConfig {
    // amazon.s3.region: ap-northeast-1
    // amazon.s3.bucket: somebuckets3
    // amazon.aws.access-key-id: AKIAX5FB23HOX4BZZ44G
    // amazon.aws.access-key-secret: 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

    private Boolean enable;

    private String region;

    private String bucketName;

    private String accessKey;

    private String secretKey;

    private String endpoint;

}
