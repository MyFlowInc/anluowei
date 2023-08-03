package com.workflow.pro.modules.oss.service;

import java.util.List;


import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.modules.oss.domain.SysOss;
import com.workflow.pro.modules.oss.param.SysOssRequest;

import org.springframework.web.multipart.MultipartFile;

public interface SysOssService {

    /**
     * 获取文件列表
     *
     * @param request 查询参数
     */
    List<SysOss> list(SysOssRequest request);

    List<SysOss> listCount(SysOssRequest request);
    /**
     * 获取文件列表
     *
     * @param request 查询参数
     */
    PageResponse<SysOss> page(SysOssRequest request);

    /**
     * 文件上传
     *
     * @param file 文件本身
     */
    String upload(MultipartFile file);

    /**
     * 多文件上传
     *
     * @param files 文件列表
     */
    List<String> upload(List<MultipartFile> files);


}
