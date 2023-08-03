package com.workflow.pro.modules.apitable.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;


import com.workflow.pro.modules.apitable.mapper.ApitableComponentMapper;
import com.workflow.pro.modules.apitable.domain.ApitableComponent;
import com.workflow.pro.modules.apitable.param.ApitableComponentRequest;
import com.workflow.pro.modules.apitable.service.IApitableComponentService;


import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;


/**
 * 格组件ServiceImpl业务层处理
 *
 * @author some
 * @date 2023-05-22
 */
@Service
public class ApitableComponentServiceImpl extends ServiceImpl<ApitableComponentMapper, ApitableComponent> implements IApitableComponentService {

    @Resource
    ApitableComponentMapper apitableComponentMapper;

    /**
     * 查询格组件
     *
     * @param id 格组件ID
     * @return 格组件
     */
    @Override
    public ApitableComponent selectApitableComponentById(String id) {
        return apitableComponentMapper.selectApitableComponentById(id);
    }

    /**
     * 查询格组件列表
     * @return 格组件
     */
    @Override
    public List<ApitableComponent> list (ApitableComponentRequest request){
        return apitableComponentMapper.selectApitableComponentList(request);
    }

    /**
     * 查询格组件
     * @return 格组件 分页集合
     * */
    @Override
    public PageResponse<ApitableComponent> page(ApitableComponentRequest request) {
        return Pageable.of(request, (() -> apitableComponentMapper.selectApitableComponentList(request)));
    }

    /**
     * 新增格组件
     *
     * @param apitableComponent 格组件
     * @return 结果
     */

    @Override
    public int insertApitableComponent(ApitableComponent apitableComponent) {
        return apitableComponentMapper.insertApitableComponent(apitableComponent);
    }

    /**
     * 修改格组件
     *
     * @param apitableComponent 格组件
     * @return 结果
     */
    @Override
    public int updateApitableComponent(ApitableComponent apitableComponent) {
        return apitableComponentMapper.updateApitableComponent(apitableComponent);
    }

    /**
     * 删除格组件对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteApitableComponentByIds(String[] ids) {
        return apitableComponentMapper.deleteApitableComponentByIds(ids);
    }

    /**
     * 删除格组件信息
     *
     * @param id 格组件ID
     * @return 结果
     */
    @Override
    public int deleteApitableComponentById(String id) {
        return apitableComponentMapper.deleteApitableComponentById(id);
    }
}
