package com.workflow.pro.modules.oss.service;

import com.workflow.pro.modules.oss.domain.QiNiuToken;

import org.springframework.web.multipart.MultipartFile;

public interface QiNiuServiceConfig {

    /**
     * 文件上传
     *
     * @param file 文件
     * @return {@link String} 存储路径
     */
    String upload(MultipartFile file);

    String uploadFile(MultipartFile multipartFile);

    /**
     * 获取token
     */
    QiNiuToken token();
}
