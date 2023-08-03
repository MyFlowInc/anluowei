package com.workflow.pro.modules.not.rest;

import com.workflow.pro.common.web.base.BaseController;
import com.workflow.pro.common.web.domain.Result;
import com.workflow.pro.modules.not.service.SysAnnounceUserService;
import io.swagger.annotations.Api;
import com.workflow.pro.common.constant.ControllerConstant;
import com.workflow.pro.modules.not.param.SysAnnounceUserRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * 用户公告控制器
 *
 * Author: SOME
 * CreateTime: 2022/03/28
 * */
@Api(tags = {"公告"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "announce/user")
public class SysAnnounceUserController extends BaseController {

    @Resource
    private SysAnnounceUserService sysAnnounceUserService;

    @GetMapping("page")
    public Result page(SysAnnounceUserRequest request){
        return success(sysAnnounceUserService.page(request));
    }

}
