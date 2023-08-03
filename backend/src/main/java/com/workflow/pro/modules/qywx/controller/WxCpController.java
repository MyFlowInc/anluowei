package com.workflow.pro.modules.qywx.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.workflow.pro.common.tools.string.SignUtil;
import com.workflow.pro.common.tools.string.StringUtil;
import com.workflow.pro.common.web.base.BaseController;
import com.workflow.pro.common.web.domain.Result;
import com.workflow.pro.modules.cms.domain.SysActivity;
import com.workflow.pro.modules.cms.service.ISysActivityService;
import com.workflow.pro.modules.qywx.entity.AppInfo;
import com.workflow.pro.modules.qywx.entity.JsonData;
import com.workflow.pro.modules.qywx.service.AppInfoMapper;
import com.workflow.pro.modules.qywx.service.WxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpDepart;
import me.chanjar.weixin.cp.bean.WxCpTag;
import me.chanjar.weixin.cp.bean.WxCpUser;
import me.chanjar.weixin.cp.bean.article.NewArticle;
import me.chanjar.weixin.cp.bean.message.WxCpMessage;
import me.chanjar.weixin.cp.bean.message.WxCpMessageSendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Api(tags = "企业微信开放接口")
@RestController
@RequestMapping("/api/wx")
public class WxCpController extends BaseController {

    @Autowired
    private WxCpService wxCpService;
    @Autowired
    private WxService wxService;
    @Autowired
    private ISysActivityService sysActivityService;
    @Autowired
    private AppInfoMapper appInfoMapper;

    /**
     * 获取当前CropId
     *
     * @return
     */
    @ApiOperation(value = "获取当前CropId")
    @GetMapping("/getCropId")
    public JsonData getCropId() {
        return JsonData.buildSuccess(wxCpService.getWxCpConfigStorage().getExpiresTime());
    }

    /**
     * 获取当前CropId
     *
     * @return
     */
    @ApiOperation(value = "获取部门列表")
    @GetMapping("/department/list")
    public Result getUser(@RequestParam Long depart_id) throws WxErrorException {
        return Result.success(wxCpService.getDepartmentService().list(depart_id));
    }

    /**
     * 获取当前CropId
     */
    @ApiOperation(value = "获取部门员工列表")
    @GetMapping("/department/user_list")
    public Result getDepartUser(@RequestParam Long depart_id) throws WxErrorException {
        return Result.success(wxCpService.getUserService().listByDepartment(depart_id, true, 1));
    }

    /**
     * 获取 AccessToken
     *
     * @return
     */
    @ApiOperation(value = "获取 AccessToken")
    @GetMapping("/getAccessToken")
    public JsonData getAccessToken() throws Exception {
        return JsonData.buildSuccess(wxCpService.getAccessToken());
    }

    /**
     * 发送普通消息
     *
     * @param user
     * @param content
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "发送普通消息")
    @PostMapping("/sendMessage")
    public Result sendMessage(@RequestParam(value = "active_id",required = false) String active_id, @RequestParam(value = "agentId") String agentId, @RequestParam(value = "user" ,required = false) String user, @RequestParam(value = "content",required = false) String content) throws Exception {
        AppInfo appInfo = appInfoMapper.selectOne(new QueryWrapper<AppInfo>().eq("agent_id", agentId));
        if (appInfo == null) {
            JsonData.buildError(400, "没有应用信息");
        }
        if (!Objects.equals(active_id, "")) {
            SysActivity activity = sysActivityService.getById(active_id);
            NewArticle article1 = new NewArticle();
            String Url=appInfo.getUrl()+"/#/act/"+active_id;
            article1.setUrl(Url);
            article1.setPicUrl(activity.getImg());
            article1.setDescription(activity.getContent());
            article1.setTitle(activity.getName());

            WxCpMessage message = WxCpMessage.NEWS().agentId(appInfo.getAgentId()) // 企业号应用ID
                    .toUser("@all") // "非必填，UserID列表（消息接收者，多个接收者用‘|’分隔）。特殊情况：指定为@all，则向关注该企业应用的全部成员发送"
                    .addArticle(article1).build();

            WxCpMessageSendResult send = wxCpService.getMessageService().send(message);
            if(send.getErrCode()==0){

                activity.setSend("1");
                sysActivityService.updateById(activity);
            }
            return  success(send);
        } else {
            WxCpMessage message = WxCpMessage.TEXT().agentId(appInfo.getAgentId()).toUser(user).content(content).build();
            return success(wxCpService.getMessageService().send(message));
        }
    }


    /**
     * 发送图片消息
     *
     * @param agentId
     * @param user
     * @param content
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "发送图片消息")
    @PostMapping("/sendImageMessage")
    public JsonData sendImageMessage(@RequestParam(value = "agentId") String agentId, @RequestParam(value = "user") String user, @RequestParam(value = "content") String content) throws Exception {
        AppInfo appInfo = appInfoMapper.selectOne(new QueryWrapper<AppInfo>().eq("agent_id", agentId));
        if (appInfo == null) {
            JsonData.buildError(400, "没有应用信息");
        }
        WxCpMessage message = WxCpMessage.IMAGE().agentId(appInfo.getAgentId()) // 企业号应用ID
                .toUser(user) // "非必填，UserID列表（消息接收者，多个接收者用‘|’分隔）。特殊情况：指定为@all，则向关注该企业应用的全部成员发送"
                .mediaId("3BII5tCkXs9tcrsi2W14ICRhvmjPfUS7QhmwyVNVpKgyUneScZ1Yq_AtiOKgDT-8U") // 调用上传接口获取
                .build();
        return JsonData.buildSuccess(wxCpService.getMessageService().send(message));
    }

    /**
     * 发送语音消息
     *
     * @param agentId
     * @param user
     * @param content
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "发送语音消息")
    @PostMapping("/sendVoiceMessage")
    public JsonData sendVoiceMessage(@RequestParam(value = "agentId") String agentId, @RequestParam(value = "user") String user, @RequestParam(value = "content") String content) throws Exception {
        AppInfo appInfo = appInfoMapper.selectOne(new QueryWrapper<AppInfo>().eq("agent_id", agentId));
        if (appInfo == null) {
            JsonData.buildError(400, "没有应用信息");
        }
        WxCpMessage message = WxCpMessage.VOICE().agentId(appInfo.getAgentId()) // 企业号应用ID
                .toUser(user) // "非必填，UserID列表（消息接收者，多个接收者用‘|’分隔）。特殊情况：指定为@all，则向关注该企业应用的全部成员发送"
                .mediaId("3BII5tCkXs9tcrsi2W14ICRhvmjPfUS7QhmwyVNVpKgyUneScZ1Yq_AtiOKgDT-8U") // 调用上传接口获取
                .build();
        return JsonData.buildSuccess(wxCpService.getMessageService().send(message));
    }

    /**
     * 发送视频消息
     *
     * @param agentId
     * @param user
     * @param content
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "发送视频消息")
    @PostMapping("/sendVideoMessage")
    public JsonData sendVideoMessage(@RequestParam(value = "agentId") String agentId, @RequestParam(value = "user") String user, @RequestParam(value = "content") String content) throws Exception {
        AppInfo appInfo = appInfoMapper.selectOne(new QueryWrapper<AppInfo>().eq("agent_id", agentId));
        if (appInfo == null) {
            JsonData.buildError(400, "没有应用信息");
        }


        WxCpMessage message = WxCpMessage.VIDEO().agentId(appInfo.getAgentId()) // 企业号应用ID
                .toUser(user) // "非必填，UserID列表（消息接收者，多个接收者用‘|’分隔）。特殊情况：指定为@all，则向关注该企业应用的全部成员发送"
                .mediaId("3BII5tCkXs9tcrsi2W14ICRhvmjPfUS7QhmwyVNVpKgyUneScZ1Yq_AtiOKgDT-8U") // 调用上传接口获取
                .build();
        return JsonData.buildSuccess(wxCpService.getMessageService().send(message));
    }

    /**
     * 发送文本卡片消息
     *
     * @param agentId
     * @param user
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "发送文本卡片消息")
    @PostMapping("/sendTextCardMessage")
    public JsonData sendTextCardMessage(@RequestParam(value = "agentId") String agentId, @RequestParam(value = "user") String user) throws Exception {
        // <div class="gray">2016年9月26日</div> <div class="normal">恭喜你抽中iPhone 7一台，领奖码：xxxx</div><div class="highlight">请于2016年10月10日前联系行政同事领取</div>
        String template = "<div class=\"gray\">%s</div> <div class=\"normal\">尊敬的菜吧吧主，督导对“全店蔬菜爆款”等3个活动进行加单申请，请及时审批。如有疑问请联系督导</div>";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        template = String.format(template, sdf.format(new Date()));
        WxCpMessage cardMsg = WxCpMessage.TEXTCARD().agentId(Integer.parseInt(agentId)).toUser(user).url("https://bit.ly/3zdYZc6").title("督导提交加单申请") // 标题，不超过128个字节，超过会自动截断（支持id转译）
                .btnTxt("点击查看") // 按钮文字。 默认为“详情”， 不超过4个文字，超过自动截断。
                .description(template).build();
        return JsonData.buildSuccess(wxCpService.getMessageService().send(cardMsg));
    }

    /**
     * 发送图文消息
     *
     * @param agentId
     * @param user
     * @param content
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "发送图文消息")
    @PostMapping("/sendNewsMessage")
    public JsonData sendNewsMessage(@RequestParam(value = "agentId") String agentId, @RequestParam(value = "user") String user, @RequestParam(value = "content") String content) throws Exception {
        AppInfo appInfo = appInfoMapper.selectOne(new QueryWrapper<AppInfo>().eq("agent_id", agentId));
        if (appInfo == null) {
            JsonData.buildError(400, "没有应用信息");
        }
        NewArticle article1 = new NewArticle();
        article1.setUrl("www.baidu.com");
        article1.setPicUrl("https://pic3.zhimg.com/v2-fdcfbc482fb9c101e3b4564bf69c527a_400x224.jpeg");
        article1.setDescription("测试消息内容");
        article1.setTitle("测试消息");
        WxCpMessage message = WxCpMessage.NEWS().agentId(appInfo.getAgentId()) // 企业号应用ID
                .toUser("@all") // "非必填，UserID列表（消息接收者，多个接收者用‘|’分隔）。特殊情况：指定为@all，则向关注该企业应用的全部成员发送"
                .addArticle(article1).build();
        return JsonData.buildSuccess(wxCpService.getMessageService().send(message));
    }

    /**
     * 构造授权链接（回调必须是可信域名地址）
     *
     * @param url
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "构造授权链接（回调必须是可信域名地址）")
    @GetMapping("/buildAuthorizationUrl")
    public JsonData buildAuthorizationUrl(@RequestParam(value = "url") String url) throws Exception {
        return JsonData.buildSuccess(wxCpService.getOauth2Service().buildAuthorizationUrl(url, null));
    }

    /**
     * oauth2获取用户信息
     *
     * @param code
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "oauth2获取用户信息")
    @GetMapping("/getOauth2UserInfo")
    public JsonData getOauth2UserInfo(@RequestParam(value = "code") String code) throws Exception {
        try {
            return JsonData.buildSuccess(wxCpService.getOauth2Service().getUserInfo(code));
        } catch (Exception exception) {
            return JsonData.buildError(401, exception.getMessage());
        }
    }
    /**
     * oauth2获取用户信息 返回系统token 登录信息
     */
    @ApiOperation(value = "oauth2获取用户信息")
    @GetMapping("/getOauth2UserInfoToken")
    public JsonData getUserDetail(@RequestParam(value = "ticket") String ticket) throws Exception {
        return JsonData.buildSuccess(wxCpService.getOauth2Service().getUserDetail(ticket));
    }

    /**
     * 根据手机号获取用户信息
     *
     * @param mobile
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "根据手机号获取用户信息")
    @GetMapping("/getUserInfoByMobile")
    public JsonData getUserInfoByMobile(@RequestParam(value = "mobile") String mobile) throws Exception {
        return JsonData.buildSuccess(wxCpService.getUserService().getUserId(mobile));
    }

    /**
     * 根据userId获取用户信息
     *
     * @param userId
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "根据userId获取用户信息")
    @GetMapping("/getUserInfoByUserId")
    public JsonData getUserInfoByUserId(@RequestParam(value = "userId") String userId) throws Exception {
        return JsonData.buildSuccess(wxCpService.getUserService().getById(userId));
    }

    /**
     * 根据agentId获取应用信息
     *
     * @param agentId
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "根据agentId获取应用信息")
    @GetMapping("/getAgentInfo")
    public JsonData getAgentInfo(@RequestParam(value = "agentId") Integer agentId) throws Exception {
        return JsonData.buildSuccess(wxCpService.getAgentService().get(agentId));
    }

    /**
     * 获取企业的jsapi_ticket
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取企业的jsapi_ticket")
    @GetMapping("/getJsApiTicket")
    public JsonData getJsApiTicket() throws Exception {
        wxCpService.getAgentJsapiTicket();
        return JsonData.buildSuccess(wxCpService.getJsapiTicket());
    }

    /**
     * 获取应用的jsapi_ticket
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取应用的jsapi_ticket")
    @GetMapping("/getAgentJsApiTicket")
    public JsonData getAgentJsApiTicket() throws Exception {
        return JsonData.buildSuccess(wxCpService.getAgentJsapiTicket());
    }

    @ApiOperation(value = "获取部门成员")
    @GetMapping("/getDepartmentUser")
    public JsonData getDepartmentUser() throws Exception {
        // 获取部门信息（如果需要指定部门则传部门id）
        List<WxCpDepart> deps = wxCpService.getDepartmentService().list(null);
        for (WxCpDepart dep : deps) {
            // 获取部门成员
            List<WxCpUser> wxCpUsers = wxCpService.getUserService().listByDepartment(dep.getId(), false, 0);
        }
        return JsonData.buildSuccess();
    }

    @ApiOperation(value = "获取标签成员")
    @GetMapping("/getTagUser")
    public JsonData getTagUser() throws Exception {
        // 标签信息（
        List<WxCpTag> tags = wxCpService.getTagService().listAll();
        for (WxCpTag tag : tags) {
            // 获取标签成员
            List<WxCpUser> wxCpUsers = wxCpService.getTagService().listUsersByTagId(tag.getId());
        }
        return JsonData.buildSuccess();
    }

    /**
     * 上传图片（用于发送图片消息）
     *
     * @param file
     * @return
     * @throws IOException
     * @throws WxErrorException
     */
    @ApiOperation(value = "上传图片（用于发送图片消息）")
    @PostMapping("/uploadImage")
    public JsonData uploadImage(@RequestParam("file") MultipartFile file) throws IOException, WxErrorException {
        return JsonData.buildSuccess(wxCpService.getMediaService().upload(WxConsts.MaterialType.IMAGE, WxConsts.MaterialType.IMAGE, file.getInputStream()));
    }

    /**
     * 上传视频（用于发送视频消息）
     *
     * @param file
     * @return
     * @throws IOException
     * @throws WxErrorException
     */
    @ApiOperation(value = "上传视频（用于发送视频消息）")
    @PostMapping("/uploadVideo")
    public JsonData uploadVideo(@RequestParam("file") MultipartFile file) throws IOException, WxErrorException {
        return JsonData.buildSuccess(wxCpService.getMediaService().upload(WxConsts.MaterialType.VIDEO, WxConsts.MaterialType.VIDEO, file.getInputStream()));
    }

    /**
     * 上传音频（用于发送语音消息）
     *
     * @param file
     * @return
     * @throws IOException
     * @throws WxErrorException
     */
    @ApiOperation(value = "上传音频（用于发送语音消息）")
    @PostMapping("/uploadVoice")
    public JsonData uploadVoice(@RequestParam("file") MultipartFile file) throws IOException, WxErrorException {
        String fileName = file.getName();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        // 上传语音文件仅支持AMR格式；语音文件的系统限制
        if (!"ARM".equalsIgnoreCase(suffix)) {
            JsonData.buildError(400, "上传语音文件仅支持AMR格式");
        }
        return JsonData.buildSuccess(wxCpService.getMediaService().upload(WxConsts.MaterialType.VOICE, WxConsts.MaterialType.VOICE, file.getInputStream()));
    }

    @ApiOperation(value = "上传音频（用于发送语音消息）")
    @GetMapping("/getJsSdkSign")
    public JsonData getJsSdkSign(HttpServletRequest request, HttpServletResponse response) throws Exception {


        String jsapi_ticket = wxCpService.getJsapiTicket();
        String noncestr = "";
        long timestamp = System.currentTimeMillis() / 1000;
        String url = request.getParameter("url");
        if (url == null || url.equals("")) ;
        {
            url = "http://bageng.art:8080/";
        }
        Map<String, String> mymap = new HashMap<String, String>();
        wxCpService.getAgentJsapiTicket();
        String appid = wxCpService.getWxCpConfigStorage().getCorpId();
        String secret = wxCpService.getWxCpConfigStorage().getCorpSecret();
        String jsSdkSign = SignUtil.sign(url, noncestr, timestamp, jsapi_ticket);
        mymap.put("appid", appid);
        mymap.put("app_secret", secret);
        mymap.put("noncestr", noncestr);
        mymap.put("timestamp", String.valueOf(timestamp));
        mymap.put("jsapi_ticket", jsapi_ticket);
        mymap.put("signature", jsSdkSign);
        mymap.put("url", url);

        return JsonData.buildSuccess(mymap);
    }


}
