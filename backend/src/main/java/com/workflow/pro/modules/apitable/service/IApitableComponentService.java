package com.workflow.pro.modules.apitable.service;

import java.util.List;


import com.baomidou.mybatisplus.extension.service.IService;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;


import com.workflow.pro.modules.apitable.param.ApitableComponentRequest;
import com.workflow.pro.modules.apitable.domain.ApitableComponent;

/**
 * 格组件IService接口
 *
 * @author some
 * @date 2023-05-22
 */
public interface IApitableComponentService  extends IService <ApitableComponent>{
    /**
     * 查询格组件
     *
     * @param id 格组件ID
     * @return 格组件
     */
        ApitableComponent selectApitableComponentById(String id);


    /**
     * 查询格组件
     * @param  request pageSize pageNum
     * @return 格组件 分页集合
     * */
    PageResponse<ApitableComponent> page(ApitableComponentRequest request);

    /**
     * 查询格组件列表
     * @return 格组件集合
     */
    List<ApitableComponent> list(ApitableComponentRequest request);

    /**
     * 新增格组件
     *
     * @param apitableComponent 格组件
     * @return 结果
     */
    int insertApitableComponent(ApitableComponent apitableComponent);

    /**
     * 修改格组件
     *
     * @param apitableComponent 格组件
     * @return 结果
     */
    int updateApitableComponent(ApitableComponent apitableComponent);

    /**
     * 批量删除格组件
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteApitableComponentByIds(String[] ids);

    /**
     * 删除格组件信息
     *
     * @param id 格组件ID
     * @return 结果
     */
    int deleteApitableComponentById(String id);

}
