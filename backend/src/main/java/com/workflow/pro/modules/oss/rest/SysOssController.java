package com.workflow.pro.modules.oss.rest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.workflow.pro.common.aop.annotation.Log;
import com.workflow.pro.common.web.base.BaseController;
import com.workflow.pro.common.web.domain.Result;
import com.workflow.pro.modules.oss.param.SysOssRequest;
import com.workflow.pro.modules.oss.service.AmazonService;
import com.workflow.pro.modules.oss.service.QiNiuServiceConfig;
import com.workflow.pro.modules.oss.service.SysOssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.*;

import com.workflow.pro.common.constant.ControllerConstant;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件控制器
 * <p>
 * Author: SOME
 * CreateTime: 2022/03/28
 */
@Api(tags = { "文件" })
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "oss")
public class SysOssController extends BaseController {


    @Resource
    private QiNiuServiceConfig qiNiuServiceConfig;

    @Resource
    private SysOssService sysOssService;


    /**
     * 查询文件列表
     *
     * @param request 查询参数
     * @return {@link String}
     */
    @GetMapping("page")
    @Log(title = "文件列表")
    @ApiOperation(value = "文件列表")
    public Result page(SysOssRequest request) {
        return success(sysOssService.page(request));
    }

    /**
     * 文件上传
     *
     * @param file 上传文件
     * @return {@link String}
     */
    @PostMapping("upload")
    @ApiOperation(value = "文件上传")
    public Result upload(@RequestParam("file") MultipartFile file) throws UnknownHostException {
        if (file.isEmpty()) {
            return failure();
        }
        Map<String, String> mymap1 = new HashMap<String, String>();
        mymap1.put("url", sysOssService.upload(file));
        return success(mymap1);
    }

    /**
     * 文件上传
     *
     * @param request 上传文件
     */
    @PostMapping("multipartUpload")
    @ApiOperation(value = "多文件上传")
    public Result multipartUpload(HttpServletRequest request) {
        return success(sysOssService.upload(((MultipartHttpServletRequest) request).getFiles("file")));
    }

    /**
     * 获取七牛token
     *
     * @return {@link Result}
     */
    @GetMapping("qiniu/token")
    @Log(title = "七牛token")
    @ApiOperation(value = "获取七牛token")
    public Result token() {
        return success(qiNiuServiceConfig.token());
    }

    /**
     * 获取七牛token
     *
     * @return {@link Result}
     */
    @PostMapping("qiniu/upload")
    @Log(title = "七牛upload")
    @ApiOperation(value = "qiniu/upload")
    public Result uploadQiNiu(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return failure();
        }
        Map<String, String> mymap1 = new HashMap<String, String>();
        mymap1.put("url", qiNiuServiceConfig.upload(file));
        return success(mymap1);
    }


    @Resource
    private AmazonService amazonService;

    @PostMapping("/uploadFile")
    public Result uploadFile(MultipartFile file) {
        return success(this.amazonService.uploadFile(file));
    }

    @DeleteMapping("/deleteFile")
    public Result deleteFile(@RequestParam(value = "url") String fileUrl) {
        return success(this.amazonService.deleteFileFromS3Bucket(fileUrl));
    }

    @GetMapping("/getFileList")
    public Result<String> getFileList() {
        return success(this.amazonService.listFiles());
    }

    @GetMapping("/getToken")
    public Result<String> getToken() {
        return success(this.amazonService.token());
    }

}
