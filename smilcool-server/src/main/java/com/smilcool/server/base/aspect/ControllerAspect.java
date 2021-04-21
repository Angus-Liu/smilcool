package com.smilcool.server.base.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Controller 方法请求及响应拦截切面
 */
@Slf4j
@Aspect
@Component
public class ControllerAspect {

    @Pointcut("@within(org.springframework.stereotype.Controller) || @within(org.springframework.web.bind.annotation.RestController)")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void doBefore(JoinPoint jp) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        String sessionId = request.getSession().getId();
        log.info("[{}] Request <---", sessionId);
        log.info("[{}] IP: {}", sessionId, request.getRemoteAddr());
        log.info("[{}] URI: {} {}", sessionId, request.getMethod(), request.getRequestURI());
        log.info("[{}] Method: {}", sessionId, jp.getSignature());
        log.info("[{}] Args: {}", sessionId, Arrays.toString(jp.getArgs()));
    }

    @AfterReturning(returning = "response", pointcut = "pointcut()")
    public void doAfterReturning(Object response) {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        String sessionId = attributes.getSessionId();
        log.info("[{}] Response --->", sessionId);
        log.info("[{}] Result: {}", sessionId, response);
    }

    @AfterThrowing(throwing = "exception", pointcut = "pointcut()")
    public void doAfterThrowing(Exception exception) {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        String sessionId = attributes.getSessionId();
        log.info("[{}] Response --->", sessionId);
        log.error("[{}] Exception: {}", sessionId, exception.getMessage());
    }
}
