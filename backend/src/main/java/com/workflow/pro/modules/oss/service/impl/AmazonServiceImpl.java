package com.workflow.pro.modules.oss.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClientBuilder;
import com.amazonaws.services.securitytoken.model.Credentials;
import com.amazonaws.services.securitytoken.model.GetSessionTokenRequest;
import com.workflow.pro.modules.oss.domain.CommonToken;
import com.workflow.pro.modules.oss.service.AmazonService;
import com.workflow.pro.modules.oss.service.config.AwsConfig;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AmazonServiceImpl implements AmazonService {

    private AmazonS3 s3client;

    @Resource
    private AwsConfig awsConfig;

    String region = "";

    String bucketName = "";
    // String accessKey = "AKIAX5FB23HO47FJNOFT";
    // String secretKey = "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";
    String accessKey = "";

    String secretKey = "";

    String endpoint = "";


    @PostConstruct
    private void initializeAmazon() {
        this.accessKey = awsConfig.getAccessKey();
        this.secretKey = awsConfig.getSecretKey();
        this.region = awsConfig.getRegion();
        this.bucketName = awsConfig.getBucketName();
        this.endpoint = awsConfig.getEndpoint();
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        this.s3client = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.fromName(region))
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();

    }


    public String uploadFile(MultipartFile multipartFile) {
        String fileUrl = "";
        try {
            System.out.print("111111111111111111111111111111111");
            System.out.print(this.awsConfig.toString());
            File file = convertMultiPartToFile(multipartFile);
            String fileName = generateFileName(multipartFile);
            uploadFileTos3bucket(fileName, file);
            file.delete();
            fileUrl = "https://" + bucketName + "." + endpoint + "/" + fileName;

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        /// 访问路径
        return fileUrl;
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

    private void uploadFileTos3bucket(String fileName, File file) {
        s3client.putObject(new PutObjectRequest(bucketName, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    public String deleteFileFromS3Bucket(String fileUrl) {
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        s3client.deleteObject(new DeleteObjectRequest(bucketName, fileName));
        return "Successfully deleted";
    }

    public List<String> listFiles() {
        ListObjectsRequest listObjectsRequest =
                new ListObjectsRequest()
                        .withBucketName(bucketName);

        List<String> keys = new ArrayList<>();
        ObjectListing objects = s3client.listObjects(listObjectsRequest);
        while (true) {
            List<S3ObjectSummary> summaries = objects.getObjectSummaries();
            if (summaries.size() < 1) {
                break;
            }
            for (S3ObjectSummary item : summaries) {
                if (!item.getKey().endsWith("/"))
                    keys.add(item.getKey());
            }

            objects = s3client.listNextBatchOfObjects(objects);
        }
        return keys;
    }

    @Override
    public CommonToken token() {
        //获取到的 token 返回前端
        //创建连接
        AWSSecurityTokenService stsClient = AWSSecurityTokenServiceClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .withRegion(region)
                .build();
        //获取sessionToken实体
        GetSessionTokenRequest getSessionTokenRequest = new GetSessionTokenRequest().withDurationSeconds(3000);
        //创建请求
        Credentials credentials = stsClient.getSessionToken(getSessionTokenRequest).getCredentials();
        //获取到的 token 返回前端

        String s = credentials.getSessionToken();

        CommonToken token = new CommonToken();
        token.setToken(s);
        token.setAccess_host("");
        URL url = s3client.getUrl(bucketName, "1662367470587-backiee-41897.jpg");

        token.setExpiration(credentials.getExpiration());
        return token;
    }

}