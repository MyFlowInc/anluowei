package com.workflow.pro.common.web.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * Base Service
 *
 * Author: SOME
 * CreateTime: 2022/03/27
 * */
public abstract class BaseService<M extends BaseMapper<T>,T> extends ServiceImpl<M,T> {

}
