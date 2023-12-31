package com.workflow.pro.common.cache.impl;

import com.workflow.pro.common.cache.BaseCache;
import com.workflow.pro.modules.sys.domain.SysDictData;
import com.workflow.pro.modules.sys.domain.SysDict;
import com.workflow.pro.modules.sys.service.SysDictDataService;
import com.workflow.pro.modules.sys.service.SysDictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 全局字典缓存
 *
 * Author: SOME
 * CreateTime: 2022/04/21
 * */
@Slf4j
@Component
public class DictionaryCache extends BaseCache<List<SysDictData>> {

    @Resource
    private SysDictService sysDictService;

    @Resource
    private SysDictDataService sysDictDataService;

    @Override
    public Map<String, List<SysDictData>> load()
    {
        log.info("Refresh Cache - 数据字典");
        Map<String, List<SysDictData>> map = new HashMap<>();
        List<SysDict> dictList = sysDictService.lambdaQuery().eq(SysDict::isEnable,true).list();
        dictList.forEach(dict -> {
            List<SysDictData> dictData = sysDictDataService.lambdaQuery()
                    .eq(SysDictData::getCode, dict.getCode())
                    .eq(SysDictData::isEnable,true).list();
            map.put(dict.getCode(),dictData);
        });
        return new HashMap<>();
    }
}
