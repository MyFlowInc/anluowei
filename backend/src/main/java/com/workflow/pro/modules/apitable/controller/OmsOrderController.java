package com.workflow.pro.modules.apitable.controller;


import com.workflow.pro.common.aop.annotation.AnonymousAccess;
import com.workflow.pro.common.constant.ControllerConstant;
import com.workflow.pro.common.context.BeanContext;
import com.workflow.pro.common.context.UserContext;
import com.workflow.pro.modules.apitable.domain.ToolAlipayConfig;
import com.workflow.pro.modules.apitable.exception.BusinessExceptionNew;
import com.workflow.pro.modules.apitable.param.OrderPayParam;
import com.workflow.pro.modules.apitable.param.OrderVo;
import com.workflow.pro.modules.apitable.service.IAliPayService;
import com.workflow.pro.modules.apitable.utils.AliPayStatusEnum;
import com.workflow.pro.modules.apitable.utils.AlipayUtils;
import io.swagger.annotations.Api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.workflow.pro.modules.apitable.service.IOmsOrderService;

import com.workflow.pro.modules.apitable.param.OmsOrderRequest;
import com.workflow.pro.modules.apitable.domain.OmsOrder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import io.swagger.annotations.ApiOperation;
import com.workflow.pro.common.web.base.BaseController;
import com.workflow.pro.common.aop.annotation.Log;
import com.workflow.pro.common.web.domain.Result;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 订单Controller
 *
 * @author some
 * @date 2023-06-09
 */
@Api(tags = { "订单" })
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "omsOrder")
public class OmsOrderController extends BaseController {

    @Resource
    private AlipayUtils alipayUtils;


    @Resource
    private IOmsOrderService omsOrderService;


    @Resource
    private IAliPayService alipayService;

    @ApiOperation("下单")
    @PostMapping(value = "/generateOrder")
    public Result<String> generateOrder(@RequestBody OrderVo trade) throws Exception {
        trade.setOutTradeNo(alipayUtils.getOrderCode());
        OmsOrder omsOrder = omsOrderService.generateOrder(trade);
        return success(omsOrder);
    }

    @Log("支付宝PC网页支付")
    @ApiOperation("PC网页支付")
    @PostMapping(value = "/toPayAsPC")
    public Result<String> toPayAsPc(@RequestBody OrderPayParam tradeNo) throws Exception {
        ToolAlipayConfig aliPay = alipayService.find();
        try {
            String payUrl = omsOrderService.toPayAsPc(aliPay, tradeNo);
            return Result.success(200,"success",payUrl);
        }
        catch (BusinessExceptionNew e) {
            return Result.failure(e.getCode(), e.getMessage());
        }

    }



    @ApiOperation("用户支付成功的回调")
    @RequestMapping(value = "/paySuccess", method = RequestMethod.POST)
    @ResponseBody
    public Result paySuccess(@RequestBody OrderPayParam tradeNo) {
        Integer count = omsOrderService.paySuccess(tradeNo.getOrderSn(),1);
        return Result.success(count, "支付成功");
    }

    @ApiIgnore
    @RequestMapping("/notify")
    @AnonymousAccess
    @ApiOperation("支付异步通知(要公网访问)，接收异步通知，检查通知内容app_id、out_trade_no、total_amount是否与请求中的一致，根据trade_status进行后续业务处理")
    public ResponseEntity<Object> notify(HttpServletRequest request) {
        // logUntil.LogToSys("支付回调", "参数", request.toString());
        ToolAlipayConfig alipay = alipayService.find();
        Map<String, String[]> parameterMap = request.getParameterMap();
        //内容验签，防止黑客篡改参数
        if (alipayUtils.rsaCheck(request, alipay)) {
            //交易状态
            String tradeStatus = new String(request.getParameter("trade_status").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            // 商户订单号
            String outTradeNo = new String(request.getParameter("out_trade_no").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            //支付宝交易号
            String tradeNo = new String(request.getParameter("trade_no").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            //付款金额
            String totalAmount = new String(request.getParameter("total_amount").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            //验证
            if (tradeStatus.equals(AliPayStatusEnum.SUCCESS.getValue()) || tradeStatus.equals(AliPayStatusEnum.FINISHED.getValue())) {
                // 验证通过后应该根据业务需要处理订单
                //logUntil.LogToSys("支付回调", "参数Map", parameterMap.toString());
                omsOrderService.paySuccess(outTradeNo, 1);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    /**
     * 查询订单列表
     * @param request 查询参数
     */
    @GetMapping("/list")
    @Log(title = "订单")
    @ApiOperation(value = "订单")
    public Result list(OmsOrderRequest request) {
        return success(omsOrderService.list(request));
    }

    /**
     * 查询订单分页列表
     * @param request 查询参数
     */
    @GetMapping("/page")
    @Log(title = "订单列表")
    @ApiOperation(value = "订单列表")
    public Result page(OmsOrderRequest request) {
        UserContext userContext = BeanContext.getBean(UserContext.class);
        if (Objects.equals(userContext.getFromAgent(), "web")) {
            request.setCreateBy(userContext.getUserId());
        }
        return success(omsOrderService.page(request));
    }


    /**
     * 新增订单类型
     *
     * @param omsOrder omsOrder 实体
     */
    @PostMapping("save")
    @Log(title = "新增订单")
    @ApiOperation(value = "订单")
    public Result save(@RequestBody OmsOrder omsOrder) {
        return auto(omsOrderService.save(omsOrder));
    }

    /**
     * 修改订单
     * @param omsOrder omsOrder 实体
     */
    @PutMapping("edit")
    @Log(title = "修改订单")
    @ApiOperation(value = "修改订单")
    public Result edit(@RequestBody OmsOrder omsOrder) {
        return auto(omsOrderService.updateById(omsOrder));
    }

    /**
     * 删除订单类型
     *
     * @param id omsOrder编号
     */
    @DeleteMapping("remove")
    @Log(title = "删除订单")
    @ApiOperation(value = "删除订单")
    public Result remove(@RequestParam String id) {
        return auto(omsOrderService.removeById(id));
    }

    /**
     * 删除订单类型
     *
     * @param ids omsOrder 实体
     */
    @DeleteMapping("removeBatch")
    @Log(title = "批量删除")
    @ApiOperation(value = "批量删除")
    public Result removeBatch(@RequestParam List<String> ids) {
        return auto(omsOrderService.removeByIds(ids));
    }
}
