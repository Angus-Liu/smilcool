package com.smilcool.server.base.handler;

import com.smilcool.server.common.dto.Result;
import com.smilcool.server.common.exception.SmilcoolException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 *
 * @author Angus
 * @date 2019/3/20
 */
@Slf4j
@RestController
@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleException(MethodArgumentNotValidException e) {
        log.info("数据校验异常");
        StringBuilder sb = new StringBuilder();
        // 获取所有的数据校验错误信息
        e.getBindingResult().getFieldErrors().forEach(fieldError ->
                sb.append(fieldError.getDefaultMessage()).append("；"));
        return Result.error(sb.toString());
    }

    /**
     * 处理业务异常
     *
     * @param e 业务异常
     * @return
     */
    @ExceptionHandler(SmilcoolException.class)
    public Result handleException(SmilcoolException e) {
        log.info("业务异常");
        return Result.error(e.getMessage());
    }

    /**
     * 处理认证异常
     *
     * @param e 认证异常
     * @return
     */
    @ExceptionHandler(AuthenticationException.class)
    public Result handleException(AuthenticationException e) {
        log.info("认证异常");
        return Result.error(401, e.getMessage());
    }

    /**
     * 处理授权异常
     *
     * @param e 授权异常
     * @return
     */
    @ExceptionHandler(AuthorizationException.class)
    public Result handleException(AuthorizationException e) {
        log.info("授权异常");
        return Result.error(403, e.getMessage());
    }

    /**
     * 处理其他系统异常
     *
     * @param e 系统异常
     * @return
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result handleException(HttpServletRequest request, Exception e) {
        log.error("系统异常 => ", e);
        return Result.error("Oops！服务器无法处理你的请求 :(");
    }
}
