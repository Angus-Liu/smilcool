package com.smilcool.server.base.handler;

import com.smilcool.server.common.dto.Result;
import com.smilcool.server.common.exception.SmilcoolException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 *
 * @author Angus
 * @date 2019/3/20
 */
@Slf4j
@ControllerAdvice
public class ExceptionsHandler {
    /**
     * 处理业务异常
     *
     * @param e 业务异常
     * @return
     */
    @ResponseBody
    @ExceptionHandler(SmilcoolException.class)
    public Result handleAutumnException(SmilcoolException e) {
        return Result.error(e.getMessage());
    }

    /**
     * 处理其他系统异常
     *
     * @param e 系统异常
     * @return
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error("系统异常 => ", e);
        return Result.error("Oops！服务器无法处理你的请求 :(");
    }
}
