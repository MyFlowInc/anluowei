package com.workflow.pro.modules.apitable.controller;


import com.amazonaws.services.dynamodbv2.xspec.S;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sun.jna.platform.unix.solaris.LibKstat.KstatNamed.UNION.STR;
import com.workflow.pro.common.constant.ControllerConstant;
import com.workflow.pro.common.context.BeanContext;
import com.workflow.pro.common.context.UserContext;
import com.workflow.pro.common.secure.uutoken.SecureUserToken;
import com.workflow.pro.common.secure.uutoken.SecureUserTokenService;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.modules.apitable.domain.ApitableDeveloper;
import com.workflow.pro.modules.apitable.domain.ApitableNodeShareSetting;
import com.workflow.pro.modules.apitable.domain.OmsOrder;
import com.workflow.pro.modules.apitable.param.ApitableDeveloperRequest;
import com.workflow.pro.modules.apitable.param.ApitableNodeShareSettingRequest;
import com.workflow.pro.modules.apitable.service.IApitableDeveloperService;
import com.workflow.pro.modules.apitable.service.IApitableNodeShareSettingService;
import com.workflow.pro.modules.apitable.service.IDatasheetServiceImpl;
import com.workflow.pro.modules.apitable.utils.IdUtil;
import com.workflow.pro.modules.sys.domain.SysUser;
import com.workflow.pro.modules.sys.param.LoginSuccess;
import io.swagger.annotations.Api;

import org.springframework.web.bind.annotation.*;

import com.workflow.pro.modules.apitable.service.IApitableDatasheetService;

import com.workflow.pro.modules.apitable.param.ApitableDatasheetRequest;
import com.workflow.pro.modules.apitable.domain.ApitableDatasheet;

import javax.annotation.Resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import io.swagger.annotations.ApiOperation;
import com.workflow.pro.common.web.base.BaseController;
import com.workflow.pro.common.aop.annotation.Log;
import com.workflow.pro.common.web.domain.Result;

/**
 * 数据格Controller
 *
 * @author some
 * @date 2023-05-22
 */
@Api(tags = { "数据格" })
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "apitableDatasheet")
public class ApitableDatasheetController extends BaseController {
    @Resource
    private SecureUserTokenService customUserDetailsTokenService;

    @Resource
    private IApitableDatasheetService apitableDatasheetService;

    @Resource
    private IApitableNodeShareSettingService shareSettingService;

    @Resource
    private IApitableDeveloperService developerService;

    /**
     * 用 户 服 务
     */
    @Resource
    private UserContext userContext;

    @Resource
    private IDatasheetServiceImpl datasheetService;

    /**
     * 查询数据格列表
     * @param request 查询参数
     */
    @GetMapping("/list")
    @Log(title = "数据格")
    @ApiOperation(value = "数据格")
    public Result list(ApitableDatasheetRequest request) {
        return success(apitableDatasheetService.list(request));
    }

    /**
     * 查询数据格分页列表
     * @param request 查询参数
     */
    @GetMapping("/page")
    @Log(title = "数据格列表")
    @ApiOperation(value = "数据格列表")
    public Result page(ApitableDatasheetRequest request) {

        PageResponse<ApitableDatasheet> page = apitableDatasheetService.page(request);


        return success(page);
    }


    /**
     * 新增数据格类型
     *
     * @param apitableDatasheet apitableDatasheet 实体
     */
    @PostMapping("save")
    @Log(title = "新增数据格")
    @ApiOperation(value = "数据格")
    public Result save(@RequestBody ApitableDatasheet apitableDatasheet) throws IOException {
        String userId = userContext.getUserId();
        // 空间id 和userid 默认取
        apitableDatasheet.setSpaceId(userId);
        apitableDatasheet.setCreateBy(userContext.getUsername());

        String disId = IdUtil.createDstId();
        datasheetService.create(userId, "default", disId, apitableDatasheet.getDstName(), "");
        return success();
    }

    /**
     * 修改数据格
     * @param apitableDatasheet apitableDatasheet 实体
     */
    @PutMapping("edit")
    @Log(title = "修改数据格")
    @ApiOperation(value = "修改数据格")
    public Result edit(@RequestBody ApitableDatasheet apitableDatasheet) {
        return auto(apitableDatasheetService.updateById(apitableDatasheet));
    }

    /**
     * 删除数据格类型
     *
     * @param id apitableDatasheet编号
     */
    @DeleteMapping("remove")
    @Log(title = "删除数据格")
    @ApiOperation(value = "删除数据格")
    public Result remove(@RequestParam String id) {
        return auto(apitableDatasheetService.removeById(id));
    }

    /**
     * 删除数据格类型
     *
     * @param ids apitableDatasheet 实体
     */
    @DeleteMapping("removeBatch")
    @Log(title = "批量删除")
    @ApiOperation(value = "批量删除")
    public Result removeBatch(@RequestParam List<String> ids) {
        return auto(apitableDatasheetService.removeByIds(ids));
    }

    /**
     * 分享的链接token
     */
    @PostMapping("shareToken")
    @Log(title = "分享")
    public Result shareToken(@RequestBody ApitableNodeShareSetting request) {
        String userId = userContext.getUserId();
        request.setCreateBy(userId);
        String s = shareSettingService.makeShareCode(request);
        Map<String, String> mymap = new HashMap<String, String>();
        mymap.put("code", s);
        return Result.success(s);
    }


    /**
     * 分享的链接token
     *
     */
    @PostMapping("verifyCode")
    @Log(title = "分享")
    public Result verifyCode(@RequestParam(value = "code") String code, @RequestParam(value = "isVisitor", defaultValue = "0") Integer isVisitor) {
        ApitableNodeShareSetting apitableNodeShareSetting = shareSettingService.getOne(new QueryWrapper<ApitableNodeShareSetting>().eq("share_id", code));
        if (apitableNodeShareSetting == null) {
            return failure("code error");
        }

        String disId = apitableNodeShareSetting.getDstId();

        ApitableDatasheet dstInfo = apitableDatasheetService.getOne(new QueryWrapper<ApitableDatasheet>().eq("dst_id", disId));
        Map<String, Object> mymap = new HashMap<String, Object>();
        mymap.put("sheetInfo", dstInfo);
        if (isVisitor == 1) {
            //造个token
            SysUser exist = new SysUser();
            exist.setUsername("游客");
            exist.setEnable(true);
            exist.setUsername("");
            exist.setId("xxxxxxx");
            exist.setAvatar("https://portrait.gitee.com/uploads/avatars/user/2813/8441097_shaynas_1610801433.png!avatar200");
            exist.setNickname("游客");
            exist.setLocked(false);
            exist.setDeleted(false);
            SecureUserToken userToken = customUserDetailsTokenService.createToken(exist);
            String tokenKey = customUserDetailsTokenService.saveToken(userToken);
            String tokenValue = userToken.getToken();
            LoginSuccess success = new LoginSuccess();
            success.setToken(tokenValue);
            success.setTokenKey(tokenKey);
            mymap.put("userToken", success);
        }
        else {
            mymap.put("userToken", null);

        }
        return Result.success(mymap);
    }

}
