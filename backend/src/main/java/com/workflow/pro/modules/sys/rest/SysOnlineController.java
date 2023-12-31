package com.workflow.pro.modules.sys.rest;

import com.workflow.pro.common.aop.annotation.Log;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import com.workflow.pro.common.constant.CacheNameConstant;
import com.workflow.pro.common.constant.ControllerConstant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.workflow.pro.common.secure.uutoken.SecureUserToken;
import com.workflow.pro.common.web.base.BaseController;
import com.workflow.pro.common.web.domain.Result;
import io.swagger.annotations.Api;
import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * 在线用户控制器
 *
 * Author: SOME
 * CreateTime: 2022/03/27
 * */
@Api(tags = {"在线"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "online")
public class SysOnlineController extends BaseController {

    @Resource
    private RedisTemplate<String, SecureUserToken> redisTemplate;

    /**
     * 在线用户列表
     *
     * param: null
     * return: Result
     * */
    @GetMapping("list")
    @Log(title = "在线列表")
    @ApiOperation(value = "在线列表")
    public Result list(){
        Set<String> keys = redisTemplate.keys(CacheNameConstant.TOKEN_NAME_PREFIX + "*");
        Set<SecureUserToken> onlineSet = new HashSet<>();
        keys.forEach(key -> {
            SecureUserToken userToken = redisTemplate.opsForValue().get(key);
            onlineSet.add(userToken);
        });
        return success(onlineSet);
    }
}
