package com.workflow.pro.modules.apitable.controller;


import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.workflow.pro.common.aop.annotation.Log;
import com.workflow.pro.common.constant.ControllerConstant;
import com.workflow.pro.common.context.UserContext;
import com.workflow.pro.common.web.base.BaseController;
import com.workflow.pro.common.web.domain.Result;
import com.workflow.pro.modules.apitable.domain.ApitableDatasheet;
import com.workflow.pro.modules.apitable.domain.ApitableDeveloper;
import com.workflow.pro.modules.apitable.domain.ApitableInviteRecord;
import com.workflow.pro.modules.apitable.exception.BusinessExceptionNew;
import com.workflow.pro.modules.apitable.param.ApitableDeveloperRequest;
import com.workflow.pro.modules.apitable.service.IApitableDeveloperService;
import com.workflow.pro.modules.apitable.service.IApitableInviteRecordService;
import com.workflow.pro.modules.apitable.service.impl.ApitableDatasheetServiceImpl;
import com.workflow.pro.modules.sys.param.SysUserRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 格协作者Controller
 *
 * @author some
 * @date 2023-06-16
 */
@Api(tags = { "格协作者" })
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "apitableDeveloper")
public class ApitableDeveloperController extends BaseController {

    @Resource
    private IApitableDeveloperService apitableDeveloperService;

    /**
     * 用 户 服 务
     */
    @Resource
    private UserContext userContext;

    /**
     * 用 户 服 务
     */
    @Resource
    private IApitableInviteRecordService inviteRecordService;

    @Resource
    private ApitableDatasheetServiceImpl datasheet;

    /**
     * 查询用户
     *
     * @param request 查询参数
     */
    @GetMapping("/user/list/all")
    @Log(title = "用户列表")
    @ApiOperation(value = "用户列表")
    public Result userList(SysUserRequest request) {
        //当前邀请的用户id
        String userId = userContext.getUserId();
        if (request.getDstId() == null) {
            throw new BusinessExceptionNew(3001, "dstId必填");
        }
        return success(apitableDeveloperService.userList(request));
    }

    /**
     * 查询格协作者列表
     * @param request 查询参数
     */
    @GetMapping("/list")
    @Log(title = "格协作者")
    @ApiOperation(value = "格协作者")
    public Result list(ApitableDeveloperRequest request) {
        return success(apitableDeveloperService.list(request));
    }

    /**
     * 查询格协作者分页列表
     * @param request 查询参数
     */
    @GetMapping("/page")
    @Log(title = "格协作者列表")
    @ApiOperation(value = "格协作者列表")
    public Result page(ApitableDeveloperRequest request) {
        request.setEnable(1);
        return success(apitableDeveloperService.page(request));
    }


    /**
     * 新增格协作者类型
     *
     * @param apitableDeveloper apitableDeveloper 实体
     */
    @PostMapping("save")
    @ApiOperation(value = "格协作者")
    public Result save(@RequestBody ApitableDeveloper apitableDeveloper) throws BusinessExceptionNew {
        // 添加邀请记录关于谁邀请 和邀请谁的记录
        if (apitableDeveloper.getUserId() == null) {
            throw new BusinessExceptionNew(3015, "邀请人必填");
        }
        if (apitableDeveloper.getDstId() == null) {
            throw new BusinessExceptionNew(3016, "表格id必填");
        }
        //当前邀请的用户id
        String userId = userContext.getUserId();
        ApitableInviteRecord record = new ApitableInviteRecord();
        record.setAccepter(apitableDeveloper.getUserId());
        String name = userContext.getNickName() != null ? userContext.getNickName() : userContext.getUsername();
        ApitableDatasheet dst_id = datasheet.getOne(new QueryWrapper<ApitableDatasheet>().eq("dst_id", apitableDeveloper.getDstId()), false);
        if (Objects.equals(dst_id.getCreateBy(), apitableDeveloper.getUserId())) {
            throw new BusinessExceptionNew(3017, "不能邀请创建者");
        }


        if (record.getContent() == "" || record.getContent() == null) {
            record.setContent("您被分配了:《" + dst_id.getDstName() + "》 " + apitableDeveloper.getInterviewUserName() + " 的面试");
        }
        //当前用户
        record.setInviter(userId);
        record.setType(0);
        record.setDstId(apitableDeveloper.getDstId());
        record.setCreateBy(userId);
        inviteRecordService.saveOrUpdate(record);
        if (apitableDeveloper.getEnable() == null) {
            apitableDeveloper.setEnable(0);
        }
        return auto(apitableDeveloperService.saveOrUpdate(apitableDeveloper));
    }

    @PostMapping("enable")
    @Log(title = "修改格协作者")
    @ApiOperation(value = "修改格协作者")
    public Result enable(@RequestBody ApitableDeveloper apitableDeveloper) {
        if (apitableDeveloper.getUserId() == null || Objects.equals(apitableDeveloper.getUserId(), "")) {
            return Result.failure(3001, "userId必传");
        }
        if (Objects.equals(apitableDeveloper.getDstId(), "") || apitableDeveloper.getDstId() == null) {
            return Result.failure(3002, "dstId必传");
        }
        ApitableDatasheet one = datasheet.getOne(new QueryWrapper<ApitableDatasheet>().eq("dst_id", apitableDeveloper.getDstId()), true);
        if (one == null) {
            throw new BusinessExceptionNew(4001, "dstId不存在");
        }

        return auto(apitableDeveloperService.enableDeveloper(apitableDeveloper.getDstId(), apitableDeveloper.getUserId()));
    }

    /**
     * 修改格协作者
     * @param apitableDeveloper apitableDeveloper 实体
     */
    @PutMapping("edit")
    @Log(title = "修改格协作者")
    @ApiOperation(value = "修改格协作者")
    public Result edit(@RequestBody ApitableDeveloper apitableDeveloper) {
        return auto(apitableDeveloperService.updateById(apitableDeveloper));
    }

    /**
     * 删除格协作者类型
     *
     * @param id apitableDeveloper编号
     */
    @DeleteMapping("remove")
    @Log(title = "删除格协作者")
    @ApiOperation(value = "删除格协作者")
    public Result remove(@RequestParam String id) {
        return auto(apitableDeveloperService.removeById(id));
    }

    /**
     * 删除格协作者类型
     *
     * @param ids apitableDeveloper 实体
     */
    @DeleteMapping("removeBatch")
    @Log(title = "批量删除")
    @ApiOperation(value = "批量删除")
    public Result removeBatch(@RequestParam List<String> ids) {
        return auto(apitableDeveloperService.removeByIds(ids));
    }
}
