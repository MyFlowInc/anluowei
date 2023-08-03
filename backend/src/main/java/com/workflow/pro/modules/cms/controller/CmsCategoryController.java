package com.workflow.pro.modules.cms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.workflow.pro.common.aop.annotation.Log;
import com.workflow.pro.common.web.base.BaseController;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.domain.Result;
import com.workflow.pro.modules.cms.domain.CmsCategory;
import com.workflow.pro.modules.cms.domain.CmsContent;
import com.workflow.pro.modules.cms.domain.SysActivity;
import com.workflow.pro.modules.cms.param.*;
import com.workflow.pro.modules.cms.service.CmsCategoryService;
import com.workflow.pro.modules.cms.service.ICmsContentService;
import com.workflow.pro.modules.cms.service.ISysActivityUserService;
import com.workflow.pro.modules.cms.service.impl.SysActivityServiceImpl;
import com.workflow.pro.modules.oss.domain.SysOss;
import com.workflow.pro.modules.oss.param.SysOssRequest;
import com.workflow.pro.modules.oss.service.impl.SysOssServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * 分类Controller
 *
 * @author Some
 * @date 2022-08-05
 */
@Api(tags = {"分类"})
@RestController
@RequestMapping("/cms/category")
public class CmsCategoryController extends BaseController {

    private String prefix = "cms/category";

    @Resource
    private CmsCategoryService cmsCategoryService;

    @Resource
    private ICmsContentService cmsContentService;

    /**
     * 查询分类列表
     *
     * @param request 查询参数
     * @return {@link com.workflow.pro.common.web.domain.Result}
     */
    @GetMapping("page")
    @Log(title = "分类列表")
    @ApiOperation(value = "分类列表")
    public Result page(CmsCategoryRequest request) {
        return success(cmsCategoryService.page(request));
    }


    /**
     * 查询分类
     *
     * @param request 查询参数
     */
    @GetMapping("list")
    @Log(title = "分类列表")
    @ApiOperation(value = "分类列表")
    public Result list(CmsCategoryRequest request) {
        return success(cmsCategoryService.list(request));
    }

    /**
     * 删除分类
     *
     * @param id 分类编号
     */
    @DeleteMapping("remove")
    @Log(title = "分类删除")
    @ApiOperation(value = "分类删除")
    public Result remove(@RequestParam String id) {
        return auto(cmsCategoryService.removeById(id));
    }

    /**
     * 分类详情
     *
     * @param userId 分类编号
     */
    @GetMapping("info")
    @Log(title = "分类详情")
    @ApiOperation(value = "分类详情")
    public Result info(String userId) {
        return success(cmsCategoryService.getById(userId));
    }


    /**
     * 新增分类
     *
     * @param cmsCategory 分类实体
     */
    @PostMapping("save")
    @Log(title = "分类新增")
    @ApiOperation(value = "分类新增")
    public Result save(@RequestBody CmsCategory cmsCategory) {
//        if (cmsCategoryService.exist(cmsCategory.getCategoryTitle())) {
//            return Result.success("已存在");
//        }
        return auto(cmsCategoryService.save(cmsCategory));
    }

    /**
     * 修改分类
     *
     * @param cmsCategory 分类实体
     */
    @PutMapping("edit")
    @Log(title = "分类修改")
    @ApiOperation(value = "分类修改")
    public Result edit(@RequestBody CmsCategory cmsCategory) {
        return auto(cmsCategoryService.updateById(cmsCategory));
    }

    /**
     * 查询分类列表
     *
     * @return {@link Result}
     */
    @GetMapping("tree")
    @Log(title = "查询分类")
    @ApiOperation(value = "查询分类")
    public Result tree() {
        return success(cmsCategoryService.tree());
    }

    /**
     * 查询分类列表
     *
     * @return {@link Result}
     */
    @GetMapping("treeAndChildren")
    @Log(title = "查询父级别树结构分类")
    @ApiOperation(value = "查询父级别树结构分类")
    public Result treeAndChildren() {
        return null;
        //return success(cmsCategoryService.treeAndChildren());
    }

    /**
     * 查询分类列表
     *
     * @return {@link Result}
     */
    @GetMapping("selectCateByParentId")
    @Log(title = "查询父级别树结构分类")
    @ApiOperation(value = "查询父级别树结构分类")
    public Result selectCateByParentId(@RequestParam(value = "parent", required = false) String parent) {
        //return null;
        if (Objects.equals(parent, "") || parent == null) {
            parent = String.valueOf(0);
        }
        return success(cmsCategoryService.selectCateByParentId(parent));
    }


    @Resource
    private ISysActivityUserService sysActivityUserService;
    @Resource
    private SysActivityServiceImpl sysActivityService;
    @Resource
    private SysOssServiceImpl sysOssService;

    @GetMapping("getRecentSearchTotal")
    @Log(title = "统计数据")
    @ApiOperation(value = "统计数据")
    public Result getRecentSearchTotal() {
        Map<String, Object> mymap = new HashMap<>();
        Map<String, Object> cateMap = new HashMap<>();
        Map<String, Object> ossMap = new HashMap<>();
        Map<String, Object> contentMap = new HashMap<>();
        Map<String, Object> actMap = new HashMap<>();

        //内容已经点击数量
        cateMap.put("categoryDetail", cmsCategoryService.totalCateInfoAll());
        cateMap.put("categoryTotal", cmsCategoryService.page(new CmsCategoryRequest()).getTotal());
        //content
        PageResponse<CmsContent> page1 = cmsContentService.page(new CmsContentRequest());
        contentMap.put("contentDetail", page1.getRecord());
        contentMap.put("contentTotal", page1.getTotal());
        //act
        PageResponse<SysActivity> page = sysActivityService.page(new SysActivityRequest());
        actMap.put("actDetail", page.getRecord());
        actMap.put("actTotal", page.getTotal());
        //oss
        List<SysOss> listS = sysOssService.listCount(new SysOssRequest());
        Map<String, Object> mymapOss = new HashMap<>();
        List<OssNeed> listp=new ArrayList<>();
        for (SysOss s : listS) {
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("suffix", s.getSuffix());
            long count = sysOssService.count(wrapper);
            OssNeed o=new OssNeed();
            o.setCount(count);
            o.setSuffix(s.getSuffix());
            listp.add(o);
        }

        ossMap.put("ossDetail", listp);
        ossMap.put("ossTotal",  sysOssService.count());

        mymap.put("act",actMap);
        mymap.put("category",cateMap);
        mymap.put("content",contentMap);
        mymap.put("oss",ossMap);
        //活动数量 以及参与人数量
        return success(mymap);
    }
}



