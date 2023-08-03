package com.workflow.pro.modules.oss.service;

import java.util.List;


import com.workflow.pro.modules.oss.domain.CommonToken;

import org.springframework.web.multipart.MultipartFile;

public interface AmazonChinaService {
    String uploadFile(MultipartFile multipartFile);

    String deleteFileFromS3Bucket(String fileUrl);

    List<String> listFiles();

    /**
     * 获取token
     */
    CommonToken token();
}
