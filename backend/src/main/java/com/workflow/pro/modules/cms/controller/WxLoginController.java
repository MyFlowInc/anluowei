package com.workflow.pro.modules.cms.controller;

import com.workflow.pro.common.constant.CacheNameConstant;
import com.workflow.pro.common.constant.SecurityConstant;
import com.workflow.pro.common.secure.uutoken.SecureUserToken;
import com.workflow.pro.common.secure.uutoken.SecureUserTokenService;
import com.workflow.pro.common.web.base.BaseController;
import com.workflow.pro.common.web.domain.Result;
import com.workflow.pro.common.web.domain.ResultCode;
import com.workflow.pro.modules.qywx.service.WxService;
import com.workflow.pro.modules.sys.domain.SysUser;
import com.workflow.pro.modules.sys.service.SysUserService;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpOauth2UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/sys")
public class WxLoginController extends BaseController {


    @Autowired
    private WxCpService wxCpService;
    @Autowired
    private WxService wxService;
    @Resource
    private SysUserService sysUserService;

    @Resource
    private SecureUserTokenService customUserDetailsTokenService;
    @Resource
    private RedisTemplate<String, WxCpOauth2UserInfo> redisTemplate;

    /**
     * 微信登录
     *
     * @param code          凭证
     * @param encryptedData 用户数据
     * @param iv            用户数据
     * @param request       request作用域:
     * @return map
     */
    @GetMapping("/login_by_weixin")
    @ResponseBody
    public Result loginByWeixin(String code, String encryptedData, String iv, HttpServletRequest request) throws IOException, WxErrorException {

        try {
            //code 获取用户信息
            WxCpOauth2UserInfo userInfo = redisTemplate.opsForValue().get(CacheNameConstant.CODE + code);
            if (userInfo == null) {
                WxCpOauth2UserInfo userInfo1 = wxService.getUserInfo(code);
                redisTemplate.opsForValue().set(CacheNameConstant.CODE + code, userInfo1, SecurityConstant.CACHE_CODE, TimeUnit.SECONDS);
                userInfo= userInfo1;
            }
            SysUser byId = sysUserService.getById(userInfo.getUserId());
            SecureUserToken userToken = customUserDetailsTokenService.createToken(byId);

            String tokenKey = customUserDetailsTokenService.saveToken(userToken);
            String tokenValue = userToken.getToken();

            return Result.success(ResultCode.LOGIN_SUCCESS, tokenKey, tokenValue);
        } catch (Exception exception) {
            return Result.failure(401, exception.getMessage());
        }


//        String sendGet = sysUserService.loginByWeixin(code, encryptedData, iv); //根据code去调用接口获取用户openid和session_key
//
//        JSONObject json = JSONObject.parseObject(sendGet);
//        System.out.println("返回过来的json数据:" + json.toString());
//        String sessionkey = json.get("session_key").toString(); //会话秘钥
//
//
//        Httprequests.sendPost("", "");
//
//        //获取用户敏感信息的接口调用
//        String openid = json.get("openid").toString(); //用户唯一标识
//        try {
//            //拿到用户session_key和用户敏感数据进行解密，拿到用户信息。
//            String decrypts = AesCbcUtil.decrypt(encryptedData, sessionkey, iv, "utf-8");//解密
//            JSONObject jsons = JSONObject.parseObject(decrypts);
//            String nickName = jsons.get("nickName").toString(); //用户昵称
//            String jsonsds = jsons.get("avatarUrl").toString(); //用户头像
////            jsons.get("avatarUrl").toString(); //头像
////            jsons.get("gender").toString();//性别
////            jsons.get("unionid").toString(); //unionid
////            jsons.get("city").toString(); //城市
////            jsons.get("province").toString();//省份
////            jsons.get("country").toString(); //国家
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return success(json);
    }

}
