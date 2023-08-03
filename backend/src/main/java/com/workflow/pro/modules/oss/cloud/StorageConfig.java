package com.workflow.pro.modules.oss.cloud;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "oss")
public class StorageConfig {

    /**
     * Aliyun 阿里云
     * <p>
     * Qiniu 七牛
     * <p>
     * Tencent 腾讯
     */
    private String enable;
}
