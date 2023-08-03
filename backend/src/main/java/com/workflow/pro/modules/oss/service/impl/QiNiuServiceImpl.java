package com.workflow.pro.modules.oss.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.workflow.pro.modules.oss.domain.QiNiuToken;
import com.workflow.pro.modules.oss.repository.SysOssRepository;
import com.workflow.pro.modules.oss.service.QiNiuServiceConfig;
import com.workflow.pro.modules.oss.service.config.QiNiuConfig;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class QiNiuServiceImpl implements QiNiuServiceConfig {
    // @Resource
    // private SysOssServiceImpl SysOssServiceImpl;


    @Resource
    private SysOssRepository sysOssRepository;

    @Resource
    private QiNiuConfig qiNiuConfig;

    String accessKey;

    String secretKey;

    String bucket;

    String domain;

    String srv;

    String upload_host;

    //默认不指定key的情况下，以文件内容的hash值作为文件名
    String key = null;

    Auth auth;

    UploadManager uploadManager;

    @PostConstruct
    private void initializeQiNiu() {
        this.accessKey = qiNiuConfig.getAccessKey();
        this.secretKey = qiNiuConfig.getSecretKey();
        this.bucket = qiNiuConfig.getBucketName();
        this.domain = qiNiuConfig.getEndpoint();
        this.srv = qiNiuConfig.getSrv();
        this.upload_host = qiNiuConfig.getUpload_host();


        this.auth = Auth.create(accessKey, secretKey);
        //第二种方式: 自动识别要上传的空间(bucket)的存储区域是华东、华北、华南。
        Configuration c = new Configuration(Region.region0());
        //创建上传对象
        this.uploadManager = new UploadManager(c);

    }


    public String uploadFile(MultipartFile multipartFile) {
        String fileUrl = "";
        String token = auth.uploadToken(bucket);
        try {
            File file = convertMultiPartToFile(multipartFile);
            String fileName = generateFileName(multipartFile);
            fileUrl = "https:" + domain + fileName;
            //打印返回的信息
            Response put = uploadManager.put(file, fileName, token);
            file.delete();
            //System.out.println(put.bodyString());
            /// 附加信息
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        /// 访问路径
        return fileUrl;
    }


    @Override
    public String upload(MultipartFile multipartFile) {
        return "";
        // SysOss sysOss = SysOssServiceImpl.parseInfo(multipartFile);
        // String token = auth.uploadToken(bucket);
        // key = sysOss.getName();
        // try {
        //
        //     File file = convertMultiPartToFile(multipartFile);
        //     String fileName = generateFileName(multipartFile);
        //
        //
        //     String fileUrl = "https:" + domain + fileName;
        //     //打印返回的信息
        //     Response put = uploadManager.put(file, fileName, token);
        //     file.delete();
        //     System.out.println(put.bodyString());
        //     /// 附加信息
        //     sysOss.setPath(fileUrl);
        //     sysOss.setBucket(bucket);
        // }
        // catch (IOException e) {
        //     // 请求失败时打印的异常的信息
        //
        // }
        // sysOssRepository.insert(sysOss);

        // return sysOss.getPath();
    }

    /**
     * 解析文件信息
     *
     * @return {@link QiNiuToken} 文件信息
     */
    @Override
    public QiNiuToken token() {
        Auth auth = Auth.create(accessKey, secretKey);
        QiNiuToken token = new QiNiuToken();

        token.setToken(auth.uploadToken(bucket));
        token.setSrv(srv);
        token.setAccess_host(domain);
        token.setUpload_host(upload_host);
        return token;
    }

    //密钥配置
    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    public String getUpToken() {
        return auth.uploadToken(bucket);
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    private String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
    }
}
