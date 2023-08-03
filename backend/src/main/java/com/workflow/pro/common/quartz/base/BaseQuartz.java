package com.workflow.pro.common.quartz.base;

/**
 * Base Quartz
 *
 * Author: SOME
 * CreateTime: 2022/05/09
 * */
public interface BaseQuartz {

    /**
     * 任 务 实 现
     * */
    void run(String param) throws Exception;

}
