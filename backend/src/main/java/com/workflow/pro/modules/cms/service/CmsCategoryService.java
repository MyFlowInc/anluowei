package com.workflow.pro.modules.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.modules.cms.domain.CmsCategory;
import com.workflow.pro.modules.cms.param.CmsCategoryRequest;

import java.util.List;
import java.util.Map;

/**
 * 分类Service接口
 *
 * @author Some
 * @date 2022-08-05
 */
public interface
CmsCategoryService extends IService<CmsCategory> {

    /**
     * 查询分类树形结构列表
     */
    List<CmsCategory> tree();
    /**
     * 根据 parent 查询分类
     * @param parent 父级编号
     * */
    List<CmsCategory> treeAndChildren(String parent);

    /**
     * 获取列表
     *
     * @param request 查询参数
     * @return {@link CmsCategory}
     */
    List<CmsCategory> list(CmsCategoryRequest request);

    /**
     * @param request 查询参数 分页
     * @return {@link CmsCategory}
     */
    PageResponse<CmsCategory> page(CmsCategoryRequest request);


    /**
     * 检测用户存在
     *
     * @param title 分类名
     * @return {@link Boolean}
     */
    Boolean exist(String title);


    /**
     * 查询分类树形结构列表
     */
    List<CmsCategory> getRecent();

    List<CmsCategory> selectCateByParentId(String parentId);

    Map<String, Object> totalCateInfoAll();

}
