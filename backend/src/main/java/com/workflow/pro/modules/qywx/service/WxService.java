package com.workflow.pro.modules.qywx.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.workflow.pro.modules.sys.domain.SysUser;
import com.workflow.pro.modules.sys.service.SysUserService;
import com.google.gson.JsonObject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.cp.api.WxCpOAuth2Service;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpOauth2UserInfo;
import me.chanjar.weixin.cp.bean.WxCpUserDetail;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class WxService implements WxCpOAuth2Service {

    @Autowired
    private SysUserService sysUserService;
    private final WxCpService mainService;

    public WxService(WxCpService mainService) {
        this.mainService = mainService;
    }

    @Override
    public String buildAuthorizationUrl(String s) {
        return null;
    }

    @Override
    public String buildAuthorizationUrl(String s, String s1) {
        return null;
    }

    @Override
    public String buildAuthorizationUrl(String s, String s1, String s2) {
        return null;
    }

    @Override
    public WxCpOauth2UserInfo getUserInfo(String code) throws WxErrorException {

        return this.getUserInfo(this.mainService.getWxCpConfigStorage().getAgentId(), code);
    }

    @Override
    public WxCpOauth2UserInfo getUserInfo(Integer agentId, String code) throws WxErrorException {

        try {
            WxCpOauth2UserInfo wxCpOauth2UserInfo = new WxCpOauth2UserInfo();
            String responseText = this.mainService.get(String.format(this.mainService.getWxCpConfigStorage().getApiUrl("/cgi-bin/auth/getuserinfo?code=%s"), code), (String) null);
            JsonObject jo = GsonParser.parse(responseText);

            String userid = GsonHelper.getString(jo, "userid");

            if (Objects.equals(userid, "")) {
                throw new WxErrorException("非当前系统用户,需要授权登录");
            }
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("id", userid);

            SysUser one = sysUserService.getOne(wrapper);
            String Name = one.getId();

            wxCpOauth2UserInfo.setUserId(userid);
            return wxCpOauth2UserInfo;
        } catch (WxErrorException e) {
            throw new WxErrorException(e);
        }
    }

    @Override
    public WxCpUserDetail getUserDetail(String userTicket) throws WxErrorException {
        JsonObject param = new JsonObject();
        param.addProperty("user_ticket", userTicket);
        String responseText = this.mainService.post(this.mainService.getWxCpConfigStorage().getApiUrl("/cgi-bin/user/getuserdetail"), param.toString());
        return (WxCpUserDetail) WxCpGsonBuilder.create().fromJson(responseText, WxCpUserDetail.class);
    }
}
