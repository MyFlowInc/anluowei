package com.workflow.pro.modules.oss.service.impl;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workflow.pro.common.constant.SystemConstant;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;
import com.workflow.pro.modules.oss.cloud.StorageConfig;
import com.workflow.pro.modules.oss.cloud.impl.CloudStorageConfig;
import com.workflow.pro.modules.oss.cloud.impl.CloudStorageService;
import com.workflow.pro.modules.oss.domain.SysOss;
import com.workflow.pro.modules.oss.param.SysOssRequest;
import com.workflow.pro.modules.oss.repository.SysOssRepository;
import com.workflow.pro.modules.oss.service.SysOssService;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SysOssServiceImpl extends ServiceImpl<SysOssRepository, SysOss> implements SysOssService {

    @Resource
    private StorageConfig storageConfig;

    @Resource
    private CloudStorageConfig cloudStorageConfig;

    @Resource
    private CloudStorageService cloudStorageService;

    @Resource
    private SysOssRepository sysOssRepository;

    @Resource
    private AmazonServiceImpl amazonServiceImpl;
    @Resource
    private AmazonChinaServiceImpl amazonChinaServiceImpl;

    @Resource
    private QiNiuServiceImpl qiNiuService;

    @Override
    public List<SysOss> list(SysOssRequest request) {
        return sysOssRepository.selectFile(request);
    }

    @Override
    public List<SysOss> listCount(SysOssRequest request) {
        return sysOssRepository.selectUniqueFile(request);
    }

    @Override
    public PageResponse<SysOss> page(SysOssRequest request) {
        return Pageable.of(request, (() -> sysOssRepository.selectFile(request)));
    }

    @Override
    @Transactional
    public List<String> upload(List<MultipartFile> files) {
        List<String> urls = new ArrayList<>();
        files.forEach(file -> {
            try {
                urls.add(upload(file));
            }
            catch (UnknownHostException e) {
                e.printStackTrace();
            }
        });
        return urls;
    }


    @Override
    @Transactional
    public String upload(MultipartFile file) throws UnknownHostException {

        /// 基础信息
        SysOss sysOss = parseInfo(file);

        if (storageConfig.getEnable().equals(SystemConstant.LOCAL)) {
            String f1 = DateUtil.format(new Date(), "yyyy/MM/dd");

            File folder = new File(System.getProperty("user.dir") +  System.getProperty("file.separator") + "/file/" + f1);
            folder.mkdirs();
            File dest = new File(folder, sysOss.getName());
            try {
                file.transferTo(dest);
            } catch (IOException e) {
                e.printStackTrace();
                log.error("文件上传失败", e);
            }
           // String path = folder + "/" + sysOss.getName();
            String path = "/file/" + f1 + "/" + sysOss.getName();
            sysOss.setPath(path);

            InetAddress ia = InetAddress.getLocalHost();

            String computerIp = ia.getHostAddress();

            sysOss.setBucket(computerIp);
        }
        /// 本地存储
        if (storageConfig.getEnable().equals(SystemConstant.ALIYUN)) {
            /// 阿里存储
            String filePath = cloudStorageService.upload(file);
            sysOss.setPath(filePath);
            sysOss.setBucket(cloudStorageConfig.getBucketName());
        }
        // aws 存储
        if (storageConfig.getEnable().equals(SystemConstant.AWS)) {
            String filePath = amazonServiceImpl.uploadFile(file);
            /// 附加信息
            sysOss.setPath(filePath);
            sysOss.setBucket(amazonServiceImpl.bucketName);
        }
        // qiniu存储
        if (storageConfig.getEnable().equals(SystemConstant.QINIU)) {
            String filePath = qiNiuService.uploadFile(file);
            /// 附加信息
            sysOss.setPath(filePath);
            sysOss.setBucket(amazonServiceImpl.bucketName);
        }
        // aws 存储
        if (storageConfig.getEnable().equals(SystemConstant.AWS_CN)) {
            String filePath = amazonChinaServiceImpl.uploadFile(file);
            /// 附加信息
            sysOss.setPath(filePath);
            sysOss.setBucket(amazonChinaServiceImpl.bucketName);
        }
        /// 存 储 记 录
        sysOssRepository.insert(sysOss);

        return sysOss.getPath();
    }

    /**
     * 解析文件信息
     *
     * @param file 文件实体
     * @return {@link SysOss} 文件信息
     */
    public SysOss parseInfo(MultipartFile file) {
        String fileName = file.getOriginalFilename();

        SysOss sysOss = new SysOss();
        sysOss.setLocation(storageConfig.getEnable());
        sysOss.setName(file.getOriginalFilename());
        sysOss.setSuffix(fileName.substring(fileName.lastIndexOf(".")));
        sysOss.setSize(file.getSize() / 1024);

        return sysOss;
    }
}
