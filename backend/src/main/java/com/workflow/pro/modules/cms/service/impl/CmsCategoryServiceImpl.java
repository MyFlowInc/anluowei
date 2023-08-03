package com.workflow.pro.modules.cms.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.workflow.pro.common.context.UserContext;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;
import com.workflow.pro.modules.cms.domain.CmsCategoryPower;
import com.workflow.pro.modules.cms.param.CmsCategoryPowerRequest;
import com.workflow.pro.modules.cms.param.CmsCategoryRequest;
import com.workflow.pro.modules.cms.param.CountNeed;
import com.workflow.pro.modules.sys.domain.SysUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.workflow.pro.modules.cms.mapper.CmsCategoryMapper;
import com.workflow.pro.modules.cms.domain.CmsCategory;
import com.workflow.pro.modules.cms.service.CmsCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;

/**
 * 分类Service业务层处理
 *
 * @author Some
 */
@Service
public class CmsCategoryServiceImpl extends ServiceImpl<CmsCategoryMapper, CmsCategory> implements CmsCategoryService {

    @Resource
    CmsCategoryMapper cmsCategoryMapper;

    @Resource
    CmsCategoryPowerServiceImpl cmsCategoryPowerService;

    /**
     * 用 户 服 务
     */
    @Resource
    private UserContext userContext;

    @Override
    public List<CmsCategory> list(CmsCategoryRequest request) {


        return cmsCategoryMapper.selectCmsCategoryList(request);

    }

    @Override
    public List<CmsCategory> tree() {

        List<CmsCategory> categoryList = cmsCategoryMapper.selectCate();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //增加分类权限控制 todo
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String dept = "1";
        if (user instanceof SysUser) {
            dept = ((SysUser) user).getDeptId();
        }
        CmsCategoryPowerRequest pow = new CmsCategoryPowerRequest();
        pow.setDeptId(dept);
        List<CmsCategoryPower> powerList = cmsCategoryPowerService.list(pow);
        //筛选 可以拿到的一级分类
        List<CmsCategory> categoryListNew = new ArrayList<>();
        List<CmsCategory> treeList = toTree(categoryList, "0");
        for (CmsCategory c : treeList) {
            for (CmsCategoryPower p : powerList) {
                if (Objects.equals(c.getId(), p.getCategoryId())) {
                    //筛选分类id==部门里的有权限的分类id
                    categoryListNew.add(c);
                }
            }

        }
        return categoryListNew;
    }


    @Override
    public List<CmsCategory> treeAndChildren(String parent) {
        List<CmsCategory> ds = cmsCategoryMapper.selectCateByParentId(parent);
        for (CmsCategory sd : ds) {
            treeAndChildren(sd, ds);
        }
        return ds;
    }

    private void treeAndChildren(CmsCategory sd, List<CmsCategory> ds) {
        List<CmsCategory> dss = cmsCategoryMapper.selectCateByParentId(sd.getId().toString());
        if (dss.size() > 0) {
            for (CmsCategory sdd : dss) {
                ds.add(sdd);
                treeAndChildren(sdd, ds);
            }
        } else {
            return;
        }
    }


    public List<CmsCategory> toTree(List<CmsCategory> sysCate, String parent) {
        List<CmsCategory> list = new ArrayList<>();
        for (CmsCategory cate : sysCate) {
            if (parent.equals(cate.getParent())) {
                cate.setChildren(toTree(sysCate, cate.getId().toString()));
                list.add(cate);
            }
        }
        return list;
    }


    @Override
    public PageResponse<CmsCategory> page(CmsCategoryRequest request) {


        return Pageable.of(request, (() -> cmsCategoryMapper.selectCmsCategoryList(request)));
    }

    @Override
    public Boolean exist(String title) {
        long count = this.lambdaQuery().eq(CmsCategory::getCategoryTitle, title).count();
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<CmsCategory> getRecent() {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.orderBy(true, false, "category_hit");
        List<CmsCategory> category_list = this.list(wrapper);
        return category_list;
    }

    @Override
    public List<CmsCategory> selectCateByParentId(String parentId) {
        return cmsCategoryMapper.selectCateByParentId(parentId);
    }

    @Override
    public Map<String, Object> totalCateInfoAll() {
        List<String> list = List.of("0");
        Map<String, Object> mymap = new HashMap<>();
        Map<String, Object> stringObjectMap = totalCateInfo(list, 0,mymap);
        return stringObjectMap;
    }


    public Map<String, Object> totalCateInfo(List<String> idList, int count,Map<String, Object> mymap) {
        count++;
       // Map<String, Object> mymap = new HashMap<>();

        CountNeed countNeed = selectCateByParentIdCount(idList);

        if (countNeed.getListCms().size() != 0) {
            mymap.put(String.valueOf(count), countNeed);
            List<String> parentList = countNeed.getListCms().stream().map(i -> i.getId()).collect(Collectors.toList());
            totalCateInfo(parentList, count,mymap);
        }
        return mymap;

    }


    private CountNeed selectCateByParentIdCount(List<String> idList) {
        CountNeed need = new CountNeed();
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.in("parent", idList);
        List<CmsCategory> category_list = this.list(wrapper);
        need.setCount(category_list.size());
        need.setListCms(category_list);
        return need;
    }
}
