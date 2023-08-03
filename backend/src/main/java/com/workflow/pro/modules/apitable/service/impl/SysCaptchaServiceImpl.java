package com.workflow.pro.modules.apitable.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workflow.pro.common.tools.string.StringUtil;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;


import com.workflow.pro.common.web.exception.base.BusinessException;
import com.workflow.pro.modules.apitable.domain.vo.EmailVo;
import com.workflow.pro.modules.apitable.exception.BusinessExceptionNew;
import com.workflow.pro.modules.apitable.mapper.SysCaptchaMapper;
import com.workflow.pro.modules.apitable.domain.SysCaptcha;
import com.workflow.pro.modules.apitable.param.SysCaptchaRequest;
import com.workflow.pro.modules.apitable.service.IEmailService;
import com.workflow.pro.modules.apitable.service.ISysCaptchaService;
import com.workflow.pro.modules.apitable.utils.RandomExtendUtil;
import com.workflow.pro.modules.qywx.entity.AppInfo;


import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


/**
 * 【请填写功能名称】ServiceImpl业务层处理
 *
 * @author some
 * @date 2023-05-30
 */
@Service
public class SysCaptchaServiceImpl extends ServiceImpl<SysCaptchaMapper, SysCaptcha> implements ISysCaptchaService {

    @Resource
    SysCaptchaMapper sysCaptchaMapper;

    @Resource
    private IEmailService emailService;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public SysCaptcha selectSysCaptchaById(Long id) {
        return sysCaptchaMapper.selectSysCaptchaById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * @return 【请填写功能名称】
     */
    @Override
    public List<SysCaptcha> list(SysCaptchaRequest request) {
        return sysCaptchaMapper.list(request);
    }

    /**
     * 查询【请填写功能名称】
     * @return 【请填写功能名称】 分页集合
     * */
    @Override
    public PageResponse<SysCaptcha> page(SysCaptchaRequest request) {
        return Pageable.of(request, (() -> sysCaptchaMapper.selectSysCaptchaList(request)));
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param sysCaptcha 【请填写功能名称】
     * @return 结果
     */

    @Override
    public int insertSysCaptcha(SysCaptcha sysCaptcha) {
        return sysCaptchaMapper.insertSysCaptcha(sysCaptcha);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param sysCaptcha 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateSysCaptcha(SysCaptcha sysCaptcha) {
        return sysCaptchaMapper.updateSysCaptcha(sysCaptcha);
    }

    /**
     * 删除【请填写功能名称】对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysCaptchaByIds(String[] ids) {
        return sysCaptchaMapper.deleteSysCaptchaByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteSysCaptchaById(Long id) {
        return sysCaptchaMapper.deleteSysCaptchaById(id);
    }

    @Override
    public SysCaptcha send(SysCaptcha sysCaptcha) throws Exception {
        //校验距离上次发送的时间是否大于1分钟
        SysCaptcha check = sysCaptchaMapper.selectOne(new QueryWrapper<SysCaptcha>().eq("email", sysCaptcha.getEmail()).last("ORDER BY `create_time` DESC limit 1"));
        if (check != null && check.getId() != null) {
            String format = check.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            long minutesBetween = getMinutesBetween(format);
            if (minutesBetween < 3) {
                throw new Exception("发送验证码时间小于1min,请稍后尝试");
            }
        }
        EmailVo emailVo = new EmailVo();
        ArrayList<String> s = new ArrayList<>();
        s.add(sysCaptcha.getEmail());
        emailVo.setTos(s);
        emailVo.setSubject("FlowSheet");
        int code = StringUtil.generateRandomNumber(6);
        sysCaptcha.setCaptcha(String.valueOf(code));
        emailVo.setContent("【MyFlow】亲爱的用户您好 您的验证码 code：" + code + "\n");
        sysCaptcha.setExpired(5);
        try {
            emailService.send(emailVo, emailService.find());
            remove(new QueryWrapper<SysCaptcha>().eq("email", sysCaptcha.getEmail()));
            save(sysCaptcha);
        }
        catch (Exception e) {

        }
        return sysCaptcha;
    }

    @Override
    public Boolean Check(String email, String code) throws BusinessExceptionNew {
        SysCaptcha check = sysCaptchaMapper.selectOne(new QueryWrapper<SysCaptcha>().eq("email", email).last("ORDER BY `create_time` DESC limit 1"));
        if (check == null || check.getId() == null) {
            throw new BusinessExceptionNew(3004, "验证码未发送,或错误");
        }
        if (!Objects.equals(check.getCaptcha(), code)) {
            throw new BusinessExceptionNew(3005, "验证码未发送,或错误");
        }
        String format = check.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        long minutesBetween = getMinutesBetween(format);
        if (minutesBetween > check.getExpired()) {
            throw new BusinessExceptionNew(3006, "验证码过期,请重新发送");
        }
        return true;
    }

    public static long getMinutesBetween(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime specifiedDateTime = LocalDateTime.parse(dateString, formatter);
        LocalDateTime currentDateTime = LocalDateTime.now();
        Duration duration = Duration.between(specifiedDateTime, currentDateTime);
        return duration.toMinutes();
    }
}
