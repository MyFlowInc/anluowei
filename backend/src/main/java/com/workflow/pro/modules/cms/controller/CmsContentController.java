package com.workflow.pro.modules.cms.controller;

import com.workflow.pro.common.aop.annotation.Log;
import com.workflow.pro.common.web.base.BaseController;
import com.workflow.pro.common.web.domain.Result;
import com.workflow.pro.modules.cms.domain.CmsContent;
import com.workflow.pro.modules.cms.param.CmsContentRequest;
import com.workflow.pro.modules.cms.service.ICmsContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 文章Controller
 *
 * @author Some
 * @date 2022-08-05
 */
@Api(tags = {"文章"})
@RestController
@RequestMapping("/cms/content")
public class CmsContentController extends BaseController {

    private String prefix = "cms/content";

    @Autowired
    private ICmsContentService cmsContentService;

    /**
     * 查询文章列表
     *
     * @param request 查询参数
     * @return {@link com.workflow.pro.common.web.domain.Result}
     */
    @GetMapping("page")
    @Log(title = "文章列表")
    @ApiOperation(value = "文章列表")
    public Result page(CmsContentRequest request) {
        return success(cmsContentService.page(request));
    }

    /**
     * 查询文章
     *
     * @param request 查询参数
     */
    @GetMapping("list")
    @Log(title = "文章列表")
    @ApiOperation(value = "文章列表")
    public Result list(CmsContentRequest request) {
        return success(cmsContentService.list(request));
    }

    /**
     * 删除文章
     *
     * @param id 文章编号
     */
    @DeleteMapping("remove")
    @Log(title = "文章删除")
    @ApiOperation(value = "文章删除")
    public Result remove(@RequestParam String id) {
        return auto(cmsContentService.removeById(id));
    }

    /**
     * 文章详情
     *
     * @param userId 文章编号
     */
    @GetMapping("info")
    @Log(title = "文章详情")
    @ApiOperation(value = "文章详情")
    public Result info(String userId) {
        return success(cmsContentService.getById(userId));
    }


    /**
     * 新增文章
     *
     * @param CmsContent 文章实体
     */
    @PostMapping("save")
    @Log(title = "文章新增")
    @ApiOperation(value = "文章新增")
    public Result save(@RequestBody CmsContent CmsContent) {
        return auto(cmsContentService.save(CmsContent));
    }

    /**
     * 修改文章
     *
     * @param CmsContent 文章实体
     */
    @PutMapping("edit")
    @Log(title = "文章修改")
    @ApiOperation(value = "文章修改")
    public Result edit(@RequestBody CmsContent CmsContent) {
        return auto(cmsContentService.updateById(CmsContent));
    }
}
