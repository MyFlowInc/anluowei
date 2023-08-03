package com.workflow.pro.modules.job.rest;

import com.workflow.pro.common.aop.annotation.Log;
import com.workflow.pro.common.web.base.BaseController;
import com.workflow.pro.common.web.domain.Result;
import com.workflow.pro.modules.job.service.SysJobLogService;
import com.workflow.pro.common.constant.ControllerConstant;
import com.workflow.pro.modules.job.param.SysJobLogRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * 任务日志控制器
 *
 * Author: SOME
 * CreateTime: 2022/04/15
 * */
@Api(tags = {"任务"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "job/log")
public class SysJobLogController extends BaseController {

    @Resource
    private SysJobLogService sysJobLogService;

    /**
     * 查询任务日志列表 (分页)
     *
     * @param request 查询参数
     */
    @GetMapping("page")
    @Log(title = "查询日志")
    @ApiOperation(value = "任务日志")
    public Result page(SysJobLogRequest request){
        return success(sysJobLogService.page(request));
    }

}
