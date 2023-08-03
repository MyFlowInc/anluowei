package com.workflow.pro.modules.qywx.handler;

import com.workflow.pro.modules.qywx.entity.JsonData;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理
 */
@Slf4j
@RestControllerAdvice
public class ExceptionAdviceHandler {

    @ExceptionHandler(value = WxErrorException.class)
    @ResponseStatus(HttpStatus.OK)
    public JsonData handleWxErrorException(WxErrorException e) {
        log.error("统一异常处理打印日志", e);
        return JsonData.buildError(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
    }
}
