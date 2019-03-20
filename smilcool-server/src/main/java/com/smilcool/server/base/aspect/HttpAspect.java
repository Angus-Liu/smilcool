package com.smilcool.server.base.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author Angus
 * @date 2019/3/20
 */
@Slf4j
@Aspect
@Component
public class HttpAspect {

    @Pointcut(value = "execution(public * com.smilcool.server.core.controller.*.*(..))")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        log.info("Request <---");
        log.info("URL: {}", request.getRequestURL());
        log.info("Method: {}", request.getMethod());
        log.info("IP: {}", request.getRemoteAddr());
        log.info("SessionID: {}", request.getSession().getId());
        log.info("Class-Method: {}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        // 默认只打印第一个参数，借助 Arrays.toString() 可打印多个参数
        log.info("Args: {}", Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "response", pointcut = "pointcut()")
    public void doAfterReturning(Object response) {
        log.info("Response --->");
        log.info("Result: {}", response);
    }

    @AfterThrowing(throwing = "exception", pointcut = "pointcut()")
    public void doAfterThrowing(Exception exception) {
        log.info("Response --->");
        log.info("Exception-Message: {}", exception.getMessage());
    }
}
