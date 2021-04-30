package com.smilcool.server.base.handler;

import com.smilcool.server.common.exception.SmilcoolException;
import com.smilcool.server.core.pojo.dto.Result;
import com.smilcool.server.core.pojo.dto.Result.Empty;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 1.RestControllerAdvice 结合 ExceptionHandler 实现全局异常处理
 * 2.实现 ErrorController 捕获 ExceptionHandler 未能捕获的异常
 * - 不破坏原 SpringBoot WebMVC 配置
 * - 可解决 Vue Router History 模式刷新问题 https://router.vuejs.org/zh/guide/essentials/history-mode.html
 */
@Slf4j
@RestController
@RestControllerAdvice
public class GlobalExceptionHandler implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @RequestMapping(ERROR_PATH)
    public Result<Empty> handleException(HttpServletRequest request) {
        String method = request.getMethod();
        Integer status = (Integer) request.getAttribute(WebUtils.ERROR_STATUS_CODE_ATTRIBUTE);
        String path = (String) request.getAttribute(WebUtils.ERROR_REQUEST_URI_ATTRIBUTE);
        String msg = (String) request.getAttribute(WebUtils.ERROR_MESSAGE_ATTRIBUTE);
        Exception exception = (Exception) request.getAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE);
        log.debug("status: {}; method: {}; path: {}; message: {}; exception: {}", status, method, path, msg, exception);
        if (StringUtils.isEmpty(msg)) {
            switch (status) {
                case HttpServletResponse.SC_UNAUTHORIZED:
                    msg = "身份验证失败，请重新登录";
                    break;
                case HttpServletResponse.SC_FORBIDDEN:
                    msg = "用户无相关权限，禁止访问";
                    break;
                case HttpServletResponse.SC_NOT_FOUND:
                    msg = path + " Not Found";
                    break;
                case HttpServletResponse.SC_METHOD_NOT_ALLOWED:
                    msg = method + " Not Allowed";
                    break;
                default:
                    msg = "OOPS！服务器无法处理你的请求 :(";
                    break;
            }
        }
        return Result.error(status, msg);
    }

    /**
     * 处理数据校验异常
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Empty> handleException(MethodArgumentNotValidException e) {
        log.error("400 BAD REQUEST", e);
        StringBuilder sb = new StringBuilder();
        // 获取所有的数据校验错误信息
        e.getBindingResult().getFieldErrors().forEach(
                fieldError -> sb.append(fieldError.getDefaultMessage()).append("；")
        );
        return Result.error(sb.toString());
    }

    /**
     * 处理业务异常
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SmilcoolException.class)
    public Result<Empty> handleException(SmilcoolException e) {
        log.error("400 BAD REQUEST", e);
        return Result.error(e.getMessage());
    }

    /**
     * 处理身份认证异常（认证失败、未认证）
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = {AuthenticationException.class, UnauthenticatedException.class})
    public Result<Empty> handleAuthenticationException(ShiroException e) {
        log.info("401 UNAUTHORIZED", e);
        return Result.error(401, e.getMessage());
    }

    /**
     * 处理权限异常
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AuthorizationException.class)
    public Result<Empty> handleException(ShiroException e) {
        log.error("403 FORBIDDEN", e);
        return Result.error(403, e.getMessage());
    }

    /**
     * 处理请求异常，请求的方法不支持
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result<Empty> handleException(HttpRequestMethodNotSupportedException e) {
        log.error("405 METHOD NOT ALLOWED", e);
        return Result.error(405, e.getMessage());
    }

    /**
     * 处理系统异常
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Result<Empty> handleException(Exception e) {
        log.error("500 INTERNAL SERVER ERROR", e);
        return Result.error(500, "OOPS！服务器无法处理你的请求 :(");
    }
}
