package com.workflow.pro.modules.oss.cloud.impl;

import lombok.Data;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 阿里云 Oss 存储配置
 *
 * Author: SOME
 * CreateTime: 2022/04/21
 * */
/** ComponentScan 这是 Spring 3.1 添加的一个注解，用来代替配置文件中的 component-scan 配置，开启组件扫描，即自动扫描包路径下的 @Component 注解进行注册 bean 实例到 context 中。
 *  ConfigurationProperties用来加载额外的配置（如 .properties 文件），可用在 @Configuration 注解类，或者 @Bean 注解方法上面。
 */
@Data
@Component
@ConfigurationProperties(prefix = "oss.aliyun")
public class CloudStorageConfig {

    private Boolean enable;

    private String endpoint;

    private String accessKeyId;

    private String accessKeySecret;

    private String bucketName;

    @Bean
    public OSS initOSSClient() {
        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }
}
