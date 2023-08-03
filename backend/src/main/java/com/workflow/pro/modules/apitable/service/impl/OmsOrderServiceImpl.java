package com.workflow.pro.modules.apitable.service.impl;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workflow.pro.common.context.UserContext;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;


import com.workflow.pro.common.web.exception.base.BusinessException;
import com.workflow.pro.modules.apitable.domain.ApitableDatasheetRecord;
import com.workflow.pro.modules.apitable.domain.OmsOrderItem;
import com.workflow.pro.modules.apitable.domain.SysUserGrade;
import com.workflow.pro.modules.apitable.domain.ToolAlipayConfig;
import com.workflow.pro.modules.apitable.domain.vo.TradeVo;
import com.workflow.pro.modules.apitable.exception.BusinessExceptionNew;
import com.workflow.pro.modules.apitable.mapper.OmsOrderItemMapper;
import com.workflow.pro.modules.apitable.mapper.OmsOrderMapper;
import com.workflow.pro.modules.apitable.domain.OmsOrder;
import com.workflow.pro.modules.apitable.mapper.SysUserGradeMapper;
import com.workflow.pro.modules.apitable.param.OmsOrderRequest;
import com.workflow.pro.modules.apitable.param.OrderPayParam;
import com.workflow.pro.modules.apitable.param.OrderVo;
import com.workflow.pro.modules.apitable.service.IOmsOrderService;
import com.workflow.pro.modules.sys.domain.SysUser;
import com.workflow.pro.modules.sys.service.SysUserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 * 订单ServiceImpl业务层处理
 *
 * @author some
 * @date 2023-06-09
 */
@Service
public class OmsOrderServiceImpl extends ServiceImpl<OmsOrderMapper, OmsOrder> implements IOmsOrderService {

    @Resource
    OmsOrderMapper omsOrderMapper;

    @Resource
    SysUserGradeMapper sysUserGradeMapper;

    @Autowired
    private OmsOrderMapper orderMapper;

    @Autowired
    private OmsOrderItemServiceImpl omsOrderItemService;

    @Autowired
    private SysUserService userService;

    /**
     * 用 户 服 务
     * */
    @Resource
    private UserContext userContext;

    /**
     * 查询订单
     *
     * @param id 订单ID
     * @return 订单
     */
    @Override
    public OmsOrder selectOmsOrderById(String id) {
        return omsOrderMapper.selectOmsOrderById(id);
    }

    /**
     * 查询订单列表
     * @return 订单
     */
    @Override
    public List<OmsOrder> list(OmsOrderRequest request) {
        return omsOrderMapper.list(request);
    }

    /**
     * 查询订单
     * @return 订单 分页集合
     * */
    @Override
    public PageResponse<OmsOrder> page(OmsOrderRequest request) {
        return Pageable.of(request, (() -> omsOrderMapper.selectOmsOrderList(request)));
    }

    /**
     * 新增订单
     *
     * @param omsOrder 订单
     * @return 结果
     */

    @Override
    public int insertOmsOrder(OmsOrder omsOrder) {
        return omsOrderMapper.insertOmsOrder(omsOrder);
    }

    /**
     * 修改订单
     *
     * @param omsOrder 订单
     * @return 结果
     */
    @Override
    public int updateOmsOrder(OmsOrder omsOrder) {
        return omsOrderMapper.updateOmsOrder(omsOrder);
    }

    /**
     * 删除订单对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOmsOrderByIds(String[] ids) {
        return omsOrderMapper.deleteOmsOrderByIds(ids);
    }

    /**
     * 删除订单信息
     *
     * @param id 订单ID
     * @return 结果
     */
    @Override
    public int deleteOmsOrderById(String id) {
        return omsOrderMapper.deleteOmsOrderById(id);
    }

    @Override
    public String toPayAsPc(ToolAlipayConfig alipay, OrderPayParam orderPayParam) throws Exception {
        //获取需要购买的 会员等级
        OmsOrder order_sn = getOne(new QueryWrapper<OmsOrder>().eq("order_sn", orderPayParam.getOrderSn()));
        if (order_sn == null || order_sn.getId() == null) {
            throw new BusinessExceptionNew(3013, "订单不存在");
        }
        if (order_sn.getStatus() != 0) {
            throw new BusinessExceptionNew(3014, "订单已支付无需支付");
        }
        if (alipay.getConfigId() == null) {
            throw new Exception("请先添加相应配置，再操作");
        }
        //价格  描述  主题 从此处获取
        TradeVo trade = new TradeVo();
        trade.setBody("会员购买");
        trade.setSubject("购买");
        trade.setTotalAmount(order_sn.getTotalAmount().toString());
        trade.setOutTradeNo(orderPayParam.getOrderSn());
        AlipayClient alipayClient = new DefaultAlipayClient(alipay.getGatewayUrl(), alipay.getAppId(), alipay.getPrivateKey(), alipay.getFormat(), alipay.getCharset(), alipay.getPublicKey(), alipay.getSignType());
        // 创建API对应的request(电脑网页版)
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        // 订单完成后返回的页面和异步通知地址
        request.setReturnUrl(alipay.getReturnUrl());
        request.setNotifyUrl(alipay.getNotifyUrl());
        // 填充订单参数
        request.setBizContent("{" +
                "    \"out_trade_no\":\"" + trade.getOutTradeNo() + "\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":" + trade.getTotalAmount() + "," +
                "    \"subject\":\"" + trade.getSubject() + "\"," +
                "    \"body\":\"" + trade.getBody() + "\"," +
                "    \"extend_params\":{" +
                "    \"sys_service_provider_id\":\"" + alipay.getSysServiceProviderId() + "\"" +
                "    }" +
                "  }");//填充业务参数
        // 调用SDK生成表单, 通过GET方式，口可以获取url
        return alipayClient.pageExecute(request, "GET").getBody();
    }

    @Override
    public OmsOrder generateOrder(OrderVo orderVo) throws Exception {
        //获取需要购买的 会员等级
        SysUserGrade sysUserGrade = sysUserGradeMapper.selectSysUserGradeById(orderVo.getGradeId());
        //价格  描述  主题 从此处获取
        OmsOrder trade = new OmsOrder();
        trade.setTotalAmount(sysUserGrade.getPrice());
        //支付方式：0->未支付；1->支付宝；2->微信
        trade.setPayType(0);
        //订单来源：0->PC订单；1->app订单
        trade.setSourceType(1);
        //订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单
        trade.setStatus(0);
        //0->未确认；1->已确认
        trade.setConfirmStatus(0);
        trade.setDeleteStatus(0);
        trade.setOrderSn(orderVo.getOutTradeNo());
        trade.setCreateBy(userContext.getUserId());
        //订单表中插入购买的商品
        OmsOrderItem item = new OmsOrderItem();
        item.setOrderSn(orderVo.getOutTradeNo());
        item.setProductId(orderVo.getGradeId());//会员id
        item.setProductPic(sysUserGrade.getImg());
        item.setProductName(sysUserGrade.getName());
        item.setProductPrice(sysUserGrade.getPrice());
        item.setCreateBy(userContext.getUserId());

        omsOrderItemService.save(item);

        save(trade);
        return trade;
    }

    @Override
    public Integer paySuccess(String orderSn, Integer payType) {

        OmsOrder order_sn = getOne(new QueryWrapper<OmsOrder>().eq("order_sn", orderSn));
        if (order_sn == null || order_sn.getId() == null) {
            throw new BusinessExceptionNew(3013, "订单不存在");
        }
        //修改订单支付状态
        order_sn.setOrderSn(orderSn);
        //订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单
        order_sn.setStatus(3);
        order_sn.setPaymentTime(new Date());
        order_sn.setPayType(payType);
        //恢复所有下单商品的锁定库存，扣减真实库存
        OmsOrderItem item = omsOrderItemService.getOne(new QueryWrapper<OmsOrderItem>().eq("order_sn", orderSn));
        SysUserGrade sysUserGrade = sysUserGradeMapper.selectSysUserGradeById(item.getProductId());
        //会员日期
        //订单获取创建人当前自己
        String createBy = order_sn.getCreateBy();
        if (Objects.equals(createBy, "") || createBy == null) {
            throw new BusinessExceptionNew(3014, "创建者不存在");
        }
        SysUser user = new SysUser();
        user.setId(createBy);
        user.setGradeId(sysUserGrade.getId());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(new Date());
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        user.setStartTime(LocalDateTime.parse(format, df));
        //如果当前用户想继续续费 择获取用户信息然后累加 todo

        LocalDateTime parse = LocalDateTime.parse(getNdayAfterTime(sysUserGrade.getValidDay()), df);
        user.setEndTime(parse);//截至日期

        updateById(order_sn);
        userService.updateById(user);
        return 0;
    }

    // 测试
    // 获取 n 天后时间
    public static String getNdayAfterTime(int n) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, n);
        return sdf.format(calendar.getTime());
    }
}
