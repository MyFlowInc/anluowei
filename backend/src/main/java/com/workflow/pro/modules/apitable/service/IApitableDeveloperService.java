package com.workflow.pro.modules.apitable.service;

import java.util.List;


import com.baomidou.mybatisplus.extension.service.IService;
import com.workflow.pro.common.web.base.page.PageResponse;


import com.workflow.pro.modules.apitable.param.ApitableDeveloperRequest;
import com.workflow.pro.modules.apitable.domain.ApitableDeveloper;
import com.workflow.pro.modules.sys.domain.SysUser;
import com.workflow.pro.modules.sys.param.SysUserRequest;

/**
 * 格协作者IService接口
 *
 * @author some
 * @date 2023-06-21
 */
public interface IApitableDeveloperService extends IService<ApitableDeveloper> {
    /**
     * 查询格协作者
     *
     * @param id 格协作者ID
     * @return 格协作者
     */
    ApitableDeveloper selectApitableDeveloperById(String id);


    /**
     * 查询格协作者
     * @param  request pageSize pageNum
     * @return 格协作者 分页集合
     * */
    PageResponse<ApitableDeveloper> page(ApitableDeveloperRequest request);

    /**
     * 查询格协作者列表
     * @return 格协作者集合
     */
    List<ApitableDeveloper> list(ApitableDeveloperRequest request);

    /**
     * 新增格协作者
     *
     * @param apitableDeveloper 格协作者
     * @return 结果
     */
    int insertApitableDeveloper(ApitableDeveloper apitableDeveloper);

    /**
     * 修改格协作者
     *
     * @param apitableDeveloper 格协作者
     * @return 结果
     */
    int updateApitableDeveloper(ApitableDeveloper apitableDeveloper);

    /**
     * 批量删除格协作者
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteApitableDeveloperByIds(String[] ids);

    /**
     * 删除格协作者信息
     *
     * @param id 格协作者ID
     * @return 结果
     */
    int deleteApitableDeveloperById(String id);

    /**
     * 删除格协作者信息
     * @return 结果
     */
    boolean enableDeveloper(String dstId, String userId);

    List<SysUser>  userList(SysUserRequest request);
}
